package portercat.petshop.repository;

import org.springframework.stereotype.Repository;
import portercat.petshop.model.Pet;

import java.util.*;

@Repository
public class PetRepository
{
    private final Map<Long, Pet> petStore = new HashMap<>();

    public List<Pet> findAll()
    {
        return new ArrayList<>(petStore.values());
    }

    public Optional<Pet> findById(Long id)
    {
        return Optional.ofNullable(petStore.get(id));
    }

    public Pet save(Pet pet)
    {
        petStore.put(pet.getId(), pet);
        return pet;
    }

    public void deleteById(Long id)
    {
        petStore.remove(id);
    }
}
