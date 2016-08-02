package util;

import po.*;
import vo.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static util.LoadUtil.loadStudentIDs;

/**
 * Created by chenh on 2016/7/30.
 */
public class PO2VO {

    public static User po2vo(UserPO userPO){
        User user=new User();
        user.id=userPO.getId();
        user.identify=userPO.getIdentify();
        user.password=userPO.getPassword();
        user.username=userPO.getUsername();
        user.nickName=userPO.getNickName();
        user.motto=userPO.getMotto();
        return user;
    }

    public static RentLogPO vo2po(RentLog rentLog){
        RentLogPO rentLogPO=new RentLogPO();
        rentLogPO.setUuid(rentLog.uuid.toString());
        rentLogPO.setApplyReason(rentLog.applyReason);
        rentLogPO.setApplyTime(TimeUtil.getTime(rentLog.applyTime));
        rentLogPO.setHandleTime(TimeUtil.getTime(rentLog.handleTime));
        rentLogPO.setHandleDescription(rentLog.handleDescription);
        rentLogPO.setClassName(rentLog.className);
        rentLogPO.setStartData(TimeUtil.getTime(rentLog.startData));
        rentLogPO.setEndData(TimeUtil.getTime(rentLog.endData));
        rentLogPO.setState(rentLog.state);
        return rentLogPO;
    }

    public static RentLog po2vo(RentLogPO rentLogPO){
        RentLog rentLog=new RentLog();
        rentLog.applyReason=rentLogPO.applyReason;
        rentLog.applyTime=TimeUtil.getDate(rentLogPO.applyTime);
        rentLog.handleTime=TimeUtil.getDate(rentLogPO.handleTime);
        rentLog.handleDescription=rentLogPO.handleDescription;
        rentLog.startData=TimeUtil.getDate(rentLogPO.startData);
        rentLog.endData=TimeUtil.getDate(rentLogPO.endData);
        ArrayList<String> studentIDs=loadStudentIDs(rentLogPO.uuid);
        rentLog.studentIDs=studentIDs;
        rentLog.className=rentLogPO.className;
        rentLog.uuid= UUID.fromString(rentLogPO.uuid);
        return rentLog;
    }

    private static String list2String(ArrayList<String> list){
        String result="";
        for (String s:list ){
            result+=s;
            result+=";";
        }
        return result;
    }

    private static ArrayList<String> String2list(String s){
        String[] segs=s.split(";");
        ArrayList<String> list=new ArrayList<>();
        for (int i=0;i<segs.length;i++){
            list.add(segs[i]);
        }
        return list;
    }


    public static BlogMessagePO vo2po(BlogMessage blogMessage){
        BlogMessagePO blogMessagePO=new BlogMessagePO();
        blogMessagePO.setAuthorId(blogMessage.author.id);
        blogMessagePO.setId(blogMessage.id);
        blogMessagePO.setSendTime(TimeUtil.getTime(blogMessage.sendTime));
        blogMessagePO.setTag(blogMessage.tag);
        blogMessagePO.setText(blogMessage.text);
        return blogMessagePO;
    }

    public static BlogMessage po2vo(BlogMessagePO blogMessagePO){
        BlogMessage blogMessage=new BlogMessage();
        blogMessage.author=po2vo(LoadUtil.loadUser(blogMessagePO.getAuthorId()));
        blogMessage.id=blogMessagePO.getId();
        blogMessage.sendTime=TimeUtil.getDate(blogMessagePO.getSendTime());
        blogMessage.tag=blogMessagePO.getTag();
        blogMessage.text=blogMessagePO.getText();
        return blogMessage;
    }


    public static AttitudeVO po2vo(AttitudePO attitudePO){
        AttitudeVO attitudeVO=new AttitudeVO();
        attitudeVO.attitude=attitudePO.isAttitude();
        attitudeVO.id=attitudePO.getId();
        attitudeVO.sheetId=attitudePO.getSheetId();
        attitudeVO.userId=attitudePO.getUserId();

        return attitudeVO;
    }

    public static AttitudePO vo2po(AttitudeVO attitudeVO){
        AttitudePO attitudePO=new AttitudePO();
        attitudePO.setAttitude(attitudeVO.attitude);
        attitudePO.setId(attitudeVO.id);
        attitudePO.setSheetId(attitudeVO.sheetId);
        attitudePO.setUserId(attitudeVO.userId);

        return attitudePO;
    }

    public static BlogCommentPO vo2po(BlogComments blogComments){
        BlogCommentPO blogCommentPO=new BlogCommentPO();
        //blogCommentPO.set


        return null;
    }

    public static BlogComments po2vo(BlogCommentPO blogCommentPO){
        BlogComments blogComments=new BlogComments();
        blogComments.author=po2vo(LoadUtil.loadUser(blogCommentPO.getAuthorId()));
        blogComments.id=blogCommentPO.getId();
        blogComments.rawMessageId=blogCommentPO.getRawMessageId();
        blogComments.sendTime= TimeUtil.getDate(blogCommentPO.getSendTime());
        blogComments.text=blogCommentPO.getText();


        return blogComments;
    }
}
