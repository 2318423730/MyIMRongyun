package com.cmx.myimrongyun;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class ConversationListActivity extends AppCompatActivity implements RongIM.UserInfoProvider {
    private static List<Conversation> list = new ArrayList<>();
    public static List<Friend> userIdList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);

        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                Log.e("MyTAG", "111111111");

                if (conversations == null) {
                    return;
                }
                list.clear();
                list.addAll(conversations);

                userIdList.clear();
                for (int i = 0; i < list.size(); i++) {//
                    if (list.get(i).getConversationType() == Conversation.ConversationType.DISCUSSION) {
                        if (list.get(i).getTargetId().equals("d4c53d9a-542f-4aae-b9b6-977ed609276d")) {

                            MyApplication.myList.add("http://pic.4j4j.cn/upload/pic/20130617/55695c3c95.jpg");
                            userIdList.add(new Friend(list.get(i).getTargetId(), list.get(i).getConversationTitle(), "http://pic.4j4j.cn/upload/pic/20130617/55695c3c95.jpg"));
                        } else {
                            MyApplication.myList.add("http://images.cnitblog.com/i/169207/201408/112229149526951.png");
                            userIdList.add(new Friend(list.get(i).getTargetId(), list.get(i).getConversationTitle(), "http://images.cnitblog.com/i/169207/201408/112229149526951.png"));
                        }

                    } else {
                        userIdList.add(new Friend("110", "110", "http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg"));
                    }
                    //userIdList.add(new Friend("123", "123", "https://10.url.cn/eth/ajNVdqHZLLAxibwnrOxXSzIxA76ichutwMCcOpA45xjiapneMZsib7eY4wUxF6XDmL2FmZEVYsf86iaw/"));

                }

                RongIM.setUserInfoProvider(ConversationListActivity.this, true);
                RongIM instance = RongIM.getInstance();

                //异步请求结果返回后，根据返回的结果调用 refreshUserInfoCache(UserInfo) 刷新用户信息。
                for (int j = 0; j < list.size(); j++) {
                    instance.refreshUserInfoCache(getUserInfo(list.get(j).getConversationTitle()));
                }

                RongIM.getInstance().registerConversationTemplate(new MyDiscussionConversationProvider());
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MyTAG", "" + errorCode.getMessage());
            }
        });
    }

    @Override
    public UserInfo getUserInfo(String s) {
        //使用for循环从集合中取出数据,提供给融云自动加载用户昵称及头像
        for (Friend i : userIdList) {
            if (i.getUserId().equals(s)) {

                return new UserInfo(i.getUserId(), i.getUserName(), Uri.parse(i.getPortraitUri()));
            }
        }
        return null;
    }
}
