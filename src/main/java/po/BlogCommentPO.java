package po;

import vo.User;

import java.util.Date;

/**
 * Created by chenh on 2016/8/1.
 */
public class BlogCommentPO {

    /**
     *
     */
    private long id;

    /**
     * 评论的文章/动态的ID
     */
    private long rawMessageId;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRawMessageId() {
        return rawMessageId;
    }

    public void setRawMessageId(long rawMessageId) {
        this.rawMessageId = rawMessageId;
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
}
