package com.cmx.myimrongyun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.ProviderContainerView;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.model.Conversation;

public class ConversationListAdapterEx extends ConversationListAdapter {
    private boolean isAdd;
     private Context context=null;
    private View view;
    public ConversationListAdapterEx(Context context) {
        super(context);
       this. context=context;
    }

    @Override
    protected View newView(final Context context, int position, ViewGroup group) {
//        if (position == 0) {
//            view=LayoutInflater.from(this.context).inflate(R.layout.header,null);
//            view.findViewById(R.id.tv_chat_private).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context,"被点击",Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            return view;
//        }else {
//            return super.newView(context, position, group);
//        }
        if(!isAdd){
            view=LayoutInflater.from(this.context).inflate(R.layout.header,null);
            ListView listView= (ListView) group;
            listView.addHeaderView(view);
            group=(ViewGroup) listView;
            isAdd=true;
        }

        return super.newView(context, position, group);
    }

    @Override
    protected void bindView(View v, int position, UIConversation data) {
//        if(position==0){
//            super.bindView(view, position, data);
//        }else {
//            super.bindView(v, position, data);
//        }
        if(data.getConversationType().equals(Conversation.ConversationType.DISCUSSION))
            data.setUnreadType(UIConversation.UnreadRemindType.REMIND_ONLY);
        super.bindView(v, position, data);

//        if (position == 0) {
//            if (position == 0) {
//            view.findViewById(R.id.tv_chat_private).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context,"被点击",Toast.LENGTH_SHORT).show();
//                }
//            });
//            super.bindView(view,0,data);
//        } else {
//            super.bindView(v, position, data);
//        }

    }
}