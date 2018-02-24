package com.cmx.myimrongyun;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.TypingMessage.TypingStatus;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

public class ChatActivity extends FragmentActivity implements RongIM.UserInfoProvider, RongIM.IGroupMembersProvider {
    private List<Friend> userIdList;
    private TextView tv_title;
    Conversation.ConversationType mConversationType;
    String mTargetId;
    private static final int SET_TEXT_TYPING_TITLE = 1;
    private static final int SET_VOICE_TYPING_TITLE = 2;
    private static final int SET_TARGETID_TITLE = 3;
    String title;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_TEXT_TYPING_TITLE:
                    tv_title.setText("对方正在输入");
                    break;
                case SET_VOICE_TYPING_TITLE:
                    tv_title.setText("对方正在讲话");
                    break;
                case SET_TARGETID_TITLE:
                    tv_title.setText(title);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tv_title = (TextView) findViewById(R.id.tv_title);
        title = getIntent().getData().getQueryParameter("title");
        tv_title.setText(title);

        mTargetId = getIntent().getData().getQueryParameter("targetId");
        mConversationType = Conversation.ConversationType.valueOf(getIntent().getData()
                .getLastPathSegment().toUpperCase(Locale.US));

        userIdList = new ArrayList();
        userIdList.add(new Friend("123", "123", "https://10.url.cn/eth/ajNVdqHZLLAxibwnrOxXSzIxA76ichutwMCcOpA45xjiapneMZsib7eY4wUxF6XDmL2FmZEVYsf86iaw/"));
        userIdList.add(new Friend("110", "110", "http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg"));


        RongIM.setUserInfoProvider(this, true);
        RongIM instance = RongIM.getInstance();

        //异步请求结果返回后，根据返回的结果调用 refreshUserInfoCache(UserInfo) 刷新用户信息。
        instance.refreshUserInfoCache(getUserInfo("123"));
        instance.refreshUserInfoCache(getUserInfo("110"));
        RongIMClient.setTypingStatusListener(new RongIMClient.TypingStatusListener() {
            @Override
            public void onTypingStatusChanged(Conversation.ConversationType type, String targetId, Collection<TypingStatus> typingStatusSet) {
                //当输入状态的会话类型和targetID与当前会话一致时，才需要显示
                if (type.equals(mConversationType) && targetId.equals(mTargetId)) {
                    //count表示当前会话中正在输入的用户数量，目前只支持单聊，所以判断大于0就可以给予显示了
                    int count = typingStatusSet.size();
                    if (count > 0) {
                        Iterator iterator = typingStatusSet.iterator();
                        TypingStatus status = (TypingStatus) iterator.next();
                        String objectName = status.getTypingContentType();

                        MessageTag textTag = TextMessage.class.getAnnotation(MessageTag.class);
                        MessageTag voiceTag = VoiceMessage.class.getAnnotation(MessageTag.class);
                        //匹配对方正在输入的是文本消息还是语音消息
                        if (objectName.equals(textTag.value())) {
                            //显示“对方正在输入”
                            mHandler.sendEmptyMessage(SET_TEXT_TYPING_TITLE);
                        } else if (objectName.equals(voiceTag.value())) {
                            //显示"对方正在讲话"
                            mHandler.sendEmptyMessage(SET_VOICE_TYPING_TITLE);
                        }
                    } else {
                        //当前会话没有用户正在输入，标题栏仍显示原来标题
                        mHandler.sendEmptyMessage(SET_TARGETID_TITLE);
                    }
                }
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

    @Override
    public void getGroupMembers(String s, RongIM.IGroupMemberCallback iGroupMemberCallback) {

    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}



