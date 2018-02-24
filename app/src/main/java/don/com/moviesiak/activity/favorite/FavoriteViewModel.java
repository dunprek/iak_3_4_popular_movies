package don.com.moviesiak.activity.favorite;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import don.com.moviesiak.db.AppDatabase;
import don.com.moviesiak.db.Favorite;
import don.com.moviesiak.db.utils.DbInitializer;

/**
 * Created by gideon on 24/02/18.
 */

public class FavoriteViewModel extends AndroidViewModel {

    LiveData<List<Favorite>> mFavorites;
    private AppDatabase mDb;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        createDB();
    }

    public void createDB(){
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        DbInitializer.getAllData(mDb);

        subcribeToDbChanges();
    }

    public LiveData<List<Favorite>> getListFavorite(){
        return  mFavorites;
    }

    private void subcribeToDbChanges(){
        mFavorites = mDb.favoriteModel().findAllFavorite();
    }
}
