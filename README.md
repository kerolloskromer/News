# News
News is a demo application that uses MVVM pattern and Android Jetpack: the Pagging 3 library with a backend API, LiveData, ViewBinding, Dagger-Hilt and Navigations Components.
The app fetches data from the network using Retrofit and Kotlin Coroutines.

## Tech stack & Open-source libraries
  - Paging 3 - for pagination
  - ViewBinding - allows to more easily write code that interacts with views and replaces ```findViewById```.
  - ViewModel - UI related data holder, lifecycle aware.
  - LiveData - Build data objects that notify views when the underlying database changes.
  - SafeArgs for navigating and passing data between fragments.
  - Navigation - Handle everything needed for in-app navigation.
  - Dagger-Hilt for dependency injection. Object creation and scoping is handled by Hilt.
  - Coil - for image loading.
  - Kotlin Coroutines + Flow - for managing background threads with simplified code and reducing needs for callbacks
  - Retrofit2 & OkHttp3 - to make REST requests to the web service integrated.
  - Timber - for logs.
 
## Architecture:
  - MVVM Architecture 
  - Repository pattern
  - Applying SOLID principles, each class has a single job with separation of concerns by making classes independent of each other and communicating with interfaces.

## Design
+ News is built with Material Components for Android.
+ The app starts with a list of news displayed in the RecyclerView widget. The screen also consists of options menu with search view to search for news. List item click takes the user to the article details with a button to oepn full article in browser. 

## Features
+ Search for news.
+ Opening an article's web site.

## News API
News uses the NewsApi for constructing RESTful API. Obtain your free API_KEY [NewsApi](https://newsapi.org/register) and paste it to the gradle.properties file to try the app.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
