package fr.louisparet.journeydiaries.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fr.louisparet.journeydiaries.models.Journey;

/**
 * Created by hugo.blanc on 09/10/17.
 */

public class JourneyViewModel extends BaseObservable {
    private Journey journey;
    public JourneyViewModel(Journey journey) {
        this.journey = journey;
    }

    public String getName() {
        return journey.getName();
    }
    public void setName(String name){ journey.setName(name);}
    public String getFrom() {
        Calendar cal = journey.getFrom();
        DateFormat sdf = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM,
                Locale.getDefault());
        return sdf.format(cal.getTime());
    }
    public void setFrom(String from){
        System.out.println(from);
    }
    public String getTo() {
        Calendar cal = journey.getTo();
        DateFormat sdf = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM,
                Locale.getDefault());
        return sdf.format(cal.getTime());
    }
    public void setTo(String to){
        System.out.println(to);
    }


    public void saveJourney(String name, String from, String to ) {


    }


    public void saveJourney(View v) {

        System.out.println("OnClick "+getName());
    }



}
