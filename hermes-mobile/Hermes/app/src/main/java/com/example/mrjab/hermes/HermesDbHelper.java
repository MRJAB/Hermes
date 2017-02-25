package com.example.mrjab.hermes;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by yatharth on 24/02/17.
 */

public class HermesDbHelper extends SQLiteOpenHelper {

    public int f =1;

    private static final String SQL_CREATE_CHAT="CREATE TABLE `tbl_chat` (" +
            "`ChatID` bigint(20) NOT NULL PRIMARY KEY," +
            "`fk_InitUserID_by` bigint(20) DEFAULT NULL," +
            "`fk_InitUserID_with` bigint(20) DEFAULT NULL," +
            "`KeyValue` varchar(255) DEFAULT NULL," +
            "`CreateDate` datetime DEFAULT NULL," +
            "FOREIGN KEY (`fk_InitUserID_by`) REFERENCES `tbl_user` (`UserID`)," +
            "FOREIGN KEY (`fk_InitUserID_with`) REFERENCES `tbl_user` (`UserID`))";


    private static final String SQL_CREATE_MESSAGE=
                    "CREATE TABLE `tbl_message` (" +
                    "`MessageID` bigint(20) NOT NULL PRIMARY KEY," +
                    "`fk_SenderUserID` bigint(20) DEFAULT NULL," +
                    "`fk_ChatID` bigint(20) DEFAULT NULL," +
                    "`Content` varchar(8000) DEFAULT ''," +
                    "`RecieveDateTime` datetime DEFAULT NULL," +
                    "FOREIGN KEY(`fk_ChatID`) REFERENCES `tbl_chat` (`ChatID`)," +
                    "FOREIGN KEY(`fk_SenderUserID`) REFERENCES `tbl_user` (`UserID`))";
    private static final String SQL_CREATE_USER=
                    "CREATE TABLE `tbl_user` (`UserID` bigint(20) NOT NULL PRIMARY KEY," +
                    "`Username` varchar(50) NOT NULL," +
                    "`Password` varchar(50) NOT NULL," +
                    "`Email` varchar(255) DEFAULT NULL," +
                    "`Name` varchar(80) DEFAULT ''," +
                    "`Status` INTEGER DEFAULT 0," +
                    "`CreateDate` datetime DEFAULT NULL ," +
                    "`ModifyDate` datetime DEFAULT NULL)";
    private static final String SQL_DELETE_ENTRIES ="";
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Hermes.db";

    public HermesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String newTable = "CREATE TABLE 'NewTable' ('SNo' INTEGER PRIMARY KEY)";
        db.execSQL("DROP TABLE IF EXISTS `tbl_message`");
        db.execSQL("DROP TABLE IF EXISTS `tbl_user`");
        db.execSQL("DROP TABLE IF EXISTS `tbl_chat`");
        db.execSQL(SQL_CREATE_CHAT);
        db.execSQL(SQL_CREATE_MESSAGE);
        db.execSQL(SQL_CREATE_USER);
        f=0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS `tbl_message`");
        db.execSQL("DROP TABLE IF EXISTS `tbl_user`");
        db.execSQL("DROP TABLE IF EXISTS `tbl_chat`");
        onCreate(db);
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}