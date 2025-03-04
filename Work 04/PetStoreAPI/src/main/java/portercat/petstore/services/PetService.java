package portercat.petstore.services;

import org.springframework.stereotype.Service;
import portercat.petstore.models.Pet;
import portercat.petstore.repositories.PetRepository;

import java.util.Optional;

@Service
public class PetService
{
    private final PetRepository _petRepository;

    public PetService(PetRepository petRepository)
    {
        _petRepository = petRepository;
    }

    public Pet add(Pet pet)
    {
        return _petRepository.save(pet);
    }

    public Pet getById(long id)
    {
        return _petRepository.findById(id);
    }

    public void update(Pet pet)
    {

    }

    public void delete(long petId)
    {
        if(_petRepository.containsKey(petId))
        {
            _petRepository.removeById(petId);
        }
        else
        {
            throw new IllegalArgumentException("Pet not found");
        }
    }
}
