package il.co.examplefinalproject2.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import il.co.examplefinalproject2.R;
import il.co.examplefinalproject2.interfaces.DataResult;
import il.co.examplefinalproject2.interfaces.IClickEvent;
import il.co.examplefinalproject2.models.Travel;
import il.co.examplefinalproject2.ui.adapters.TravelsAdapter;
import il.co.examplefinalproject2.ui.viewmodels.OpenTravelsViewModel;
import il.co.examplefinalproject2.utils.DialogUtils;
import il.co.examplefinalproject2.utils.Globals;

public class OpenTravels extends Fragment {

    private OpenTravelsViewModel viewModel;
    protected TravelsAdapter travelsAdapter;
    protected RecyclerView listTravels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.open_travels, container, false);
        listTravels = root.findViewById(R.id.listTravels);
        listTravels.setLayoutManager(new LinearLayoutManager(getContext()));

        travelsAdapter = new TravelsAdapter(null);
        listTravels.setAdapter(travelsAdapter);

        viewModel =  ViewModelProviders.of(this).get(OpenTravelsViewModel.class);
        viewModel.getNewTravels();
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<DataResult>() {
            @Override
            public void onChanged(DataResult dataResult) {
                try {
                    if (dataResult.getOperation().equals(DataResult.Operation.NewTravels)) {
                        final ArrayList<Travel> travels = ( ArrayList<Travel>) dataResult.getResult();
                        travelsAdapter.setTravels(travels);
                        travelsAdapter.setClickEvent(new IClickEvent() {
                            @Override
                            public void addOnClickListener(Object result) {
                                Travel travel =  (Travel) result;
                                travel.setCompany(Globals.company);
                                travel.setStatus(Travel.Status.Selected);
                                viewModel.saveTravel(travel);
                            }
                        });
                        travelsAdapter.notifyDataSetChanged();
                    }
                } catch (Exception ex) {
                    Log.d(Globals.TAG,"Error in loading new travels, see error: " + ex.getMessage());
                }

            }
        });

        return root;
    }
}