# 🎬 Movies App  

Movies App is an Android application that allows users to explore and discover popular and upcoming movies. The app is powered by the [TMDB API](https://developer.themoviedb.org/), providing real-time access to a wide range of movie data.

---

## 📹 App Demo  

Check out the demo video to see the app in action:  
[**Watch the Video**](https://drive.google.com/file/d/1F75SaLNL5_GEfFzzV7y_yMttCuEIYTf0/view?usp=sharing)

---

## 📲 Features  

- Browse popular and upcoming movies.
- View detailed information about each movie, including synopsis, release date, and ratings.
- Clean interface.  

---

## 🛠️ Installation Instructions  

To run the Movies App on your local machine:

1. **Clone the repository**
2. Obtain your API key from TMDB.
3. Add your API key to the gradle.properties file or as a constant in the app:
   
   ```bash
   const val API_KEY = "your_api_key_here"
5. Build and run

## 🔧 Technologies Used  

- **Kotlin**  
- **Jetpack Compose**  
- **Retrofit**
- **gson**
- **hilt**
- **Coil**
- **TMDB API**
- **junit and mockk**

## 🚀 Next Steps  

Here are the next steps for this app:

- **Improve unit test and implement ui tests**  
- **Adding Loading behavior, based on current Home State, to a better user experience.**
- **Add phased loading, since requests can return movies by page, which allows to load a new page when it reaches the end of the previous page.**
- **Handling error on requests, to avoid crashing when requests is not successfull**
- **Implement Search Feature**  
- **Implement Favorite and User section:**
- **Improve movie details screen so it is possible to watch the trailer**




   
