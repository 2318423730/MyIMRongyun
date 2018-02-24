package com.cmx.myimrongyun.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/2/22/0022.
 */
@Entity
public class UnReadMessage {
    @Id(autoincrement = true) // id自增长
    private Long dbId;
    @NotNull
    @Unique
    private String targetId;
    @NotNull
    private int count;

    public UnReadMessage() {
    }

    public UnReadMessage(String targetId, int count) {
        this.targetId = targetId;
        this.count = count;
    }

    @Generated(hash = 816686923)
    public UnReadMessage(Long dbId, @NotNull String targetId, int count) {
        this.dbId = dbId;
        this.targetId = targetId;
        this.count = count;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
