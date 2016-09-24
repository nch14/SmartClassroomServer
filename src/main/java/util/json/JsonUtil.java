package util.json;

import org.json.JSONException;
import org.json.JSONObject;
import po.AttitudePO;
import po.BlogMessagePO;
import po.TimeTableCourse;
import po.UserPO;
import util.TimeUtil;
import vo.BlogComments;
import vo.BlogMessage;
import vo.Classroom;
import vo.User;

/**
 * Created by chenh on 2016/7/27.
 */
public class JsonUtil {

    public static JSONObject packClassroom(Classroom classroom){
        JSONObject json=new JSONObject();
        if (classroom.classroomClient!=null){
            try {
                json.put("temperature",classroom.classroomClient.temperature);
                json.put("currentNumOfStudents",classroom.classroomClient.currentNumOfStudents);
                json.put("humidity",classroom.classroomClient.humidity);
                json.put("name",classroom.name);
                json.put("state",classroom.state);

                return json;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static User getUser(JSONObject json){
        User user=new User();
        try {
            user.id=json.getString("id");
            user.identify=json.getInt("identify");
            user.password=json.getString("password");
            user.username=json.getString("username");
            user.nickName=json.getString("nickName");
            user.motto=json.getString("motto");
            user.courseEnabled=json.getBoolean("courseEnabled");
            user.university=json.getString("university");
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static JSONObject pack(BlogMessage blogMessage){
        JSONObject json=new JSONObject();
        json.put("id",blogMessage.id);
        json.put("sendTime",TimeUtil.getTime(blogMessage.sendTime));
        json.put("tag",blogMessage.tag);
        json.put("text",blogMessage.text);
        json.put("author",pack(blogMessage.author));
        return json;
    }

    public static JSONObject pack(BlogComments blogComments){
        JSONObject json=new JSONObject();
        json.put("id",blogComments.id);
        json.put("sendTime", TimeUtil.getTime(blogComments.sendTime));
        json.put("rawMessageId",blogComments.rawMessageId);
        json.put("text",blogComments.text);
        json.put("author",pack(blogComments.author));
        return json;
    }

    public static JSONObject pack(UserPO userPO){
        JSONObject json=new JSONObject();
        json.put("id",userPO.getId());
        json.put("password",userPO.getPassword());
        json.put("identify",userPO.getIdentify());
        json.put("username",userPO.getUsername());
        json.put("nickName",userPO.getNickName());
        json.put("motto",userPO.getMotto());
        json.put("university",userPO.getUniversity());
        json.put("courseEnabled",userPO.isCourseEnabled());
        return json;
    }

    public static JSONObject pack(User user){
        JSONObject json=new JSONObject();
        json.put("id",user.id);
        json.put("password",user.password);
        json.put("identify",user.identify);
        json.put("username",user.username);
        json.put("nickName",user.nickName);
        json.put("motto",user.motto);
        json.put("university",user.university);
        json.put("courseEnabled",user.courseEnabled);
        return json;
    }

    public static JSONObject pack(AttitudePO attitudePO){
        JSONObject json=new JSONObject();
        json.put("id",attitudePO.getId());
        json.put("sheetId",attitudePO.getSheetId());
        json.put("userId",attitudePO.getUserId());
        json.put("attitude",attitudePO.isAttitude());
        return json;
    }

    public static JSONObject pack(TimeTableCourse timeTableCourse){
        JSONObject json=new JSONObject();
        json.put("id",timeTableCourse.getId());
        json.put("userKey",(timeTableCourse.getUserKey()==null?timeTableCourse.getUserKey():"暂时没有"));
        json.put("term",(timeTableCourse.getTerm()==null?timeTableCourse.getTerm():"暂时没有"));
        json.put("courseClassroom",timeTableCourse.getCourseClassroom());
        json.put("courseDate",timeTableCourse.getCourseDate());
        json.put("courseName",timeTableCourse.getCourseName());
        json.put("courseTeacher",timeTableCourse.getCourseTeacher());
        json.put("courseType",timeTableCourse.getCourseType());
        json.put("note",(timeTableCourse.getNote()==null?timeTableCourse.getNote():"暂时没有"));
        json.put("campus",timeTableCourse.getCampus());
        json.put("startSection",timeTableCourse.getStartSection());
        json.put("lastSection",timeTableCourse.getLastSection());
        json.put("week",timeTableCourse.getWeek());
        return json;
    }

}
