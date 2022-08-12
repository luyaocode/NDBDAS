package com.asurplus.tool.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asurplus.system.vo.PageVO;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.consts.CommonConstants;
import com.asurplus.common.exception.CustomException;
import com.asurplus.common.utils.Convert;
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.PageUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.tool.entity.GenTable;
import com.asurplus.tool.entity.GenTableColumn;
import com.asurplus.tool.mapper.GenTableColumnMapper;
import com.asurplus.tool.mapper.GenTableMapper;
import com.asurplus.tool.service.GenTableService;
import com.asurplus.tool.utils.GenConstants;
import com.asurplus.tool.utils.GenUtils;
import com.asurplus.tool.utils.VelocityInitializer;
import com.asurplus.tool.utils.VelocityUtils;
import com.asurplus.tool.vo.GenTableUpdateVO;
import com.asurplus.tool.vo.GenTableVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 代码生成业务表 服务实现类
 * </p>
 *
 * @author lizhou
 * @since 2021-07-21
 */
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements GenTableService {

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    @Override
    public TableInfo list(GenTable genTable) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<GenTable> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(genTable.getTableName())) {
            queryWrapper.lambda().like(GenTable::getTableName, genTable.getTableName());
        }
        if (StringUtils.isNotBlank(genTable.getTableComment())) {
            queryWrapper.lambda().like(GenTable::getTableComment, genTable.getTableComment());
        }
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(GenTable::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(GenTable::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(GenTable::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.selectPage(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper));
    }

    @Override
    public TableInfo dbList(GenTable genTable) {
        // 获取分页对象
        PageVO pageVO = PageUtils.getPageVO();
        // 查询条件
        QueryWrapper<GenTable> queryWrapper = new QueryWrapper<>();
        // 时间段
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(GenTable::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(GenTable::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // 排序
        if (StringUtils.isNotBlank(pageVO.getField())) {
            if (pageVO.getIsAsc()) {
                queryWrapper.orderByAsc(pageVO.getField());
            } else {
                queryWrapper.orderByDesc(pageVO.getField());
            }
        } else {
            queryWrapper.lambda().orderByDesc(GenTable::getCreateTime);
        }
        return TableInfo.ok(this.baseMapper.dbList(new Page<>(pageVO.getPageNum(), pageVO.getPageSize()), queryWrapper, genTable));
    }

    @Override
    public RES saveImportTable(String tables) {
        if (StringUtils.isBlank(tables)) {
            return RES.no("请选择需要操作的数据");
        }
        String[] tableNames = Convert.toStrArray(tables);
        List<GenTable> list = this.baseMapper.selectDbTableListByNames(tableNames);
        if (CollectionUtil.isEmpty(list)) {
            return RES.no("表结构不存在");
        }
        Integer userId = StpUtil.getLoginIdAsInt();
        for (GenTable table : list) {
            String tableName = table.getTableName();
            GenUtils.initTable(table, userId);
            int row = this.baseMapper.insert(table);
            if (row > 0) {
                // 保存列信息
                List<GenTableColumn> genTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);
                for (GenTableColumn column : genTableColumns) {
                    GenUtils.initColumnField(column, table);
                    genTableColumnMapper.insert(column);
                }
            }
        }
        return RES.ok();
    }

    @Override
    public RES previewCode(Integer tableId) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        // 查询表信息
        GenTableVO table = this.baseMapper.getGenTableVO(tableId);
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);
        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CommonConstants.UTF8);
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return RES.ok(dataMap);
    }

    @Override
    public GenTableVO selectGenTableById(Integer id) {
        GenTableVO genTableVO = this.baseMapper.getGenTableVO(id);
        setTableFromOptions(genTableVO);
        return genTableVO;
    }

    @Override
    public List<GenTableVO> selectGenTableAll() {
        return this.baseMapper.selectGenTableAll();
    }

    /**
     * 设置代码生成其他选项值
     *
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(GenTableVO genTable) {
        JSONObject paramsObj = JSONObject.parseObject(genTable.getOptions());
        if (null != paramsObj) {
            String treeCode = paramsObj.getString(GenConstants.TREE_CODE);
            String treeParentCode = paramsObj.getString(GenConstants.TREE_PARENT_CODE);
            String treeName = paramsObj.getString(GenConstants.TREE_NAME);
            String parentMenuId = paramsObj.getString(GenConstants.PARENT_MENU_ID);
            String parentMenuName = paramsObj.getString(GenConstants.PARENT_MENU_NAME);
            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
            genTable.setParentMenuId(parentMenuId);
            genTable.setParentMenuName(parentMenuName);
        }
    }

    @Override
    public byte[] downloadCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            generatorCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public void generatorCode(String tableName) {
        // 查询表信息
        GenTableVO table = this.baseMapper.selectGenTableByName(tableName);
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            if (!StringUtils.containsAny(template, "sql.vm", "api.js.vm", "index.vue.vm", "index-tree.vue.vm")) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, CommonConstants.UTF8);
                tpl.merge(context, sw);
                try {
                    String path = getGenPath(table, template);
                    FileUtils.writeStringToFile(new File(path), sw.toString(), CommonConstants.UTF8);
                } catch (IOException e) {
                    throw new CustomException("渲染模板失败，表名：" + table.getTableName());
                }
            }
        }
    }

    @Override
    public void updateSynchDb(String tableName) {
        GenTableVO table = this.baseMapper.selectGenTableByName(tableName);
        List<GenTableColumn> tableColumns = table.getColumns();
        List<String> tableColumnNames = tableColumns.stream().map(GenTableColumn::getColumnName).collect(Collectors.toList());

        List<GenTableColumn> dbTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);
        if (CollectionUtil.isEmpty(dbTableColumns)) {
            throw new CustomException("同步数据失败，原表结构不存在");
        }
        List<String> dbTableColumnNames = dbTableColumns.stream().map(GenTableColumn::getColumnName).collect(Collectors.toList());

        dbTableColumns.forEach(column -> {
            if (!tableColumnNames.contains(column.getColumnName())) {
                GenUtils.initColumnField(column, table);
                genTableColumnMapper.insert(column);
            }
        });

        List<GenTableColumn> delColumns = tableColumns.stream().filter(column -> !dbTableColumnNames.contains(column.getColumnName())).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(delColumns)) {
            genTableColumnMapper.deleteBatchIds(delColumns);
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param table    业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(GenTableVO table, String template) {
        String genPath = table.getGenPath();
        if (StringUtils.equals(genPath, "/")) {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(template, table);
        }
        return genPath + File.separator + VelocityUtils.getFileName(template, table);
    }

    @Override
    public RES update(GenTableUpdateVO genTable) {
        RES res = this.validateEdit(genTable);
        if (!res.isSuccess()) {
            return res;
        }
        String options = JSON.toJSONString(genTable.getParams());
        genTable.setOptions(options);
        int row = this.baseMapper.updateById(genTable);
        if (row > 0) {
            for (GenTableColumn cenTableColumn : genTable.getColumns()) {
                genTableColumnMapper.updateById(cenTableColumn);
            }
        }
        return RES.ok();
    }

    @Override
    public RES validateEdit(GenTableUpdateVO genTable) {
        if (GenConstants.TPL_TREE.equals(genTable.getTplCategory())) {
            String options = JSON.toJSONString(genTable.getParams());
            JSONObject paramsObj = JSONObject.parseObject(options);
            if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_CODE))) {
                return RES.no("树编码字段不能为空");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_PARENT_CODE))) {
                return RES.no("树父编码字段不能为空");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_NAME))) {
                return RES.no("树名称字段不能为空");
            } else if (GenConstants.TPL_SUB.equals(genTable.getTplCategory())) {
                if (StringUtils.isEmpty(genTable.getSubTableName())) {
                    return RES.no("关联子表的表名不能为空");
                } else if (StringUtils.isEmpty(genTable.getSubTableFkName())) {
                    return RES.no("子表关联的外键名不能为空");
                }
            }
        }
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("请选择需要操作的数据");
        }
        LambdaQueryWrapper<GenTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(GenTable::getTableId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper);
        return RES.ok();
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
        // 查询表信息
        GenTableVO table = this.baseMapper.selectGenTableByName(tableName);
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CommonConstants.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, CommonConstants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 设置主子表信息
     *
     * @param table 业务表信息
     */
    public void setSubTable(GenTableVO table) {
        String subTableName = table.getSubTableName();
        if (StringUtils.isNotEmpty(subTableName)) {
            table.setSubTable(this.baseMapper.selectGenTableByName(subTableName));
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(GenTableVO table) {
        for (GenTableColumn column : table.getColumns()) {
            if ("1".equals(column.getIsPk())) {
                table.setPkColumn(column);
                break;
            }
        }
        if (null == table.getPkColumn()) {
            table.setPkColumn(table.getColumns().get(0));
        }
        if (GenConstants.TPL_SUB.equals(table.getTplCategory())) {
            for (GenTableColumn column : table.getSubTable().getColumns()) {
                if ("1".equals(column.getIsPk())) {
                    table.getSubTable().setPkColumn(column);
                    break;
                }
            }
            if (null == table.getSubTable().getPkColumn()) {
                table.getSubTable().setPkColumn(table.getSubTable().getColumns().get(0));
            }
        }
    }
}
