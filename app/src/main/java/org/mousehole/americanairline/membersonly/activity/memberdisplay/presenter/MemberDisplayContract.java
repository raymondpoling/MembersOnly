package org.mousehole.americanairline.membersonly.activity.memberdisplay.presenter;

import org.mousehole.americanairline.membersonly.model.MemberModel;

public interface MemberDisplayContract {
    interface MemberDisplayView {
        void displayMember(MemberModel member);
    }
    interface MemberDisplayPresenter {
        void deleteMember(MemberModel member);
    }
}
