package com.cmx.myimrongyun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cmx.myimrongyun.bean.MyMessage;
import com.cmx.myimrongyun.bean.UnReadMessage;

import java.util.ArrayList;
import java.util.List;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;


public class MyListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyConversationAdapter myConversationAdapter;
    private List<MyMessage> list = new ArrayList<>();

    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);



        myBroadcastReceiver=new MyBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("aaa");
        registerReceiver(myBroadcastReceiver,intentFilter);

        //模拟添加数据
        for (int i = 0; i < 10; i++) {
            MyMessage myMessage = new MyMessage();
            if (i == 0) {
                myMessage.setId("110");
                myMessage.setHeadUrl("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");


            } else if (i == 1) {
                myMessage.setId("70adbc35-a0de-48a9-babe-3cb7fe2696eb");
                myMessage.setHeadUrl("");
                myMessage.setTitle("讨论组");
            } else {
                myMessage.setId("1");
                myMessage.setTitle("1");
                myMessage.setHeadUrl("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
            }
            myMessage.setContent("txt");
            myMessage.setMessageType("txt");
            myMessage.setUnreadMessageCount(0);
            myMessage.setConversationType(Conversation.ConversationType.PRIVATE.getName());
            list.add(myMessage);
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myConversationAdapter = new MyConversationAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myConversationAdapter);

        RongIMClient.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {

                for (int i = 0; i < list.size(); i++) {
                    if(conversations!=null){
                        for (int j = 0; j < conversations.size(); j++) {
                            if (conversations.get(j).getTargetId().equals(list.get(i).getId())) {
                                list.get(i).setContent("txt");
                                list.get(i).setMessageType("txt");
                                list.get(i).setHeadUrl(conversations.get(j).getPortraitUrl() != null||!conversations.get(j).getPortraitUrl().equals("") ? conversations.get(j).getPortraitUrl() : "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
                                list.get(i).setTitle(conversations.get(j).getConversationTitle());
                                list.get(i).setUnreadMessageCount(conversations.get(j).getUnreadMessageCount());
                                list.get(i).setConversationType(conversations.get(j).getConversationType().getName());
                                break;
                            } else {
                                list.get(i).setHeadUrl("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
                                list.get(i).setContent("txt1");
                                list.get(i).setMessageType("txt1");
                                list.get(i).setTitle("ceshi");
                                list.get(i).setUnreadMessageCount(0);
                                list.get(i).setConversationType(conversations.get(j).getConversationType().getName());
                            }

                        }

                    }else {

                    }
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //查询数据库未读消息记录
                        unReadMessageListfromdb = MyApplication.myUnReadMessageUtil.queryAllUnReadMessage();

                        if(unReadMessageListfromdb==null||unReadMessageListfromdb.size()<=0){
                            for(int i=0;i<list.size();i++){
                                UnReadMessage unReadMessage=new UnReadMessage();
                                unReadMessage.setTargetId(list.get(i).getId());
                                unReadMessage.setCount(list.get(i).getUnreadMessageCount());
                                unReadMessageListfromdb.add(unReadMessage);
                            }
                            //插入数据
                            boolean insertSuccess=MyApplication.myUnReadMessageUtil.insertMultUnReadMessage(unReadMessageListfromdb);
                            if(!insertSuccess){
                                Log.e("MyTAG","保存到数据库失败");
                            }
                        }else {
                            for(int i=0;i<unReadMessageListfromdb.size();i++){
                                for(int j=0;j<list.size();j++){
                                    if(unReadMessageListfromdb.get(i).getTargetId().equals(list.get(j).getId())){
                                        //重新设置未读条数,并修改数据库
                                        int servceCount=list.get(j).getUnreadMessageCount();
                                        int dbCount= unReadMessageListfromdb.get(i).getCount();
                                        if(servceCount!=dbCount){
                                            unReadMessageListfromdb.get(i).setCount(list.get(j).getUnreadMessageCount());
                                            MyApplication.myUnReadMessageUtil.updateUnreadMessage(unReadMessageListfromdb.get(i));
                                        }
                                        break;
                                    }
                                }
                            }
                        }


                        myConversationAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    List<UnReadMessage> unReadMessageListfromdb;

    class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if("aaa".equals(action)){
                if(myConversationAdapter!=null){
                    int count=intent.getIntExtra("count",0);
                    String targetId=intent.getStringExtra("targetId");
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).getId().equals(targetId)){
                            list.get(i).setUnreadMessageCount(count);
                            break;
                        }
                    }
                    myConversationAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myBroadcastReceiver);
        super.onDestroy();
    }
}
