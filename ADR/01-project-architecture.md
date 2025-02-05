# Project Architecture

## Status

Accepted v2

## Context

At the start of the project, we need to decide a tech stack and make overarching architecture decisions so that implementation can start.

## Decision

### Frontend stack
  - SPA served by backend
  - JSON fetched from backend to hydrate
  - JS to create the UI
    - UI managed by React
    - data pulled using the Fetch API
    - no need for redux etc
  - This implies: no server-side templating or rendering

Because:
  - react programming model is extensible and helps keep code modular
  - fetch API is simple, built-in and well-documented how to use with react
  - domain is simple enough that we don't need state-management libraries like redux

### Backend stack
  - Spring Boot (Java) app
    - Components: Spring Web

Because:
  - Developer familiarity
  - Large amount of docs and reference resources
  - Very "Batteries included" framework

This separates the FE and BE to be individually buildable and testable.

## Consequences

Development can start 🎉🎉🎉