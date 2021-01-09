package il.co.examplefinalproject2.ui.viewmodels;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import il.co.examplefinalproject2.interfaces.DataResult;
import il.co.examplefinalproject2.interfaces.IDataSource;
import il.co.examplefinalproject2.models.Travel;
import il.co.examplefinalproject2.sources.Repository;

public class OpenTravelsViewModel extends ViewModel {

    protected IDataSource _dataSource =  Repository.getDataSource(Repository.Type.FireBase);
    public MutableLiveData<DataResult> getLiveData() {
        return _dataSource.result();
    }
    public OpenTravelsViewModel() {
    }

    public void getNewTravels() {
        _dataSource.getNewTravels();
    }

    public void saveTravel(Travel travel) {
        _dataSource.saveTravel(travel);
    }
}