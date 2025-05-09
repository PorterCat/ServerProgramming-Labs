using AppointmentService.Core.Abstractions;
using AppointmentService.Core.Contracts;
using Microsoft.AspNetCore.Mvc;

namespace AppontmentService.Api.Controllers;

[ApiController]
[Route("[controller]")]
public class AppointmentController : ControllerBase
{
    private readonly IAppointmentService _service;

    public AppointmentController(IAppointmentService service) => _service = service;

    [HttpPost]
    public async Task<ActionResult<AppointmentResponse>> Create(
        [FromBody] CreateAppointmentRequest request)
    {
        try
        {
            var response = await _service.CreateAppointmentAsync(request);
            return CreatedAtAction(nameof(Create), response);
        }
        catch (ArgumentException ex)
        {
            return BadRequest(ex.Message);
        }
    }
}