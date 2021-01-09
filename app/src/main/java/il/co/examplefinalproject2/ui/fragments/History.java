package il.co.examplefinalproject2.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import il.co.examplefinalproject2.R;
import il.co.examplefinalproject2.ui.viewmodels.HistoryViewModel;

public class History extends Fragment {

    private HistoryViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =  ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.history, container, false);
        return root;
    }
}