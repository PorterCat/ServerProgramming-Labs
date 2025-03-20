package portercat.petstoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import portercat.petstoreapi.models.Status;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetRequestDTO
{
    private String name;
    private Long categoryId;
    private List<Long> tagIds;
    private Status status;
}