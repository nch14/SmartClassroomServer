package net;

import model.ClassroomManage;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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
    public String temperature="未知";

    /**
     *教室当前湿度
     */
    public String humidity="未知";

    /**
     * 教室当前人数
     */
    public int currentNumOfStudents;


    public ClassroomClient(Socket client) {
        super(client);
        initTable();
    }


    protected void read() throws IOException {
        DataInputStream inputStream = null;
        inputStream =new DataInputStream(socket.getInputStream());
        char c;
        ArrayList<Character> chars=new ArrayList<>();
        String s="";
        ArrayList<Byte> bytes=new ArrayList<>();
        byte b;
        while ((b=(byte) inputStream.read())!=-1) {
            if (b==59){
                for (Byte aByte:bytes){
                    char cc=transfer(aByte);
                    s+=cc;
                }
                System.out.println(socket.getInetAddress()+"接受到一条数据！"+s);
                handleMessage(s);
                bytes.clear();
                s="";
            }else
                bytes.add(b);
        }


    }



    protected void write() throws IOException {
        DataOutputStream outputStream = null;
        String message=messages.get(0);
        messages.remove(0);

        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        outputStream.write(transfer(message));
        outputStream.flush();
            /*outputStream.close();*/
        System.out.println("发送完成！message:"+message);
    }



    /**
     *1.name：xxxx;
     *2.t:xx,h:xx;
     *3.+;c
     *4.-;
     * @param s
     */
    protected void handleMessage(String s){
        s=s.replace("*","");
        char c=s.charAt(0);
        String[] seg;
        switch (c){
            case 'n':
                seg = s.split(":");
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
                if (currentNumOfStudents>0)
                    currentNumOfStudents--;
                break;
            case 't':
                seg=s.split(":");
                temperature=seg[1].substring(0,2)+"℃";
                humidity=seg[2];
                break;
        }
    }



    private char transfer(Byte b){
        char c;
        if (table.containsKey(b)){
            c=table.get(b);
        }else {
            c='*';
        }
        return c;
    }

    public byte[] transfer(String s){
        char[] chars=s.toCharArray();
        byte[] bytes=new byte[chars.length];
        for (int i=0;i<chars.length;i++){
            if (table2.containsKey(chars[i]))
                bytes[i]=table2.get(chars[i]);
            else
                bytes[i]='*';
        }
        return bytes;
    }
    HashMap<Character,Byte> table2=new HashMap<>();

    HashMap<Byte,Character> table=new HashMap<>();


    private void initTable(){
        table.put((byte)48,'0');
        table.put((byte)49,'1');
        table.put((byte)50,'2');
        table.put((byte)51,'3');
        table.put((byte)52,'4');
        table.put((byte)53,'5');
        table.put((byte)54,'6');
        table.put((byte)55,'7');
        table.put((byte)56,'8');
        table.put((byte)57,'9');

        table.put((byte)110,'n');
        table.put((byte)116,'t');

        table.put((byte)58,':');
        table.put((byte)59,';');

        table.put((byte)111,'o');
        table.put((byte)99,'c');
        table.put((byte)43,'+');
        table.put((byte)45,'-');

        table2.put('0',(byte)48);
        table2.put('1',(byte)49);
        table2.put('2',(byte)50);
        table2.put('3',(byte)51);
        table2.put('4',(byte)52);
        table2.put('5',(byte)53);
        table2.put('6',(byte)54);
        table2.put('7',(byte)55);
        table2.put('8',(byte)56);
        table2.put('9',(byte)57);
        table2.put(':',(byte)58);
        table2.put(';',(byte)59);
        table2.put('n',(byte)110);
        table2.put('t',(byte)116);
        table2.put('o',(byte)111);
        table2.put('c',(byte)99);
        table2.put('+',(byte)43);
        table2.put('-',(byte)45);

    }
}
