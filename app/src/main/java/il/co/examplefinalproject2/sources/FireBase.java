package il.co.examplefinalproject2.sources;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import il.co.examplefinalproject2.interfaces.DataResult;
import il.co.examplefinalproject2.interfaces.IDataSource;
import il.co.examplefinalproject2.models.Customer;
import il.co.examplefinalproject2.models.Travel;


public class FireBase implements IDataSource {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference travels = db.getReference("travels");
    MutableLiveData<DataResult> data =  new MutableLiveData<DataResult>();

    @Override
    public void saveTravel(Travel travel) {
        travels.child(travel.getKey())
                .setValue(travel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        DataResult res = new DataResult();
                        res.setEntity(DataResult.Entity.Travels);
                        res.setOperation(DataResult.Operation.Add);
                        res.setResult(true);
                        data.postValue(res);
                    }
                });

    }

    public void lastAccess(Date date) {
        db.getReference("lastAccess").setValue(date);
    }

    @Override
    public void getTravels(final Customer customer) {
        travels.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Travel> travelsData = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Travel travel = child.getValue(Travel.class);
                    if (customer == null)
                        travelsData.add(travel);
                    else if(travel.getCustomer().getFullName().equals(customer.getFullName())){
                        travelsData.add(travel);
                    }
                }

                DataResult res = new DataResult();
                res.setEntity(DataResult.Entity.Travels);
                res.setOperation(DataResult.Operation.Select);
                res.setResult(travelsData);
                data.postValue(res);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public MutableLiveData<DataResult> result() {
        return data;
    }

}
