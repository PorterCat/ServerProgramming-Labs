using AppointmentService.Core.Abstractions;

namespace AppontmentService.Api.Services;

public class SlotOptimizationService : BackgroundService
{
    private readonly IServiceProvider _services;
    private readonly ILogger<SlotOptimizationService> _logger;

    public SlotOptimizationService(
        IServiceProvider services,
        ILogger<SlotOptimizationService> logger)
    {
        _services = services;
        _logger = logger;
    }

    protected override Task ExecuteAsync(CancellationToken stoppingToken)
    {
        throw new NotImplementedException();
    }
}