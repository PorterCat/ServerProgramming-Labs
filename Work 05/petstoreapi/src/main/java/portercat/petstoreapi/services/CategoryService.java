package portercat.petstoreapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portercat.petstoreapi.models.Category;
import portercat.petstoreapi.repositories.ICategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService
{
    private final ICategoryRepository _categoryRepository;

    public List<Category> getAllCategories()
    {
        List<Category> categories = new ArrayList<>();

        for(Category cat : _categoryRepository.findAll())
            categories.add(new Category(cat.getId(),
                    cat.getName())
            );

        return categories;
    }

    public Optional<Category> getCategoryById(long id)
    {
        return _categoryRepository.findById(id);
    }

    public Category createCategory(Category tag)
    {
        return _categoryRepository.save(tag);
    }
}
