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

    private void removeItemFromList(String id, Object n) {
        String url = "http://localhost:" + port + "/api/list/" + id + "/item/" + n;
        this.restTemplate.delete(url);

        // note: restTemplate doesn't allow for response bodies to DELETE requests
        // nor does it give you the ResponseEntity to assert on status codes etc
    }

    private void toggleStruckOut(String id, int index){
        ShoppingItem item = getShoppingListById(id).getBody().getItems().get(index);
        item.setStruckOut(!item.isStruckOut());

        String url = "http://localhost:" + port + "/api/list/" + id + "/item/" + index;

        this.restTemplate.put(url, item);
    }


    // Tests

    @Test
    public void nonExistentListGenerates404() {
        ResponseEntity<ShoppingList> response = getShoppingListById("non-existent");

        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    public void postToCreateNewList() {
        ShoppingList created = createEmptyShoppingList().getBody();

        emptyShoppingListAssertions(created);
    }


    @Test
    public void fetchNewlyCreatedList() {
        ShoppingList created = createEmptyShoppingList().getBody();
        ShoppingList fetched = getShoppingListById(created.getId()).getBody();

        emptyShoppingListAssertions(fetched);
    }

    @Test
    public void addItemToList() {
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
    public void addItemToNonExistentListGenerates404() {
        ShoppingItem milk = new ShoppingItem("Milk");
        ResponseEntity<ShoppingList> errorResponse = addItemToList("non-existent", milk);

        assertThat(errorResponse.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    public void removeItemFromList(){
        ShoppingList created = createEmptyShoppingList().getBody();

        addItemToList(created.getId(), new ShoppingItem("Milk")).getBody();
        addItemToList(created.getId(), new ShoppingItem("Eggs")).getBody();
        addItemToList(created.getId(), new ShoppingItem("Spam")).getBody();

        removeItemFromList(created.getId(), 1);

        ResponseEntity<ShoppingList> fetchedEntity = getShoppingListById(created.getId());
        assertThat(fetchedEntity.getStatusCode().value()).isEqualTo(200);
        ShoppingList fetchedShoppingList = fetchedEntity.getBody();

        // POST (add item) response
        assertThat(fetchedShoppingList.getItems().size()).isEqualTo(2);
        assertThat(fetchedShoppingList.getItems().get(0).getName()).isEqualTo("Milk");
        assertThat(fetchedShoppingList.getItems().get(1).getName()).isEqualTo("Spam");
    }

    @Test
    public void removeItemNoSuchItem(){
        ShoppingList created = createEmptyShoppingList().getBody();

        addItemToList(created.getId(), new ShoppingItem("Milk")).getBody();
        addItemToList(created.getId(), new ShoppingItem("Eggs")).getBody();
        addItemToList(created.getId(), new ShoppingItem("Spam")).getBody();

        removeItemFromList(created.getId(), 6); // no 6th element
        ShoppingList fetchedShoppingList = getShoppingListById(created.getId()).getBody();

        assertThat(fetchedShoppingList.getItems().size()).isEqualTo(3);
    }

    @Test
    public void removeItemNonIntegerItem(){
        ShoppingList created = createEmptyShoppingList().getBody();
        addItemToList(created.getId(), new ShoppingItem("Milk")).getBody();
        addItemToList(created.getId(), new ShoppingItem("Eggs")).getBody();
        addItemToList(created.getId(), new ShoppingItem("Spam")).getBody();

        removeItemFromList(created.getId(), "this_is_not_a_number"); // no 6th element
        ShoppingList fetchedShoppingList = getShoppingListById(created.getId()).getBody();

        assertThat(fetchedShoppingList.getItems().size()).isEqualTo(3);
    }

    @Test
    public void setItemStruckOut(){
        ShoppingList created = createEmptyShoppingList().getBody();
        addItemToList(created.getId(), new ShoppingItem("Milk")).getBody();
        addItemToList(created.getId(), new ShoppingItem("Eggs")).getBody();

        // default to non-struckout
        ShoppingList fetchedShoppingList1 = getShoppingListById(created.getId()).getBody();
        assertThat(fetchedShoppingList1.getItems().get(1).isStruckOut()).isFalse();

        toggleStruckOut(created.getId(), 1);
        ShoppingList fetchedShoppingList2 = getShoppingListById(created.getId()).getBody();

        // is now struck out
        assertThat(fetchedShoppingList2.getItems().get(1).isStruckOut()).isTrue();
    }

}
