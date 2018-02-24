package com.cmx.myimrongyun;

import android.content.Context;

import java.util.List;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.fragment.IHistoryDataResultCallback;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by Administrator on 2018/2/8/0008.
 */

public class MyFragment extends ConversationListFragment {
    ConversationListAdapterEx mAdapter;
    @Override
    public ConversationListAdapter onResolveAdapter(Context context) {
        mAdapter = new ConversationListAdapterEx(context);
        return mAdapter;
    }


    /**

     * 开发者可以重写此方法，来填充自定义数据到会话列表界面。

     * 如果需要同时显示 sdk 中默认会话列表数据，在重写时可使用 super.getConversationList()。反之，不需要调用 super 方法。

     * <p>

     * 注意：通过 callback 返回的数据要保证在 UI 线程返回。

     *

     * @param conversationTypes 当前会话列表已配置显示的会话类型。

     */

    public void getConversationList(final IHistoryDataResultCallback<List<Conversation>> callback, Conversation.ConversationType[] conversationTypes) {

        RongIMClient.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {

            @Override

            public void onSuccess(List<Conversation> conversations) {

                if (callback != null) {

                    callback.onResult(conversations);

                }

            }



            @Override

            public void onError(RongIMClient.ErrorCode e) {

                if (callback != null) {

                    callback.onError();

                }

            }

        }, conversationTypes);

    }
}
