package github.portercat.shop.api.dto;

import lombok.Data;

@Data
public class OrderDetailDto
{
    private QuantityDto quantity;
    private String taxStatus;
    private Long itemId;
}
