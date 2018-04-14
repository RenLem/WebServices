package omy.boston.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import omy.boston.webservices.models.Movie;
import omy.boston.webservices.rest.IMovies;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editText_movie;
    private Button btnGetWebData;
    private Callback<Movie> callback;
    private IMovies iMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_movie = (EditText) findViewById(R.id.editText_movie);
        btnGetWebData = (Button) findViewById(R.id.button_getWebData);

        btnGetWebData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_movie.getText().toString().length()>0){
                    getMovie(editText_movie.getText().toString());
                }else {
                    Toast.makeText(getApplicationContext(), "Please enter movie title!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        setupRestAdapter();
    }

    private void getMovie(String title){
        iMovies.getMovie(title, callback);
    }

    private void setupRestAdapter(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(IMovies.ENDPOINT_URL)
                .build();
        iMovies = restAdapter.create(IMovies.class);
        callback = new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {
                StringBuilder text = new StringBuilder();
                text.append(movie.getTitle() + "\n");
                text.append(movie.getYear() + "\n");
                text.append(movie.getDirector() + "\n");
                text.append(movie.getActors());
                Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
