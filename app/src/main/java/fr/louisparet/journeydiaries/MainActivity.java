package fr.louisparet.journeydiaries;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fr.louisparet.journeydiaries.databinding.MainActivityBinding;
import fr.louisparet.journeydiaries.fragments.CreatorFragment;
import fr.louisparet.journeydiaries.fragments.GeolocFragment;
import fr.louisparet.journeydiaries.fragments.JourneysFragment;
import fr.louisparet.journeydiaries.permission.PermissionHelper;

/**
 * Created by lparet on 09/10/17.
 */

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.main_activity);
        this.showStartup();
        setContentView(R.layout.main_activity);
    }

    public MainActivity() {

    }

    public void showStartup() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        JourneysFragment fragment = new JourneysFragment();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    public void onJourneyClick(View v) {
        String tag = null;
        if(v.getTag() != null){
            tag = v.getTag().toString();
        }
        int id = 0;
        if(tag != null){
            id = Integer.parseInt(tag);
        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CreatorFragment fragment = new CreatorFragment();
        if(id > 0){
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            fragment.setArguments(bundle);
        }
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    public void onMapGeoloc(View v){
        PermissionHelper permissionHelper = new PermissionHelper();
        GeolocFragment fragment = new GeolocFragment();
        permissionHelper.askPermission(fragment, this);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goBack(){
        showStartup();
    }
}
