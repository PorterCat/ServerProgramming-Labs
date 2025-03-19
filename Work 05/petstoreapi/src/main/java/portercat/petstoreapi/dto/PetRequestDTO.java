package portercat.petstoreapi.dto;

import lombok.Builder;
import lombok.Data;
import portercat.petstoreapi.models.Status;

import java.util.List;

@Data
@Builder
public class PetRequestDTO
{
    private String name;
    private Long categoryId;
    private List<Long> tagIds;
    private Status status;
}