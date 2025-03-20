package portercat.petstoreapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import portercat.petstoreapi.dto.PetRequestDTO;
import portercat.petstoreapi.models.Category;
import portercat.petstoreapi.models.Pet;
import portercat.petstoreapi.models.Tag;
import portercat.petstoreapi.repositories.ICategoryRepository;
import portercat.petstoreapi.repositories.IPetRepository;
import portercat.petstoreapi.repositories.ITagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PetService
{
    private final IPetRepository _petRepository;
    private final CategoryService _categoryService;
    private final TagService _tagService;
    public List<Pet> getAllPets()
    {
        List<Pet> pets = new ArrayList<>();

        for(Pet pet : _petRepository.findAll())
            pets.add(new Pet(pet.getId(), pet.getName(),
                    pet.getCategory(), pet.getTags(), pet.getStatus())
            );

        return pets;
    }

    public Pet createPet(PetRequestDTO dto)
    {
        Optional<Category> category = _categoryService.getCategoryById(dto.getCategoryId());
        List<Tag> tags = _tagService.getTagsByIds(dto.getTagIds());

        return _petRepository.save(Pet.builder()
                .name(dto.getName())
                .category(category.get())
                .tags(tags)
                .status(dto.getStatus())
                .build());
    }

    public Optional<Pet> getById(long id)
    {
        return _petRepository.findById(id);
    }

    public void deletePetById(long id)
    {
        if (!_petRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found with ID: " + id);

        _petRepository.deleteById(id);
    }

    public Pet updatePet(long id, PetRequestDTO dto)
    {
        Pet existingPet = _petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Pet not found with ID: " + id));

        existingPet.setName(dto.getName());
        existingPet.setCategory(_categoryService.getCategoryById(dto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found")));
        existingPet.setTags(_tagService.getTagsByIds(dto.getTagIds()));
        existingPet.setStatus(dto.getStatus());

        return _petRepository.save(existingPet);
    }
}
