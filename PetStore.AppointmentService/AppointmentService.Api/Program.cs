using AppointmentService.Core.Abstractions;
using AppointmentService.DataAccess;
using AppointmentService.DataAccess.Repositoires;
using AppontmentService.Api.Clients;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    c.SwaggerDoc("v1", new OpenApiInfo { Title = "Appointment Service", Version = "v1" });
});

builder.Services.AddDbContext<AppointmentDbContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("AppointmentDbContext")));

builder.Services.AddStackExchangeRedisCache(options =>
    options.Configuration = builder.Configuration.GetConnectionString("Redis"));

builder.Services.Configure<ExternalServiceSettings>(
    builder.Configuration.GetSection("ExternalServices"));

builder.Services.AddHttpClient<IProfileServiceClient, ProfileServiceClient>((provider, client) => 
{
    var settings = provider.GetRequiredService<IOptions<ExternalServiceSettings>>().Value;
    client.BaseAddress = new Uri(settings.ProfileService);
});

builder.Services.AddHttpClient<IClinicServiceClient, ClinicServiceClient>((provider, client) => 
{
    var settings = provider.GetRequiredService<IOptions<ExternalServiceSettings>>().Value;
    client.BaseAddress = new Uri(settings.ClinicService);
});

builder.Services.AddScoped<IAppointmentRepository, AppointmentRepository>();
builder.Services.AddScoped<IAppointmentService, AppontmentService.Api.Services.AppointmentService>();


var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", 
        "Appointment Service v1"));
}

app.MapControllers();
app.Run();