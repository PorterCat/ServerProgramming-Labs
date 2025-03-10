package portercat.petstore;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import portercat.petstore.models.Pet;
import portercat.petstore.repositories.PetRepository;
import portercat.petstore.services.PetService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PetServiceTest
{
    @Test
    void testGetById()
    {
        PetRepository petRepository = Mockito.mock(PetRepository.class);
        PetService petService = new PetService(petRepository);

        Pet pet = new Pet();
        pet.setId(0);
        when(petRepository.findById(0)).thenReturn(pet);

        assertEquals(0, petService.getById(0).getId());
    }
}
