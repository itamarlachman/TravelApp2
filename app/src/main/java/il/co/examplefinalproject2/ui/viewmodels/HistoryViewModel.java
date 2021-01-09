package il.co.examplefinalproject2.ui.viewmodels;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import il.co.examplefinalproject2.interfaces.IDataSource;
import il.co.examplefinalproject2.models.HistoryTravel;
import il.co.examplefinalproject2.sources.Repository;

public class HistoryViewModel extends ViewModel {

    public IDataSource get_room() {
        return _room;
    }

    protected IDataSource _room =  Repository.getDataSource(Repository.Type.Room);
    protected MutableLiveData<List<HistoryTravel>> liveData = new MutableLiveData<>();

    public MutableLiveData<List<HistoryTravel>> getLiveData() {
        return liveData;
    }
    public HistoryViewModel() {}


    public LiveData<List<HistoryTravel>> getHistory() {
        return _room.getHistory();
    }


}