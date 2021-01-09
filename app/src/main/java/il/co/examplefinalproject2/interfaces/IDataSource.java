package il.co.examplefinalproject2.interfaces;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import il.co.examplefinalproject2.models.Company;
import il.co.examplefinalproject2.models.Customer;
import il.co.examplefinalproject2.models.HistoryTravel;
import il.co.examplefinalproject2.models.Travel;

public interface IDataSource {
    void setApplication(Application application);
    void setContext(Context context);
    void saveTravel(Travel travel);
    void getTravels(Customer customer);
    void getNewTravels();
    LiveData<List<HistoryTravel>> getHistory();
    void getMyTravels(Company company);
    MutableLiveData<DataResult> result();

}
