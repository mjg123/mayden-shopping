# Story 3

5. Remove stuff from the shopping list ⏳
```
   As a ... shopper
   I want to ... be able to remove items from my shopping list
   So I can ... Change my mind or fix my mistakes
   Requirements
       Create a way for user to remove an item to the shopping list
```

## Implementation plan

 - Backend to support `DELETE` to `/api/{listId}/item/{n}` to remove the nth item.
 - 404 for missing list, or invalid item number.



