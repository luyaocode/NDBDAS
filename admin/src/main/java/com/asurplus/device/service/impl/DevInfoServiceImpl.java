package com.asurplus.device.service.impl;

import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.device.entity.DevInfo;
import com.asurplus.device.entity.DevLocInfo;
import com.asurplus.device.mapper.DevInfoMapper;
import com.asurplus.device.mapper.DevLocInfoMapper;
import com.asurplus.device.service.DevInfoService;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * (DevInfo)表服务实现类
 *
 * @author makejava
 * @since 2022-10-19 14:27:19
 */
@Service
public class DevInfoServiceImpl extends ServiceImpl<DevInfoMapper, DevInfo> implements DevInfoService {

    @Autowired
    private DevInfoMapper devInfoMapper;
    @Autowired
    private DevLocInfoMapper devLocInfoMapper;

    @Override
    /**
     * devInfo包含了locId，支持根据locId查询设备
     * 包含了devId，支持根据devId查询设备
     * 包含了status，支持根据设备状态查询设备
     */
    public TableInfo list(DevInfo devInfo, boolean isExport) {
//        System.out.println("接收到的devInfo是："+devInfo.toString());
        // 获取分页对象，前端时间信息存入到pageVO的beginTime和endTime属性
//        考虑到原来的PageVO和PageUtils已经满足使用需求，所以直接使用就行了
        PageVO pageVO = PageUtils.getPageVO();

//      获取要查询的初始locId
        String locId = devInfo.getLocId();
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
                list2.add(Integer.valueOf(locId));//list1: [13]
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
                list2.add(Integer.valueOf(locId));//最后将原locId加入list。list2: [6,7,8,9,10, 11,12,13,14,15, 1]
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

    @Override
    public TableInfo binList() {
        PageVO pageVO = PageUtils.getPageVO();
        System.out.println("查询回收站参数"+pageVO.getPageNum()+","+pageVO.getPageSize());
        QueryWrapper<DevInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 1);
        return TableInfo.ok(devInfoMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public RES add(DevInfo devInfo) {
//        入参检验
        if (null == devInfo) {
            return RES.no("输入数据错误！");
        }
        if (StringUtils.isBlank(devInfo.getDevId())) {
            return RES.no("请输入设备编号！");
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
        devInfoMapper.insert(devInfo);
        return RES.ok();//成功直接返回：{code=200,msg="操作成功"}
    }

    @Override
    public RES getById(Integer id) {
        //        入参检验
        if (null == id) {
            return RES.no("输入数据错误！");
        }
        return RES.ok(devInfoMapper.selectById(id));
    }

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

    @Override
    public RES update(Integer[] ids) {
        System.out.println("binIds=="+ Arrays.toString(ids));
        if(null==ids){
            return RES.no("输入数据错误！");
        }
        DevInfo devInfo=new DevInfo();
        devInfo.setUpdateTime(new Date());
        devInfo.setDelFlag(0);
        for(Integer id:ids){
            devInfo.setId(id);
            devInfoMapper.updateById(devInfo);
        }
        return RES.ok();
    }

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

    @Override
    public RES remove(Integer[] ids) {
        if (null == ids) {
            return RES.no("输入数据错误！");
        }
        devInfoMapper.deleteBatchIds(Arrays.asList(ids));
        return RES.ok();
    }
}
