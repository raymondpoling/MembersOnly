package org.mousehole.americanairline.membersonly.presenter;

import android.content.Context;

import org.mousehole.americanairline.membersonly.model.MemberModel;

public interface AddMemberPresenterContract {
    public interface AddMemberView {
        void displayAddMember();
        Context getContext();
    }
    public interface AddMemberPresenter {
        void addMember(MemberModel member);
    }
}
