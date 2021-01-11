package org.mousehole.americanairline.membersonly.activity.listdisplay.presenter;

import org.mousehole.americanairline.membersonly.model.db.MemberDBHelper;

public class ListMemberPresenter implements ListMemberPresenterContract.ListMemberPresenter {

    MemberDBHelper memberDBHelper;

    public ListMemberPresenter(ListMemberPresenterContract.ListMemberView listMemberView) {
        memberDBHelper = new MemberDBHelper(listMemberView.getContext());
    }

    @Override
    public void getAllMembers(ListMemberPresenterContract.ListMemberView listUseView) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                listUseView.displayListView(memberDBHelper.getAllMembers());
            }
        }).start();
    }
}
