package org.mousehole.americanairline.membersonly.activity.presenter;

import org.mousehole.americanairline.membersonly.activity.addmember.presenter.AddMemberPresenterContract;
import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.model.db.MemberDBHelper;

public class AddMemberPresenter implements AddMemberPresenterContract.AddMemberPresenter {

    private MemberDBHelper memberDBHelper;
    private AddMemberPresenterContract.AddMemberView addMemberView;

    public AddMemberPresenter(AddMemberPresenterContract.AddMemberView addMemberView) {
        this.addMemberView = addMemberView;
        memberDBHelper = new MemberDBHelper(addMemberView.getContext());
    }

    @Override
    public void addMember(MemberModel member) {
        memberDBHelper.addMember(member);
    }
}
