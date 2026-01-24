package idv.kuma;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class Order {
    private final List<Item> itemList;


    static Order of(List<Map<String, Object>> items) {
        List<Item> itemList = items.stream().map(map -> new Item((double) map.get("p"), (int) map.get("q"))).toList();
        Order o = new Order(itemList);
        return o;
    }
}
