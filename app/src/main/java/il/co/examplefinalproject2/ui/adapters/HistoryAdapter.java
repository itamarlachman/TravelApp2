package il.co.examplefinalproject2.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import il.co.examplefinalproject2.R;
import il.co.examplefinalproject2.models.HistoryTravel;


public class HistoryAdapter extends  RecyclerView.Adapter<HistoryAdapter.TravelViewHolder> {
    protected List<HistoryTravel> travels;

    public List<HistoryTravel> getTravels() {
        return travels;
    }
    public void setTravels(List<HistoryTravel> travels) {
        this.travels = travels;
    }

    public class TravelViewHolder extends RecyclerView.ViewHolder{
        TextView date,name,source,destination;
        View view;
        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);
            source = itemView.findViewById(R.id.source);
            destination = itemView.findViewById(R.id.destination);
        }
    }

    public HistoryAdapter(ArrayList<HistoryTravel> travels) {
        this.travels = travels;
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_travel, parent, false);
        TravelViewHolder holder = new TravelViewHolder((view));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TravelViewHolder holder, int position) {
        if (travels == null)
            return;
        HistoryTravel travel = travels.get(position);
        holder.date.setText(travel.date);
        holder.name.setText(travel.customer);
        holder.source.setText(travel.source);
        holder.destination.setText(travel.destination);
    }

    @Override
    public int getItemCount() {
        if (travels == null)
            return 0;
        else
            return travels.size();
    }
}
