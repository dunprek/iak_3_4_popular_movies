package don.com.moviesiak.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import don.com.moviesiak.Constants;
import don.com.moviesiak.R;
import don.com.moviesiak.db.AppDatabase;
import don.com.moviesiak.db.utils.DbInitializer;

public class DetailActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    TextView tvTitle, tvYear, tvRating, tvOverview;

    String moviePoster,movieTitle,movieYear,movieOverview;
    Integer movieId;
    ImageView imageView;
    Double movieRating;

    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getExtra();

        setUi();

        setContent();

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_pink));

            DbInitializer.addFavorite(AppDatabase.getInMemoryDatabase(getApplicationContext()),
                    movieId,
                    moviePoster);
        }else{
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_grey));

            DbInitializer.deleteFavoriteItem(AppDatabase.getInMemoryDatabase(getApplicationContext()),
                    movieId,
                    moviePoster);
        }

    }


    protected void onDestroy() {
        super.onDestroy();
    }

    private void getExtra(){
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            movieId = extras.getInt("MOVIE_ID");
            moviePoster = extras.getString("MOVIE_POSTER");
            Log.d("POSTER",moviePoster);
            movieTitle = extras.getString("MOVIE_TITLE");
            movieYear = extras.getString("MOVIE_YEAR");
            movieRating = extras.getDouble("MOVIE_RATING");
            movieOverview = extras.getString("MOVIE_OVERVIEW");

            Log.d("OVERVIEW-VIEW",movieOverview);


        }
    }

    private void setUi(){
        imageView = (ImageView)findViewById(R.id.iv_poster);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvYear = (TextView) findViewById(R.id.tv_year);
        tvRating = (TextView) findViewById(R.id.tv_rating);
        tvOverview = (TextView) findViewById(R.id.tv_overview);

        toggleButton = findViewById(R.id.toggle_button);
        toggleButton.setChecked(false);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_grey));
        toggleButton.setOnCheckedChangeListener(this);
    }

    private void setContent(){
        //set imageurl + posterPath ke imageView
        Picasso.with(getApplicationContext())
                .load(Constants.BASE_IMAGE_URL+moviePoster)
                .fit()
                .into(imageView);

        //set string  text ke masing - masing textView
        tvTitle.setText(movieTitle);
        tvYear.setText(movieYear);
        tvRating.setText(String.valueOf(movieRating));
        tvOverview.setText(movieOverview);
    }
}
