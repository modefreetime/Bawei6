package com.example.home.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_symsg")
public class SyMsgEntity {

    /**
     * id : 1
     * msgtype : 0
     * msg : 服务器将于凌晨0-6点时段升级，给各位带来不便还请谅解
     * title : 服务器升级通知
     */
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo
    private int id;
    @ColumnInfo
    private String msgtype;
    @ColumnInfo
    private String msg;
    @ColumnInfo
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
