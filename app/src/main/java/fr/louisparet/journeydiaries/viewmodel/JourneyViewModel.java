package fr.louisparet.journeydiaries.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import fr.louisparet.journeydiaries.models.Journey;
import fr.louisparet.journeydiaries.sqlite.JourneyService;

/**
 * Created by hugo.blanc on 09/10/17.
 */

public class JourneyViewModel extends BaseObservable {
    private Journey journey;
    private Context context;
    public JourneyViewModel(Journey journey) {
        this.journey = journey;
    }

    public JourneyViewModel(Journey journey, Context context) {
        this.context = context;
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

        System.out.println(name + from +to);
        this.journey.setName(name);
        this.journey.setFrom(JourneyService.converteStringToDate(from));
        this.journey.setTo(JourneyService.converteStringToDate(to));

        JourneyService service = JourneyService.getInstance(this.context);
        service.save(this.journey);
    }


    public void saveJourney() {

        System.out.println("OnClick "+getName());
    }





}
