package po;

import org.hibernate.annotations.Table;
import vo.User;

import javax.persistence.Entity;
import java.util.UUID;

/**
 * Created by chenh on 2016/7/27.
 */
public class UserPO {

    private String username;//用户名

    private String id;//学号

    private int identify;//身份

    private String nickName;//昵称

    private String motto;//签名

    private String university;//所在学校

    private boolean courseEnabled;//是否启用课程表

    private String password;




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdentify() {
        return identify;
    }

    public void setIdentify(int identify) {
        this.identify = identify;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public boolean isCourseEnabled() {
        return courseEnabled;
    }

    public void setCourseEnabled(boolean courseEnabled) {
        this.courseEnabled = courseEnabled;
    }


    public static UserPO createUser(String userID){
        UserPO u=new UserPO();
        u.nickName="未名";
        u.username="未名";
        u.id=userID;
        u.courseEnabled=false;
        u.motto="没签名，注学渣~";
        u.university="南京大学";
        u.password= UUID.randomUUID().toString().substring(17,25);
        return u;
    }
}
