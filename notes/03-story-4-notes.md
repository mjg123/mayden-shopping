# Story 4

4. When I’ve bought something from my list I want to be able to cross it off the list
```
   As a ... shopper
   I want to ... have a way of marking off when I’ve picked up an item
   So I can ... see what I still need to find
   Requirements
        Create a way for users to know what they have and haven’t already picked up
```

## Implementation plan

 - Backend to support `PUT` to `/api/{listId}/item/{n}` to alter the nth item.
 - 404 for missing list, or invalid item number.
 - The whole item should be in the PUT request body: `{"name":"dogfood", "struckOut":true}`
   - this would allow renaming of list items but the UI doesn't expose that. 



