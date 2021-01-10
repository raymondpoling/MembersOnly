package org.mousehole.americanairline.membersonly.activity.addmember.presenter;

import android.content.Context;

import org.mousehole.americanairline.membersonly.model.MemberModel;

public interface AddMemberPresenterContract {
    interface AddMemberView {
        void displayAddMember();
        Context getContext();
    }
    interface AddMemberPresenter {
        void addMember(MemberModel member);
    }
}
