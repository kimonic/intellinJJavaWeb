package S_9_1;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class UserInfoTrace implements HttpSessionBindingListener {
    private String user;
    private UserInfoList container=UserInfoList.getInstance();

    public UserInfoTrace() {
        user="";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("上线---"+user);

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("下线---"+user);
        if (user!=null){
            container.removeUserInfo(user);
        }

    }
}
