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

}
