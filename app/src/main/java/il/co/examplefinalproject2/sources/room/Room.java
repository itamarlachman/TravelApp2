package il.co.examplefinalproject2.sources.room;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import il.co.examplefinalproject2.interfaces.DataResult;
import il.co.examplefinalproject2.interfaces.IDataSource;
import il.co.examplefinalproject2.models.Company;
import il.co.examplefinalproject2.models.Customer;
import il.co.examplefinalproject2.models.HistoryTravel;
import il.co.examplefinalproject2.models.Travel;
import il.co.examplefinalproject2.utils.Globals;

public class Room implements IDataSource {
    RoomRepository roomRepository = null;
    Application application = null;;
    Context context = null;;
    MutableLiveData<DataResult> data =  new MutableLiveData<DataResult>();

    @Override
    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public void setContext(Context context) { this.context = context; }

    @Override
    public void saveTravel(Travel travel) {
        try {
            if (roomRepository == null)
                roomRepository = new RoomRepository(this.context.getApplicationContext());

            HistoryTravel historyTravel = new HistoryTravel();
            historyTravel.key = travel.getKey();
            historyTravel.date = travel.getDateString();
            historyTravel.customer = travel.getCustomer().getFullName();
            historyTravel.source = travel.getSource().getAddress();
            historyTravel.destination = travel.getDestination().getAddress();
            historyTravel.distance = travel.getDistance();
            roomRepository.insertHistory(historyTravel);
        } catch (Exception ex) {
            Log.d(Globals.TAG,"Error saving history travel, see error: " + ex.getMessage());
        }
    }

    @Override
    public void getTravels(Customer customer) {
        // NOT USED

    }

    @Override
    public void getNewTravels() {
        // NOT USED.
    }

    @Override
    public LiveData<List<HistoryTravel>> getHistory() {
        if (roomRepository == null)
            roomRepository = new RoomRepository(this.context.getApplicationContext());

       return roomRepository.getHistoryItems();
    }

    @Override
    public void getMyTravels(Company company) {
        // NOT USED.
    }

    @Override
    public MutableLiveData<DataResult> result() {
        return data;
    }


}
