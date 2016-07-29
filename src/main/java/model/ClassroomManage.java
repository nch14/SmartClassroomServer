package model;

import net.ClassroomClient;
import util.json.LoadClassroomUtil;
import vo.Classroom;

import java.util.ArrayList;

/**
 * 本地的教室模型
 * Created by chenh on 2016/7/27.
 */
public class ClassroomManage {
    /**
     * 未开放教室
     */
    private ArrayList<Classroom> mSleepingClassrooms;

    /**
     * 开放教室列表
     */
    private ArrayList<Classroom> mOpenClassrooms;

    /**
     * 借用中教室列表
     */
    private ArrayList<Classroom> mRentClassrooms;

    private static ClassroomManage classroomManage;


    public static ClassroomManage getClassroomManage(){
        if (classroomManage ==null)
            classroomManage =new ClassroomManage();
        return classroomManage;
    }

    private ClassroomManage(){
        mSleepingClassrooms=LoadClassroomUtil.LoadClassroom();
        mOpenClassrooms=new ArrayList<>();
        mRentClassrooms=new ArrayList<>();
        control();
    }

    /**
     * 即需要增加教室时启用
     */
    private void addClassroom(){
        Classroom classroom=mSleepingClassrooms.get(0);
        mSleepingClassrooms.remove(0);
        mOpenClassrooms.add(classroom);
    }

    /**
     * 即需要关闭教室时启用
     * @param name
     */
    private void deleteClassroom(String name){
        Classroom classroom=getClassroom(name);
        mOpenClassrooms.remove(classroom);
        mSleepingClassrooms.add(classroom);
    }

    /**
     * 查找出这间教室
     * @param name 教室名字
     * @return
     */
    private Classroom getClassroom(String name){
        for (int i = 0; i< mOpenClassrooms.size(); i++){
            if (mOpenClassrooms.get(i).name.equals(name))
                return mOpenClassrooms.get(i);
        }
        for (int i = 0; i< mSleepingClassrooms.size(); i++){
            if (mSleepingClassrooms.get(i).name.equals(name))
                return  mSleepingClassrooms.get(i);
        }
        for (int i = 0; i< mRentClassrooms.size(); i++){
            if (mRentClassrooms.get(i).name.equals(name))
                return  mRentClassrooms.get(i);
        }
        return null;
    }

    public boolean bindClassroomAndDevice(ClassroomClient classroomClient){
        getClassroom(classroomClient.name).bind(classroomClient);
        return true;
    }

    /**
     * 职责：管理增加教室的开放还是减少教室的开放
     */
    private void control(){
        new Thread(()-> {
                addClassroom();
                addClassroom();
                while (true){
                    //查找有没有需要关闭的教室。默认保留2间常开教室、最多允许一件额外的教室里少于5人
                    if (mOpenClassrooms.size()>2){
                        ArrayList<String > indexsDelete=new ArrayList<>();
                        for (int i=2;i<mOpenClassrooms.size();i++){
                            if (mOpenClassrooms.get(i).classroomClient.currentNumOfStudents<5)
                                indexsDelete.add(mOpenClassrooms.get(i).name);
                        }
                        if (indexsDelete.size()>1){
                            for (int i=1;i<indexsDelete.size();i++){
                                deleteClassroom(indexsDelete.get(i));
                            }
                        }
                    }
                    //搜索是否需要增加教室。当所有的教室都有超过30人时，就增加新的教室
                    int count=0;
                    for (int i=0;i<mOpenClassrooms.size();i++){
                        if (mOpenClassrooms.get(i).classroomClient.currentNumOfStudents>30)
                            count++;
                    }
                    if (count==mOpenClassrooms.size())
                        addClassroom();
                    //每5分钟进行一次检查操作
                    try {
                        Thread.sleep(300000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }
}
