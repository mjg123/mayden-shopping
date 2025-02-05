# Mayden Technical Assessment
Matthew Gilliard, Feb 2025



# Building, Development and Testing 

## Prerequisites
Built with:
 - Node v23.2.0
 - pnpm 9.15.4
 - Java 23

## Build for release

To build the full app:
 - Build the frontend
   - `cd frontend`
   - `pnpm run build`
   - `cd ..`
 - Add the built frontend code to the backend
   - `cp -R frontend/dist/* src/main/resources/static`
 - Build the combined app
   - `./mvnw clean package`

The full app is now packaged into `target/mayden-shopping-0.0.1-SNAPSHOT.jar` which can be run with `java -jar <path_to_jar>`.
The app will be served at `http://localhost:8080`.

Note that the built frontend code is .gitignore'd so you will always need to `pnpm run build` on a fresh clone.

## Development

Frontend and backend may be worked on independently.

The backend has no dependency on the frontend.
For backend work a Java IDE is very useful (recommended: IntelliJ IDEA).

For frontend dev you will need the backend running _somewhere_, but it can be a separate instance
(served on a different host/port, codebase in a different directory, etc). Start either a full build
of the app or backend only, then make sure to update `frontend/vite.config.js` so that your
WIP frontend code will be proxied to the running backend. Note that this file might need
a fixed hostname or IP address so will very likely need editing. If you see `http proxy error`
in the frontend console logs this is probably the cause.

Run a hot-reloading dev instance of the frontend code with `pnpm run dev` in the `frontend`
directory.

## Testing

`./mvnw test` will run all unit and integration tests for the backend

`cd frontend; pnpm exec playwright test` will run all the E2E tests.

Notes:
 - App must be running with frontend able to reach backend, or tests will fail
 - set the base URL to test against in `frontend/e2e/util/locators.js`

