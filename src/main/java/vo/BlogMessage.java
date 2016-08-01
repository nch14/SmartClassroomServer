package vo;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chenh on 2016/7/27.
 */
public class BlogMessage{
    /**
     *
     */
    public long id;

    /**
     * 发送时间
     */
    public Date sendTime;

    /**
     * 消息发出者
     */
    public User author;

    /**
     * 消息正文
     */
    public String text;

    /**
     * 话题
     */
    public String tag;

}
