package org.mousehole.americanairline.membersonly.model;

import java.util.Date;

public class MemberModel {
    private int id;
    private String name;
    private String Gender;
    private Date Membership;
    private Date birthday;
    private MembershipLevel membershipLevel;

    /**
     * This constructor is used for building objects from the database. See other constructor for parameters
     * @param id The database id of the record
     */
    public MemberModel(int id, String name, String gender, Date membership, Date birthday, MembershipLevel membershipLevel) {
        this.id = id;
        this.name = name;
        Gender = gender;
        Membership = membership;
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
    public MemberModel(String name, String gender, Date membership, Date birthday, MembershipLevel membershipLevel) {
        this.id = id;
        this.name = name;
        Gender = gender;
        Membership = membership;
        this.birthday = birthday;
        this.membershipLevel = membershipLevel;
    }

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
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getMembership() {
        return Membership;
    }

    public void setMembership(Date membership) {
        Membership = membership;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }
}
