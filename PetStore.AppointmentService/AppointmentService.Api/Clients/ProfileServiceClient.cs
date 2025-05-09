using AppointmentService.Core.Abstractions;

namespace AppontmentService.Api.Clients;

public class ProfileServiceClient : IProfileServiceClient
{
    private readonly HttpClient _httpClient;

    public ProfileServiceClient(HttpClient httpClient) 
        => _httpClient = httpClient;

    public async Task<bool> ValidateUserAsync(string userId)
    {
        var response = await _httpClient.GetAsync($"/api/users/validate/{userId}");
        return response.IsSuccessStatusCode;
    }
}