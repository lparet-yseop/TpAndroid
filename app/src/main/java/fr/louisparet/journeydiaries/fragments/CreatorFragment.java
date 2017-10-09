package fr.louisparet.journeydiaries.fragments;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import fr.louisparet.journeydiaries.MainActivity;
import fr.louisparet.journeydiaries.R;
import fr.louisparet.journeydiaries.adapters.JourneyListAdapter;
import fr.louisparet.journeydiaries.databinding.JourneyCreatorBinding;
import fr.louisparet.journeydiaries.databinding.JourneysFragmentBinding;
import fr.louisparet.journeydiaries.models.Journey;
import fr.louisparet.journeydiaries.viewmodel.JourneyViewModel;

/**
 * Created by hugo.blanc on 09/10/17.
 */

public class CreatorFragment extends Fragment {


    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        JourneyCreatorBinding binding = DataBindingUtil.inflate(inflater, R.layout.journey_creator,container,false);
        binding.setJvm(new JourneyViewModel(new Journey()));
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context){
        this.mainActivity. = (MainActivity)context;
    }



}
