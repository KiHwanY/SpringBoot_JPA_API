package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    @Transactional // 우선권을 가진다.
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //변경감지로 명확하게 업데이트 처리 해주자.
    //이 방식을 선호한다.
    @Transactional //트랜잭션으로 인해 커밋이 된다.
    public void updateItem(Long itemId, int price, String name, int stockQuantity){
        //영속 상태
        Item findItem = itemRepository.findOne(itemId);

        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
