package lol.gilliard.mayden;

import lol.gilliard.mayden.domain.ShoppingList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// Functions used in multiple tests, collected into one place
public class TestHelpers {

    public static void emptyShoppingListAssertions(ShoppingList actual) {


        assertThat(actual.getId().length()).isEqualTo(36);

        String newListNameSuffix = "'s Shopping List";
        assertThat(actual.getName()).endsWith(newListNameSuffix);
        assertThat(actual.getName().length()).isGreaterThan(newListNameSuffix.length() + 2);

        assertThat(actual.getItems().size()).isEqualTo(0);
    }

}
