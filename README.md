# RepoSearch - repository search app

Simple app using REST API v3 for searching repositories.

## App architecture & technology

- App was made using MVVM pattern.
- Apps supports any screen size & resolution.
- Apps should work in portrait (only).
- Dependency injection: Koin.
- Libraries:
* Retrofit
* Kotlin Coroutines
* Moshi
* Timber
* Material Components
* EventBus

## Example views

Correct basic search:
![Basic Search](/screenshots/exampleBasicSearch.png)

Correct advanced search:
![Advanced Search](/screenshots/exampleAdvancedSearch.png)

Query error:
![Error View](/screenshots/exampleError.png)

Info about error during call if phone not connected to internet:
![No internet](/screenshots/exampleNoInternet.png)


## Quick install

To install app: you can download app-release.apk added in app/release directory and directly install app on the phone.

Have fun!