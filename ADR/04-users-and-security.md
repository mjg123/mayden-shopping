# Title

## Status

accepted

## Context

Story 10 asks for user/password protection for shopping lists

### User and password protect
```
    As a ... shopper
    I want to ... protect my shopping list from other people
    So I can ... be sure it’s unchanged when I go back to it
    Requirements
        Add a login system to persist shopping lists for different users
```

This conflicts with another requirement from story 9 ("I want to share my shopping list via email
").

In a world where there are multiple users who can be logged in, we need to consider:
 - how does sharing a list work?
 - can lists have multiple owners?
 - do we need to consider the concept of user groups, or roles?
 - any other use cases for sharing or privacy?

## Decision

 - We won't implement Story 10 until we have discussed the requirements more thoroughly.
 - General shopping list creation/modification work can proceed and will be reusable.
 - Obviously security would be necessary in a finished app, we just decide not to do it immediately.

## Consequences

No user/password authentication or protection. Everything is public and can be modified by anyone.
