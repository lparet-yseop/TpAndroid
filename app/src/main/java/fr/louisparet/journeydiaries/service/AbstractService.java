package fr.louisparet.journeydiaries.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import fr.louisparet.journeydiaries.models.Journey;
import fr.louisparet.journeydiaries.sqlite.SqlLiteOpener;

/**
 * Created by hugo.blanc on 11/10/17.
 */

public abstract class AbstractService<T> {

    SqlLiteOpener sqlLiteOpener;

    protected AbstractService(Context context) {
        this.sqlLiteOpener = SqlLiteOpener.getInstance(context);
    }

    protected List<T> findAll(String table){
        SQLiteDatabase db = this.sqlLiteOpener.getWritableDatabase();
        db.beginTransaction();
        List<T> objects = new ArrayList<>();
        T object;
        Cursor cursor = db.rawQuery("SELECT * FROM " + table, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    object = convertCursorToType(cursor);
                    objects.add(object);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("SQL ", "Error pour selectionner");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return objects;
    }

    protected T findOne(Long id, String table){
        SQLiteDatabase db = this.sqlLiteOpener.getWritableDatabase();
        db.beginTransaction();
        T object = null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + table + " WHERE id = '"+id+"';", null);
        try {
            if (cursor.moveToFirst()) {
                object = convertCursorToType(cursor);
            }
        } catch (Exception e) {
            Log.d("SQL ", "Error pour selectionner");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return object;
    }


    protected long save(ContentValues contentValue, String table){
        SQLiteDatabase db = this.sqlLiteOpener.getWritableDatabase();
        long objectId = -1;

        db.beginTransaction();
        try {
            objectId = db.insert(table, null, contentValue);
            if (objectId > 0) {
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d("SQL","Erreur pour ajouter ");
        } finally {
            db.endTransaction();
        }
        return objectId;
    }



    protected long update(ContentValues contentValues, Long id, String table){
        SQLiteDatabase db = this.sqlLiteOpener.getWritableDatabase();
        long nbLines = -1;
        db.beginTransaction();
        try {
            String[] array = {id+""};
            nbLines = db.update(table, contentValues, " id = ? ", array);
            if (nbLines > 0) {
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d("SQL","Erreur pour ajouter ");
        } finally {
            db.endTransaction();
        }
        return nbLines;
    }






    protected abstract T convertCursorToType(Cursor cursor);
}
