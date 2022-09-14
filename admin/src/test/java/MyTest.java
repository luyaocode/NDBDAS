import com.asurplus.AsurplusApplication;
import com.asurplus.device.entity.DevEParaInfo;
import com.asurplus.device.mapper.DevEParaInfoMapper;
import com.asurplus.system.entity.SysUserRole;
import com.asurplus.system.mapper.SysUserRoleMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * mybatis-plus框架的单元测试
 */
@SpringBootTest(classes = AsurplusApplication.class)
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

}
