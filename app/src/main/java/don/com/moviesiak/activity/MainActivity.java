package don.com.moviesiak.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;


import don.com.moviesiak.R;
import don.com.moviesiak.adapter.MainAdapter;
import don.com.moviesiak.model.MainModel;
import don.com.moviesiak.model.ResultsItem;
import don.com.moviesiak.services.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private String MY_TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;

    MainAdapter adapter;

    ApiClient apiClient;

    ArrayList<ResultsItem> resultsItems = new ArrayList<>();

    String myApiKey;

    private final String KEY_RECYCLER_STATE = "recycler_state";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_main);

        myApiKey = getString(R.string.api_key);
        Log.d(MY_TAG, myApiKey);


        if (savedInstanceState == null) {
            getMyMovies();
        }
    }

    private void getMyMovies() {
        apiClient = new ApiClient();
        Call<MainModel> call = apiClient.getApiInterface().getNowPlayingMovies(myApiKey, 1);
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if (response.isSuccessful()) {
                    Log.d(MY_TAG, "SUKSES");

                    MainModel mainModel = response.body();
                    resultsItems = mainModel.getResults();

                    setRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Log.d(MY_TAG, t.getMessage());
                Log.d(MY_TAG, "GAGAL-2");
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need into "outState"
        super.onSaveInstanceState(outState);


        outState.putParcelableArrayList("List", resultsItems);

//        Log.i ("myApplication", outState +"");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Read values from the "savedInstanceState"

        savedInstanceState.getParcelableArrayList("List");
        Log.i("myApplication", savedInstanceState + "");

        resultsItems = savedInstanceState.getParcelableArrayList("List");

        setRecyclerView();

    }

    private void setRecyclerView() {
        //ini gunanya untuk membuat smooth scroll
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        //ngambil data adapter
        adapter = new MainAdapter(resultsItems, getApplicationContext());

        recyclerView.setAdapter(adapter);
    }
}