package fr.louisparet.journeydiaries.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import fr.louisparet.journeydiaries.R;
import fr.louisparet.journeydiaries.databinding.JourneyItemBinding;
import fr.louisparet.journeydiaries.models.Journey;

/**
 * Created by lparet on 09/10/17.
 */

public class JourneyListAdapter extends RecyclerView.Adapter<JourneyListAdapter.BindingHolder>
{
    public List<Journey> journeys;

    public JourneyListAdapter(List<Journey> journeys) {
        this.journeys = journeys;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        JourneyItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.journey_item,parent,false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(JourneyListAdapter.BindingHolder holder, int position) {
        JourneyItemBinding binding = holder.binding;
        Journey journey = journeys.get(position);
        binding.name.setText(journey.getName());
        Calendar cal = journey.getFrom();
        DateFormat sdf =
                SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM,
                        Locale.getDefault());
        binding.startDate.setText(sdf.format(cal.getTime()));
        cal = journey.getTo();
        binding.endDate.setText(sdf.format(cal.getTime()));
    }

    @Override
    public int getItemCount() {
        return journeys.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private JourneyItemBinding binding;
        BindingHolder(JourneyItemBinding binding) {
            super(binding.journeyItem);
            this.binding = binding;
        }
    }
}
