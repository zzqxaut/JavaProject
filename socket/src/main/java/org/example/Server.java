package org.example;

//package jffx.blogs.net;

import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

//服务器并没有用GUI
//当然也可以，不过没有必要,
//一般是写入服务器的配置文件.
//当然,一切看你
public class Server {
    /**
     * 保存和服务器连接的每个客户端流
     */
    ArrayList<DataOutputStream> clientOutputStream = null;


    public static void main(String[] args) {
        new Server().start();
    }

    /**
     * 程序主要处理阶段
     */
    private void start() {
        //为存储客户流分配空间
        clientOutputStream = new ArrayList<>();

        try {
            ServerSocket server = new ServerSocket(4242);

            //使服务器一直处于监听状态
            while (true) {
                Socket sock = server.accept();
                DataOutputStream out = new DataOutputStream(
                        new BufferedOutputStream(sock.getOutputStream())
                );
                clientOutputStream.add(out);

                //处理每个客户
                new Thread(new ClientHandler(sock)).start();
                //显示消息
                System.out.println(new Date());
                System.out.println("\t建立一个连接");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //发送消息给所有客户
    private void sendToEveryOne(String msg) {
        try {
            for (DataOutputStream writer : clientOutputStream) {
                writer.writeUTF(msg);
                writer.flush();//立刻发送输出流
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 单独处理每个客户的任务
     */
    private class ClientHandler implements Runnable {
        Socket socket = null;
        DataInputStream read = null;

        public ClientHandler(Socket socket) {
            try {
                this.socket = socket;
                read = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream())
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String msg;
            try {
                //从客户端读取所有消息
                while ((msg = read.readUTF()) != null) {
                    System.out.println("read : " + msg);
                    sendToEveryOne(msg);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}