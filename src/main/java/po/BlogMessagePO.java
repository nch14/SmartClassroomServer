package po;

import vo.User;

import java.util.Date;

/**
 * Created by chenh on 2016/7/27.
 */
public class BlogMessagePO {
    /**
     *
     */
    private long id;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 消息发出者
     */
    private String authorId;

    /**
     * 消息正文
     */
    private String text;

    /**
     * 话题
     */
    private String tag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
