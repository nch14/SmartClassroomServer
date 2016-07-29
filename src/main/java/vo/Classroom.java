package vo;

import net.ClassroomClient;

/**
 * Created by chenh on 2016/7/27.
 */
public class Classroom {

    public static final int OPEN=1;
    public static final int Close=2;
    public static final int rent=3;


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

}
