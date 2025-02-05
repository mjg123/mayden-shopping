package lol.gilliard.mayden.components;

import lol.gilliard.mayden.domain.ShoppingItem;
import lol.gilliard.mayden.domain.ShoppingList;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ShoppingListService {

    private final Map<String, ShoppingList> storedLists = new HashMap<>();

    private String generateShoppingListName(){
        String dayOfWeekAllCaps = LocalDate.now().getDayOfWeek().toString();
        String dayOfWeek = dayOfWeekAllCaps.charAt(0) + dayOfWeekAllCaps.substring(1).toLowerCase();
        return dayOfWeek + "'s Shopping List";
    }

    public ShoppingList createEmptyList() {
        ShoppingList newList = new ShoppingList(UUID.randomUUID().toString(), generateShoppingListName(), new ArrayList<>());
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

    public ShoppingList removeItem(String id, int n) {

        try {
            return storedLists.computeIfPresent(id,
                    (k, list) -> {
                        list.getItems().remove(n);
                        return list;
                    });

        } catch (IndexOutOfBoundsException e){
            return null;
        }

    }
}
