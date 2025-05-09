namespace AppointmentService.Core.Contracts;

public record CreateAppointmentRequest(
    string UserId, 
    string SlotId, 
    string Notes);