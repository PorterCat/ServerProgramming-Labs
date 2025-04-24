package github.portercat.shop.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import github.portercat.shop.api.dto.ItemRequestDto;
import github.portercat.shop.core.model.entity.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(target = "shippingWeight.measurement.name", source = "shippingWeight.name")
    @Mapping(target = "shippingWeight.measurement.symbol", source = "shippingWeight.symbol")
    Item toEntity(ItemRequestDto itemDto);
}
