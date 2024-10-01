package com.example.test.moviesAPI;

import retrofit2.Call;

import com.example.test.Movie.Movie;
import com.example.test.Movie.MovieResponse;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesAPI {


    String BASE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=1d889e4a858ab2dcd9c859a288beee66";

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("1d889e4a858ab2dcd9c859a288beee66") String apiKey, @Query("es-ES") String language, @Query("page") int page);

    @GET("search/movie")
    Call<MovieResponse> searchMovies(@Query("1d889e4a858ab2dcd9c859a288beee66") String apiKey, @Query("es-ES") String language, @Query("query") String query, @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int movieId, @Query("1d889e4a858ab2dcd9c859a288beee66") String apiKey, @Query("es-ES") String language);


}