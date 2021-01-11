package org.mousehole.americanairline.membersonly.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import static org.mousehole.americanairline.membersonly.util.Constants.LOG_TAG;

public class MemberModel implements Parcelable {
    private Bitmap memberBitmap;
    private int id;
    private String name;
    private String gender;
    private String membership;
    private String birthday;
    private MembershipLevel membershipLevel;

    /**
     * This constructor is used for building objects from the database. See other constructor for parameters
     * @param id The database id of the record
     */
    public MemberModel(int id, String name, Bitmap memberBitmap, String gender, String membership, String birthday, MembershipLevel membershipLevel) {
        this.id = id;
        this.name = name;
        this.memberBitmap = memberBitmap;
        this.gender = gender;
        this.membership = membership;
        this.birthday = birthday;
        this.membershipLevel = membershipLevel;
    }

    /**
     * This is the constructor for general use. Do not set your own id: that's for database use only
     * @param name member name
     * @param gender self identified gender
     * @param membership When the person joined, to determine how long a member for retainment programs, etc
     * @param birthday date of birth for promotions (age related programs, gift certificate, etc)
     * @param membershipLevel the level of membership for special benefits
     */
    public MemberModel(String name, Bitmap memberBitmap, String gender, String membership, String birthday, MembershipLevel membershipLevel) {
        this.name = name;
        this.memberBitmap = memberBitmap;
        this.gender = gender;
        this.membership = membership;
        this.birthday = birthday;
        this.membershipLevel = membershipLevel;
    }

    protected MemberModel(Parcel in) {
        memberBitmap = (Bitmap) in.readValue(getClass().getClassLoader());
        String ml = in.readString();
        Log.d(LOG_TAG, "What is the in string? " + membershipLevel);
        membershipLevel = MembershipLevel.valueOf(ml);
        id = in.readInt();
        name = in.readString();
        gender = in.readString();
        membership = in.readString();
        birthday = in.readString();
    }

    public static final Creator<MemberModel> CREATOR = new Creator<MemberModel>() {
        @Override
        public MemberModel createFromParcel(Parcel in) {
            return new MemberModel(in);
        }

        @Override
        public MemberModel[] newArray(int size) {
            return new MemberModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public Bitmap getMemberBitmap() {
        return memberBitmap;
    }

    public void setMemberBitmap(Bitmap memberBitmap) {
        this.memberBitmap = memberBitmap;
    }

    @Override
    public String toString() {
        return String.format("%d - %s - %s - %s - %s - %s ", id, name, gender, birthday, membership, membershipLevel);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.d(LOG_TAG, "What is the enum out? " + membershipLevel.toString());
        parcel.writeValue(memberBitmap);
        parcel.writeString(membershipLevel.toString());
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(gender);
        parcel.writeString(membership);
        parcel.writeString(birthday);
    }
}
