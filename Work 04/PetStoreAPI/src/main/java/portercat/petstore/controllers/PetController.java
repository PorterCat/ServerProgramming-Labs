package portercat.petstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import portercat.petstore.services.PetService;

import portercat.petstore.models.*;

@RestController
@RequestMapping("/api/v3/pet")
public class PetController
{
    private final PetService _petService;

    public PetController(PetService petService)
    {
        _petService = petService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody Pet pet)
    {
        try
        {
            _petService.add(pet);
            ApiResponse response = new ApiResponse(200, "success", "Pet added successfully");
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            ApiResponse response = new ApiResponse(400, "error", "Failed to add pet");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{petId}")
    public ResponseEntity<ApiResponse> get(@PathVariable long petId)
    {
        try
        {
            return _petService.getById(petId) != null ?
                    ResponseEntity.ok(
                            new ApiResponse(200, "success", "Pet found")) :
                    ResponseEntity.status(404).body(
                            new ApiResponse(404, "error", "Pet not found"));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(
                    new ApiResponse(500, "error", "Internal server error"));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updatePet(@RequestBody Pet pet)
    {
        try
        {
            _petService.update(pet);
            ApiResponse response = new ApiResponse(200, "success", "Pet updated successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            ApiResponse response = new ApiResponse(400, "error", "Failed to update pet");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<ApiResponse> deletePet(@PathVariable long petId)
    {
        try
        {
            _petService.delete(petId);
            ApiResponse response = new ApiResponse(200, "success", "Pet deleted successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            ApiResponse response = new ApiResponse(404, "error", "Pet not found");
            return ResponseEntity.status(404).body(response);
        }
    }
}
