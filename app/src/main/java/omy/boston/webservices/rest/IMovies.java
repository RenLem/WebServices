package omy.boston.webservices.rest;

import omy.boston.webservices.models.Movie;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by LosFrancisco on 21-Mar-17.
 */

public interface IMovies {

    public static final String ENDPOINT_URL = "http://omdbapi.com";
    @GET("/")
    void getMovie(
            @Query("t") String ovmieName, Callback<Movie> callback
    );
}
