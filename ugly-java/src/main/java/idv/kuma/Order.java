package idv.kuma;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Order {
    private final List<Item> itemList;


}
