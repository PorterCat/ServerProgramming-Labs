using System.Runtime.InteropServices;
using System.Text.Json;
using AppointmentService.Core.Abstractions;
using AppointmentService.Core.Contracts;
using AppointmentService.Core.Models;
using Microsoft.Extensions.Caching.Distributed;

namespace AppontmentService.Api.Services;

public class AppointmentService : IAppointmentService
{
    private readonly IAppointmentRepository _repository;
    private readonly IDistributedCache _cache;
    private readonly IClinicServiceClient _clinicClient;
    
    public AppointmentService(
        IAppointmentRepository repository,
        IDistributedCache cache,
        IClinicServiceClient clinicClient)
    {
        _repository = repository;
        _cache = cache;
        _clinicClient = clinicClient;
    }
    
    public async Task<List<AppointmentSlot>> GetAvailableSlots(string doctorId)
    {
        var cacheKey = $"slots:{doctorId}";
        var cachedSlots = await _cache.GetStringAsync(cacheKey);

        if (!string.IsNullOrEmpty(cachedSlots))
        {
            return JsonSerializer.Deserialize<List<AppointmentSlot>>(cachedSlots);
        }
        else
        {
            throw new ExternalException();
        }

        var slots = await _clinicClient.GetSlotsAsync(doctorId);
        await _cache.SetStringAsync(cacheKey, JsonSerializer.Serialize(slots), new DistributedCacheEntryOptions
        {
            AbsoluteExpirationRelativeToNow = TimeSpan.FromMinutes(5)
        });

        return slots;
    }
}