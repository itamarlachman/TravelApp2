package il.co.examplefinalproject2.sources;
import androidx.lifecycle.MutableLiveData;

import il.co.examplefinalproject2.interfaces.DataResult;
import il.co.examplefinalproject2.interfaces.IDataSource;
import il.co.examplefinalproject2.models.Customer;
import il.co.examplefinalproject2.models.Travel;


public class Room implements IDataSource {
    @Override
    public void saveTravel(Travel travel) {
        // TODO: implement method.
    }

    @Override
    public void getTravels(Customer customer) {
        // TODO: implement method.
    }

    @Override
    public MutableLiveData<DataResult> result() {
        // TODO: implement method.
        return null;
    }

}
