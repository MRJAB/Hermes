package com.example.mrjab.hermes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yatharth on 24/02/17.
 */

public class HermesDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES ="\n" +
            "SET FOREIGN_KEY_CHECKS=0;\n" +
            "\n" +
            "-- ----------------------------\n" +
            "-- Table structure for tbl_chat\n" +
            "-- ----------------------------\n" +
            "DROP TABLE IF EXISTS `tbl_chat`;\n" +
            "CREATE TABLE `tbl_chat` (\n" +
            "  `ChatID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n" +
            "  `fk_InitUserID_by` bigint(20) unsigned DEFAULT NULL,\n" +
            "  `fk_InitUserID_with` bigint(20) unsigned DEFAULT NULL,\n" +
            "  `KeyValue` varchar(255) DEFAULT NULL,\n" +
            "  `CreateDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,\n" +
            "  PRIMARY KEY (`ChatID`),\n" +
            "  KEY `initUser` (`fk_InitUserID_by`),\n" +
            "  KEY `othrUser` (`fk_InitUserID_with`),\n" +
            "  CONSTRAINT `initUser` FOREIGN KEY (`fk_InitUserID_by`) REFERENCES `tbl_user` (`UserID`),\n" +
            "  CONSTRAINT `othrUser` FOREIGN KEY (`fk_InitUserID_with`) REFERENCES `tbl_user` (`UserID`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
            "\n" +
            "-- ----------------------------\n" +
            "-- Table structure for tbl_message\n" +
            "-- ----------------------------\n" +
            "DROP TABLE IF EXISTS `tbl_message`;\n" +
            "CREATE TABLE `tbl_message` (\n" +
            "  `MessageID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n" +
            "  `fk_SenderUserID` bigint(20) unsigned DEFAULT NULL,\n" +
            "  `fk_ChatID` bigint(20) unsigned DEFAULT NULL,\n" +
            "  `Content` varchar(8000) DEFAULT '',\n" +
            "  `RecieveDateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,\n" +
            "  PRIMARY KEY (`MessageID`),\n" +
            "  KEY `sndrUser` (`fk_SenderUserID`),\n" +
            "  KEY `rltdChat` (`fk_ChatID`),\n" +
            "  CONSTRAINT `rltdChat` FOREIGN KEY (`fk_ChatID`) REFERENCES `tbl_chat` (`ChatID`),\n" +
            "  CONSTRAINT `sndrUser` FOREIGN KEY (`fk_SenderUserID`) REFERENCES `tbl_user` (`UserID`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
            "\n" +
            "-- ----------------------------\n" +
            "-- Table structure for tbl_user\n" +
            "-- ----------------------------\n" +
            "DROP TABLE IF EXISTS `tbl_user`;\n" +
            "CREATE TABLE `tbl_user` (\n" +
            "  `UserID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n" +
            "  `Username` varchar(50) NOT NULL,\n" +
            "  `Password` char(50) NOT NULL,\n" +
            "  `Email` varchar(255) DEFAULT NULL,\n" +
            "  `Name` varchar(80) DEFAULT '',\n" +
            "  `Status` bit(1) DEFAULT b'1',\n" +
            "  `CreateDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,\n" +
            "  `ModifyDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,\n" +
            "  PRIMARY KEY (`UserID`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
    private static final String SQL_DELETE_ENTRIES ="";
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Hermes.db";

    public HermesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}