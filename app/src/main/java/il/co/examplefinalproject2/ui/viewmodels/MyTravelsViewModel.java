package il.co.examplefinalproject2.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import il.co.examplefinalproject2.interfaces.DataResult;
import il.co.examplefinalproject2.interfaces.IDataSource;
import il.co.examplefinalproject2.models.Company;
import il.co.examplefinalproject2.models.Travel;
import il.co.examplefinalproject2.sources.Repository;

public class MyTravelsViewModel extends ViewModel {
    protected IDataSource _firebase =  Repository.getDataSource(Repository.Type.FireBase);
    protected IDataSource _room =  Repository.getDataSource(Repository.Type.Room);
    public IDataSource get_room() {
        return _room;
    }

    public MutableLiveData<DataResult> getLiveData() {
        return _firebase.result();
    }
    public MyTravelsViewModel() {}

    public void getMyTravels(Company company) {
        _firebase.getMyTravels(company);
    }

    public void saveTravel(Travel travel) {
        _firebase.saveTravel(travel);
    }

    public void saveHistoryTravel(Travel travel) {
        _room.saveTravel(travel);
    }
}