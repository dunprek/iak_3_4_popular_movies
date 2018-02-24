package don.com.moviesiak.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by don on 2/24/2018.
 */

public interface FavoriteDao {

    @Query("SELECT * FROM Favorite")
    LiveData<List<Favorite>> findAllFavorite();

    @Insert(onConflict = IGNORE)
    void insertFavorite(Favorite book);


}
