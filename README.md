# Movie Demo Android

Android app built with Kotlin and Jetpack Compose that displays popular movies using The Movie Database (TMDB) API.

## 📹 Demo Video

[Watch the demo](https://drive.google.com/file/d/1ngotZokTtMojcxq0RFO-HC_OrRwlO37M/view?usp=sharing)

## Features

- List of popular movies with poster, title, overview, and rating
- Movie detail screen with full information
- Error handling for network and parsing issues
- Manual retry for failed states
- Loading state indicators
- Unit tests for data, domain, and presentation layers

## Architecture

The project follows Clean Architecture with a modular structure:

```
:app                → Application entry point
:feature-movies     → Presentation layer (Compose UI + ViewModels)
:domain             → Business logic (use cases, repository interfaces, domain models)
:data               → Data layer (Ktor client, DTOs, mappers, repository implementations)
:di                 → Dependency injection (Koin modules)
:design-system      → Shared UI components, theme, and design tokens
```

**Presentation pattern:** MVI — user actions are expressed as `Intent` objects, and the ViewModel reduces them into immutable `UiState` values exposed via `StateFlow`.

**Reactive streams** are implemented using Kotlin Flow.

```
UI (Compose + ViewModel)
        ↓
Domain (Use Cases + Repository interfaces)
        ↓
Data (Ktor + DTOs + Mappers)
        ↓
TMDB REST API
```

## Tech Stack

| Layer | Library |
|---|---|
| UI | Jetpack Compose, Material 3 |
| Navigation | Navigation Compose |
| Image loading | Coil |
| Networking | Ktor Client (OkHttp engine) |
| Serialization | Kotlinx Serialization |
| Dependency injection | Koin |
| Testing | JUnit 4, MockK, Kotest, Turbine |
| Static analysis | Detekt |
| CI | GitHub Actions |

## API

**TMDB (The Movie Database)** — movie list and detail data

- `GET /movie/popular` — paginated list of popular movies
- `GET /movie/{movieId}` — single movie detail

## API Key Setup

The API key is not included in the repository. It is loaded from:

- `gradle.properties` for local development
- Environment variable for CI

Add the following to your local `gradle.properties`:

```properties
TMDB_API_KEY=your_key_here
```

## Getting Started

**Prerequisites:**
- JDK 17+
- Android Studio Ladybug or newer
- Android SDK 36

**Build & run:**

```bash
./gradlew assembleDebug          # Build debug APK
./gradlew testDebugUnitTest      # Run unit tests
./gradlew lintDebug              # Run lint checks
./gradlew detekt                 # Run static analysis
```

## Testing

Tests cover all architectural layers using:

- **MockK** for mocking dependencies
- **Turbine** for Flow assertions
- **Kotest** for expressive assertion DSL
- **Coroutines Test** with `StandardTestDispatcher` for deterministic async execution
- **Test fixtures** shared via domain module's `testFixtures` source set

## CI

The GitHub Actions pipeline runs on pushes to `develop`, `release/*`, and `feature/*` branches, and on PRs targeting `develop` and `main`:

1. Android Lint
2. Unit tests
3. Debug APK build + artifact upload
