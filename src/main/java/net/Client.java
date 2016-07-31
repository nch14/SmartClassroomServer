package net;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/30.
 */
public abstract class Client implements Runnable{
    protected Socket socket;
    protected ArrayList<String> messages;

    public Client(Socket client) {
        socket = client;
        messages=new ArrayList<>();
        try {
            socket.setKeepAlive(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        System.out.println("客户端地址："+socket.getInetAddress());
        new Thread(this).start();
    }

    public void run() {
        System.out.println("客户端数据已经连接" + "客户端地址：" + socket.getInetAddress().toString());
        //有没有要读的。要读就读一下
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    read();
                }
            }
        }).start();
        //有没有要写的。要写就写一下
        new Thread(()-> {
            while (true) {
                if (messages.size()!=0) {
                    write();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    public void addMessage(String s){
        messages.add(s);
    }


    protected abstract void write();
    protected abstract void read();

    protected abstract void handleMessage(String s);

}
