package don.com.moviesiak.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by don on 2/24/2018.
 */
@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM Favorite")
    LiveData<List<Favorite>> findAllFavorite();

    @Insert(onConflict = REPLACE)
    void insertFavorite(Favorite book);


}
