package com.cmx.myimrongyun.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/2/8/0008.
 */
@Entity
public class MyMessage {
    @Id(autoincrement = true) // id自增长
    private Long abId;
    @NotNull
    @Unique
    private String id;
    @NotNull
    private String title;
    private String content;
    private String headUrl;
    @NotNull
    private String messageType;
    @NotNull
    private String conversationType;
    @NotNull
    private int unreadMessageCount;
    @Generated(hash = 219049713)
    public MyMessage(Long abId, @NotNull String id, @NotNull String title,
            String content, String headUrl, @NotNull String messageType,
            @NotNull String conversationType, int unreadMessageCount) {
        this.abId = abId;
        this.id = id;
        this.title = title;
        this.content = content;
        this.headUrl = headUrl;
        this.messageType = messageType;
        this.conversationType = conversationType;
        this.unreadMessageCount = unreadMessageCount;
    }

    @Generated(hash = 1215588955)
    public MyMessage() {
    }

    public Long getAbId() {
        return abId;
    }

    public void setAbId(Long abId) {
        this.abId = abId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getConversationType() {
        return conversationType;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public int getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(int unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }
}
