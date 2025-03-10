package portercat.petstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import portercat.petstore.controllers.PetController;
import portercat.petstore.models.Pet;
import portercat.petstore.services.PetService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PetService petService;

    @Test
    void testGetById() throws Exception
    {
        Pet pet = new Pet();
        pet.setId(1);
        when(petService.getById(1)).thenReturn(pet);

        mockMvc.perform(get("/api/v3/pet/1"))
                .andExpect(status().isOk());
    }
}