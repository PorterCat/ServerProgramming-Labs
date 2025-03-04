package portercat.petstore.repositories;

import org.springframework.stereotype.Repository;
import portercat.petstore.models.Pet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PetRepository
{
    private final Map<Long, Pet> _petStore = new HashMap<>();

    public Pet save(Pet pet)
    {
        _petStore.put(pet.getId(), pet);
        return pet;
    }

    public Pet findById(long id)
    {
        return _petStore.get(id);
    }

    public boolean containsKey(long id)
    {
        return _petStore.containsKey(id);
    }

    public void removeById(long id)
    {
        _petStore.remove(id);
    }
}
