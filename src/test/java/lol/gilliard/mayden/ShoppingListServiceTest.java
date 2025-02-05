package lol.gilliard.mayden;

import lol.gilliard.mayden.components.ShoppingListService;
import lol.gilliard.mayden.domain.ShoppingItem;
import lol.gilliard.mayden.domain.ShoppingList;
import org.junit.jupiter.api.Test;

import static lol.gilliard.mayden.TestHelpers.emptyShoppingListAssertions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShoppingListServiceTest {

    @Test
    public void createEmptyShoppingList() {
        ShoppingList emptyList = new ShoppingListService().createEmptyList();

        emptyShoppingListAssertions(emptyList);
    }

    @Test
    public void fetchNonExistentShoppingList(){
        ShoppingList nonExistentList = new ShoppingListService().getById("DOES NOT EXIST");

        assertThat(nonExistentList).isNull();
    }

    @Test
    public void createAndFetchShoppingList(){
        ShoppingListService shoppingListService = new ShoppingListService();

        ShoppingList created = shoppingListService.createEmptyList();
        ShoppingList fetched = shoppingListService.getById(created.getId());

        emptyShoppingListAssertions(fetched);
    }

    @Test
    public void addItemToList(){
        ShoppingListService shoppingListService = new ShoppingListService();

        ShoppingList created = shoppingListService.createEmptyList();
        ShoppingList bananas = shoppingListService.addItemToList(created.getId(), new ShoppingItem("Bananas"));

        // check the response to .addItemToList
        assertThat(bananas.getItems().size()).isEqualTo(1);
        assertThat(bananas.getItems().getFirst().getName()).isEqualTo("Bananas");

        // fetch again with same ID
        ShoppingList refetchedBananas = shoppingListService.getById(created.getId());
        assertThat(refetchedBananas.getItems().size()).isEqualTo(1);
        assertThat(refetchedBananas.getItems().getFirst().getName()).isEqualTo("Bananas");
    }

    @Test
    public void removeItemFromList(){
        ShoppingListService shoppingListService = new ShoppingListService();

        ShoppingList theList = shoppingListService.createEmptyList();
        shoppingListService.addItemToList(theList.getId(), new ShoppingItem("Bananas"));
        shoppingListService.addItemToList(theList.getId(), new ShoppingItem("Chocolate"));
        shoppingListService.addItemToList(theList.getId(), new ShoppingItem("Gloves"));

        assertThat(theList.getItems().size()).isEqualTo(3);

        // take chocolate off the list
        theList = shoppingListService.removeItem(theList.getId(), 1);
        assertThat(theList.getItems().size()).isEqualTo(2);
        assertThat(theList.getItems().get(0).getName()).isEqualTo("Bananas");
        assertThat(theList.getItems().get(1).getName()).isEqualTo("Gloves");

        // take bananas off the list
        theList = shoppingListService.removeItem(theList.getId(), 0);
        assertThat(theList.getItems().size()).isEqualTo(1);
        assertThat(theList.getItems().get(0).getName()).isEqualTo("Gloves");

        // finally, the gloves come off 🥁
        theList = shoppingListService.removeItem(theList.getId(), 0);
        assertThat(theList.getItems().size()).isEqualTo(0);
    }

    @Test
    public void removeInvalidItemsFromList(){
        ShoppingListService shoppingListService = new ShoppingListService();

        ShoppingList theList = shoppingListService.createEmptyList();
        shoppingListService.addItemToList(theList.getId(), new ShoppingItem("Bananas"));
        shoppingListService.addItemToList(theList.getId(), new ShoppingItem("Chocolate"));
        shoppingListService.addItemToList(theList.getId(), new ShoppingItem("Gloves"));

        assertThat(theList.getItems().size()).isEqualTo(3);

        // take the 4th item off a 3-item list
        ShoppingList theNewList = shoppingListService.removeItem(theList.getId(), 4);

        assertThat(theNewList).isNull();

        assertThat(theList.getItems().size()).isEqualTo(3);
        assertThat(theList.getItems().get(0).getName()).isEqualTo("Bananas");
        assertThat(theList.getItems().get(1).getName()).isEqualTo("Chocolate");
        assertThat(theList.getItems().get(2).getName()).isEqualTo("Gloves");


        // take the -1th item off a 3-item list
        theNewList = shoppingListService.removeItem(theList.getId(), -1);

        assertThat(theNewList).isNull();

        assertThat(theList.getItems().size()).isEqualTo(3);
        assertThat(theList.getItems().get(0).getName()).isEqualTo("Bananas");
        assertThat(theList.getItems().get(1).getName()).isEqualTo("Chocolate");
        assertThat(theList.getItems().get(2).getName()).isEqualTo("Gloves");
    }

}
