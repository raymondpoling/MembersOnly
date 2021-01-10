package org.mousehole.americanairline.membersonly.activity.listdisplay.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.mousehole.americanairline.membersonly.R;
import org.mousehole.americanairline.membersonly.activity.addmember.view.AddMemberActivity;
import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.model.db.MemberAdapter;
import org.mousehole.americanairline.membersonly.activity.listdisplay.presenter.ListMemberPresenter;
import org.mousehole.americanairline.membersonly.activity.listdisplay.presenter.ListMemberPresenterContract;
import org.mousehole.americanairline.membersonly.activity.memberdisplay.view.MemberDisplayActivity;

import java.util.List;

public class MemberListActivity extends AppCompatActivity implements ListMemberPresenterContract.ListMemberView, MemberAdapter.DisplayDelegate {

    private ListMemberPresenterContract.ListMemberPresenter listMemberPresenter;

    public ListView memberListView;
    public Button addMemberButton;

    public static final String DISPLAY_MEMBER = "display_member";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        memberListView = findViewById(R.id.member_listview);
        addMemberButton = findViewById(R.id.add_member_button);

        listMemberPresenter = new ListMemberPresenter(this);

        addMemberButton.setOnClickListener(v -> {
            Intent addMemberIntent = new Intent(this, AddMemberActivity.class);
            startActivity(addMemberIntent);
        });

        listMemberPresenter.getAllMembers(this);
    }

    @Override
    public void displayListView(List<MemberModel> members) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MemberAdapter memberAdapter = new MemberAdapter(members, MemberListActivity.this);
                memberListView.setAdapter(memberAdapter);
            }
        });
    }

    // Let's just assume the view changes while the activity is not displayed.
    @Override
    protected void onResume() {
        super.onResume();
        listMemberPresenter.getAllMembers(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMembers(MemberModel member) {
        Intent showMember = new Intent(this, MemberDisplayActivity.class);
        showMember.putExtra(DISPLAY_MEMBER, member);
        startActivity(showMember);
    }
}