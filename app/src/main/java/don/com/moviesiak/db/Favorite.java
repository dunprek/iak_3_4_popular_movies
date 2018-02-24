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

    @NonNull
    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(@NonNull int movie_id) {
        this.movie_id = movie_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}