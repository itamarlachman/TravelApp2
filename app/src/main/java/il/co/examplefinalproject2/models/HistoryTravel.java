package il.co.examplefinalproject2.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "historyTravels")
public class HistoryTravel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "key")
    public String key;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "customer")
    public String customer;
    @ColumnInfo(name = "source")
    public String source;
    @ColumnInfo(name = "dest")
    public String destination;
    @ColumnInfo(name = "distance")
    public double distance;
}
