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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import il.co.examplefinalproject2.R;
import il.co.examplefinalproject2.models.HistoryTravel;
import il.co.examplefinalproject2.ui.adapters.HistoryAdapter;
import il.co.examplefinalproject2.ui.viewmodels.HistoryViewModel;

import il.co.examplefinalproject2.utils.Globals;

public class History extends Fragment {

    private List<HistoryTravel> travels = new ArrayList<>();
    private HistoryViewModel viewModel;
    protected HistoryAdapter travelsAdapter;
    protected RecyclerView listTravels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =  ViewModelProviders.of(this).get(HistoryViewModel.class);
        viewModel.get_room().setContext(getContext());
        View root = inflater.inflate(R.layout.history, container, false);
        listTravels = root.findViewById(R.id.listTravels);
        listTravels.setLayoutManager(new LinearLayoutManager(getContext()));
        travelsAdapter = new HistoryAdapter(null);
        listTravels.setAdapter(travelsAdapter);

        viewModel.getHistory().observe(getViewLifecycleOwner(), new Observer<List<HistoryTravel>>() {
                    @Override
                    public void onChanged(List<HistoryTravel> historyTravels) {
                        travelsAdapter.setTravels(historyTravels);
                        travelsAdapter.notifyDataSetChanged();
                    }
                });


       return root;
    }
}