using System.Collections;
using AppointmentService.Core.Abstractions;

namespace AppontmentService.Api.Clients;

public class ClinicServiceClient : IClinicServiceClient
{
    private readonly HttpClient _httpClient;

    public ClinicServiceClient(HttpClient httpClient) 
        => _httpClient = httpClient;

    public async Task<bool> ValidateSlotAsync(string slotId)
    {
        var response = await _httpClient.GetAsync($"/api/slots/validate/{slotId}");
        return response.IsSuccessStatusCode;
    }

    public Task<IEnumerable> GetDoctorsAsync()
    {
        
    }
}