package org.mousehole.americanairline.membersonly.presenter;

import android.content.Context;

import org.mousehole.americanairline.membersonly.model.MemberModel;

import java.util.List;

public interface ListMemberPresenterContract {
    public interface ListMemberView {
        void displayListView(List<MemberModel> members);
        Context getContext();

    }
    public interface ListMemberPresenter {
        void getAllMembers(ListMemberView listUseView);
    }
}
