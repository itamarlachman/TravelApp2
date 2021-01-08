package il.co.examplefinalproject2.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OpenTravelsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OpenTravelsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is open_travels fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}