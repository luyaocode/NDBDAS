package com.asurplus.device.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.excel.ExportExcelUtil;
import com.asurplus.common.utils.RES;
import com.asurplus.device.entity.DevInfo;
import com.asurplus.device.service.DevInfoService;
import com.asurplus.system.vo.TableInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DevInfo)表控制层
 *
 * @author makejava
 * @since 2022-10-19 14:27:10
 */
@RestController
@RequestMapping("/device/management")
public class DevInfoController {
    /**
     * 服务对象
     */
    @Resource
    private DevInfoService devInfoService;

    /**
     * 1、查询所有设备信息，返回TableInfo对象
     *
     * @param devInfo
     * @return
     */
    @SaCheckPermission("system:user:list")
    @GetMapping("/list")
    public TableInfo list(DevInfo devInfo) {
//        String locId = devInfo.getLocId();
//        System.out.println("前端获取的locId=="+locId);
        TableInfo table = devInfoService.list(devInfo, false);
        System.out.println("查询到的设备信息[TableInfo对象]:" + table);
        return table;
    }

    /**
     * 2、增加设备
     *
     * @RequestBody :接收参数
     */
    @SysLog(title = "设备管理", type = BusinessType.INSERT)
    @SaCheckPermission("system:user:list")
    @PostMapping
    public RES add(@RequestBody DevInfo devInfo) {
//        System.out.println("接收到的devInfo是："+devInfo);

        RES res = devInfoService.add(devInfo);
//        System.out.println("增加设备结果res.data=="+res.toString());
//        增加设备结果res.data=={msg=操作成功, code=1}
        return res;
    }

    /**
     * 3、根据记录编号查询设备信息
     */
    @SaCheckPermission("system:user:list")
    @GetMapping("/{id}")
    public RES get(@PathVariable Integer id) {
        RES res = devInfoService.getById(id);
//        System.out.println("res=="+res.toString());
        return res;
    }

    /**
     * 4、修改设备信息
     *
     * @param devInfo
     * @return
     */
    @SysLog(title = "设备管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:user:list")
    @PutMapping
    public RES update(@RequestBody DevInfo devInfo) {
        RES res = devInfoService.update(devInfo);
//        System.out.println("updateRes=="+res);
        return res;
    }

    /**
     * 5、删除设备
     *
     * @param ids
     * @return
     */
    @SysLog(title = "设备管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:user:list")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable("ids") Integer[] ids) {
//        for (Integer id:ids){
//            System.out.println("id=="+id);
//        }
        return devInfoService.delete(ids);
    }

    /**
     * 6、查询已删除设备信息
     *
     * @param
     * @return
     */
    @SaCheckPermission("system:user:list")
    @GetMapping("/bin/list")
    public TableInfo binList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
//        System.out.println("接收到前端的参数："+pageNum+","+pageSize);
        TableInfo table = devInfoService.binList();//分页参数在new PageVO对象的时候获取到了，所以这里不需要传递进去
//        System.out.println("查询到的已删除设备信息[TableInfo对象]:"+ table);
        return table;
    }

    /**
     * 7、清除回收站
     *
     * @param ids
     * @return
     */
    @SysLog(title = "设备管理", type = BusinessType.DELETE)
    @SaCheckPermission("system:user:list")
    @DeleteMapping("/bin/{ids}")
    public RES remove(@PathVariable("ids") Integer[] ids) {
        return devInfoService.remove(ids);
    }

    /**
     * 8、恢复回收站
     */
    @SysLog(title = "设备管理", type = BusinessType.UPDATE)
    @SaCheckPermission("system:user:list")
    @GetMapping("/bin/{ids}")
    public RES restore(@PathVariable("ids") Integer[] ids) {
        return devInfoService.update(ids);

    }

    /**
     * 9、导出设备信息excel表
     */
    @SysLog(title = "设备管理", type = BusinessType.EXPORT)
    @SaCheckPermission("system:user:list")
    @GetMapping("/export")
    public void exportXls(DevInfo devInfo) {
        List<?> list = devInfoService.list(devInfo, true).getRows();
        ExportExcelUtil.exportExcel(list, DevInfo.class, "设备信息表", "设备信息统计");
    }

    /**
     * 10、导入设备信息表
     */
    @SysLog(title = "设备管理", type = BusinessType.IMPORT)
    @SaCheckPermission("system:user:list")
    @PostMapping("/importData")
    public RES importData(@RequestParam(name = "file", required = false) MultipartFile file, @RequestParam(name = "updateSupport") Boolean updateSupport) {
        return devInfoService.importExcel(file,updateSupport);
    }
}

