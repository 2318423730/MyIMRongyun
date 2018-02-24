package com.cmx.myimrongyun;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cmx.myimrongyun.bean.MyMessage;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8/0008.
 */

public class MyConversationAdapter extends BaseQuickAdapter<MyMessage, BaseViewHolder> {

    public MyConversationAdapter(Context context, @Nullable List<MyMessage> data) {
        super(R.layout.item_message, data);
        mContext = context;
    }



    @Override
    protected void convert(BaseViewHolder helper, MyMessage item) {
        ImageView imageView = helper.getView(R.id.iv_head);
        TextView tv_fromUser = helper.getView(R.id.tv_title);
        TextView tv_content = helper.getView(R.id.tv_content);

        tv_fromUser.setText(item.getTitle());


        if (item.getMessageType().equals("txt")) {
            tv_content.setText(item.getContent());
        } else if (item.getMessageType().equals("语音")) {
            tv_content.setText("[语音]");
        } else if (item.getMessageType().equals("图片")) {
            tv_content.setText("[图片]");
        } else if (item.getMessageType().equals("文件")) {
            tv_content.setText("[文件]");
        } else if (item.getMessageType().equals("表情")) {
            tv_content.setText("[表情]");
        } else if (item.getMessageType().equals("location")) {
            tv_content.setText("[位置]");
        } else if (item.getMessageType().equals("txt1")) {
            tv_content.setText("[txt1]");
        }


        Glide.with(mContext).load(item.getHeadUrl()).into(imageView);
        TextView tv_unReadCount = helper.getView(R.id.tv_unReadCount);
        int unReadCount = item.getUnreadMessageCount();
        if (unReadCount >= 100) {
            tv_unReadCount.setText("99+");
            tv_unReadCount.setVisibility(View.VISIBLE);
        } else if (unReadCount > 0 && unReadCount < 100) {
            tv_unReadCount.setText(""+unReadCount);
            tv_unReadCount.setVisibility(View.VISIBLE);
        }else {
            tv_unReadCount.setText("");
            tv_unReadCount.setVisibility(View.GONE);
        }
//        for (int i = 0; i < MyApplication.unReadMessageCountList.size(); i++) {
//            if (MyApplication.unReadMessageCountList.get(i).getTargetId().equals(item.getId())) {
//                count = MyApplication.unReadMessageCountList.get(i).getCount();
//                if (count == 100) {
//                    tv_unReadCount.setText("99+");
//                    tv_unReadCount.setVisibility(View.VISIBLE);
//                } else if (count > 0 && count < 100) {
//                    tv_unReadCount.setText(""+count);
//                    tv_unReadCount.setVisibility(View.VISIBLE);
//                } else {
//                    tv_unReadCount.setText("");
//                    tv_unReadCount.setVisibility(View.VISIBLE);
//                }
//
//                break;
//            }
//        }

//        if (count <= 0) {
//            tv_unReadCount.setVisibility(View.GONE);
//        }
    }
}
