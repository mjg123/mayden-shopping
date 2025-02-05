# Testing Strategies

## Status

accepted

## Context

This project needs automated testing.

## Decision

Generally unit testing may be used as a design tool, preferring higher level tests
to verify behaviours and catch regressions.

### Backend

We use:
 - JUnit unit testing for single classes
 - `SpringBootTest` features of the Spring framework to test backend at the HTTP level

### Frontend

We use:
 - Cypress for E2E scenario testing - testing the whole app including backend.

## Consequences

 - E2E testing allows behaviour verification of the whole app
 - As the codebase grows it will be helpful to add more component/unit tests.