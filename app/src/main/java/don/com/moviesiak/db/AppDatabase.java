package don.com.moviesiak.db;

import android.arch.core.executor.DefaultTaskExecutor;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by don on 2/24/2018.
 */

@Database(entities = {Favorite.class}, version = 1)


public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract FavoriteDao favoriteModel();

    public static final String DATABASE_NAME = "basic-sample-db";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();


    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
//                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }





    public static void destroyInstance() {
        INSTANCE = null;
    }

}


