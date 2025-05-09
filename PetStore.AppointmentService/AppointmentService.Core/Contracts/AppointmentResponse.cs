namespace AppointmentService.Core.Contracts;

public record AppointmentResponse(
    Guid Id, 
    string UserId, 
    string SlotId, 
    string Notes, 
    DateTime CreatedAt);