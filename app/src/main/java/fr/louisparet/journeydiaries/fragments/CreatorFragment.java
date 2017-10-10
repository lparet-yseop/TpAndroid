package fr.louisparet.journeydiaries.fragments;

import android.app.Activity;
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
import fr.louisparet.journeydiaries.interaction.MainActivityPresenter;
import fr.louisparet.journeydiaries.models.Journey;
import fr.louisparet.journeydiaries.sqlite.JourneyService;
import fr.louisparet.journeydiaries.viewmodel.JourneyViewModel;

/**
 * Created by hugo.blanc on 09/10/17.
 */

public class CreatorFragment extends Fragment implements MainActivityContract.View  {


    private MainActivity mainActivity;
    private MainActivityContract.Presenter mainActivityPresenter;
    public Context context;
    public Journey journey;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        this.mainActivityPresenter = new MainActivityPresenter(this);
        JourneyCreatorBinding binding = DataBindingUtil.inflate(inflater, R.layout.journey_creator,container,false);
        context = binding.getRoot().getContext();
        Bundle args = getArguments();
        int index = 0;
        if(args != null){
            index = args.getInt("id", 0);
        }
        Journey journeyLocal = new Journey();
        JourneyService service = JourneyService.getInstance(context);
        if(index > 0){
            journeyLocal = service.findOne(index);
        }
        this.journey = journeyLocal;
        binding.setJvm(new JourneyViewModel(journeyLocal, context));
        binding.setPresenter(mainActivityPresenter);

        //this.mainActivity.goBack();
        return binding.getRoot();

    }

    @Override
    public void onAttach(Context context){

        super.onAttach(context);
        this.mainActivity = (MainActivity)context;
    }

    // API VERSION AVANT 23
    @Override
    public void onAttach(Activity activity){

        super.onAttach(activity);
        this.mainActivity = (MainActivity)activity;
    }


    @Override
    public void saveData(String name, String from, String to) {
        System.out.println(name + from +to);
        journey.setName(name);
        journey.setFrom(JourneyService.converteStringToDate(from));
        journey.setTo(JourneyService.converteStringToDate(to));
        this.mainActivity.goBack();
        JourneyService service = JourneyService.getInstance(this.context);
        if(journey.getId() > 0 )
        {
            service.update(journey);
        } else {
            service.save(journey);
        }
    }

}
