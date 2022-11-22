package com.asurplus.mytcp;

import com.asurplus.myutil.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 应使用线程池管理线程，所以这个线程类不用了。改用注解@Async形式
 *
 * @author chenluyao
 */

public class ThreadReceive extends Thread {
    private Socket socket;
    private static final Logger log = LoggerFactory.getLogger(ThreadReceive.class);

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            StringBuilder recvStr = new StringBuilder();
            int len;
            log.info("开启接收线程");
//            String recvStr; //收到的字符串
//            String[] ss;    //字节码转字符串的数组
            while (true) {
                len = is.read(bytes);
                if (len == -1) {
                    continue;
                }
//                System.out.println(len);
//                ASCII转字符
                for (int i = 0; i < len; i++) {
                    char ch = (char) bytes[i];
                    recvStr.append(ch);
                }
                System.out.println(socket + "响应帧：" + recvStr.toString());
//                提取响应参数，组成合适的sql语句
                String sql = null;
                sql = "insert into test_table(resp_data) values('" + recvStr + "')";
                //                这里调用一个将字符串携带的信息写进数据库的工具类
                SqlUtil.executeUpdate(sql);
                recvStr.delete(0, recvStr.length());
//                recvStr=new String(bytes,0,len);
//                System.out.println(recvStr);
//                ss = FrameUtil.FrameSplit(bytes, len);
//                System.out.println("\n服务端："+recvStr);
//                recvStr解析
//                for (String str : ss) {
//                    System.out.println(str);
//                }
            }
        } catch (IOException e) {
            log.info("接收响应异常！");
        }
    }

    public ThreadReceive(Socket socket) {
        this.socket = socket;
    }

}
