namespace AppointmentService.Core.Models;

public class Appointment
{
    public Guid Id { get; set; }
    public string UserId { get; set; }
    public string SlotId { get; set; } 
    public string Notes { get; set; }
    public DateTime CreatedAt { get; set; } = DateTime.UtcNow;
}