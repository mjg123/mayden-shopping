# Database usage

## Status

accepted

## Context

We are developing under a strict time budget. Database setup is time consuming.

## Decision

We will store all application state in memory in the Backend.

## Consequences

Normal functionality, except:
 - all data will be lost on backend application restart

`spring-data-jpa` module can be added later to provide persistent storage.