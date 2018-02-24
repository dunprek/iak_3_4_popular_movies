package don.com.moviesiak.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import don.com.moviesiak.R;
import don.com.moviesiak.db.AppDatabase;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
