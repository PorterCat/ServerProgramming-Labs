package portercat.petstoreapi.models;

import lombok.Data;

@Data
public class ApiResponse
{
    private int code;
    private String type;
    private String message;
}
