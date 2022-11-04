import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.asurplus.App;
import com.asurplus.common.onlyfortest.TestCommand;
import com.asurplus.device.entity.DevEParaInfo;
import com.asurplus.device.entity.DevLocInfo;
import com.asurplus.device.mapper.DevEParaInfoMapper;
import com.asurplus.device.mapper.DevLocInfoMapper;
import com.asurplus.gateway.mapper.GatewayMapper;
import com.asurplus.myutil.DownloadExcelTemplateUtils;
import com.asurplus.myutil.SqlUtil;
import com.asurplus.system.entity.SysDeptInfo;
import com.asurplus.system.entity.SysUserRole;
import com.asurplus.system.mapper.SysDeptInfoMapper;
import com.asurplus.system.mapper.SysUserRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
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
    @Autowired
    private GatewayMapper gatewayMapper;

    @Test
    public void test01(){
        System.out.println("---DevEParaInfo: selectAll method test---");
        List<DevEParaInfo> eParaList = devEParaInfoMapper.selectList(null);
//        Assert.assertEquals(1,eParaList.size());
        eParaList.forEach(System.out::println);
    }



    @Test
    public void test02(){
        System.out.println("---SysUserRole: selectAll method test---");
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(null);
//        Assert.assertEquals(1,sysUserRoles.size());
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

    /**
     * 调用Mapper接口的方法
     */

    @Test
    public void test07(){
        System.out.println(gatewayMapper.selectCount(null));
    }

    /**
     * 08、测试TreeNode
     */
    @Autowired
    private SysDeptInfoMapper sysDeptInfoMapper;
    @Test
    public void test08(){
        System.out.println("------------获取list-----------------");
        List<SysDeptInfo> list = sysDeptInfoMapper.selectList(null);
        list.forEach(System.out::println);
        System.out.println("--------测试TreeNode-----------");
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        for (SysDeptInfo item : list) {
            nodeList.add(new TreeNode<>(String.valueOf(item.getId()), String.valueOf(item.getPid()), item.getName(), String.valueOf(item.getSort())).setExtra(Dict.create()
                    .set("pid", item.getPid())
                    .set("label", item.getName())
            ));
        }
        // 0表示最顶层的id是0
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        System.out.println(treeList);
    }

    /**
     * 09 测试DevLocInfo
     */
    @Autowired
    private DevLocInfoMapper devLocInfoMapper;
    @Test
    public void test09(){
        System.out.println("-------------list------------------");
        List<DevLocInfo> list = devLocInfoMapper.selectList(null);
        System.out.println(list);
        System.out.println("-------------TreeNode--------------");
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        for (DevLocInfo item : list) {
            TreeNode<String> node = new TreeNode<>(String.valueOf(item.getId()), String.valueOf(item.getPid()), item.getName(), String.valueOf(item.getSort())).setExtra(Dict.create()
                    .set("pid", item.getPid())
                    .set("label", item.getName())
            );
            System.out.println("=====打印====");
            String nodeStr="[id="+node.getId()+",parentId="+node.getParentId()+",weight="+
                    node.getWeight()+",name="+node.getName()+",pid="+node.getExtra()+"]";
            System.out.println(nodeStr);
            nodeList.add(node);
            // 0表示最顶层的id是0
//            node==>nodeList==>treeList根据结点保存的父结点信息构成一棵树
            List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
            System.out.println(treeList);

        }

    }

    /**
     * 10、测试下载excel模板工具类
     */
    @Test
    public void test10() throws IOException {
        DownloadExcelTemplateUtils.downloadFromUrl("https://blog.csdn.net/qq_44182284/article/details/107765912","no10","D:\\sprintboot\\new");
    }

    /**
     * 11、测试ExcelUpload工具类，前端上传的文件到底存放在哪里
     */
    @Test
    public void test11() {
    }

    /**
     * 12、测试list的remove方法
     */
    @Test
    public void test12(){
        List<String> list=new ArrayList<>();
        list.add("happy");
        list.add("sun");
        list.add("pico");
        System.out.println(list);
        list.remove("birthday");
        System.out.println(list);

    }

}
