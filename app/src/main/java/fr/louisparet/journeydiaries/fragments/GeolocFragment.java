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
import fr.louisparet.journeydiaries.databinding.GeolocBinding;
import fr.louisparet.journeydiaries.interaction.MainActivityContract;
import fr.louisparet.journeydiaries.interaction.MainActivityPresenter;
import fr.louisparet.journeydiaries.permission.PermissionHelper;

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class GeolocFragment extends Fragment {

    Context context;

    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        GeolocBinding binding = DataBindingUtil.inflate(inflater, R.layout.geoloc,container,false);
        this.context = binding.getRoot().getContext();

        //binding.setJvm(new JourneyViewModel(journeyLocal, context));


        //this.mainActivity.goBack();
        return binding.getRoot();
        //return null;

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


    public void getLocation(View v){
        PermissionHelper permissionHelper = new PermissionHelper();

        permissionHelper.askPermission(this.mainActivity);

    }


}
