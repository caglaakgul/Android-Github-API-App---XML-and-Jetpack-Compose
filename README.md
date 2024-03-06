# # ECabs Android App Challenge

![GitHub last commit](https://img.shields.io/github/last-commit/yourusername/yourrepositoryname)
![GitHub language count](https://img.shields.io/github/languages/count/yourusername/yourrepositoryname)
![GitHub top language](https://img.shields.io/github/languages/top/yourusername/yourrepositoryname)

This project is developed in Kotlin using both XML and Jetpack Compose. It follows the MVVM design pattern and is tailored for the Android platform.

## Project Description

The project consists of two main screens: List and Detail. The List screen is populated with data fetched from the Github Events API. It operates on a single activity with two fragments using the Navigation Component.

- **List Screen**: Initially displays the first 5 data fetched from the API. Every 10 seconds, new data is fetched from the API and added to the existing list.
- **Detail Screen**: This screen is implemented using Jetpack Compose. Upon initial load, a request is made to access the repository's URL. Clicking the button opens the relevant webview.

## Technologies Used

- **MVVM Design Pattern**: The application is structured using the Model-View-ViewModel design pattern.
- **Retrofit**: Used for HTTP-based API calls.
- **Coroutines Flow**: Used to facilitate asynchronous operations.
- **Dagger Hilt**: Used for dependency injection.
- **Jetpack Compose**: Used for the Detail screen.
- **Androidx**: Used for Android-specific components.
- **Material**: Used for Material Design components.
- **Coil**: Used for image loading and displaying.
- **RecyclerView**: Used for the List screen.

## How to Run

1. Clone this project.
2. Open the project in Android Studio.
3. Install the necessary dependencies.
4. Run the project on an emulator or real device.

## Contribution

If you would like to contribute, please feel free to submit a pull request or open an issue. Any contributions and feedback are highly appreciated.

## License

This project is licensed under the MIT License. For more information, see the [LICENSE](LICENSE) file.

