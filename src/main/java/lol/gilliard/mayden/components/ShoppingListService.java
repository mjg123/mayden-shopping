package lol.gilliard.mayden.components;

import lol.gilliard.mayden.domain.ShoppingItem;
import lol.gilliard.mayden.domain.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingListService {

    private final Map<String, ShoppingList> storedLists = new HashMap<>();

    public ShoppingList createEmptyList() {
        ShoppingList newList = new ShoppingList(UUID.randomUUID().toString(), "name", new ArrayList<>());
        storedLists.put(newList.getId(), newList);
        return newList;
    }

    public ShoppingList getById(String id) {
        return storedLists.get(id);
    }

    public ShoppingList addItemToList(String id, ShoppingItem item) {

        return storedLists.computeIfPresent(id,
                (k, list) -> {
                    list.getItems().add(item);
                    return list;
                });
    }
}
