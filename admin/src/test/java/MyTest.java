import com.asurplus.App;
import com.asurplus.common.onlyfortest.TestCommand;
import com.asurplus.device.entity.DevEParaInfo;
import com.asurplus.device.mapper.DevEParaInfoMapper;
import com.asurplus.myutil.SqlUtil;
import com.asurplus.system.entity.SysUserRole;
import com.asurplus.system.mapper.SysUserRoleMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * mybatis-plus框架的单元测试
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private DevEParaInfoMapper devEParaInfoMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Test
    public void test01(){
        System.out.println("---DevEParaInfo: selectAll method test---");
        List<DevEParaInfo> eParaList = devEParaInfoMapper.selectList(null);
        Assert.assertEquals(1,eParaList.size());
        eParaList.forEach(System.out::println);
    }



    @Test
    public void test02(){
        System.out.println("---SysUserRole: selectAll method test---");
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(null);
        Assert.assertEquals(1,sysUserRoles.size());
        sysUserRoles.forEach(System.out::println);
    }

    /**
     * selectById 按照id查询
     * selectList 批量查询
     */
    @Test
    public void test03(){
        System.out.println("---DevEParaInfo: selectAll method test---");
        DevEParaInfo devEParaInfo = devEParaInfoMapper.selectById(1);
        System.out.println("查询单条数据："+devEParaInfo);
        List<DevEParaInfo> devEParaInfos = devEParaInfoMapper.selectList(null);
        System.out.println("查询多条数据：");
        devEParaInfos.forEach(System.out::println);

    }

    /**
     * 测试多线程
     * 可以确定的是：
     * 1、@Async注解的方法和没有@Async注解的方法都共享属性
     * 2、有同步关系的就不要加@Async注解，加了异步注解的两个方法将不确定开始和结束的顺序
     * 2、调用异步方法，异步方法执行时间最晚
     *
     */
    @Test
    public void test04() throws InterruptedException {
        ConfigurableApplicationContext application = SpringApplication.run(App.class);
        TestCommand testCommand = application.getBean(TestCommand.class);
        testCommand.getObj();
        testCommand.setObj();
        sleep(2000);
        testCommand.getObj();
        testCommand.setObj();
        sleep(2000);
        testCommand.getObj();

    }

    /**
     * 测试静态方法能否直接调用非静态属性
     * 不行
     */
    @Test
    public void test05(){

    }

    /**
     * 测试sql工具类
     */
    @Test
    public void test06(){
        String recvStr="0808 0000 0006 01 03 fffff ffff";
        SqlUtil.insertUtil(recvStr);
    }

}
