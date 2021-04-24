# Open Pages 📖 

[![Build Status](https://app.bitrise.io/app/251f57924f3db10e/status.svg?token=WuXGBaE6kGS_s1iuh9e2Kg&branch=develop)](https://app.bitrise.io/app/251f57924f3db10e) [![codecov](https://codecov.io/gh/hblonski/pages/branch/develop/graph/badge.svg?token=B0Y7D65FLL)](https://codecov.io/gh/hblonski/pages)

<p align="center"><img src="https://ch3302files.storage.live.com/y4mZNn-dm_YMpaSHAUL_2UJPmYSdI09ZNYYrtfVr2H3cgOCkoa1eglvIjQDCcAGltMs9mxi6BfMl05iBR3Ali2y8tOKu-LHg4QzYdZA7a3VMF7_IYb3wh2TzZffIp9xOZ-fLn09mBuJ0lv_evoQfTZBmvQMkIIP5m-HDiltTsFxxHoU27qBMl6jjBhThaxOhCrU?width=120&height=120&cropmode=none" /></p>

Open Pages is an open source sample application with code directly extracted from [Pages](https://play.google.com/store/apps/details?id=com.hb.pages), an Android application developed to allow users to manage their book collections. It provides an organized way to store the books, by letting users divide them into custom bookshelves.  
Books can be added by online searching, scanning its barcode, or even by typing the details manually. The first two ways are powered by the [Google Books Api](https://developers.google.com/books), which ensures a huge information database is always available. Users can also add books to a wishlist, in case they don't own it yet.  
Once a book is saved, several details can be attached to it, such as the number of pages the user has read, if it is lent/borrowed, the dates in which the user has started/finished reading it, among others. Pages also helps the user keep track of these informations, by providing useful reminders, charts and statistics.

## Objective

The goal of both projects is to demonstrate my skills with Android/Kotlin development and general programming.  
I aim to replicate a real life agile development cycle, starting by prioritizing one requirement at a time, breaking it into tasks and managing them in a Kanban board.  
All the tasks are implemented with focus on high code quality and maintainability, by applying the best code practices and a well defined architecture. During development, I always make sure to keep a comprehensive test coverage, by writing unit and integration tests that stress the code behavior, as well as component tests that stress the features.  
I also apply continuous integration, by having a build set up on Bitrise, which is triggered every time I open a new Pull Request on Pages repository. [Its success is a request for merging with the ```develop``` branch](https://docs.github.com/en/github/administering-a-repository/configuring-protected-branches), and by doing so I ensure the main branch is always fully working. Also, it helps me to keep track of the test coverage by running a Gradle script that will generate a JaCoCo report, which Bitrise will post on CodeCov.

## Technology Stack

Pages and Open Pages use roughly the same technologies, which are:

- [Kotlin](https://kotlinlang.org/)
- [Android Jetpack](https://developer.android.com/jetpack) libraries by using [AndroidX](https://developer.android.com/jetpack/androidx) components
- MVVM + Clean Architecture
- SOLID principles
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) (ViewModel + LiveData + Room Database)
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
- Dependency injection by using [Koin](https://insert-koin.io/)
- Unit tests using [Junit](https://junit.org/junit4/) + [MockK](https://mockk.io/)
- Component tests by using [Espresso](https://developer.android.com/training/testing/espresso) and the [Robots Pattern](https://jakewharton.com/testing-robots/)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
- Image loading with [Glide](https://github.com/bumptech/glide)
- API consuming with [Retrofit 2](https://square.github.io/retrofit/)
- JSON handling with [Moshi](https://github.com/square/moshi)
- Animations with [Lottie](https://airbnb.design/lottie/)

## License
```
Copyright 2021 Hayllander Blonski

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

### Notes

To build Pages locally, make sure to replace the Google Books API Key and Firebase Config File by your own. They are included in this repository with dummy values, to ensure Bitrise can build it.

All the icons were acquired from https://www.flaticon.com/. If you wish to use Open Pages code, make sure to check their licenses first.
