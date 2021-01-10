package org.mousehole.americanairline.membersonly.presenter;

import org.mousehole.americanairline.membersonly.model.MemberModel;

public interface MemberDisplayContract {
    public interface MemberDisplayView {
        void displayMember(MemberModel member);
    }
    public interface MemberDisplayPresenter {
        void deleteMember(MemberModel member);
    }
}
