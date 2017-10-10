package fr.louisparet.journeydiaries.fragments;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.louisparet.journeydiaries.MainActivity;
import fr.louisparet.journeydiaries.R;
import fr.louisparet.journeydiaries.databinding.JourneyCreatorBinding;
import fr.louisparet.journeydiaries.interaction.MainActivityContract;
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
        binding.setJvm(new JourneyViewModel(new Journey(), binding.getRoot().getContext()));
        binding.setPresenter((MainActivityContract.Presenter)getArguments().getSerializable("presenter"));
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context){
        this.mainActivity = (MainActivity)context;
    }





}
