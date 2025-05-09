namespace AppointmentService.Core.Abstractions;

public interface IProfileServiceClient
{
    Task<bool> ValidateUserAsync(string userId);
}
