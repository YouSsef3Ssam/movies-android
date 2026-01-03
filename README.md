# 🎬 Movie App

A modern Android movie application built with **Jetpack Compose**, following **Clean Architecture** and **MVI**, using *
*Paging 3 with RemoteMediator** for efficient data loading and offline caching.

This project is designed with **production-level standards**, scalability, and testability in mind.

---

## 🎥 Demo Video

A demo video is provided to showcase the app functionality and user experience.

▶️ **Watch the demo video:**  
[Open Demo Video](demo/movie-app-demo.mp4)

> The demo is recorded using a **debug build**, showcasing Pluto notifications and inspection tools.

---

## 📱 Features

- Fetch movies from a **remote API**
- Cache data locally using **Room Database**
- Seamless pagination using **Paging 3**
- **RemoteMediator** to synchronize network & local cache
- Fully declarative UI using **Jetpack Compose**
- Unidirectional data flow using **MVI**
- Modular and scalable **multi-module architecture**
- Custom **Gradle convention plugins**
- Strong **code quality & architecture enforcement**
- Comprehensive **unit testing**

---

### Why Multi-Module?

- Clear separation of concerns
- Faster Gradle builds
- Better test isolation
- Improved scalability
- Easier feature ownership

---

## ⚙️ Gradle Convention Plugins

The project uses **custom Gradle convention plugins** to:

- Centralize module configuration
- Eliminate duplicated Gradle code
- Enforce consistent setup across modules
- Standardize:
    - Kotlin & JVM configuration
    - Compose setup
    - Dependency management
    - Linting rules
    - Testing configuration

---

## 📦 Tech Stack

### UI

- Jetpack Compose
- Material 3

### Architecture

- Clean Architecture
- MVI
- Multi-module setup

### Data

- Ktor Client
- Room Database
- Paging 3
- RemoteMediator

### Dependency Injection

- Koin

### Async & Concurrency

- Kotlin Coroutines
- Flow

---

## 🔍 Debugging & Inspection

### Pluto Integration

The project integrates **Pluto** as a debugging and inspection tool during development.

Pluto is used for:

- **Network inspection**
    - Monitor outgoing requests and incoming responses
    - Inspect headers, payloads, and error responses from the API
- **Database inspection**
    - View and validate cached Room database entries
    - Debug RemoteMediator behavior and pagination states
- **Logging interception**
    - Capture and inspect application logs
    - Simplify debugging by correlating logs with runtime behavior

> **Note:** Pluto is enabled only in **debug builds** and is excluded from release variants to avoid any performance or
> security impact.

### 🔔 Pluto Notification Permission

To use Pluto inspectors:

- **Notification permission must be granted**
- Once enabled, a **notification icon** will appear in the **notification center**
- Tapping the notification opens the Pluto inspector dashboard

This notification acts as the entry point to access network, database, and logging inspection tools during runtime.

This integration significantly improves developer productivity and helps validate caching, logging, and data
synchronization behavior.

## 🧪 Testing

Unit tests are implemented for:

- ViewModels
- Repositories
- Remote Source

### Testing Tools

- JUnit
- MockK
- Turbine
- Kotlin Coroutines Test

---

## 🧹 Code Quality & Static Analysis

The project enforces high code quality using:

- **ktlint** – Kotlin code formatting
- **detekt** – Static code analysis
- **Konsist** – File structure & architecture validation

These tools ensure:

- Clean and readable code
- Architectural consistency
- Long-term maintainability

---

## 🪝 Git Hooks

The project uses **Git hooks** to enforce code quality and architectural correctness before changes are committed or
pushed.

### Pre-commit Hook

The **pre-commit** hook runs the following tasks:

- **ktlint**
    - Automatically formats Kotlin code
    - Ensures consistent code style across the project

- **detekt**
    - Performs static code analysis
    - Detects code smells and potential issues early

This ensures that only properly formatted and statically analyzed code can be committed.

---

### Pre-push Hook

The **pre-push** hook runs the following checks:

- **Konsist tests**
    - Enforces file structure and architectural rules
    - Validates module boundaries and layer dependencies

- **Unit tests**
    - Runs all unit test suites
    - Prevents pushing breaking changes to the repository

This guarantees that only architecturally valid and tested code is pushed.

> These hooks help maintain high code quality, architectural consistency, and reduce issues during code reviews and CI
> execution.

---

## 🚀 Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run the app

