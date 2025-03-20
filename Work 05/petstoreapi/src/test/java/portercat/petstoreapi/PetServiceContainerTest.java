package portercat.petstoreapi;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import portercat.petstoreapi.dto.PetRequestDTO;
import portercat.petstoreapi.models.Category;
import portercat.petstoreapi.models.Pet;
import portercat.petstoreapi.models.Status;
import portercat.petstoreapi.models.Tag;
import portercat.petstoreapi.services.CategoryService;
import portercat.petstoreapi.services.PetService;
import portercat.petstoreapi.services.TagService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PetServiceContainerTest {

    private static final Logger logger = LoggerFactory.getLogger(PetServiceContainerTest.class);

    @SuppressWarnings("resource")
    public static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:16")
                    .withDatabaseName("PetstoreDb")
                    .withUsername("postgres")
                    .withPassword("4275");

    @Autowired
    private PetService petService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @AfterEach
    void removeData()
    {
        try
        {
            jdbcTemplate.execute("TRUNCATE TABLE pets CASCADE");
            jdbcTemplate.execute("TRUNCATE TABLE tags CASCADE");
            jdbcTemplate.execute("TRUNCATE TABLE categories CASCADE");
            jdbcTemplate.execute("TRUNCATE TABLE pet_tags CASCADE");
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error truncating tables", e);
        }
    }

    @Test
    @Transactional
    public void addPet()
    {
        Tag tag = new Tag();
        tag.setName("Orange");
        Tag createdTag = tagService.createTag(tag);

        Category category = new Category();
        category.setName("Cat");
        Category createdCategory = categoryService.createCategory(category);

        PetRequestDTO pet = new PetRequestDTO();
        pet.setName("Marsel");
        pet.setStatus(Status.available);
        pet.setTagIds(List.of(createdTag.getId()));
        pet.setCategoryId(createdCategory.getId());

        Pet createdPet = petService.createPet(pet);
        var testPet = petService.getById(createdPet.getId()).get();

        assertEquals(pet.getName(), testPet.getName());
        assertEquals(pet.getStatus(), testPet.getStatus());
        assertEquals(1, testPet.getTags().size());
    }
}