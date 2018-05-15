package S_9_1;

import java.util.Vector;

public class UserInfoList {
    private static UserInfoList user=new UserInfoList();
    private Vector<String> vector=null;
    private UserInfoList(){
        this.vector=new Vector<>();
    }
    public static UserInfoList getInstance(){
        return user;
    }

    public boolean addUserinfo(String user){
        if (user!=null){
            this.vector.add(user);
            return true;
        }else {
            return false;
        }
    }

    public Vector<String> getList(){
        return vector;
    }

    public void  removeUserInfo(String user){
        if (user!=null){
            vector.removeElement(user);
        }
    }
}
