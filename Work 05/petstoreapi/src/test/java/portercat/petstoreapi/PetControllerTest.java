package portercat.petstoreapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import portercat.petstoreapi.controllers.PetController;
import portercat.petstoreapi.dto.PetRequestDTO;
import portercat.petstoreapi.models.Category;
import portercat.petstoreapi.models.Pet;
import portercat.petstoreapi.models.Status;
import portercat.petstoreapi.models.Tag;
import portercat.petstoreapi.services.CategoryService;
import portercat.petstoreapi.services.PetService;
import portercat.petstoreapi.services.TagService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private PetService petService;
    @MockitoBean
    private CategoryService categoryService;
    @MockitoBean
    private TagService tagService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllPets() throws Exception
    {
        mockMvc.perform(get("/api/v3/pet"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePet_ShouldReturnCreatedPet() throws Exception
    {
        PetRequestDTO dto = new PetRequestDTO();
        dto.setName("Buddy");
        dto.setCategoryId(10L);
        dto.setTagIds(List.of(1L));
        dto.setStatus(Status.available);

        var createdPet = new Pet();
        createdPet.setId(1L);
        createdPet.setName("Buddy");
        createdPet.setCategory(new Category(10L, "Dog"));
        createdPet.setTags(List.of(new Tag(1L, "Friendly")));
        createdPet.setStatus(Status.available);

        when(petService.createPet(any(PetRequestDTO.class))).thenReturn(createdPet);

        mockMvc.perform(post("/api/v3/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Buddy"))
                .andExpect(jsonPath("$.category.id").value(10))
                .andExpect(jsonPath("$.tags[0].id").value(1))
                .andExpect(jsonPath("$.status").value("available"));

        verify(petService, times(1)).createPet(any(PetRequestDTO.class));
    }

    @Test
    public void testDeletePet_ShouldReturnNoContent() throws Exception
    {
        Long petId = 1L;
        doNothing().when(petService).deletePetById(petId);

        mockMvc.perform(delete("/api/v3/pet/{id}", petId))
                .andExpect(status().isNoContent());

        verify(petService, times(1)).deletePetById(petId);
    }

    @Test
    public void testDeletePet_NotFound() throws Exception
    {
        Long petId = 999L;
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"))
                .when(petService).deletePetById(petId);

        mockMvc.perform(delete("/api/v3/pet/{id}", petId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdatePet_ShouldReturnUpdatedPet() throws Exception
    {
        Long petId = 1L;
        PetRequestDTO dto = new PetRequestDTO();
        dto.setName("Updated Buddy");
        dto.setCategoryId(10L);
        dto.setTagIds(List.of(1L));
        dto.setStatus(Status.available);

        Pet updatedPet = new Pet();
        updatedPet.setId(petId);
        updatedPet.setName("Updated Buddy");
        updatedPet.setCategory(new Category(10L, "Dog"));
        updatedPet.setTags(List.of(new Tag(1L, "Friendly")));
        updatedPet.setStatus(Status.available);

        when(petService.updatePet(eq(petId), any(PetRequestDTO.class))).thenReturn(updatedPet);

        mockMvc.perform(put("/api/v3/pet/{id}", petId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(petId))
                .andExpect(jsonPath("$.name").value("Updated Buddy"));

        verify(petService, times(1)).updatePet(eq(petId),
                any(PetRequestDTO.class));
    }

}
