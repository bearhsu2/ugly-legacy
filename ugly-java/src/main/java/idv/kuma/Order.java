package idv.kuma;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class Order {
    private final List<Item> itemList;


    static Optional<Order> of(List<Map<String, Object>> items) {

        if (items == null || items.size() <= 0) {
            return Optional.empty();
        }

        return Optional.of(
                new Order(items.stream().map(map -> new Item((double) map.get("p"), (int) map.get("q"))).toList())
        );
    }
}
