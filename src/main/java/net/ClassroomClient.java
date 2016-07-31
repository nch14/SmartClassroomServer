package net;

import model.ClassroomManage;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/27.
 */
public class ClassroomClient extends Client implements Runnable {

    /**
     * 绑定的教室的名称
     */
    public String name;

    /**
     * 教室当前温度
     */
    public String temperature;

    /**
     *教室当前湿度
     */
    public String humidity;

    /**
     * 教室当前人数
     */
    public int currentNumOfStudents;


    public ClassroomClient(Socket client) {
        super(client);
    }


    protected void read() {
        DataInputStream inputStream = null;
        try {
            inputStream =new DataInputStream(socket.getInputStream());
            char c;
            ArrayList<Character> chars=new ArrayList<>();
            while ((c=inputStream.readChar())!=';'){
                chars.add(c);
            }
            String s="";
            for (Character character:chars){
                s+=character;
            }
            System.out.println("接受到一条数据！");
            handleMessage(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    protected void write(){
        DataOutputStream outputStream = null;
        String message=messages.get(0);
        messages.remove(0);

        try {
            outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            outputStream.writeChars(message);
            outputStream.flush();
            /*outputStream.close();*/
            System.out.println("发送完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     *1.name：xxxx;
     *2.t:xx,h:xx;
     *3.+;c
     *4.-;
     * @param s
     */
    protected void handleMessage(String s){
        char c=s.charAt(0);
        String[] seg;
        switch (c){
            case 'n':
                seg = s.split(";");
                name=seg[1];
                //注册和绑定设备
                ClassroomManage.getClassroomManage().bindClassroomAndDevice(this);
                //告诉设备空调的开启温度
                addMessage("t:26");
                break;
            case '+':
                currentNumOfStudents++;
                break;
            case  '-':
                currentNumOfStudents--;
                break;
            case 't':
                seg=s.split(":");
                temperature=seg[1].substring(0,2);
                humidity=seg[2];
                break;
        }
    }


}
