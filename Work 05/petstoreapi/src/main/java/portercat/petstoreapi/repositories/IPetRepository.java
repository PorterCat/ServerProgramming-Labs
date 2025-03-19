package portercat.petstoreapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import portercat.petstoreapi.models.Pet;

@Repository
public interface IPetRepository extends CrudRepository<Pet, Long>
{

}
