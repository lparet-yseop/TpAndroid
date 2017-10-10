package fr.louisparet.journeydiaries;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import fr.louisparet.journeydiaries.databinding.MainActivityBinding;
import fr.louisparet.journeydiaries.fragments.CreatorFragment;
import fr.louisparet.journeydiaries.fragments.GeolocFragment;
import fr.louisparet.journeydiaries.fragments.JourneysFragment;
import fr.louisparet.journeydiaries.interaction.MainActivityContract;
import fr.louisparet.journeydiaries.interaction.MainActivityPresenter;
import fr.louisparet.journeydiaries.models.Journey;
import fr.louisparet.journeydiaries.permission.PermissionHelper;

/**
 * Created by lparet on 09/10/17.
 */

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private MainActivityBinding binding;

    public static final String TAG = "MainActivity";

    public MainActivity() {

    }

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

//        Bundle bundle = new Bundle();
//        bundle.putSerializable("presenter", this.mainActivityPresenter);
//        fragment.setArguments(bundle);

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
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    public void onMapGeoloc(View v){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        GeolocFragment fragment = new GeolocFragment();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }



    public void getLocation(View v){
        PermissionHelper permissionHelper = new PermissionHelper();

        permissionHelper.askPermission(this);

    }


    public void goBack(){
        showStartup();
    }



    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

//        if (requestCode == REQUEST_CAMERA) {
//            // BEGIN_INCLUDE(permission_result)
//            // Received permission result for camera permission.
//            Log.i(TAG, "Received response for Camera permission request.");
//
//            // Check if the only required permission has been granted
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Camera permission has been granted, preview can be displayed
//                Log.i(TAG, "CAMERA permission has now been granted. Showing preview.");
//                Snackbar.make(mLayout, R.string.permision_available_camera,
//                        Snackbar.LENGTH_SHORT).show();
//            } else {
//                Log.i(TAG, "CAMERA permission was NOT granted.");
//                Snackbar.make(mLayout, R.string.permissions_not_granted,
//                        Snackbar.LENGTH_SHORT).show();
//
//            }
//            // END_INCLUDE(permission_result)
//
//        } else if (requestCode == REQUEST_CONTACTS) {
//            Log.i(TAG, "Received response for contact permissions request.");
//
//            // We have requested multiple permissions for contacts, so all of them need to be
//            // checked.
//            if (PermissionUtil.verifyPermissions(grantResults)) {
//                // All required permissions have been granted, display contacts fragment.
//                Snackbar.make(mLayout, R.string.permision_available_contacts,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
//            } else {
//                Log.i(TAG, "Contacts permissions were NOT granted.");
//                Snackbar.make(mLayout, R.string.permissions_not_granted,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
//            }
//
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
    }


}
