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

/*
POST /api/categories — создать категорию
GET /api/categories — получить все категории
POST /api/tags — создать тег
GET /api/tags — получить все теги
POST /api/pets — создать питомца с проверкой category и tags
GET /api/pets — получить всех питомцев
 */

@RestController
@RequestMapping("/api/v3/pet")
@RequiredArgsConstructor
public class PetController
{
    private final TagService _tagService;
    private final CategoryService _categoryService;
    private final PetService _petService;

    @GetMapping("/tags")
    public List<Tag> getAllTags() { return _tagService.getAllTags(); }

    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(_tagService.createTag(tag));
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() { return _categoryService.getAllCategories(); }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok(_categoryService.createCategory(category));
    }

    @GetMapping
    public List<Pet> getAllPets() { return _petService.getAllPets(); }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody PetRequestDTO dto)
    {
        return ResponseEntity.ok(_petService.createPet(dto));
    }
}
