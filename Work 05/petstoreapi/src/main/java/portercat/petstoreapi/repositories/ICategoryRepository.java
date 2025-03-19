package portercat.petstoreapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import portercat.petstoreapi.models.Category;

@Repository
public interface ICategoryRepository extends CrudRepository<Category, Long>
{
}
