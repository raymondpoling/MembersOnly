package org.mousehole.americanairline.membersonly.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.util.Constants;

import java.lang.reflect.Member;


public class MemberDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "membership_db";
    public static final String TABLE_NAME = "membership_table";
    public static final String NAME_COLUMN = "name_column";
    public static final String GENDER_COLUMN = "gender_column";
    public static final String BIRTHDAY_COLUMN = "birthday_column";
    public static final String MEMBERSHIP_DATE_COLUMN = "membership_date_column";
    public static final String MEMBERSHIPLEVEL_COLUMN = "membershiplevel_column";

    private static int DB_VERSION = 1;

    public MemberDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                NAME_COLUMN + " TEXT, " +
                GENDER_COLUMN + " TEXT, " +
                BIRTHDAY_COLUMN + " DATE, " +
                MEMBERSHIP_DATE_COLUMN + " DATE, " +
                MEMBERSHIPLEVEL_COLUMN + " TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        DB_VERSION = DB_VERSION + 1;
    }

    public void addMember(MemberModel member) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN, member.getName());
        contentValues.put(GENDER_COLUMN, member.getGender());
        contentValues.put(BIRTHDAY_COLUMN, member.getBirthday().toString());
        contentValues.put(MEMBERSHIP_DATE_COLUMN, member.getMembership().toString());
        contentValues.put(MEMBERSHIPLEVEL_COLUMN, member.getMembershipLevel().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getWritableDatabase().insert(TABLE_NAME, null, contentValues);

                } catch (Exception e) {
                    Log.e(Constants.LOG_TAG, e.getMessage(), e);

                }
            }
        }).start();

    }
}
