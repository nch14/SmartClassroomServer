package util.json;

import org.json.JSONException;
import org.json.JSONObject;
import po.AttitudePO;
import po.BlogMessagePO;
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
}
