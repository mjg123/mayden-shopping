package lol.gilliard.mayden;

import lol.gilliard.mayden.domain.ShoppingItem;
import lol.gilliard.mayden.domain.ShoppingList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static lol.gilliard.mayden.TestHelpers.emptyShoppingListAssertions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingListScenarioTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // REST template helpers

    private ResponseEntity<ShoppingList> getShoppingListById(String id) {
        return this.restTemplate.getForEntity("http://localhost:" + port + "/api/list/" + id, ShoppingList.class);
    }

    private ResponseEntity<ShoppingList> createEmptyShoppingList() {
        return this.restTemplate.postForEntity("http://localhost:" + port + "/api/list", null, ShoppingList.class);
    }

    private ResponseEntity<ShoppingList> addItemToList(String id, ShoppingItem item) {
        String url = "http://localhost:" + port + "/api/list/" + id + "/item";
        return this.restTemplate.postForEntity(
                url,
                item,
                ShoppingList.class);
    }


    // Tests

    @Test
    void nonExistentListGenerates404() {
        ResponseEntity<ShoppingList> response = getShoppingListById("non-existent");

        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    void postToCreateNewList() {
        ShoppingList created = createEmptyShoppingList().getBody();

        emptyShoppingListAssertions(created);
    }


    @Test
    void fetchNewlyCreatedList() {
        ShoppingList created = createEmptyShoppingList().getBody();
        ShoppingList fetched = getShoppingListById(created.getId()).getBody();

        emptyShoppingListAssertions(fetched);
    }

    @Test
    void addItemToList() {
        ShoppingList created = createEmptyShoppingList().getBody();

        ShoppingItem milk = new ShoppingItem("Milk");
        ShoppingList listWithMilk = addItemToList(created.getId(), milk).getBody();

        // POST (add item) response
        assertThat(listWithMilk.getItems().size()).isEqualTo(1);
        assertThat(listWithMilk.getItems().getFirst().getName()).isEqualTo("Milk");

        // re-GET by ID
        ShoppingList fetched = getShoppingListById(created.getId()).getBody();
        assertThat(fetched.getItems().size()).isEqualTo(1);
        assertThat(fetched.getItems().getFirst().getName()).isEqualTo("Milk");
    }

    @Test
    void addItemToNonExistentListGenerates404() {
        ShoppingItem milk = new ShoppingItem("Milk");
        ResponseEntity<ShoppingList> errorResponse = addItemToList("non-existent", milk);

        assertThat(errorResponse.getStatusCode().value()).isEqualTo(404);
    }

}
