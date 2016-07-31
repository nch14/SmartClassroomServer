package util;

import po.RentLogPO;
import po.RentLogUser;
import po.UserPO;
import vo.RentLog;
import vo.User;

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
        return user;
    }

    public static RentLogPO vo2po(RentLog rentLog){
        RentLogPO rentLogPO=new RentLogPO();
        rentLogPO.setUuid(rentLog.uuid.toString());
        rentLogPO.setApplyTime(rentLog.applyReason);
        rentLogPO.setApplyTime(rentLog.applyTime.toString());
        rentLogPO.setHandleTime(rentLog.handleTime.toString());
        rentLogPO.setHandleDescription(rentLog.handleDescription);
        rentLogPO.setClassName(rentLog.className);
        rentLogPO.setStartData(rentLog.startData.toString());
        rentLogPO.setEndData(rentLog.endData.toString());
        rentLogPO.setState(rentLog.state);
        return rentLogPO;
    }

    public static RentLog po2vo(RentLogPO rentLogPO){
        RentLog rentLog=new RentLog();
        rentLog.applyReason=rentLogPO.applyReason;
        rentLog.applyTime=new Date(rentLogPO.applyTime);
        rentLog.handleTime=new Date(rentLogPO.handleTime);
        rentLog.handleDescription=rentLogPO.handleDescription;
        rentLog.startData=new Date(rentLogPO.startData);
        rentLog.endData=new Date(rentLogPO.endData);
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
}
