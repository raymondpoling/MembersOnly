package org.mousehole.americanairline.membersonly.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.mousehole.americanairline.membersonly.R;
import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.model.MembershipLevel;
import org.mousehole.americanairline.membersonly.presenter.MemberDisplayContract;

public class MemberDisplayActivity extends AppCompatActivity implements MemberDisplayContract.MemberDisplayView {

    TextView nameTextView, genderTextView, birthdayTextView, membershipDateTextView, memberShipLevelTextView;

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

        backButton = findViewById(R.id.back_button);
        deleteButton = findViewById(R.id.delete_button);
    }

    @Override
    public void displayMember(MemberModel member) {
        nameTextView.setText(member.getName());
        genderTextView.setText(member.getGender());
        birthdayTextView.setText(member.getBirthday().toString());
        membershipDateTextView.setText(member.getMembership().toString());

        deleteButton.setOnClickListener(v -> {
            memberDisplayPresenter.deleteMember(member);
            finish();
        });
        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}