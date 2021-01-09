package il.co.examplefinalproject2.sources;

import il.co.examplefinalproject2.interfaces.IDataSource;
import il.co.examplefinalproject2.sources.firebase.FireBase;
import il.co.examplefinalproject2.sources.room.Room;

public class Repository {
    public static FireBase fireBase = new FireBase();
    public static Room room = new Room();
    public enum Type{
        FireBase,
        Room
    }
    public static IDataSource getDataSource(Type type) {
        switch (type) {
            case FireBase:
                return fireBase;
            case Room:
                return room;
        }
        return  fireBase;
    }
}
