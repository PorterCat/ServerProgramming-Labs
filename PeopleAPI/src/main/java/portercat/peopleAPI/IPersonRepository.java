package portercat.peopleAPI;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IPersonRepository extends CrudRepository<Person, Long>
{
}
