package priv.zwc.Common;

import java.io.Serializable;

/**
 * Created by admin on 2016/6/2.
 */
public class AuthUser implements Serializable {
    private long id;
    private String userName;
    private int role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
