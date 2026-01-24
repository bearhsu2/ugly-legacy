package idv.kuma;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class Order {
    private final List<Item> itemList;


    static Order of(List<Map<String, Object>> items) throws EmptyOrderException {

        if (items == null || items.size() <= 0) {
            throw new EmptyOrderException();
        }

        return new Order(items.stream().map(map -> new Item((double) map.get("p"), (int) map.get("q"))).toList());
    }
}
