package portercat.peopleAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
public class PersonController
{
    PersonService _personService;

    public PersonController(PersonService personService)
    {
        _personService = personService;
    }

    @GetMapping
    public ResponseEntity getAllPeople()
    {
        var people = _personService.getAllPeople();
        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity createPerson(@RequestBody Person person)
    {
        return ResponseEntity.ok(_personService.add(person));
        //return ResponseEntity.ok("Person successfully created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonById(@PathVariable long id)
    {
        _personService.delete(id);
        return ResponseEntity.ok("Person [" + id + "] successfully deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePersonById(@PathVariable long id, @RequestBody Person person)
    {
        return ResponseEntity.ok(_personService.update(id, person));
        //return ResponseEntity.ok("Person [" + id + "] successfully updated");
    }
}
