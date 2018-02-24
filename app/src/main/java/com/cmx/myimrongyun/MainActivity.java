package com.cmx.myimrongyun;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cmx.myimrongyun.bean.UnReadMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.IHistoryDataResultCallback;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imkit.plugin.DefaultLocationPlugin;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.ImagePlugin;
import io.rong.imkit.widget.provider.FilePlugin;
import io.rong.imlib.NativeObject;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Message;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity implements IUnReadMessageObserver {

    private TextView textView;
    final Conversation.ConversationType[] conversationTypes = {
            Conversation.ConversationType.PRIVATE,
            Conversation.ConversationType.DISCUSSION
            //Conversation.ConversationType.GROUP,
            //Conversation.ConversationType.SYSTEM,
            //Conversation.ConversationType.PUBLIC_SERVICE,
            //Conversation.ConversationType.APP_PUBLIC_SERVICE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        // 动态权限
        PermissionGen.with(MainActivity.this)
                .addRequestCode(0)
                .permissions(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CONTACTS)
                //Manifest.permission.GET_ACCOUNTS
                .request();

/*
        List<IPluginModule> pluginModuleList = new ArrayList<>();
        IPluginModule image = new ImagePlugin();
        IPluginModule locationPlugin = new DefaultLocationPlugin();
        FilePlugin filePlugin=new FilePlugin();
        pluginModuleList.add(image);
        pluginModuleList.add(locationPlugin);
        pluginModuleList.add(filePlugin);*/
        //123 QJstRIE5J0Hlc6RaIgkXFYUAkFPHx0mHK5OfUsFNWCteSk+JIM0QEC9criq/mEyfW8PF2oxen7R48dbxQLmykw==
        //110 FObgEZb6gXMT6q3Mw2/+xoUAkFPHx0mHK5OfUsFNWCsjskAg3yfyIbLVjvxae76N2vAVFymw2Yh48dbxQLmykw==

        //
        connect("FObgEZb6gXMT6q3Mw2/+xoUAkFPHx0mHK5OfUsFNWCsjskAg3yfyIbLVjvxae76N2vAVFymw2Yh48dbxQLmykw==");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * 启动单聊,先判断RongIM对象不为null
                 * context - 应用上下文。
                 * targetUserId - 要与之聊天的用户 Id，也就是上面注册的token(用户)
                 * title - 聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                 */
//                if (RongIM.getInstance() != null) {
//                    RongIM.getInstance().startPrivateChat(MainActivity.this, "110", "110");
//                    //RongIM.getInstance().startPrivateChat(MainActivity.this, "123", "123");
//                }
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(MainActivity.this, "123", "123");
                    //RongIM.getInstance().startPrivateChat(MainActivity.this, "123", "123");
                }

//                List<String> list = new ArrayList<>();
//                list.add("123");
//                list.add("110");
//                RongIM.getInstance().createDiscussion("test讨论组3", list, new RongIMClient.CreateDiscussionCallback() {
//                    @Override
//                    public void onSuccess(String s) {
//                        Log.e("MyTAG",s);
////                        if (RongIM.getInstance() != null) {
////                            Map<String, Boolean> map = new HashMap<>();
////                            map.put(Conversation.ConversationType.PRIVATE.getName(), false);
////                            map.put(Conversation.ConversationType.DISCUSSION.getName(), true);
////                            map.put(Conversation.ConversationType.CHATROOM.getName(), false);
////                            map.put(Conversation.ConversationType.CUSTOMER_SERVICE.getName(), false);
////                            map.put(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), false);
////                            map.put(Conversation.ConversationType.PUSH_SERVICE.getName(), true);
////                            map.put(Conversation.ConversationType.PUBLIC_SERVICE.getName(), false);
////                            map.put(Conversation.ConversationType.SYSTEM.getName(), true);
////                            RongIM.getInstance().startConversationList(MainActivity.this, map);
////                        }
//                    }
//
//                    @Override
//                    public void onError(RongIMClient.ErrorCode errorCode) {
//                        Log.e("MyTAG",errorCode.getMessage());
//                    }
//                });

                // Map<String, Boolean> map = new HashMap<>();
                //map.put(Conversation.ConversationType.DISCUSSION.getName(), true);
                //RongIM.getInstance().startSubConversationList(MainActivity.this, Conversation.ConversationType.DISCUSSION);
                //RongIM.getInstance().startDiscussionChat(MainActivity.this, "70adbc35-a0de-48a9-babe-3cb7fe2696eb", "我的讨论组");


/*                Map<String, Boolean> map = new HashMap<>();
                           map.put(Conversation.ConversationType.PRIVATE.getName(), false);
                            map.put(Conversation.ConversationType.DISCUSSION.getName(), false);
////                            map.put(Conversation.ConversationType.CHATROOM.getName(), false);
////                            map.put(Conversation.ConversationType.CUSTOMER_SERVICE.getName(), false);
////                            map.put(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), false);
////                            map.put(Conversation.ConversationType.PUSH_SERVICE.getName(), true);
////                            map.put(Conversation.ConversationType.PUBLIC_SERVICE.getName(), false);
////                            map.put(Conversation.ConversationType.SYSTEM.getName(), true);
                            RongIM.getInstance().startConversationList(MainActivity.this, map);*/


//                Intent intent=new Intent(MainActivity.this,TestListActivity.class);
//                startActivity(intent);

//                Intent intent = new Intent(MainActivity.this, MyListActivity.class);
//                startActivity(intent);
            }
        });

        RongIM.getInstance().setOnReceiveMessageListener(new MyReceiveMessageListener());

        //未读消息
        RongIM.getInstance().addUnReadMessageCountChangedObserver(MainActivity.this, conversationTypes);


//
//        Discussion discussion=new Discussion(new NativeObject.DiscussionInfo());
//        RongIM.getInstance().refreshDiscussionCache();

    }

    @Override
    public void onCountChanged(int i) {
        Log.e("MyTAG", "未读=" + i);
    }

    private class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {


        /**
         * 收到消息的处理。
         *
         * @param message 收到的消息实体。
         * @param left    剩余未拉取消息数目。
         * @return 收到消息是否处理完成，true 表示自己处理铃声和后台通知，false 走融云默认处理方式。
         */
        @Override
        public boolean onReceived(Message message, int left) {
            //开发者根据自己需求自行处理
            String targetId = message.getTargetId();
//            for (int i = 0; i < MyApplication.unReadMessageCountList.size(); i++) {
//                if (MyApplication.unReadMessageCountList.get(i).getTargetId().equals(targetId)) {
//                    int lastCount = MyApplication.unReadMessageCountList.get(i).getCount();
//                    MyApplication.unReadMessageCountList.get(i).setCount(lastCount > 99 ? 100 : lastCount + 1);
//                    break;
//                }
//            }
            //数据库中targetId是唯一的，只有一个
            List<UnReadMessage> unReadMessageList = MyApplication.myUnReadMessageUtil.queryUnReadMessageByTargetId(targetId);
            UnReadMessage unReadMessage = null;
            if (unReadMessageList.size() == 0) {


            } else {
                unReadMessage = unReadMessageList.get(0);
                unReadMessage.setCount(unReadMessageList.get(0).getCount() > 99 ? 100 : unReadMessageList.get(0).getCount() + 1);
                MyApplication.myUnReadMessageUtil.updateUnreadMessage(unReadMessage);
            }


            Intent intent = new Intent();
            intent.setAction("aaa");
            intent.putExtra("count", unReadMessage == null ? 0 : unReadMessage.getCount());
            intent.putExtra("targetId",targetId);
            sendBroadcast(intent);

            return false;
        }
    }

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 初始化融云sdk 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @param callback 连接回调。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.e("", "");
                    Toast.makeText(MainActivity.this, "onTokenIncorrect", Toast.LENGTH_LONG);
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_LONG);
                   /* Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                    finish();*/
                    //读消息数目和新消息气泡在 IMKit 中默认不显示 可以在连接成功后通过下面方法设置。
                    RongIM.getInstance().enableNewComingMessageIcon(true);//显示新消息提醒
                    RongIM.getInstance().enableUnreadMessageIcon(true);//显示未读消息数目
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.e("", "");
                    Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_LONG);
                }
            });


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 0)
    public void doSomething() {
        //Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();

    }

    @PermissionFail(requestCode = 0)
    public void doFailSomething() {
        Toast.makeText(this, "permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        /**
         * <p>断开与融云服务器的连接。当调用此接口断开连接后，仍然可以接收 Push 消息。</p>
         * <p>若想断开连接后不接受 Push 消息，可以调用{@link #logout()}</p>
         * public void disconnect()
         */


        /**
         * <p>断开与融云服务器的连接，并且不再接收 Push 消息。</p>
         * <p>若想断开连接后仍然接受 Push 消息，可以调用 {@link #disconnect()}</p>
         *  public void logout()
         */

        RongIM.getInstance().disconnect();

        super.onDestroy();

    }
}
