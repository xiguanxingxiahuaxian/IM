package com.sutdy.work.rongyproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.sutdy.work.rongyproject.apiService.apiService;
import com.sutdy.work.rongyproject.appConfig.config;
import com.sutdy.work.rongyproject.appConfig.interCeptor;
import com.sutdy.work.rongyproject.bean.TokenBean;
import com.sutdy.work.rongyproject.fragment.FriendFragment;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpage;
    private Fragment frag_msg;
    List<Fragment>listFragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPage();
        // initMessageCount
        //initMessageCount();
    }

    private void initMessageCount() {

    }

    private void initPage() {
        listFragment=new ArrayList<>();
        viewpage=(ViewPager)findViewById(R.id.viewpager);
        frag_msg=initConversationList();
        listFragment.add(frag_msg);
        listFragment.add(FriendFragment.getInstance());
        FragmentPagerAdapter fragmentPagerAdapter =new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }
        };
        viewpage.setAdapter(fragmentPagerAdapter);

    }

    private void initView() {
        // 获取token
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.URL).client(new OkHttpClient().newBuilder().addInterceptor(new interCeptor())
                        .build()).
                        addConverterFactory(GsonConverterFactory.create(new Gson())).build();

        Call<TokenBean> call = retrofit.create(apiService.class).getToken(
                config.ID,config.NAME,config.PORTRAITURI
        );
        call.enqueue(new Callback<TokenBean>() {
            @Override
            public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {
                if (response != null) {
                    String token = response.body().getToken();
                    // sysout token and contect follow
                    connect(token);
                }
            }

            @Override
            public void onFailure(Call<TokenBean> call, Throwable t) {

            }
        });
        // 获取连接
       /* findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RongIM.getInstance()!=null){
                    RongIM.getInstance().startPrivateChat(MainActivity.this,
                            "123456","私人聊天");
                }
            }
        });
        findViewById(R.id.juh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RongIM.getInstance()!=null){
                    RongIM.getInstance().startConversationList(MainActivity.this);
                }
            }
        });*/
    }


    private void connect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {

            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                Log.d("LoginActivity", "--onSuccess" + userid);
                // 链接后广播 注册
                Log.i("userid", userid);
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.i("errorCode", errorCode + "");
            }
        });
    }

    public void open(View view) {

    }

    public void juh(View view) {

    }


    /**
     * 初始化会话列表
     * @return
     */
    private Fragment initConversationList() {
        if (frag_msg == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            Uri uri=Uri.parse("rong://"+getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                    .build();
            listFragment.setUri(uri);
            return  listFragment;
        }else{
            return frag_msg;
        }
    }
  /*  private void initUnreadCountListener() {
        final Conversation.ConversationType[] conversationTypes = {Conversation.ConversationType.PRIVATE, Conversation.ConversationType.DISCUSSION,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE};

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RongIM.getInstance().setOnReceiveUnreadCountChangedListener(mCountListener, conversationTypes);
            }
        }, 500);
    }
    public RongIM.OnReceiveUnreadCountChangedListener mCountListener = new RongIM.OnReceiveUnreadCountChangedListener() {
        @Override
        public void onMessageIncreased(int count) {
            Log.e("tag","count:" + count);
            if (count == 0) {
                tvTabBadge.setVisibility(View.GONE);
            } else if (count > 0 && count < 100) {
                tvTabBadge.setVisibility(View.VISIBLE);
                tvTabBadge.setText(count + "");
            } else {
                tvTabBadge.setVisibility(View.VISIBLE);
                tvTabBadge.setText("99+");
            }
        }
    };*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();
    }
}


