package org.mousehole.americanairline.membersonly.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.model.MembershipLevel;
import org.mousehole.americanairline.membersonly.util.Constants;

import java.lang.reflect.Member;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mousehole.americanairline.membersonly.util.Constants.LOG_TAG;


public class MemberDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "membership_db";
    public static final String TABLE_NAME = "membership_table";
    public static final String MEMBERID_COLUMN = "memberid_column";
    public static final String NAME_COLUMN = "name_column";
    public static final String GENDER_COLUMN = "gender_column";
    public static final String BIRTHDAY_COLUMN = "birthday_column";
    public static final String MEMBERSHIP_DATE_COLUMN = "membership_date_column";
    public static final String MEMBERSHIPLEVEL_COLUMN = "membershiplevel_column";
    public static final String IMAGE_COLUMN = "image_column";

    private static int DB_VERSION = 1;

    public MemberDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                MEMBERID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COLUMN + " TEXT, " +
                IMAGE_COLUMN + " TEXT, " +
                GENDER_COLUMN + " TEXT, " +
                BIRTHDAY_COLUMN + " TEXT, " +
                MEMBERSHIP_DATE_COLUMN + " TEXT, " +
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
        contentValues.put(BIRTHDAY_COLUMN, member.getBirthday());
        contentValues.put(MEMBERSHIP_DATE_COLUMN, member.getMembership());
        contentValues.put(MEMBERSHIPLEVEL_COLUMN, member.getMembershipLevel().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getWritableDatabase().insert(TABLE_NAME, null, contentValues);

                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage(), e);

                }
            }
        }).start();

    }

    private String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }

    public List<MemberModel> getAllMembers() {
        getWritableDatabase();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.move(-1);
        List<MemberModel> members = new ArrayList<>(cursor.getCount());
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MEMBERID_COLUMN));
            String name = getString(cursor, NAME_COLUMN);
            String imagePath = getString(cursor, IMAGE_COLUMN);
            String gender = getString(cursor, GENDER_COLUMN);
            String birthday = getString(cursor,BIRTHDAY_COLUMN);
            String membershipDate = getString(cursor,MEMBERSHIP_DATE_COLUMN);
            MembershipLevel membershipLevel = MembershipLevel.valueOf(cursor.getString(cursor.getColumnIndex(MEMBERSHIPLEVEL_COLUMN)));

            MemberModel member = new MemberModel(id, name, imagePath, gender, birthday, membershipDate, membershipLevel);
            members.add(member);
        }
        return members;
    }

    public void deleteMember(MemberModel member) {
        Log.d(LOG_TAG, "Trying to delete: " + member.toString());
        getWritableDatabase().delete(TABLE_NAME, MEMBERID_COLUMN + " = " + member.getId(), null);
    }
}
