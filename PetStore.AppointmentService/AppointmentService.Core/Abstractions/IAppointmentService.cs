using AppointmentService.Core.Contracts;

namespace AppointmentService.Core.Abstractions;

public interface IAppointmentService
{
    Task<AppointmentResponse> CreateAppointmentAsync(CreateAppointmentRequest request);
}