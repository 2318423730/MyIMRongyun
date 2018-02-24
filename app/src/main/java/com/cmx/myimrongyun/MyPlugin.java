package com.cmx.myimrongyun;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.image.PictureSelectorActivity;
import io.rong.imkit.utilities.PermissionCheckUtil;
import io.rong.imlib.model.Conversation;

/**
 * Created by Administrator on 2018/1/10/0010.
 */

public class MyPlugin implements IPluginModule {
    Conversation.ConversationType conversationType;
    String targetId;
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.about_seal);
    }

    @Override
    public String obtainTitle(Context context) {
        return "语音聊天";
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        String[] permissions = new String[]{"android.permission.READ_EXTERNAL_STORAGE"};
        if(PermissionCheckUtil.requestPermissions(fragment, permissions)) {
            this.conversationType = rongExtension.getConversationType();
            this.targetId = rongExtension.getTargetId();
            Intent intent = new Intent(fragment.getActivity(), PictureSelectorActivity.class);
            rongExtension.startActivityForPluginResult(intent, 23, this);
        }
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
