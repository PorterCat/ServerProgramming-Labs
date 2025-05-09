using AppointmentService.Core.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace AppointmentService.DataAccess.Configurations;

public class AppointmentConfiguration 
{
    public void Configure(EntityTypeBuilder<Appointment> builder)
    {
        builder.ToTable("Appointments");
        builder.HasKey(a => a.Id);
        builder.Property(a => a.UserId).IsRequired().HasMaxLength(100);
        builder.Property(a => a.SlotId).IsRequired().HasMaxLength(100);
        builder.Property(a => a.Notes).HasMaxLength(500);
    }
}