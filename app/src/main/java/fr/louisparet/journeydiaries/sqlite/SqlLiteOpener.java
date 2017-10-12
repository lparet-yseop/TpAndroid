package fr.louisparet.journeydiaries.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class SqlLiteOpener extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "journeyDatabase";
    private static final int DATABASE_VERSION = 1;


    // Table Names
    public static final String TABLE_JOURNEY = "journey";
    public static final String TABLE_MARKER = "marker";

    // Journey Table Columns
    public static final String KEY_JOURNEY_ID = "id";
    public static final String KEY_JOURNEY_NAME = "name";
    public static final String KEY_DATE_FROM = "from_date";
    public static final String KEY_DATE_TO = "to_date";


    // Marker Table Columns
    public static final String KEY_MARKER_ID = "id";
    public static final String KEY_MARKER_LONGITUDE = "longitude";
    public static final String KEY_MARKER_LATITUDE = "latitude";
    public static final String KEY_MARKER_NAME = "name";



    private static SqlLiteOpener sInstance;


    public static synchronized SqlLiteOpener getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new SqlLiteOpener(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private SqlLiteOpener(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLES = "CREATE TABLE " + TABLE_JOURNEY +
                "(" +
                KEY_JOURNEY_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_JOURNEY_NAME + " TEXT," +
                KEY_DATE_FROM + " INTEGER," +
                KEY_DATE_TO + " INTEGER" +
                ")";

        db.execSQL(CREATE_TABLES);

        CREATE_TABLES = "CREATE TABLE " + TABLE_MARKER +
                "(" +
                KEY_MARKER_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_MARKER_LONGITUDE + " TEXT," +
                KEY_MARKER_LATITUDE+ " TEXT," +
                KEY_MARKER_NAME + " TEXT" +
                ")";

        db.execSQL(CREATE_TABLES);

    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            String drop = "DROP TABLE IF EXISTS " + TABLE_JOURNEY + " ;";
            // db.execSQL(drop);
            drop = "DROP TABLE IF EXISTS " + TABLE_MARKER + " ;";
            // db.execSQL(drop);
            // onCreate(db);
        }
    }
}
