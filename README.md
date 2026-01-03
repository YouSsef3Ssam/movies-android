# 🎬 Movie App

A modern Android movie application built with **Jetpack Compose**, following **Clean Architecture** and **MVI**, using **Paging 3 with RemoteMediator** for efficient data loading and offline caching.

This project is designed with **production-level standards**, scalability, and testability in mind.

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

## 🚀 Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run the app
---

## 📝 Notes

- Easy to extend with new features and modules
- Strong focus on scalability and maintainability

