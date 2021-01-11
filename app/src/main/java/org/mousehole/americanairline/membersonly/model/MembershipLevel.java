package org.mousehole.americanairline.membersonly.model;

import org.mousehole.americanairline.membersonly.R;

public enum MembershipLevel {
    REGULAR,
    GOLD,
    PLATINUM;

    public static int getImageReference(MembershipLevel membershipLevel) {
        int resId = R.drawable.ic_regular_medallion;
        switch(membershipLevel) {
            case GOLD: resId = R.drawable.ic_gold_medallion; break;
            case REGULAR:
                break;
            case PLATINUM: resId = R.drawable.ic_platinum_medallion; break;
        }
        return resId;
    }
}
