package com.asurplus.device.service.impl;

import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.device.entity.DevInfo;
import com.asurplus.device.entity.DevLocInfo;
import com.asurplus.device.mapper.DevInfoMapper;
import com.asurplus.device.mapper.DevLocInfoMapper;
import com.asurplus.device.service.DevInfoService;
import com.asurplus.myutil.ExcelUpload;
import com.asurplus.myutil.ExcelUtils;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * (DevInfo)表服务实现类
 *
 * @author makejava
 * @since 2022-10-19 14:27:19
 * <p>
 * updateById(devInfo)---根据devInfo的id找到数据库对应的记录，将devInfo的其他信息到原记录上
 * update(devInfo,qw)---根据qw找到数据库对应的记录，将devInfo的信息更新到原记录上
 */
@Service
public class DevInfoServiceImpl extends ServiceImpl<DevInfoMapper, DevInfo> implements DevInfoService {

    @Autowired
    private DevInfoMapper devInfoMapper;
    @Autowired
    private DevLocInfoMapper devLocInfoMapper;

    /**
     * 查询回收站外的设备--
     * devInfo包含了locId，支持根据locId查询设备
     * 包含了devId，支持根据devId查询设备
     * 包含了status，支持根据设备状态查询设备
     *
     * @param devInfo  设备参数
     * @param isExport 是否导出
     * @return TableInfo
     */
    @Override
    public TableInfo list(DevInfo devInfo, boolean isExport) {
//        System.out.println("接收到的devInfo是："+devInfo.toString());
        // 获取分页对象，前端时间信息存入到pageVO的beginTime和endTime属性
//        考虑到原来的PageVO和PageUtils已经满足使用需求，所以直接使用就行了
        PageVO pageVO = PageUtils.getPageVO();

//      获取要查询的初始locId
        Integer locId = devInfo.getLocId();
//        System.out.println("locId=="+locId);

//        去楼层表查询locIds，条件pid=locId
        QueryWrapper<DevLocInfo> wrapper = new QueryWrapper<>();
        // 查询条件
        QueryWrapper<DevInfo> queryWrapper = new QueryWrapper<>();
        List<DevLocInfo> devLocInfos = null;
//        存储locIds的list
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
//      提供locId时，构建locId数组：list2
        if (null != locId) {
            wrapper.eq("pid", locId);
            devLocInfos = devLocInfoMapper.selectList(wrapper);
            wrapper.clear();
//        如果有子结点，就将子结点存到list
            if (!devLocInfos.isEmpty()) {
                for (DevLocInfo info : devLocInfos) {
                    Integer id = info.getId();
                    list1.add(id);//list1: [6,7,8,9,10]
                    list2.add(id);//list2: [6,7,8,9,10]
                }
            } else {
                //            如果没有子结点，就将自身存到list2，直接结束
                list2.add(locId);//list1: [13]
            }
//        遍历list1
//            System.out.println("第一次遍历list=======");
//            System.out.println(list1);
//            System.out.println("第一次遍历list结束=======");
//        如果list1不为空
            if (!list1.isEmpty()) {
                for (Integer i : list1) {
//                System.out.println("locId=="+locId);
                    wrapper.eq("pid", String.valueOf(i));
                    devLocInfos = devLocInfoMapper.selectList(wrapper);
                    wrapper.clear();
//                如果不为空
                    if (!devLocInfos.isEmpty()) {
                        for (DevLocInfo info : devLocInfos) {
                            Integer id = info.getId();
                            list2.add(id);//非叶子结点list2: [6,7,8,9,10, 11,12,13,14,15]
//                        叶子结点list2: [11]
//                            System.out.println(list2);
                        }
                    }
                }
                list2.add(locId);//最后将原locId加入list。list2: [6,7,8,9,10, 11,12,13,14,15, 1]
            }
            //        遍历list2
//            System.out.println("第二次遍历list=======");
//            System.out.println(list2);
//            System.out.println("第二次遍历list结束=======");
        }

//        是否导出
        if (isExport) {
            pageVO.setPageSize(Integer.MAX_VALUE);
        }
//      1、根据devId查询设备
        if (StringUtils.isNotBlank(devInfo.getDevId())) {
            queryWrapper.eq("dev_id", devInfo.getDevId());
        }

//      2、根据locId查询设备
        if (!list2.isEmpty()) {
            queryWrapper.nested(qw -> {

                for (int i = 0; i < list2.size(); i++) {
                    if (i == list2.size() - 1) {
                        qw = qw.eq("loc_id", String.valueOf(list2.get(i)));
                    } else {
                        qw = qw.eq("loc_id", String.valueOf(list2.get(i))).or();
                    }
                }
            });
        }
        /*
         * SELECT COUNT(1) FROM dev_info WHERE (loc_id = ? OR loc_id = ? OR loc_id = ?) AND WHERE (status=?)
         */
        //      3、根据设备状态查询设备
//        重点实现：(a or b or ...) and c,方法是使用QueryWrapper的nested方法将条件用括号括起来
        if (null != devInfo.getStatus()) {
            queryWrapper.eq("status", devInfo.getStatus());
        }

//        4、根据设备创建时间查询
//        要求a.create_time大于等于前端参数createTime，小于等于前端参数endTime
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.ge("create_time", DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.le("create_time", DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }

        //加上delFlag==0
        queryWrapper.eq("del_flag", 0);
        return TableInfo.ok(devInfoMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));

    }

    /**
     * 查询回收站设备
     *
     * @return TableInfo
     */
    @Override
    public TableInfo binList() {
        PageVO pageVO = PageUtils.getPageVO();
        QueryWrapper<DevInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 1);
        return TableInfo.ok(devInfoMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    /**
     * 设备导入
     *
     * @param file          前端excel文件
     * @param updateSupport 是否覆盖原设备
     * @return RES
     */
    @SneakyThrows
    @Override
    public RES importExcel(MultipartFile file, boolean updateSupport) {
        if (null == file) {
            return RES.no("文件上传错误！");
        }
        Date date = new Date();
//        System.out.println("接收到前端请求updateSupport==" + updateSupport);
//        接收MultipartFile file,保存到本地，并返回文件路径
        String fileName = ExcelUpload.uploadFile(file);
//        System.out.println("fileName==" + fileName);
        // 创建Excel文件
        File excelFile = new File(fileName);

//读取单页excel，获取List<HashMap<String, Object>>格式，list里面的每个元素是map对象,形如[{k11=v11,k12=v12,...},{k21=v21,k22=v22,...},...]
        List<HashMap<String, Object>> list = ExcelUtils.parseSingleExcelToMap(excelFile);
//        System.out.println("list==" + list);
        List<DevInfo> devList = new ArrayList<>();//        设备list

        //将excel信息变成DevInfo类对象
//        List<String> devIds = new ArrayList<>();//        设备编号list
        for (HashMap<String, Object> m : list) {
//            设置默认值
            DevInfo devInfo = new DevInfo();
            devInfo.setCreateUser("1");
            devInfo.setCreateTime(date);
            devInfo.setUpdateUser("1");
            devInfo.setUpdateTime(date);
            devInfo.setDelFlag(0);
            devInfo.setStatus(1);
            devInfo.setRemark("");
            for (String k : m.keySet()) {
                if (k.contains("设备编号")) {
                    if (m.get(k) != null) {
                        devInfo.setDevId((String) m.get(k));
                    }
//                    devIds.add((String) m.get(k));
                } else if (k.contains("设备楼层编号")) {
                    if (m.get(k) != null) {
                        devInfo.setLocId(Integer.parseInt((String) m.get(k)));
                    }
                } else if (k.contains("设备名称")) {
                    if (m.get(k) != null) {
                        devInfo.setDevName((String) m.get(k));
                    }
                } else if (k.contains("设备状态")) {
                    devInfo.setStatus(Integer.parseInt((String) m.get(k)));
                } else if (k.contains("删除标记")) {
                    devInfo.setDelFlag(Integer.parseInt((String) m.get(k)));
                } else if (k.contains("备注")) {
                    if (m.get(k) != null) {
                        devInfo.setRemark((String) m.get(k));
                    }
                } else if (k.contains("创建者")) {
                    devInfo.setCreateUser(((String) m.get(k)));
                } else if (k.contains("创建时间")) {
                    devInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) m.get(k)));
                } else if (k.contains("修改者")) {
                    devInfo.setUpdateUser(((String) m.get(k)));
                } else if (k.contains("修改时间")) {
                    devInfo.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) m.get(k)));
                }
            }
            devList.add(devInfo);
        }
//        for (DevInfo d : devList) {
//            System.out.println(d.toString());
//        }
//        System.out.println(devList);
//        System.out.println(devIds);

        QueryWrapper<DevInfo> queryWrapper = new QueryWrapper<>();
        List<DevInfo> devInfos = devInfoMapper.selectList(queryWrapper);//原设备
        List<String> devs = new ArrayList<>();//原设备id
        for (DevInfo e : devInfos) {
            devs.add(e.getDevId());
        }
        for (DevInfo devInfo : devList) {
//            如果原设备不存在，不要求覆盖旧数据，直接插入。
//            如果原设备不存在，且要求覆盖旧数据，就假删除旧数据
//            如果原设备存在，且要求覆盖旧数据，就直接更新。
//            如果原设备存在，不要求覆盖旧数据，不做任何处理。
            if (null == devInfo.getDevId()) {
                continue;//待插入的设备编号为空就不处理
            }
            if (!devs.contains(devInfo.getDevId())) {
//                检测是否和已有设备冲突
                LambdaQueryWrapper<DevInfo> qw = new LambdaQueryWrapper<>();
                qw.eq(DevInfo::getDevId, devInfo.getDevId());
                if (devInfoMapper.selectCount(qw) < 1) {
                    devInfoMapper.insert(devInfo);
                }
                System.out.println("设备已存在！");
            } else {
                devs.remove(devInfo.getDevId());
                if (updateSupport) {
                    queryWrapper.eq("dev_id", devInfo.getDevId());
                    devInfoMapper.update(devInfo, queryWrapper);
                    //                记得清除查询条件
                    queryWrapper.clear();
                }
            }
        }
        if (updateSupport) {
//            回收旧设备
            DevInfo devInfo = new DevInfo();
            devInfo.setDelFlag(1);
            devInfo.setStatus(1);
            devInfo.setUpdateTime(date);

            for (String s : devs) {
                devInfo.setDevId(s);
                queryWrapper.eq("dev_id", s);
                devInfoMapper.update(devInfo, queryWrapper);
                queryWrapper.clear();
//                System.out.println("回收旧设备=="+s);

            }
        }
        return RES.ok();
		/*//读取多页excel，获取List<HashMap<String, Object>>格式
		List<List<HashMap<String, Object>>> lists =ExcelUtils.parseComplexExcel(excelFile);*/
    }

    /**
     * 新增设备
     *
     * @param devInfo 设备参数
     * @return RES
     */
    @Override
    public RES add(DevInfo devInfo) {
//        入参检验
        if (null == devInfo) {
            return RES.no("输入数据错误！");
        }
        if (StringUtils.isBlank(devInfo.getDevId())) {
            return RES.no("请输入设备编号！");
        }
//        未分配位置的设备的locId默认是0
        if (null == devInfo.getLocId()) {
            devInfo.setLocId(0);
        }
        // 查重
        LambdaQueryWrapper<DevInfo> queryWrapper = new LambdaQueryWrapper<>();
//        复习：DevInfo::getDevId 这种写法表示实例化对象并调用后面的方法
        queryWrapper.eq(DevInfo::getDevId, devInfo.getDevId());
        int rows = devInfoMapper.selectCount(queryWrapper);
        if (0 < rows) {
            return RES.no("该设备已经存在");
        }
//        设置默认参数
        Date date = new Date();
        devInfo.setCreateTime(date);
        devInfo.setUpdateTime(date);
        devInfo.setDelFlag(0);
        devInfo.setStatus(1);
        devInfoMapper.insert(devInfo);
        return RES.ok();//成功直接返回：{code=200,msg="操作成功"}
    }

    /**
     * 根据主键查询
     *
     * @param id 主键键值
     * @return RES
     */
    @Override
    public RES getById(Integer id) {
        //        入参检验
        if (null == id) {
            return RES.no("输入数据错误！");
        }
        return RES.ok(devInfoMapper.selectById(id));
    }

    /**
     * 修改设备
     *
     * @param devInfo 设备参数
     * @return RES
     */
    @Override
    public RES update(DevInfo devInfo) {
//        入参检验
        if (null == devInfo) {
            return RES.no("输入数据错误！");
        }
        // 查重
        LambdaQueryWrapper<DevInfo> queryWrapper = new LambdaQueryWrapper<>();
//        复习：DevInfo::getDevId 这种写法表示实例化对象并调用后面的方法
        queryWrapper.eq(DevInfo::getDevId, devInfo.getDevId());
        int rows = devInfoMapper.selectCount(queryWrapper);
        if (rows < 1) {
            return RES.no("该设备不存在");
        }
//        System.out.println("[update]devInfo=="+devInfo);
//        设置更新时间
        devInfo.setUpdateTime(new Date());
        devInfoMapper.updateById(devInfo);
        return RES.ok();
    }

    /**
     * 恢复设备
     *
     * @param ids 设备键值数组
     * @return RES
     */
    @Override
    public RES update(Integer[] ids) {
//        System.out.println("binIds==" + Arrays.toString(ids));
        if (null == ids) {
            return RES.no("输入数据错误！");
        }
        DevInfo devInfo = new DevInfo();
        devInfo.setUpdateTime(new Date());
        devInfo.setDelFlag(0);
        for (Integer id : ids) {
            devInfo.setId(id);
            devInfoMapper.updateById(devInfo);
        }
        return RES.ok();
    }

    /**
     * 回收设备
     *
     * @param ids 设备键值数组
     * @return RES
     */
    @Override
    public RES delete(Integer[] ids) {
//        入参检验

        if (null == ids) {
            return RES.no("输入数据错误！");
        }
//        设置参数
        DevInfo devInfo = new DevInfo();
        Date date = new Date();
        devInfo.setDelFlag(1);
        devInfo.setStatus(1);
        devInfo.setUpdateTime(date);
        for (Integer id : ids) {
            devInfo.setId(id);
            devInfoMapper.updateById(devInfo);
        }
        return RES.ok();
    }

    /**
     * 清除设备
     *
     * @param ids 设备键值数组
     * @return RES
     */
    @Override
    public RES remove(Integer[] ids) {
        if (null == ids) {
            return RES.no("输入数据错误！");
        }
        devInfoMapper.deleteBatchIds(Arrays.asList(ids));
        return RES.ok();
    }

}
