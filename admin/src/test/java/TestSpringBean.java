import com.asurplus.App;
import com.asurplus.device.entity.DevGatewayInfo;
import com.asurplus.myutil.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootTest(classes = App.class)
public class TestSpringBean {
    @Autowired
    private ApplicationContext ctx;
    @Test
    public void test(){
//        No qualifying bean of type 'com.asurplus.device.entity.DevGatewayInfo' available
//        因为没有将这个类放进容器
        DevGatewayInfo bean = ctx.getBean(DevGatewayInfo.class);
        log.info("bean=="+bean);
        DevGatewayInfo bean1 = SpringUtil.getBean(DevGatewayInfo.class);
        DevGatewayInfo bean2 = SpringUtil.getBean(DevGatewayInfo.class);
        log.info("bean1=="+bean1);
        log.info("bean2=="+bean2);

    }

}
