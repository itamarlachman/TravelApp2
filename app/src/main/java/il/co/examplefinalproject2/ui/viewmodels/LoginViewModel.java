package il.co.examplefinalproject2.ui.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import il.co.examplefinalproject2.interfaces.DataResult;
import il.co.examplefinalproject2.models.Company;
import il.co.examplefinalproject2.utils.Globals;

public class LoginViewModel extends ViewModel {

    protected FirebaseAuth auth = FirebaseAuth.getInstance();
    protected MutableLiveData<DataResult> liveData = new MutableLiveData<>();
    public MutableLiveData<DataResult> getLiveData() {
        return liveData;
    }

    public void Register(final Company company) {
        Globals.company = company;
        auth.createUserWithEmailAndPassword(company.getEmail(), company.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(Globals.TAG, "createUserWithEmailAndPassword:success");
                            Globals.user = auth.getCurrentUser();
                            Globals.company.setName(Globals.user.getEmail());
                            liveData.postValue(new DataResult(
                                    DataResult.Operation.Register,
                                    DataResult.Entity.Companies,
                                    true,
                                    Globals.company,
                                    null));

                        } else {
                            Log.d(Globals.TAG, "createUserWithEmailAndPassword:failure", task.getException());
                            liveData.postValue(new DataResult(
                                    DataResult.Operation.Register,
                                    DataResult.Entity.Companies,
                                    false,
                                    null,
                                    task.getException()));
                        }
                    }
                });
    }

    public void SignIn(final Company customer) {
        auth.signInWithEmailAndPassword(customer.getEmail(),customer.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(Globals.TAG, "signInWithEmailAndPassword:success");
                            Globals.user = auth.getCurrentUser();
                            customer.setName(Globals.user.getDisplayName());
                            liveData.postValue(new DataResult(DataResult.Operation.Login,
                                    DataResult.Entity.Companies,
                                    true,
                                     Globals.user,
                                    null));
                        } else {
                            Log.d(Globals.TAG, "signInWithEmailAndPassword:failure", task.getException() );
                            liveData.postValue(new DataResult(DataResult.Operation.Login,
                                    DataResult.Entity.Companies,
                                    false,
                                    null,
                                    task.getException()));
                        }
                    }
                });

    }
}
