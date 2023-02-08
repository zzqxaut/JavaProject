package com.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * 聊天程序的客户端
 */
public class Client extends Application {
    TextArea ta = new TextArea();
    TextField tf = new TextField();
    DataInputStream in = null;
    DataOutputStream out = null;
    Socket socket;

    @Override
    public void start(Stage primaryStage) {
        //画图
        BorderPane mainPane = new BorderPane();

        mainPane.setCenter(new ScrollPane(ta));
        ta.setPrefColumnCount(60);
        ta.setPrefRowCount(80);
        ta.setEditable(false);

        Button bt = new Button("Send");
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(tf, bt);
        tf.setPrefColumnCount(50);
        mainPane.setBottom(hbox);

        //初始化流
        setUpNetWorking();

        //设置GUI事件 -- lambda()
        bt.setOnAction(event -> {
            try {
                out.writeUTF(tf.getText());
                out.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //清空文本域
            tf.setText("");
        });

        //创建线程单独处理接收消息
        //和用户与GUI交互分开
        Thread readThread = new Thread(() -> {
            String message;
            //反复读取服务器发送的内容
            try {
                while ((message = in.readUTF()) != null) {
                    System.out.println("read : " + ta.getText());
                    ta.appendText(new Date() + "\n");
                    ta.appendText("Client:" + message + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        readThread.start();

        //显示
        Scene scene = new Scene(mainPane, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client");
        primaryStage.show();
    }

    //初始化流
    private void setUpNetWorking() {
        try {
            socket = new Socket("127.0.0.1", 4242);
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            System.out.println("时间:" + new Date());
            System.out.println("\t连接建立!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
