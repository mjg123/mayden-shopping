# Story 6

6. I want to be able to reorder items on my list ⏳
```
   As a ... shopper
   I want to ... be able to reorder items on my shopping list
   So I can ... maintain my supermarket route when the supermarket swaps things
   around on it’s shelves
   Requirements
       Create a way for user to be able to change the order of items in their shopping list
```

## Implementation plan

 - Backend to support `POST` to `/api/{listId}/reorder`.
 - POST body should be a mapping from old item indices to new, something like:

```
[[0,0] [1,2] [2,1]]
```
  to indicate that item 0 is unchanged and items 1 and 2 swap places. 

### Mapping validation

Not all mappings would be valid, the resulting list may not:
 - have gaps,
 - have two items at the same index,
 - map a nonexistent item into another place (eg the fourth of a three-item list),
 - map the same item into more than one other place.

In this case the backend should return `HTTP/400` or `409`. Otherwise accept the mapping, implement it
and return `200` with the new shopping list in the body.
 
## Development
  This is quite a heavy piece of UI work. I would expect to implement some drag and drop reordering. I do not have time to complete this.


