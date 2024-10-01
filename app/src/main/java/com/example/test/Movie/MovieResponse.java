package com.example.test.Movie;

import java.util.List;

public class MovieResponse {

    // URL https://api.themoviedb.org/3/movie/popular?api_key=1d889e4a858ab2dcd9c859a288beee66

    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}