package fr.louisparet.journeydiaries.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

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
    private Context context;
    public JourneyViewModel(Journey journey) {
        this.journey = journey;
    }

    public JourneyViewModel(Journey journey, Context context) {
        this.context = context;
        this.journey = journey;
    }

    public int getId(){return journey.getId();}
    public void setId(int id){journey.setId(id);}
    public String getName() {
        return journey.getName();
    }
    public void setName(String name){ journey.setName(name);}
    public String getFrom() {
        Calendar cal = journey.getFrom();
        DateFormat sdf = SimpleDateFormat.getDateInstance(SimpleDateFormat.DATE_FIELD,
                Locale.getDefault());
        return sdf.format(cal.getTime());
    }
    public void setFrom(String from){
        System.out.println(from);
    }
    public String getTo() {
        Calendar cal = journey.getTo();
        DateFormat sdf = SimpleDateFormat.getDateInstance(SimpleDateFormat.DATE_FIELD,
                Locale.getDefault());
        return sdf.format(cal.getTime());
    }
    public void setTo(String to){
        System.out.println(to);
    }
}
