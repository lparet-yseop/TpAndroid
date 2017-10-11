package fr.louisparet.journeydiaries.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.louisparet.journeydiaries.models.Journey;
import fr.louisparet.journeydiaries.sqlite.SqlLiteOpener;

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class JourneyService {
    private SqlLiteOpener sqlLiteOpener;
    private static JourneyService sInstance;


    public static synchronized JourneyService getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new JourneyService(context.getApplicationContext());
        }
        return sInstance;
    }


    private JourneyService(Context context) {
        this.sqlLiteOpener = SqlLiteOpener.getInstance(context);
    }



    public Journey save(Journey journey){
        SQLiteDatabase db = this.sqlLiteOpener.getWritableDatabase();
        long journeyId = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SqlLiteOpener.KEY_JOURNEY_NAME, journey.getName());
            values.put(SqlLiteOpener.KEY_DATE_FROM, journey.getFrom().getTimeInMillis()/1000);
            values.put(SqlLiteOpener.KEY_DATE_TO, journey.getTo().getTimeInMillis()/1000);

            journeyId = db.insert(SqlLiteOpener.TABLE_JOURNEY, null, values);

            if (journeyId > 0) {
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d("SQL","Erreur pour ajouter ");
        } finally {
            db.endTransaction();
        }
        if(journeyId > 0){
            return journey;
        } else {
            return null;
        }
    }


    public Journey update(Journey journey){

        SQLiteDatabase db = this.sqlLiteOpener.getWritableDatabase();
        long nbLines = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SqlLiteOpener.KEY_JOURNEY_ID, journey.getId());
            values.put(SqlLiteOpener.KEY_JOURNEY_NAME, journey.getName());
            values.put(SqlLiteOpener.KEY_DATE_FROM, journey.getFrom().getTimeInMillis()/1000);
            values.put(SqlLiteOpener.KEY_DATE_TO, journey.getTo().getTimeInMillis()/1000);

            String[] array = {journey.getId()+""};
            nbLines = db.update(SqlLiteOpener.TABLE_JOURNEY, values, " id = ? ", array);

            if (nbLines > 0) {
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d("SQL","Erreur pour ajouter ");
        } finally {
            db.endTransaction();
        }
        if(nbLines > 0){
            return journey;
        } else {
            return null;
        }

    }





    public List<Journey> findAll(){
        SQLiteDatabase db = this.sqlLiteOpener.getWritableDatabase();
        db.beginTransaction();
        List<Journey> journeys = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SqlLiteOpener.TABLE_JOURNEY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Journey journey = new Journey();
                    journey.setId(cursor.getInt(cursor.getColumnIndex(SqlLiteOpener.KEY_JOURNEY_ID)));
                    journey.setName(cursor.getString(cursor.getColumnIndex(SqlLiteOpener.KEY_JOURNEY_NAME)));
                    journey.setFrom(convertStrTimestampToCalendar(cursor.getString(cursor.getColumnIndex(SqlLiteOpener.KEY_DATE_FROM))));
                    journey.setTo(convertStrTimestampToCalendar(cursor.getString(cursor.getColumnIndex(SqlLiteOpener.KEY_DATE_TO))));
                    journeys.add(journey);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("SQL - Journey", "Error pour selectionner");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return journeys;
    }


    public Journey findOne(int id){
        SQLiteDatabase db = this.sqlLiteOpener.getWritableDatabase();
        db.beginTransaction();
        Journey journey = new Journey();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SqlLiteOpener.TABLE_JOURNEY + " WHERE id = '"+id+"';", null);
        try {
            if (cursor.moveToFirst()) {
                    journey.setId(cursor.getInt(cursor.getColumnIndex(SqlLiteOpener.KEY_JOURNEY_ID)));
                    journey.setName(cursor.getString(cursor.getColumnIndex(SqlLiteOpener.KEY_JOURNEY_NAME)));
                    journey.setFrom(convertStrTimestampToCalendar(cursor.getString(cursor.getColumnIndex(SqlLiteOpener.KEY_DATE_FROM))));
                    journey.setTo(convertStrTimestampToCalendar(cursor.getString(cursor.getColumnIndex(SqlLiteOpener.KEY_DATE_TO))));

            }
        } catch (Exception e) {
            Log.d("SQL - Journey", "Error pour selectionner");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return journey;
    }





    public static Calendar convertStrTimestampToCalendar(String timestamp){
        long timestampLong = Long.parseLong(timestamp)*1000;
        Date d = new Date(timestampLong);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c;
    }

    public static Calendar converteStringToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date convertedDate = new java.util.Date();
        Calendar cal = null;
        try {
            convertedDate = dateFormat.parse(dateString);
            cal=Calendar.getInstance();
            cal.setTime(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(convertedDate);
        return cal;
    }


}
