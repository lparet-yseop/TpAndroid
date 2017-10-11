package fr.louisparet.journeydiaries.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import fr.louisparet.journeydiaries.models.Marker;
import fr.louisparet.journeydiaries.sqlite.SqlLiteOpener;

/**
 * Created by hugo.blanc on 11/10/17.
 */

public class MarkerService extends AbstractService<Marker> {


    public MarkerService(Context context) {
        super(context);
    }

    public List<Marker> findAll(){
        return super.findAll(SqlLiteOpener.TABLE_MARKER);
    }

    public Marker findOne(Long id){
        return super.findOne(id, SqlLiteOpener.TABLE_MARKER);
    }


    public Marker update(Marker marker){
        Long lines = super.update(createContenValue(marker), marker.getId(), SqlLiteOpener.TABLE_MARKER);
        if(lines > 0) {
            return marker;
        } else {
            return null;
        }
    }


    public Marker save(Marker marker){
        marker.setId(super.save(createContenValue(marker), SqlLiteOpener.TABLE_MARKER));
        return marker;
    }

    private ContentValues createContenValue(Marker marker){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqlLiteOpener.KEY_MARKER_LATITUDE, marker.getLatitude().toString());
        contentValues.put(SqlLiteOpener.KEY_MARKER_LONGITUDE, marker.getLongitude().toString());
        contentValues.put(SqlLiteOpener.KEY_MARKER_NAME, marker.getName());
        return contentValues;
    }



    @Override
    protected Marker convertCursorToType(Cursor cursor) {
        Marker marker = new Marker();
        marker.setId(cursor.getLong(cursor.getColumnIndex(SqlLiteOpener.KEY_MARKER_ID)));
        marker.setName(cursor.getString(cursor.getColumnIndex(SqlLiteOpener.KEY_MARKER_NAME)));
        marker.setLatitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(SqlLiteOpener.KEY_MARKER_LATITUDE))));
        marker.setLongitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(SqlLiteOpener.KEY_MARKER_LONGITUDE))));
        return marker;
    }
}
