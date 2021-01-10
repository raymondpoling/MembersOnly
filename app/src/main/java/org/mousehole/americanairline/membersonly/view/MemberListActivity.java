package org.mousehole.americanairline.membersonly.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.mousehole.americanairline.membersonly.R;
import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.presenter.ListMemberPresenter;
import org.mousehole.americanairline.membersonly.presenter.ListMemberPresenterContract;

import java.util.List;

public class MemberListActivity extends AppCompatActivity implements ListMemberPresenterContract.ListMemberView {

    private ListMemberPresenterContract.ListMemberPresenter listMemberPresenter;

    public ListView memberListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        memberListView = findViewById(R.id.member_listview);

        listMemberPresenter = new ListMemberPresenter(this);

        listMemberPresenter.getAllMembers(this);
    }

    @Override
    public void displayListView(List<MemberModel> members) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }
}