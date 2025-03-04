package portercat.petstore.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Pet
{
    long id;
    String name;
    Category category;
    ArrayList<Tag> tags;
    Status status;
}
