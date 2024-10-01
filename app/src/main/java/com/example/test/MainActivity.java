package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Movie.Movie;
import com.example.test.Movie.MovieResponse;
import com.example.test.moviesAPI.MoviesAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private MoviesAPI moviesApi; // Cambiado a MoviesAPI
    private String apiKey = "1d889e4a858ab2dcd9c859a288beee66"; // Cambia por tu clave de API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPopularMovies = findViewById(R.id.btnPopular);
        Button btnSearchMovie = findViewById(R.id.btnBuscar);
        Button btnMovieDetails = findViewById(R.id.btnDetalle);
        TextView txtResult = findViewById(R.id.txtResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MoviesAPI.BASE_URL) // Cambiado a MoviesAPI
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        moviesApi = retrofit.create(MoviesAPI.class); // Cambiado a MoviesAPI

        btnPopularMovies.setOnClickListener(v -> fetchPopularMovies());
        btnSearchMovie.setOnClickListener(v -> searchMovies("titanic")); // Cambia el término de búsqueda según lo necesites
        btnMovieDetails.setOnClickListener(v -> fetchMovieDetails(550)); // Cambia el ID de película según lo necesites
    }

    private void fetchPopularMovies() {
        Call<MovieResponse> call = moviesApi.getPopularMovies(apiKey, "es-ES", 1); // Cambiado a moviesApi
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    StringBuilder result = new StringBuilder();
                    for (Movie movie : movies) {
                        result.append(movie.getTitle()).append("\n");
                    }
                    ((TextView) findViewById(R.id.txtResult)).setText(result.toString());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchMovies(String query) {
        Call<MovieResponse> call = moviesApi.searchMovies(apiKey, "es-ES", query, 1); // Cambiado a moviesApi
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    StringBuilder result = new StringBuilder();
                    for (Movie movie : movies) {
                        result.append(movie.getTitle()).append("\n");
                    }
                    ((TextView) findViewById(R.id.txtResult)).setText(result.toString());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchMovieDetails(int movieId) {
        Call<Movie> call = moviesApi.getMovieDetails(movieId, apiKey, "es-ES"); // Cambiado a usar el movieId
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Movie movie = response.body();
                    String result = movie.getTitle() + "\n" + movie.getOverview();
                    ((TextView) findViewById(R.id.txtResult)).setText(result);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
