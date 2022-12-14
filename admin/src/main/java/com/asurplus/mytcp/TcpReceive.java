package com.asurplus.mytcp;

import com.asurplus.gateway.service.GatewayInfoService;
import com.asurplus.myutil.Dict;
import com.asurplus.myutil.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

/**
 * @author chenluyao
 */
@Component
public class TcpReceive {
    private static final Logger log = LoggerFactory.getLogger(TcpReceive.class);
    @Autowired
    private GatewayInfoService gatewayInfoService;

    @Async("taskExecutor")
    public void receive(Socket socket) {
        if (socket == null || TcpServer.isClientClose(socket)) {
            return;
        }
        String ip = socket.getInetAddress().getHostAddress();
        Integer port = socket.getPort();
        InputStream is = null;
        try {
            is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            StringBuilder recvStr = new StringBuilder();
            int len;
            log.info("开启接收线程" + socket);
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
                log.info("响应帧：" + recvStr.toString() + socket);
//                提取响应参数，组成合适的sql语句
                String sql = null;
                sql = "insert into test_table(resp_data) values('" + recvStr + "')";
                //                这里调用一个将字符串携带的信息写进数据库的工具类
                SqlUtil.executeUpdate(sql);
//                解析帧
                Map<String, String> map = FrameUtil.frameSplit(recvStr.toString());
                if (map != null) {
//                  如果是读保持寄存器
                    if (Dict.FUN_CODE_READ.equals(map.get(Dict.FUN_CODE))) {
//                      读设备编号，保存到表gateway_info

                    }

                }
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
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
