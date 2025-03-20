package portercat.petstoreapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portercat.petstoreapi.dto.PetRequestDTO;
import portercat.petstoreapi.models.ApiResponse;
import portercat.petstoreapi.models.Category;
import portercat.petstoreapi.models.Pet;
import portercat.petstoreapi.models.Tag;
import portercat.petstoreapi.services.CategoryService;
import portercat.petstoreapi.services.PetService;
import portercat.petstoreapi.services.TagService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3/pet")
@RequiredArgsConstructor
public class PetController
{
    private final TagService _tagService;
    private final CategoryService _categoryService;
    private final PetService _petService;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags()
    {
        return ResponseEntity.ok(_tagService.getAllTags());
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(_tagService.createTag(tag));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        return ResponseEntity.ok(_categoryService.getAllCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(_categoryService.createCategory(category));
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() { return ResponseEntity.ok(_petService.getAllPets()); }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable long id)
    {
        Optional<Pet> pet = _petService.getById(id);
        return pet.isPresent() ?
                ResponseEntity.ok(pet.get()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody PetRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(_petService.createPet(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePet(@PathVariable long id)
    {
        _petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable long id, @RequestBody PetRequestDTO dto)
    {
        Pet updatedPet = _petService.updatePet(id, dto);
        return ResponseEntity.ok(updatedPet);
    }
}
