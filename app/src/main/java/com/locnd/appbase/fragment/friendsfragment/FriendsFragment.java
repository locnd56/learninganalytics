package com.locnd.appbase.fragment.friendsfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.locnd.appbase.R;
import com.locnd.appbase.abstracts.AbstractFragment;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class FriendsFragment extends AbstractFragment {
    TextView tv_content;
    FriendsBusiness friendsBusiness;

    @Override
    public void onCreate(Bundle savedInstanceState

    ) {
        super.onCreate(savedInstanceState);
        friendsBusiness = new FriendsBusiness(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        showToast(getMainActivity(), "FriendsFragment created");
        friendsBusiness.callGetData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
//        tv_content.setText(getString(R.string.title_friends));
    }

    private void initView(View view) {
        tv_content = (TextView) view.findViewById(R.id.tv_fragmentfriends_content);
    }

    public void setContent(String content) {
        tv_content.setText(content);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_friends);
    }
}
