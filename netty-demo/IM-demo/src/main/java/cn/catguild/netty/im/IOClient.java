package cn.catguild.netty.im;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author liu.zhi
 * @date 2020/12/24 9:15
 */
public class IOClient {

    /**
     * Client客户端连接服务端8000端口每隔2秒向服务端写带有时间戳的 "hello world"
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        socket.getOutputStream().flush();
                        //Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
