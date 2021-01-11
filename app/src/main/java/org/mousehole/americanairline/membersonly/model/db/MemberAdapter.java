package org.mousehole.americanairline.membersonly.model.db;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.mousehole.americanairline.membersonly.R;
import org.mousehole.americanairline.membersonly.model.MemberModel;
import org.mousehole.americanairline.membersonly.model.MembershipLevel;
import org.mousehole.americanairline.membersonly.util.Constants;

import java.util.List;

public class MemberAdapter extends BaseAdapter {

    private List<MemberModel> members;
    private final DisplayDelegate displayDelegate;

    public interface DisplayDelegate {
        void showMembers(MemberModel member);
    }

    public MemberAdapter(List<MemberModel> members, DisplayDelegate displayDelegate) {
        this.members = members;
        this.displayDelegate = displayDelegate;
    }

    @Override
    public int getCount() {
        return members.size();
    }

    @Override
    public MemberModel getItem(int i) {
        return members.get(i);
    }

    @Override
    public long getItemId(int i) {
        return members.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MemberModel member = members.get(i);

        Log.d(Constants.LOG_TAG, member.toString() + " displayed on " + viewGroup);

          View mainView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.member_list_item,
                            viewGroup,
                            false);

        ImageView memberImage = mainView.findViewById(R.id.member_imageview);
        TextView memberName = mainView.findViewById(R.id.member_name_textview);
        ImageView membershipLevel = mainView.findViewById(R.id.membershiplevel_imageview);

        memberName.setText(member.getName());

        membershipLevel.setImageResource(MembershipLevel.getImageReference(member.getMembershipLevel()));

        if(member.getMemberBitmap() != null) {
            Glide.with(mainView.getContext()).load(member.getMemberBitmap()).into(memberImage);
        }

        mainView.setOnClickListener(view1 -> displayDelegate.showMembers(member));

        return mainView;
    }
}
