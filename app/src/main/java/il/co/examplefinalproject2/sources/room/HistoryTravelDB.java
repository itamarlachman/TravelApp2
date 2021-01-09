package il.co.examplefinalproject2.sources.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import il.co.examplefinalproject2.models.HistoryTravel;


@Database(entities = {HistoryTravel.class}, version = 1, exportSchema = false)
public abstract class HistoryTravelDB extends RoomDatabase {
    public abstract HistoryTravelDAO historyTravelDAO();
    private static volatile HistoryTravelDB INSTANCE;
    public static HistoryTravelDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HistoryTravelDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HistoryTravelDB.class, "history")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
