package com.cmx.myimrongyun;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;


public class SamplePlugin implements IPluginModule {

    Conversation.ConversationType conversationType;
    String targetId;

    @Override
    public Drawable obtainDrawable(Context context) {
        //设置插件 Plugin 图标
        return ContextCompat.getDrawable(context, R.drawable.rc_ext_plugin_image_selector);
    }

    @Override
    public String obtainTitle(Context context) {
        //设置插件 Plugin 展示文字
        return "示例";
    }

    @Override
    public void onClick(final Fragment currentFragment, RongExtension extension) {
        //示例获取 会话类型、targetId、Context,此处可根据产品需求自定义逻辑，如:开启新的 Activity 等。
        conversationType = extension.getConversationType();
        targetId = extension.getTargetId();
        Message message = Message.obtain(targetId, conversationType, TextMessage.obtain("示例插件功能"));
        RongIM.getInstance().sendMessage(message, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {
                Toast.makeText(currentFragment.getActivity(), "消息发送成功, 示例获取 Context", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {

            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}