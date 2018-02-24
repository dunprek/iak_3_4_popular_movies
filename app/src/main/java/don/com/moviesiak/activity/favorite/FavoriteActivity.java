package don.com.moviesiak.activity.favorite;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import don.com.moviesiak.R;
import don.com.moviesiak.adapter.FavoriteAdapter;
import don.com.moviesiak.adapter.MainAdapter;
import don.com.moviesiak.db.AppDatabase;
import don.com.moviesiak.db.Favorite;
import don.com.moviesiak.db.utils.DbInitializer;
import don.com.moviesiak.model.MainModel;
import don.com.moviesiak.services.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteActivity extends AppCompatActivity {

    private FavoriteViewModel mViewModel;

    RecyclerView recyclerView;
    FavoriteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = findViewById(R.id.rv_favorite);



        mViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        subcribeUiFavorite();

    }

    @Override
    protected void onDestroy() {
//        AppDatabase.destroyInstance();
        super.onDestroy();
    }


    private void subcribeUiFavorite(){
        mViewModel.getListFavorite().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(@Nullable List<Favorite> favorites) {
                showFavoriteInUi(favorites);
            }
        });
    }

    private void showFavoriteInUi(final @NonNull List<Favorite> favorites){

        if(favorites.size()==0){
            Toast.makeText(getApplicationContext(),"KOSONG",Toast.LENGTH_LONG).show();
        }else{
                //ini gunanya untuk membuat smooth scroll
                recyclerView.setHasFixedSize(true);


                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

                //ngambil data adapter
                adapter = new FavoriteAdapter(favorites, getApplicationContext());

                recyclerView.setAdapter(adapter);
        }
    }

}
