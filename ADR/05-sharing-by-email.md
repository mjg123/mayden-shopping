# Sharing by email

## Status

accepted v2

## Context

_Every program attempts to expand until it can send mail_  (after jwz)


Story 9 has:

9. I want to share my shopping list via email
```
   As a ... shopper
   I want to ... I want to send my shopping list to my partner
   So I can ... shop instead of me
   Requirements
       Add functionality to send the shopping list via email
```

This is not straightforward, because:
 - Sending email from the browser is hard. Might be possible with something like Sendgrid.
 - Sending email programmatically from the backend is tricky without using 3rd party services. 

But:
 - `mailto` links can be used in the browser:  [https://css-tricks.com/snippets/html/mailto-links/](https://css-tricks.com/snippets/html/mailto-links/)
 - These open the user's default email app with some elements of the email prepopulated

## Decision

 - We will use `mailto` links for this story
   - this is quick and easy, and will let users share their lists
   - `mailto` links can specify subject and body text
 - We can discuss more if this is not sufficient

**Updated for v2:**
 - I find that support for `mailto` links is really inconsistent between different platforms and clients.
   - The subject may be truncated
   - The body may be malformed, or missing entirely
   - If the email client is not configured properly it will not be able to handle the link at all

Decision: This story needs more discussion, will most likely benefit from using a 3rd party mail service.

## Consequences

"Email to someone" functionality will rely on the user having a default mail app set up and working.
