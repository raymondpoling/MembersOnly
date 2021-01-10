package org.mousehole.americanairline.membersonly.activity.listdisplay.presenter;

import android.content.Context;

import org.mousehole.americanairline.membersonly.model.MemberModel;

import java.util.List;

public interface ListMemberPresenterContract {
    interface ListMemberView {
        void displayListView(List<MemberModel> members);
        Context getContext();

    }
    interface ListMemberPresenter {
        void getAllMembers(ListMemberView listUseView);
    }
}
