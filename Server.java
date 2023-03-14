package cn.edu.guet.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    //main主线程
    public static void main(String[] args) throws IOException {
        /*
        端口号范围：0~65535
        0~1024：操作系统预留（实际项目开发不要用这个范围内的号码）
         */
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();//阻塞方法
        System.out.println("新用户进入");

        GetMessageThread gmt = new GetMessageThread(socket);
        gmt.start();//启动了收消息线程

        SendMessageThread smt=new SendMessageThread(socket);
        smt.start();
    }
}

class SendMessageThread extends Thread {
    Socket socket;
    public SendMessageThread(Socket socket) {
        //此socket是局部变量
        this.socket = socket;
    }
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputStream);//包装流
            while (true) {
                System.out.println("请输入你要发的消息：");
                //如何接受键盘输入？
                String message = scanner.next();
                dos.writeUTF(message);
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class GetMessageThread extends Thread {
    Socket socket;
    public GetMessageThread(Socket socket) {

        this.socket = socket;
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            while (true) {
                // 收消息
                String message = dis.readUTF();
                System.out.println("对方说：" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}