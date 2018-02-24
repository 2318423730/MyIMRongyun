package com.cmx.myimrongyun;

import android.net.Uri;

import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.widget.provider.DiscussionConversationProvider;

//继承
@ConversationProviderTag(conversationType = "discussion", portraitPosition = 1)
public class MyDiscussionConversationProvider extends DiscussionConversationProvider {

    //重写
    @Override
    public Uri getPortraitUri(String id) {

        for(int i=0;i< MyApplication.myList.size();i++){
            if(id.equals( MyApplication.myList.get(i))){
                return Uri.parse( MyApplication.myList.get(i));
            }
        }

        return null;
    }
}