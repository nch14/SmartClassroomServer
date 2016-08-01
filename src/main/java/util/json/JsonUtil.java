package util.json;

import org.json.JSONException;
import org.json.JSONObject;
import po.BlogMessagePO;
import po.UserPO;
import vo.BlogMessage;
import vo.Classroom;
import vo.User;

/**
 * Created by chenh on 2016/7/27.
 */
public class JsonUtil {

/*    public static Classroom getClassroom(JSONObject json){
        Classroom classroom=new Classroom();
        try {
            classroom.temperature=json.getString("temperature");
            classroom.currentNumOfStudents=json.getInt("currentNumOfStudents");
            classroom.humidity=json.getString("humidity");
            classroom.name=json.getString("name");
            classroom.state=json.getInt("state");

            return classroom;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }*/

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
        json.put("sendTime",blogMessage.sendTime.toString());
        json.put("tag",blogMessage.tag);
        json.put("text",blogMessage.text);
        json.put("author",pack(blogMessage.author));
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


/*    public static BlogMessagePO parse(JSONObject json){

    }*/
}
