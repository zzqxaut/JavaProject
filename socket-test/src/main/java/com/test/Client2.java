package com.test;


//package jack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;
//import org.omg.CORBA.PRIVATE_MEMBER;
/**
 * 被请求连接时称为服务器；主动连接对方时称为客户端。
 * @author Administrator
 *
 */
public class Client2 extends JFrame implements ActionListener{
    private static int sign = 0; //用来记录选项卡的标签，0标示第一个。
    private JToolBar toolBar1,toolBar2;
    private JLabel waitPortLabel,hostLabel,portLabel;
    private JTextField waitPortTF,hostTF,portTF,sendTF;
    private JTabbedPane tab;
    private JButton sendB,leaveB,deleteB,connB;
    private ArrayList<ChatThread> serverThreads; //存放服务器线程的容器。即存放被请求连接时所创建的线程的容器。
    private ArrayList<ChatThread> clientThreads; //存放客户端线程的容器。即存放主动连接对方时所创建的线程的容器。
    private ArrayList<ServerSocket> servers; //服务对象的容器。
    private ArrayList<MyJTextArea> serverTextAreas; //作为服务器时，存放所创建的对话记录显示区域的容器。
    private ArrayList<MyJTextArea> clientTextAreas; //作为客户端时，岑芳所创建的对话记录显示区域的容器。
    private ArrayList<Socket> serverSockets; //存放被请求连接时所创建的socket的容器。
    private ArrayList<Socket> clientSockets; //存放主动请求连接时所创立的socket的容器。
    private ArrayList<PrintWriter> serverPWriters; //存放服务端输出流对象的容器。
    private ArrayList<BufferedReader> serverBReaders; //存放服务端输入流对象的容器。
    private ArrayList<PrintWriter> clientPWriters; //存放客户端输出流对象的容器。
    private ArrayList<BufferedReader> clientBReaders; //存放客户端输入流对象的容器。
    private ArrayList<Integer> ports; //存放作为服务器时已连接的端口。
    private int port = 2345; //指定自己开放的第一个端口号，方便其他人连接。
    private String name; //储存自己的名称。
    public Client2(String name) throws IOException{
        super(name);
        this.name = name;
        toolBar1 = new JToolBar();
        toolBar2 = new JToolBar();
        waitPortLabel = new JLabel("等待端口");
        hostLabel = new JLabel("主机");
        portLabel = new JLabel("端口");
        waitPortTF = new JTextField("2345");
        hostTF = new JTextField("127.0.0.1");
        portTF = new JTextField(5);
        sendTF = new JTextField();
        tab = new JTabbedPane();
        sendB = new JButton("发送");
        leaveB = new JButton("离线");
        deleteB = new JButton("删除页");
        connB = new JButton("连接端口");
        servers = new ArrayList<ServerSocket>();
        serverTextAreas = new ArrayList<MyJTextArea>();
        clientTextAreas = new ArrayList<MyJTextArea>();
        serverSockets = new ArrayList<Socket>();
        clientSockets = new ArrayList<Socket>();
        serverPWriters = new ArrayList<PrintWriter>();
        serverBReaders = new ArrayList<BufferedReader>();
        clientPWriters = new ArrayList<PrintWriter>();
        clientBReaders = new ArrayList<BufferedReader>();
        serverThreads = new ArrayList<ChatThread>();
        clientThreads = new ArrayList<ChatThread>();
        ports = new ArrayList<Integer>();
        toolBar1.add(waitPortLabel);
        toolBar1.add(waitPortTF);
        toolBar1.add(hostLabel);
        toolBar1.add(hostTF);
        toolBar1.add(portLabel);
        toolBar1.add(portTF);
        toolBar1.add(connB);
        toolBar2.add(sendTF);
        toolBar2.add(sendB);
        toolBar2.add(leaveB);
        toolBar2.add(deleteB);
        waitPortTF.setEnabled(false); //设置等待的textfield不可以编辑。
        hostTF.setEnabled(false); //设置连接的ip地址不可编辑，当然这里可以更改成其他电脑的ip地址。
        this.getContentPane().add(toolBar1, "North"); //添加工具栏到最上方。
        this.getContentPane().add(tab,"Center"); //添加选项卡窗格。
        this.getContentPane().add(toolBar2,"South"); //添加工具栏到下方。
        this.setBounds(200, 200, 350, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sendB.addActionListener(this);
        leaveB.addActionListener(this);
        deleteB.addActionListener(this);
        connB.addActionListener(this);
//主线程进入之后 在server.accept()阻塞等待客户端来连接。
        while(true){
            ServerSocket server = new ServerSocket(port); //作为服务器，开发自己供连接的端口。
            servers.add(server);
            Socket serverSocket = server.accept(); //等待对方连接。
            serverSockets.add(serverSocket);
            ports.add(port); //将已连接的端口加入容器。
            PrintWriter serverPWriter = new PrintWriter(serverSocket.getOutputStream(),true);//初始化输出流对象。
            serverPWriters.add(serverPWriter);
            BufferedReader serverBReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));//初始化输入流对象。
            serverBReaders.add(serverBReader);
            serverPWriter.println("连接"+name+"成功"); //将连接成功的消息发送给请求方，提醒对方连接成功。
            serverPWriter.println(name); //将自己的名称发送给对方，方便对方设置选项卡的名称。
            String content = serverBReader.readLine(); //此时对方也发送了上面两条消息过来。读入对方发送过来的提醒消息。
            String name2 = serverBReader.readLine(); //读取对方的名称。方便后面设置选项卡的名称。
            System.out.println(content);
            System.out.println(name2);
            MyJTextArea serverTextArea = new MyJTextArea(sign);
            sign++; //new了一个textArea后，sign自动增加1，好和选项卡对应，
//知道这个选项卡加到哪个容器了，是服务器的还是客户端的。
            serverTextAreas.add(serverTextArea);
            this.tab.addTab(name2,new JScrollPane(serverTextArea));//在选项卡窗格中添加一个选项卡。
            serverTextArea.setEditable(false); //设置对话记录显示区域不可编辑。
            serverTextArea.setLineWrap(true); //设置对话记录显示区域自动换行。
            serverTextArea.append(content+"\n"); //在对话记录区域输出连接成功这条消息。
            ChatThread thread = new ChatThread(); //new了一个线程去执行run方法，用于和对方交流，对方也会开启一个线程来和你交流。
//这里要开启新线程的原因是main线程经过一轮后会在上面accept方法处阻塞。
            serverThreads.add(thread);
            thread.start(); //启动该线程，方便接收对方发来的消息。
            port++; //端口号加一，开放下一个供连接的端口。
            waitPortTF.setText(port+""); //更新显示等待的下个端口。
        }
    }
    private class ChatThread extends Thread {
        private String[] serverContents = new String[10]; //当作为服务端时，用来存放相互发送消息时的一句话。
        private String[] clientContents = new String[10]; //当作为客户端时，用来存放相互发送消息时的一句话。
        private boolean isServerThread = true; //判断当前在执行run方法的线程是不是服务端线程。
        @Override
        public void run() {
            while(true){
                if(serverThreads.size()>0){ //判断当前的线程是否是服务线程。先判断是否大于0，是为了防止serverThreads
                    for(int i=0;i<serverThreads.size();i++){ //报数组越界。
                        if(Thread.currentThread() == serverThreads.get(i)){ //拿当前线程和服务端容器里的线程去比，看是否是服务端的线程。
                            isServerThread = true;
                        }
                    }
                }
                if(clientThreads.size()>0){ //判断当前的线程是否是客户线程。 先判断是否大于0，是为了防止clientThreads
                    for(int i=0;i<clientThreads.size();i++){ //报数组越界。
                        if(Thread.currentThread() == clientThreads.get(i)){ //拿当前线程和客户端容器里的线程去比，看是否是客户端的线程。
                            isServerThread = false;
                        }
                    }
                }
                if(isServerThread){ //如果是服务端的线程，将readline方法接受到的值赋给相应的content。
                    for(int i=0;i<serverThreads.size();i++){
                        if(Thread.currentThread() == serverThreads.get(i)){ //判断具体是服务端里的那条线程。
                            try {
                                serverContents[i] = serverBReaders.get(i).readLine();//将对方发送过来的消息赋值给这条线程的接受消息字符串。
                            } catch (IOException e) {
                                e.printStackTrace();
                                return; //出现异常时直接退出方法。
                            }
                            if(serverContents[i]==null){ //在自己点击离线按钮时，serverContents[i]为null，
                                return; //因此在这里进行处理，避免后面报错。
                            }
                            if(serverContents[i].equals("关闭")){ //接收到对方因点击离开按钮而发出的消息“离开”，关闭自己的连接。
                                sendTF.setText("已断开连接");
                                serverPWriters.get(i).close();
                                try {
                                    serverBReaders.get(i).close();
                                    serverSockets.get(i).close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return; //关闭完后退出。
                            }
                            serverTextAreas.get(i).append(serverContents[i]+"\n");//将接受到的消息显示在自己的对话记录区域。
                            break;
                        }
                    }
                }else{ //如果是客户线程，将readline方法接受到的值赋给相应的content。
                    for(int i=0;i<clientThreads.size();i++){
                        if(Thread.currentThread() == clientThreads.get(i)){ //判断具体是客户端中的哪一条线程。
                            try {
                                clientContents[i] = clientBReaders.get(i).readLine();//拿到对方发送过来的消息并保存给自己的字符串。
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if(clientContents[i] == null){ //当自己点击离线按钮时，clientContents[i]会为null，
                                return; //为了防止下面报错，在这里进行处理。
                            }
                            if(clientContents[i].equals("关闭")){ //接收到对方因点击离线按钮而发出的消息“关闭”，而关闭自己的连接。
                                sendTF.setText("已断开连接");
                                clientPWriters.get(i).close();
                                try {
                                    clientBReaders.get(i).close();
                                    clientSockets.get(i).close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return;
                            }
                            clientTextAreas.get(i).append(clientContents[i]+"\n");//作为客户端时，将接受到的消息显示在对话记录显示区域。
                            break;
                        }
                    }
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "连接端口": //如果是连接端口，则执行以下操作。
                try {
                    Socket clientSocket = new Socket(hostTF.getText(),Integer.parseInt(portTF.getText()));//拿到工具栏一中填的端口号并生成socket去连接对方。
                    clientSockets.add(clientSocket);
                    PrintWriter clientPWriter = new PrintWriter(clientSocket.getOutputStream(),true);//初始化输出流对象。
                    clientPWriters.add(clientPWriter);
                    BufferedReader clientBReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//初始化输入流对象。
                    clientBReaders.add(clientBReader);
                    clientPWriter.println("连接"+name+"成功"); //将连接成功的消息发送给对方，提醒对方连接成功。
                    clientPWriter.println(name); //将自己的名称发送给对方，方便对方设置选项卡的名称。
                    System.out.println(11111111);
                    String content = clientBReader.readLine(); //读入对方发送过来的提醒消息。对方已执行了上面两条语句，发送了对应的消息。
                    System.out.println(22222222);
                    String name2 = clientBReader.readLine(); //读取对方的名称。
                    System.out.println(content);
                    System.out.println(name2);
                    MyJTextArea clientTextArea = new MyJTextArea(sign);
                    sign++; //配和选项卡的index，记录每个选项卡加到那个容器里去了，是服务器的容器还是客户端的容器。
                    clientTextAreas.add(clientTextArea);
                    this.tab.addTab(name2,new JScrollPane(clientTextArea)); //在选项卡窗格中添加一个选项卡。
                    clientTextArea.setEditable(false); //设置对话记录区域不可编辑。
                    clientTextArea.setLineWrap(true); //设置对话记录区域自动换行。
                    clientTextArea.append(content+"\n"); //在对话记录区域显示连接成功这条消息。
                    ChatThread clientThread = new ChatThread();
                    clientThreads.add(clientThread);
                    clientThread.start(); //启动该线程，方便和对方相互发送消息，因为主线程已经在上面accept（）处阻塞。
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case "发送":
                if(serverTextAreas.size()>0){ //如果是被请求连接时而创建的选项卡要发送消息。即服务端。
                    for(int i=0;i<serverTextAreas.size();i++){
                        if(tab.getSelectedIndex() == serverTextAreas.get(i).getSign()){ //通过获取当前选项卡的index去和jtextArea的sign比，因为他们的index和sign是匹配的，
                            String sendContent = sendTF.getText(); //从而确定它是服务端的哪条线程,该用那对输入输出流进行发送和接受消息。
                            if(serverSockets.get(i).isClosed()){ //如果已断开连接,则直接返回。
                                sendTF.setText("已断开连接");
                                return;
                            }
                            if(sendContent.equals("")){ //如果发送的内容为空则直接接结束。
                                sendTF.setText("请输入内容");
                                return;
                            }else{
                                serverPWriters.get(i).println(name+": "+sendContent); //将发送消息框中的消息发送出去，并在前面加上自己的姓名。
                                serverTextAreas.get(i).append("我: "+sendContent+"\n");//在自己的对话记录区域加上这句话。
                                sendTF.setText(""); //将发送消息框中的数据清空。
                                return;
                            }
                        }
                    }
                }
                if(clientTextAreas.size()>0){ //如果是客户端。
                    for(int i=0;i<clientTextAreas.size();i++){
                        if(tab.getSelectedIndex() == clientTextAreas.get(i).getSign()){ //通过获取当前选项卡的index去和jtextArea的sign比，因为他们的index和sign是匹配的，
                            String sendContent = sendTF.getText(); //从而确定它是客户端的哪条线程,该用那对输入输出流进行发送和接受消息。
                            if(clientSockets.get(i).isClosed()){ //如果连接已断开则直接返回。
                                sendTF.setText("已断开连接");
                                return;
                            }
                            if(sendContent.equals("")){ //如果发送的内容为空则直接接结束。
                                sendTF.setText("请输入内容");
                                return;
                            }else{
                                clientPWriters.get(i).println(name+": "+sendContent); //将发送消息框中的消息发送出去，并在前面加上自己的姓名。
                                clientTextAreas.get(i).append("我: "+sendContent+"\n"); //在自己的对话记录区域加上这句话
                                sendTF.setText(""); //将发送消息框中的数据清空。
                                return;
                            }
                        }
                    }
                }
                break;
            case "离开":
                if(serverTextAreas.size()>0){ //如果是服务端。
                    for(int i=0;i<serverTextAreas.size();i++){
                        if(tab.getSelectedIndex() == serverTextAreas.get(i).getSign()){ //更前面一样的道理，判断是服务端的那个选项卡需要闭关。
                            serverPWriters.get(i).println("关闭"); //发送关闭消息，提醒对方也要关闭该socket连接。
                            sendTF.setText("已断开连接");
                            serverPWriters.get(i).close();
                            try {
                                serverBReaders.get(i).close();
                                serverSockets.get(i).close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        }
                    }
                }
                if(clientTextAreas.size()>0){ //如果是客户端。
                    for(int i=0;i<clientTextAreas.size();i++){
                        if(tab.getSelectedIndex() == clientTextAreas.get(i).getSign()){ //更前面一样的道理，判断是客户端的那个选项卡需要闭关。
                            clientPWriters.get(i).println("关闭"); //发送关闭消息，提醒对方也要关闭该socket连接。
                            sendTF.setText("已断开连接");
                            clientPWriters.get(i).close();
                            try {
                                clientBReaders.get(i).close();
                                clientSockets.get(i).close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        }
                    }
                }
                break;
            case "删除页":
                if(serverTextAreas.size()>0){ //为了防止下面的serverTextAreas数组越界。
                    for(int i=0;i<serverTextAreas.size();i++){
                        if(tab.getSelectedIndex() == serverTextAreas.get(i).getSign()){ //跟上面一样的道理，判断当前选项卡是属于服务端还是客户端。
                            if(serverSockets.get(i).isClosed()){ //先判断是否断开连接，否则不允许关闭。
                                tab.remove(i); //删除当前选项卡。
                            }else{
                                sendTF.setText("请先关闭当前的连接");
                                return;
                            }
                        }
                    }
                }
                if(clientTextAreas.size()>0){ //为了防止下面的serverTextAreas数组越界。
                    for(int i=0;i<clientTextAreas.size();i++){
                        if(tab.getSelectedIndex() == clientTextAreas.get(i).getSign()){ //跟上面一样的道理，判断当前选项卡是属于服务端还是客户端。
                            if(clientSockets.get(i).isClosed()){ //先判断是否断开连接，否则不允许关闭。
                                tab.remove(i); //删除当前选项卡。
                            }else{
                                sendTF.setText("请先关闭当前的连接");
                                return;
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
    public static void main(String[] args) throws IOException{
        new Client2("zzq");
    }
}
