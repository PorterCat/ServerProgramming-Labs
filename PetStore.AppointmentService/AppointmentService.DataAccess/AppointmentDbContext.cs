using AppointmentService.Core.Models;
using Microsoft.EntityFrameworkCore;

namespace AppointmentService.DataAccess;

public class AppointmentDbContext : DbContext
{
    public AppointmentDbContext(DbContextOptions<AppointmentDbContext> options) : base(options) { }
    
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.ApplyConfigurationsFromAssembly(typeof(AppointmentDbContext).Assembly);
    }
    
    public DbSet<Appointment> Appointments { get; set; }
}