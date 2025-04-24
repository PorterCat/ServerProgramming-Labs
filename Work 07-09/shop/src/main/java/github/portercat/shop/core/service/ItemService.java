package github.portercat.shop.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import github.portercat.shop.api.dto.ItemRequestDto;
import github.portercat.shop.api.mapper.ItemMapper;
import github.portercat.shop.core.model.entity.Item;
import github.portercat.shop.core.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public Item createItem(ItemRequestDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        return itemRepository.save(item);
    }

}
