# Mayden Technical Assessment
Matthew Gilliard, Feb 2025

Hello, thank you for reading this submission.

I was given a list of [ten stories](./notes/00-all-stories.md), of which:

 - Stories 1,2,3,4,5 are ready for review.
 - Story 6 would take too long for the guide of 6 hours, but I have drafted [an implementation plan](ADR/05-sharing-by-email.md).
 - Stories 7 and 8 need more discussion about where to source pricing information ([more info](ADR/02-grocery-prices.md)).
 - Story 9 needs the ability to send email, which is nontrivial ([more info](ADR/05-sharing-by-email.md))
 - Story 10 needs refinement ([more info](ADR/04-users-and-security.md))

I think this is reasonable for the time spent.

As well as the info linked above, I documented:
 - [The project architecture](ADR/01-project-architecture.md)
 - [Decisions around database usage](ADR/03-database-usage.md)
 - [Testing strategies](ADR/06-testing-strategies.md)

## Reading and running the code

_Note: Full dev documentation is in [DEVELOPMENT.md](./DEVELOPMENT.md)_

 - Backend source code (Java, Spring Boot) is in `/src`
 - Frontend source code (Javascript, React) is in `/frontend`

The easiest way to run the code is to download the pre-built `mayden-shopping-0.0.1-SNAPSHOT.jar` from [the releases page](https://github.com/mjg123/mayden-shopping/releases/tag/0.0.1-SNAPSHOT) of this repo.

Run this code with `java -jar mayden-shopping-0.0.1-SNAPSHOT.jar`, then open `http://localhost:8080` in your browser.
Java 23 is expected. If needed you can download it from the [Eclipse releases page](https://adoptium.net/en-GB/temurin/releases/?version=23).

Please note that I am not a graphic designer, this is ugly but functional.