package portercat.petstoreapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name", nullable = false)
    String name;
}
