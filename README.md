# One Piece Image Slider (Kotlin)

A modern Android image slider application built with Kotlin, showcasing characters from the popular anime, One Piece. The app is designed with a clean, modern UI and follows best practices for Android development, including a robust software architecture.

## 📜 Description

This application displays a gallery of One Piece characters one at a time. Users can navigate through the images manually using "next" and "previous" buttons or activate an automatic slideshow mode. Each image is presented with its title and a brief description, all within a sleek, card-based interface with smooth fade animations.

## ✨ Features

* **Manual Navigation:** Cycle through images with forward and backward buttons.
* **Looping Gallery:** When you reach the last image, the next one is the first, creating a seamless loop.
* **Automatic Slideshow:** A play/pause button to start or stop an automatic slideshow that transitions every 3 seconds.
* **Character Details:** Each image is accompanied by the character's name and a short description.
* **Modern UI:** A clean, visually appealing interface featuring Material Design components, a gradient background, and smooth cross-fade animations between images.
* **State Preservation:** The app is lifecycle-aware and correctly handles configuration changes (like screen rotation) without losing its current state.

## 🛠️ Technologies Used

* **Language:** [Kotlin](https://kotlinlang.org/)
* **UI Toolkit:** [Android Jetpack](https://developer.android.com/jetpack) with XML layouts and View Binding.
* **Concurrency:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for managing background tasks like the slideshow timer.
* **State Management:** [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) to create an observable, reactive UI that updates automatically.
* **UI Components:** [Material Design Components](https://material.io/develop/android) for modern and consistent styling.

## 🏛️ Architectural Design

This project is built using the **Model-View-ViewModel (MVVM)** design pattern, which separates the UI (View) from the business logic and state (ViewModel). This is further enhanced by the **Repository Pattern**.

* **View (MainActivity):** Responsible only for observing the UI state and forwarding user inputs. It remains "dumb" and straightforward.
* **ViewModel (ImageViewModel):** Holds the UI state, handles all business logic (like navigating images or managing the slideshow), and is completely independent of the Android Framework, making it easy to test.
* **Repository (ImageRepository):** Acts as the single source of truth for the image data. It abstracts the data source from the ViewModel, making the application more modular and scalable.
* **Model (ImageItem):** A simple data class representing a single image with its properties.

This architecture ensures a clean separation of concerns, making the codebase more maintainable, scalable, and testable.

## 🚀 Setup and Installation

To run this project, follow these simple steps:

1. **Clone the repository:**

   Repository link: [https://github.com/thanthtooaung-coding/one-piece-image-slider-kotlin.git](https://github.com/thanthtooaung-coding/one-piece-image-slider-kotlin.git)

   ```bash
   git clone https://github.com/thanthtooaung-coding/one-piece-image-slider-kotlin.git
   ```
2.  **Open in Android Studio:**
    * Open Android Studio.
    * Click on `File` > `Open`.
    * Navigate to the cloned repository folder and select it.
3.  **Add Images:**
    * Make sure you have the necessary images (`luffy.png`, `zoro.png`, etc.) in your `app/src/main/res/drawable` folder.
4.  **Build and Run:**
    * Click the "Run" button (▶️) in Android Studio to build and install the app on an emulator or a physical device.
