package portercat.petstoreapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import portercat.petstoreapi.models.Tag;

@Repository
public interface ITagRepository extends CrudRepository<Tag, Long>
{

}
