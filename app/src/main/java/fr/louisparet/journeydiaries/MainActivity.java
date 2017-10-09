package fr.louisparet.journeydiaries;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.Serializable;

import fr.louisparet.journeydiaries.databinding.MainActivityBinding;
import fr.louisparet.journeydiaries.fragments.CreatorFragment;
import fr.louisparet.journeydiaries.fragments.JourneysFragment;
import fr.louisparet.journeydiaries.models.Journey;

/**
 * Created by lparet on 09/10/17.
 */

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.main_activity);
        this.showStartup();
    }

    public void showStartup() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        JourneysFragment fragment = new JourneysFragment();

        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    public void onJourneyClick(View v) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CreatorFragment fragment = new CreatorFragment();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }


    public void reload(Journey journey){
        Bundle bundle = new Bundle();
        bundle.putSerializable("journey", journey);
        //fragment.setArguments(bundle);
    }

}
