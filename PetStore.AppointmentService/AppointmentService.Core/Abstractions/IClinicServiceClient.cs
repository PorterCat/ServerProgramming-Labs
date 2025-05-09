using System.Collections;

namespace AppointmentService.Core.Abstractions;

public interface IClinicServiceClient
{
    Task<List<DoctorSchedule>> GetDoctorSchedulesAsync();
    Task<bool> BookSlotAsync(string slotId);
    Task<bool> CancelSlotAsync(string slotId);
}