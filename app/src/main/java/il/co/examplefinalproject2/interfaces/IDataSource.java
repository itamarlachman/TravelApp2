package il.co.examplefinalproject2.interfaces;

import androidx.lifecycle.MutableLiveData;

import il.co.examplefinalproject2.models.Company;
import il.co.examplefinalproject2.models.Customer;
import il.co.examplefinalproject2.models.Travel;

public interface IDataSource {
    void saveTravel(Travel travel);
    void getTravels(Customer customer);
    void getNewTravels();
    void getMyTravels(Company company);
    MutableLiveData<DataResult> result();

}
