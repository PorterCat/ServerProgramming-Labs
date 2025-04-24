package github.portercat.shop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import github.portercat.shop.core.model.entity.user.Address;

@Data
@AllArgsConstructor
public class RegisterRequestDto
{
    private String username;
    private String password;
    private String role;
    private Address address;
}
