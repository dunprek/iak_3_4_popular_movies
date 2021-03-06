package don.com.moviesiak.db.utils;

import android.support.annotation.NonNull;

import don.com.moviesiak.db.AppDatabase;
import don.com.moviesiak.db.Favorite;

/**
 * Created by don on 2/24/2018.
 */

public class DbInitializer {
    // Simulate a blocking operation delaying each Loan insertion with a delay:
    private static final int DELAY_MILLIS = 500;



    public static Favorite addFavorite(final AppDatabase db, final int movie_id, final String image_url) {
        Favorite favorite = new Favorite();
        favorite.movie_id = movie_id;
        favorite.image_url = image_url;
        db.favoriteModel().insertFavorite(favorite);
        return favorite;
    }

    public static Favorite deleteFavoriteItem(final AppDatabase db, final int movie_id, final String image_url) {
        Favorite favorite = new Favorite();
        favorite.movie_id = movie_id;
        favorite.image_url = image_url;
        db.favoriteModel().deleteByID(favorite);
        return favorite;
    }


    public static String getAllData(AppDatabase db){
        return db.favoriteModel().findAllFavorite().toString();
    }

}
