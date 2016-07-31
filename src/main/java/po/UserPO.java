package po;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;

/**
 * Created by chenh on 2016/7/27.
 */
public class UserPO {

    private String username;

    private String id;

    private int identify;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

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
}
