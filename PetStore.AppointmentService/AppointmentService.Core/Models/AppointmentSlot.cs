namespace AppointmentService.Core.Models;

public class AppointmentSlot
{
    public string Id { get; set; } = Guid.NewGuid().ToString(); 
    public string DoctorId { get; set; }                       
    public DateTime Start { get; set; }                         
    public DateTime End { get; set; } 
    public bool IsAvailable { get; set; } = true;
}