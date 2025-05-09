using AppointmentService.Core.Models;

namespace AppointmentService.Core.Abstractions;

public interface IAppointmentRepository
{
    Task AddAsync(Appointment appointment);
    Task SaveChangesAsync();
}