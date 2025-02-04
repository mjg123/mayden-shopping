# Stories 1, 2, 5

 - View a list of items on a shopping list
 - Add items to the shopping list
 - Persist the data so I can view the list if I move away from the page

## Implementation plan

Do these together. Shopping lists start empty, so being able to _add_ items makes being able to _see_
them testable. Having them persisted on the BE means testable at the API level too.

This will include project setup for BE and FE.

Overall flow:
- User browses to `/` and sees a `+ New` button to create a list
- on click: FE POSTs to `/api/list` and gets the id of a new list
- browser redirects to `/api/list/:id` and shows an empty list
- user can add to the list. Changes are persisted.

API needs:
- POST `/api/list` (Empty body) -> {id: xxx}
- GET `/api/list/:id` -> {id: xxx, items: [], listName: xxx}
- POST `/api/list/:id/item` (Item in body) -> same response (Add to list)

FE notes
 - minimal CSS