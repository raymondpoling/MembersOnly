package org.mousehole.americanairline.membersonly.activity.memberdisplay.presenter;

import android.content.Context;

import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.model.db.MemberDBHelper;

public class MemberDisplayPresenter implements MemberDisplayContract.MemberDisplayPresenter {

    MemberDBHelper memberDBHelper;

    public MemberDisplayPresenter(Context context) {
        memberDBHelper = new MemberDBHelper(context);
    }

    @Override
    public void deleteMember(MemberModel member) {
        memberDBHelper.deleteMember(member);
    }
}
