package il.co.examplefinalproject2.sources.room;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import il.co.examplefinalproject2.models.HistoryTravel;

public class RoomRepository extends Application {
    HistoryTravelDB database = null;
    HistoryTravelDAO dao = null;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =  Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public  RoomRepository(Context app)
    {
        database = HistoryTravelDB.getDatabase(app);
        dao = database.historyTravelDAO();
    }

    public void insertHistory(final HistoryTravel item)
    {
        databaseWriteExecutor.execute(new Runnable() {
            public void run() {
                dao.insert(item);
            }
        });
    }

    public LiveData<List<HistoryTravel>> getHistoryItems() {
        return dao.all();
    }

}
