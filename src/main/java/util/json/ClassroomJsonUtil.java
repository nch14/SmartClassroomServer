package util.json;

import org.json.JSONException;
import org.json.JSONObject;
import vo.Classroom;

/**
 * Created by chenh on 2016/7/27.
 */
public class ClassroomJsonUtil {

    public static Classroom getClassroom(JSONObject json){
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
    }

    public static JSONObject packClassroom(Classroom classroom){
        JSONObject json=new JSONObject();
        try {
            json.put("temperature",classroom.temperature);
            json.put("currentNumOfStudents",classroom.currentNumOfStudents);
            json.put("humidity",classroom.humidity);
            json.put("name",classroom.name);
            json.put("state",classroom.state);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
        }
            return null;
    }
}
