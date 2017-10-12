package fr.louisparet.journeydiaries.fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.louisparet.journeydiaries.R;
import fr.louisparet.journeydiaries.adapters.JourneyListAdapter;
import fr.louisparet.journeydiaries.databinding.JourneysFragmentBinding;
import fr.louisparet.journeydiaries.models.Journey;
import fr.louisparet.journeydiaries.service.JourneyService;

/**
 * Created by lparet on 09/10/17.
 */

public class JourneysFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        JourneysFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.journeys_fragment, container, false);
        JourneyService journeyService = JourneyService.getInstance(binding.getRoot().getContext());
        List<Journey> journeys;
        journeys = journeyService.findAll();

        binding.journeysList.setAdapter(new JourneyListAdapter(journeys));
        binding.journeysList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        return binding.getRoot();
    }
}