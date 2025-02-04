package lol.gilliard.mayden.domain;

import java.util.List;

public class ShoppingList {

    private String id;
    private String name;
    private List<ShoppingItem> items;

    public ShoppingList(String id, String name, List<ShoppingItem> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ShoppingItem> getItems() {
        return items;
    }

}