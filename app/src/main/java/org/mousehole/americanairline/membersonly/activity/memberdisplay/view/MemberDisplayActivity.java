package org.mousehole.americanairline.membersonly.activity.memberdisplay.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.mousehole.americanairline.membersonly.R;
import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.activity.memberdisplay.presenter.MemberDisplayContract;
import org.mousehole.americanairline.membersonly.activity.memberdisplay.presenter.MemberDisplayPresenter;
import org.mousehole.americanairline.membersonly.util.Constants;

import static org.mousehole.americanairline.membersonly.activity.listdisplay.view.MemberListActivity.DISPLAY_MEMBER;

public class MemberDisplayActivity extends AppCompatActivity implements MemberDisplayContract.MemberDisplayView {

    TextView nameTextView, genderTextView, birthdayTextView, membershipDateTextView, memberShipLevelTextView;

    ImageView memberImage;

    Button backButton, deleteButton;

    MemberDisplayContract.MemberDisplayPresenter memberDisplayPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_display);

        nameTextView = findViewById(R.id.member_name_textview);
        genderTextView = findViewById(R.id.gender_textview);
        birthdayTextView = findViewById(R.id.birthday_textview);
        membershipDateTextView = findViewById(R.id.membership_date_textview);
        memberShipLevelTextView = findViewById(R.id.membershiplevel_textview);
        memberImage = findViewById(R.id.icon_imageview);

        backButton = findViewById(R.id.back_button);
        deleteButton = findViewById(R.id.delete_button);

        memberDisplayPresenter = new MemberDisplayPresenter(this);

        Intent toDisplay = getIntent();
        if(toDisplay != null) {
            MemberModel model = toDisplay.getParcelableExtra(DISPLAY_MEMBER);
            if(model == null) {
                finish();
                return;
            }
            displayMember(model);
        }
    }

    @Override
    public void displayMember(MemberModel member) {
        nameTextView.setText(member.getName());
        genderTextView.setText(member.getGender());
        birthdayTextView.setText(member.getBirthday());
        membershipDateTextView.setText(member.getMembership());
        memberImage.setImageBitmap(member.getMemberBitmap());

        deleteButton.setOnClickListener(v -> {
            memberDisplayPresenter.deleteMember(member);
            finish();
        });
        backButton.setOnClickListener(v -> {
            finish();
            Log.d(Constants.LOG_TAG, "Member display was clicked.");
        });
    }
}