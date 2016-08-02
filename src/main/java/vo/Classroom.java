package vo;

import net.ClassroomClient;

/**
 * Created by chenh on 2016/7/27.
 */
public class Classroom {

    public static final int OPEN=1;
    public static final int CLOSE=0;
    public static final int RENT=3;
    public static final int EXCEPTION=4;

    /**
     * 例如：甲区501
     * 作为教室的唯一标识、不可重复
     */
    public String name;

    /**
     * 教室当前状态
     */
    public int state;

    /**
     *教室对应的物理设备
     */
    public ClassroomClient classroomClient;

    public void bind(ClassroomClient classroomClient){
        this.classroomClient = classroomClient;
    }


    public void openClassroom(){
        this.state=OPEN;
        classroomClient.addMessage("open;");
    }

    public void closeClassroom(){
        this.state= CLOSE;
        classroomClient.addMessage("close;");
    }

    public void rentClassroom(){
        this.state= RENT;
        classroomClient.addMessage("open;");
    }

    public String getH(){
        if (classroomClient!=null)
            return classroomClient.humidity;
        return "数据不存在";
    }

    public String getT(){
        if (classroomClient!=null)
            return classroomClient.temperature+"°C";
        return "数据不存在";
    }
    public int getNumOfStudent(){
        if (classroomClient!=null)
            return classroomClient.currentNumOfStudents;
        return 0;
    }

}
