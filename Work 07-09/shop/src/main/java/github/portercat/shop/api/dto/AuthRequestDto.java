package github.portercat.shop.api.dto;

import lombok.Data;

@Data
public class AuthRequestDto
{
    private String username;
    private String password;
}
