package model;

import util.LoadUtil;
import util.SaveUtil;
import vo.Classroom;
import vo.RentLog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenh on 2016/7/27.
 */
public class RentManage {

    /**
     *     public static final int WAITING_TO_HANDLE=1;
     public static final int PASS_BUT_NOT_TAKE_EFFECT=2;
     public static final int PASS_AND_FINISHED=3;
     public static final int PASS_AND_IN_USE=4;
     public static final int REJECTED=5;
     */

    /**
     * 待处理单据
     */
    private ArrayList<RentLog> logsToHandle;

    /**
     * 待生效单据
     */
    private ArrayList<RentLog> logsToTakeEffect;

    private ArrayList<RentLog> logsInEffect;



    private static RentManage rentManage;

    public static RentManage getRentManage(){
        if (rentManage==null)
           rentManage=new RentManage();
        return rentManage;

    }
    private RentManage(){
        logsToHandle=LoadUtil.loadRentLog(RentLog.WAITING_TO_HANDLE);
        logsToTakeEffect=LoadUtil.loadRentLog(RentLog.PASS_BUT_NOT_TAKE_EFFECT);
        logsInEffect=LoadUtil.loadRentLog(RentLog.PASS_AND_IN_USE);
        //control();
    }


    public void addRentLog(RentLog rentLog){
        logsToHandle.add(rentLog);
        SaveUtil.save(rentLog);
    }

    public void handleRentLog(int index,int state,String handleDescription){
        RentLog rentLog=logsToHandle.get(index);
        rentLog.state=state;
        rentLog.handleDescription=handleDescription;
        rentLog.handleTime=new Date();
        logsToTakeEffect.add(rentLog);
        SaveUtil.update(rentLog);
    }

    private void control(){
        new Thread(()->{
            while (true){
                Calendar calendar=Calendar.getInstance();

                //打开预约生效的教室
                Date startDate;
                for (int i=0;i<logsToTakeEffect.size();i++){
                    RentLog rentLog=logsToTakeEffect.get(i);
                    startDate=rentLog.startData;
                    if (startDate.getDay()==calendar.get(Calendar.DATE)){
                        if (startDate.getHours()==calendar.get(Calendar.HOUR)){
                            if (startDate.getMinutes()==calendar.get(Calendar.MINUTE)){
                                ClassroomManage.getClassroomManage().getClassroom(rentLog.className).rentClassroom();
                                rentLog.state=RentLog.PASS_AND_IN_USE;
                                logsToTakeEffect.remove(rentLog);
                                logsInEffect.add(rentLog);
                                SaveUtil.update(rentLog);
                            }
                        }
                    }
                }

                //关闭预约完成的教室
                Date endDate;
                for (int i=0;i<logsInEffect.size();i++){
                    RentLog rentLog=logsInEffect.get(i);
                    endDate=rentLog.endData;
                    if (endDate.getDay()==calendar.get(Calendar.DATE)){
                        if (endDate.getHours()==calendar.get(Calendar.HOUR)){
                            if (endDate.getMinutes()==calendar.get(Calendar.MINUTE)){
                                ClassroomManage.getClassroomManage().getClassroom(rentLog.className).closeClassroom();
                                rentLog.state=RentLog.PASS_AND_FINISHED;
                                logsInEffect.remove(rentLog);
                                SaveUtil.update(rentLog);
                            }
                        }
                    }
                }

                //休眠50秒
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
