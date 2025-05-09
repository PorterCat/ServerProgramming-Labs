using AppointmentService.Core.Abstractions;
using AppointmentService.Core.Models;

namespace AppointmentService.DataAccess.Repositoires;

public class AppointmentRepository : IAppointmentRepository
{
    private readonly AppointmentDbContext _context;

    public AppointmentRepository(AppointmentDbContext context) => _context = context;

    public async Task AddAsync(Appointment appointment) 
        => await _context.Appointments.AddAsync(appointment);

    public async Task SaveChangesAsync() 
        => await _context.SaveChangesAsync();
}