// ports: 5173 (frontend dev mode) or 8080 (full app)
const baseUrl = 'http://localhost:5173';

export const pageLocators = {
    baseUrl: baseUrl,
    shoppingList: 'div#shoppingList',
    newListButton: 'button#create-new-list',
    urlWithUUID: '[a-zA-Z0-9-]{36}$',
    shoppingListSize: '#shopping-list-size',
    newItemField: '#new-item-text',
    addNewItemButton: '#add-new-item',
    shoppingListItem: '.listItemName',
    removeItemButton: '.removeItemButton'
};
