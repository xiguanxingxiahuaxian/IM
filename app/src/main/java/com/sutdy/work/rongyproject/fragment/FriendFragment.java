package com.sutdy.work.rongyproject.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sutdy.work.rongyproject.R;

import io.rong.imkit.RongIM;

/**
 * 项目名称：RongYProject
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/6/14 11:08
 * 修改备注
 */
public class FriendFragment extends Fragment {

    private static FriendFragment mfriendfragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frient,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        getActivity().findViewById(R.id.friend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RongIM.getInstance()!=null){
                    RongIM.getInstance().startPrivateChat(getActivity(),
                            "123456","私人聊天");
                }
            }
        });
    }

    // 建立单例
    public static FriendFragment getInstance() {
        if (mfriendfragment == null) {
            mfriendfragment = new FriendFragment();
        }
        return mfriendfragment;
    }

}
