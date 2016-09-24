package model;

import net.ClassroomClient;
import util.LoadUtil;
import vo.Classroom;

import java.util.ArrayList;

/**
 * 本地的教室模型
 * Created by chenh on 2016/7/27.
 */
public class ClassroomManage {
    public ArrayList<Classroom> getmClassrooms() {
        return mClassrooms;
    }

    /**
     * 教室
     */
    private ArrayList<Classroom> mClassrooms;


    private static ClassroomManage classroomManage;


    public static ClassroomManage getClassroomManage(){
        if (classroomManage ==null)
            classroomManage =new ClassroomManage();
        return classroomManage;
    }

    private ClassroomManage(){
        mClassrooms= LoadUtil.LoadClassroom();
        control();
    }

    /**
     * 即需要增加教室时启用
     */
    private void addClassroom(){
        Classroom newClassroom;
        for (int i=0;i<mClassrooms.size();i++){
            newClassroom=mClassrooms.get(i);
            if (newClassroom.state==Classroom.CLOSE &&newClassroom.classroomClient!=null){
                newClassroom.openClassroom();
                break;
            }
        }

    }

    /**
     * 即需要关闭教室时启用
     * @param name
     */
    private void deleteClassroom(String name){
        Classroom classroom=getClassroom(name);
        classroom.closeClassroom();
    }

    /**
     * 查找出这间教室
     * @param name 教室名字
     * @return
     */
    public Classroom getClassroom(String name){
        for (int i = 0; i< mClassrooms.size(); i++){
            if (mClassrooms.get(i).name.equals(name))
                return mClassrooms.get(i);
        }
        return null;
    }

    public boolean bindClassroomAndDevice(ClassroomClient classroomClient){
        getClassroom(classroomClient.name).bind(classroomClient);
        return true;
    }

    /**
     * 获得当前开放的教室
     * @return
     */
    public ArrayList<Classroom> getOpenClassrooms(){
        ArrayList<Classroom> classrooms=new ArrayList<>();
        Classroom classroom;
        for (int i=0;i<mClassrooms.size();i++){
            classroom=mClassrooms.get(i);
            if(classroom.state==Classroom.OPEN)
                classrooms.add(classroom);
        }
        return classrooms;
    }

    /**
     * 职责：管理增加教室的开放还是减少教室的开放
     */
    private void control(){
        new Thread(()-> {
                addClassroom();
                while (true){
                    //查找有没有需要关闭的教室。默认保留1间常开教室、最多允许一件额外的教室里少于1人
                    boolean close=false;
                    ArrayList<Classroom> openClassrooms=getOpenClassrooms();
                    if (openClassrooms.size()>1){
                        Classroom classroom;
                        for (int i=1;i<openClassrooms.size();i++){
                            classroom=openClassrooms.get(i);
                            if (classroom.classroomClient!=null){
                                //教室设备运转正常
                                if (classroom.classroomClient.currentNumOfStudents<1) {
                                    deleteClassroom(classroom.name);
                                    close=true;
                                }
                            }else {
                                //教室设备出现问题,设置为异常教室。通知管理员
                                classroom.state=Classroom.EXCEPTION;
                            }
                        }
                    }

                    //搜索是否需要增加教室。当所有的教室都有超过5人时，就增加新的教室
                    if (close){
                        //刚关闭闲置教室。本轮不需要增加新教室
                    }else {
                        openClassrooms=getOpenClassrooms();
                        if (openClassrooms.size()==0){
                            addClassroom();
                        }else {
                            int count=0;
                            Classroom classroom;
                            for (int i=0;i<openClassrooms.size();i++){
                                classroom=openClassrooms.get(i);
                                if (classroom.classroomClient!=null){
                                    //教室设备运转正常
                                    if (openClassrooms.get(i).classroomClient.currentNumOfStudents>5)
                                        count++;
                                }else {
                                    //教室设备出现问题,设置为异常教室。通知管理员
                                    classroom.state=Classroom.EXCEPTION;
                                }
                            }
                            if (count==openClassrooms.size())
                                addClassroom();
                        }
                    }


                    //每5分钟进行一次检查操作
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }












    /*------------------------------以下方法为辅助方法-------------------------------------*/



}
