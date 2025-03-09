# Mistplay Feed app

App to show users and posts fetched from https://jsonplaceholder.typicode.com/.

Given the time constraints, my aim here was to focus more on the architectural side of the app, and to keep the UI fairly clean and simple.

## Architecture

Uses a modular approach. "Core" modules include `model`, `database`, `data`, `ui`, and `testing` and are used as dependencies for feature modules. Each screen has its own feature module.
The application module ties together the feature modules and handles navigation.

Additionally there is an included build `build-logic` with custom gradle plugins to make the gradle scripts composable.

I chose an MVVM architecture, with a Room database as the source of truth. I chose Hilt for DI.

## UI

The UI is fully in Compose with a single activity approach (using Compose NavHost for navigation as well). 
Followed Material3 guidelines for the UI. The app supports light and dark mode depending on your device configuration.
I kept the features fairly simple due to time constraints, there's a list view to display users along with a subset of the posts, and a detail view that shows all the posts.
The two screens are tied together by a shared element transition animation to make the transition between the screens smoother.

Features that I considered for implementation but ultimately left out because I felt they either didn't showcase much complexity or because they weren't worth the implementation time:
- Link to open user's website in a Chrome custom tab
- Link to open phone app using user's phone number
- Google Map integration to display pin for user's location
- Animated logo on splashscreen

## Testing and development

The app doesn't have complete test coverage due to time constraints, but I did include a few tests. There are unit tests in `SyncUsersAndPostsUseCaseTest` and `GetUserPropsUseCaseTest`, and a simple instrumented test in `UserDetailScreenTest`.
I added Compose previews for most UI elements.

## APK

You can find a release apk for download in Github releases
