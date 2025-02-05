package lol.gilliard.mayden.components;

import lol.gilliard.mayden.domain.ShoppingItem;
import lol.gilliard.mayden.domain.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController()
public class ShoppingListController {

    @Autowired
    private ShoppingListService slService;


    private ShoppingList throw404ifNull(ShoppingList shoppingList) {
        if (shoppingList == null) {
            throw new ResponseStatusException(NOT_FOUND, "No such list");
        } else {
            return shoppingList;
        }
    }


    @PostMapping("/api/list")
    public ShoppingList createShoppingList() {
        return slService.createEmptyList();
    }

    @GetMapping("/api/list/{id}")
    public ShoppingList getList(@PathVariable String id) {
        ShoppingList shoppingList = slService.getById(id);
        return throw404ifNull(shoppingList);
    }

    @PostMapping("/api/list/{id}/item")
    public ShoppingList addItemToList(@PathVariable String id, @RequestBody ShoppingItem item) {
        ShoppingList shoppingList = slService.addItemToList(id, item);
        return throw404ifNull(shoppingList);
    }

    @DeleteMapping("/api/list/{id}/item/{n}")
    public ShoppingList deleteItemFromList(@PathVariable String id, @PathVariable Integer n){
        ShoppingList shoppingList = slService.removeItem(id, n);
        return throw404ifNull(shoppingList);
    }

    @PutMapping("/api/list/{id}/item/{index}")
    public ShoppingList replaceItemInList(@PathVariable("id") String id, @PathVariable("index") Integer index, @RequestBody ShoppingItem item){
        ShoppingList shoppingList = slService.replaceItemInList(id, item, index);
        return throw404ifNull(shoppingList);
    }

}
