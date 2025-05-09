# DailyPulse
Educational repo for the course [Kotlin Multiplatform Masterclass](https://github.com/petros-efthymiou/DailyPulse/tree/4_articles_networking_and_business_logic). The course aims to teach the state-of-art KMP development.

Daily Pulse includes a native Android and a native iOS apps, where the business logic and inftrastructure is shared in a KMP module.

Daily Pulse is using the news API to fetch, cache and display the top US business articles. It also contains a screen to diaply the list of news sources we use to fetch the articles from. Finally, it contains third screen to display informartion regarding the user's device.

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

### 2.0

#### Feat

* Add Local DB by using SQLDelight
* Add Repository pattern for articles
* Add pull to refresh functionality on iOS and Android

### 1.0

#### Feat

* Add Networking with Ktor
* Add Dependency Injection with Koin
