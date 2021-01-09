package il.co.examplefinalproject2.sources.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import il.co.examplefinalproject2.models.HistoryTravel;

@Dao
public interface HistoryTravelDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(HistoryTravel historyTravel);
    @Query("SELECT * FROM historyTravels ORDER BY date ASC")
    LiveData<List<HistoryTravel>> all();
}
