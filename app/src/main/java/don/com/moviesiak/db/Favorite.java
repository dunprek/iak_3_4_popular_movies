package don.com.moviesiak.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by don on 2/24/2018.
 */

@Entity
public class Favorite {
    @PrimaryKey
    @NonNull
    public int movie_id;

    public String image_url;
}