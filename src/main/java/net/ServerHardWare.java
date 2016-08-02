package net;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/27.
 */
public class ServerHardWare {
    public static final int PORT = 12345;//

    private static ServerHardWare server;
    public static ServerHardWare getServer(){
        if (server==null)
            server=new ServerHardWare();
        return server;
    }

    private ServerHardWare(){
        new Thread(()-> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                System.out.println("端口已经打开");
                while (true) {
                    // 一旦有堵塞, 则表示服务器与客户端获得了连接
                    Socket client = serverSocket.accept();
                    // 处理这次连接
                    new ClassroomClient(client);
                }
            } catch (Exception e) {
                System.out.println("创建连接的时候服务器异常: " + e.getMessage());
            }
        }).start();
    }
}
