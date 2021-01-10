package org.mousehole.americanairline.membersonly.activity.addmember.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.mousehole.americanairline.membersonly.R;
import org.mousehole.americanairline.membersonly.activity.addmember.presenter.AddMemberPresenterContract;
import org.mousehole.americanairline.membersonly.activity.presenter.AddMemberPresenter;
import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.model.MembershipLevel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mousehole.americanairline.membersonly.util.Constants.LOG_TAG;

public class AddMemberActivity extends AppCompatActivity implements AddMemberPresenterContract.AddMemberView {

    AddMemberPresenterContract.AddMemberPresenter addMemberPresenter;

    EditText nameEditText, birthdayEditText, membershipDateEditText;
    Spinner membershipLevelSpinner, genderSpinner;
    ImageView selectImageView;
    Button addMemberButton;
    String gender = "Prefer Not to Say";
    MembershipLevel membershipLevel = MembershipLevel.REGULAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        // bindings
        nameEditText = findViewById(R.id.name_edittext);
        genderSpinner = findViewById(R.id.gender_spinner);
        birthdayEditText = findViewById(R.id.birthday_edittext);
        membershipDateEditText = findViewById(R.id.membership_date_edittext);
        membershipLevelSpinner = findViewById(R.id.memberhip_level_spinner);
        selectImageView = findViewById(R.id.select_imageview);
        addMemberButton = findViewById(R.id.add_member_button);

        addMemberPresenter = new AddMemberPresenter(this);
        displayAddMember();
    }

    @Override
    public void displayAddMember() {
        ArrayAdapter<CharSequence> membershipLevelAdapter = ArrayAdapter.createFromResource(this,
                R.array.membership_levels, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        membershipLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        membershipLevelSpinner.setAdapter(membershipLevelAdapter);
        membershipLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tview = (TextView)view;
                membershipLevel = MembershipLevel.valueOf(tview.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // nop
            }
        });

        ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        genderSpinner.setAdapter(genderSpinnerAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tview = (TextView)view;
                gender = tview.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // nop
            }
        });


        // prefill start date as today
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        membershipDateEditText.setText(dateFormat.format(new Date()));

        addMemberButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String birthday = birthdayEditText.getText().toString();
            String membershipDate = membershipDateEditText.getText().toString();
            MemberModel member = new MemberModel(name, "", gender, membershipDate, birthday, membershipLevel);
            addMemberPresenter.addMember(member);
            finish();
        });


    }

    @Override
    public Context getContext() {
        return this;
    }
}