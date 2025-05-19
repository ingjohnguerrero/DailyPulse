# DailyPulse
Educational repo for the course [Kotlin Multiplatform Masterclass](https://github.com/petros-efthymiou/DailyPulse/tree/4_articles_networking_and_business_logic). The course aims to teach the state-of-art KMP development.

Daily Pulse includes a native Android and a native iOS apps, where the business logic and inftrastructure is shared in a KMP module.

Daily Pulse is using the news API to fetch, cache and display the top US business articles. It also contains a screen to diaply the list of news sources we use to fetch the articles from. Finally, it contains third screen to display informartion regarding the user's device.

## App preview

| iOS | Android |
|--------|-------|
| <img src="https://github.com/user-attachments/assets/9ce67ef7-0943-41a9-a13d-d7dd2795e0c3" height="500"> | <img src="https://github.com/user-attachments/assets/6a024735-ac2a-4bbc-a358-464b85368c5b" height="500">|

## Tech Stack
It is a prototype app based on the following technologies and patterns:

1. Clean Architecture
2. MVI
3. Ktor
4. SQL Delight
5. Koin
6. Jetpack Compose
7. Swift UI

## Releases

### 3.0

#### Feat

* Add Local DB data source for sources
* Add Repository pattern for sources
* Add pull to refresh functionality for sources on iOS and Android
* Add sources screen to display the list of news sources
* Add unit tests for sources module

### 2.0

#### Feat

* Add Local DB by using SQLDelight
* Add Repository pattern for articles
* Add pull to refresh functionality on iOS and Android
* Add unit tests for articles module

### 1.0

#### Feat

* Add Networking with Ktor
* Add Dependency Injection with Koin
