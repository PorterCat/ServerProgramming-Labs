package portercat.peopleAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService
{
    @Autowired
    private IPersonRepository _personRepository;

    public PersonService(IPersonRepository personRepository)
    {
        _personRepository = personRepository;
    }

    public List<Person> getAllPeople()
    {
        Iterable<Person> peopleEntity = _personRepository.findAll();
        List<Person> people = new ArrayList<>();
        for(Person person : peopleEntity)
        {
            people.add(new Person(person.getId(),
                    person.getName(),
                    person.getAge()));
        }

        return people;
    }

    public Person add(Person person)
    {
        return _personRepository.save(person);
    }

    public void delete(long id)
    {
        _personRepository.deleteById(id);
    }

    public Person update(long id, Person person)
    {
        Person existingPerson = _personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        existingPerson.setAge(person.getAge());
        existingPerson.setName(person.getName());

        return _personRepository.save(existingPerson);
    }
}
