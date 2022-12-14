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
 * ไปฃ็ ็ๆไธๅก่กจ ๆๅกๅฎ็ฐ็ฑป
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
        // ่ทๅๅ้กตๅฏน่ฑก
        PageVO pageVO = PageUtils.getPageVO();
        // ๆฅ่ฏขๆกไปถ
        QueryWrapper<GenTable> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(genTable.getTableName())) {
            queryWrapper.lambda().like(GenTable::getTableName, genTable.getTableName());
        }
        if (StringUtils.isNotBlank(genTable.getTableComment())) {
            queryWrapper.lambda().like(GenTable::getTableComment, genTable.getTableComment());
        }
        // ๆถ้ดๆฎต
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(GenTable::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(GenTable::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // ๆๅบ
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
        // ่ทๅๅ้กตๅฏน่ฑก
        PageVO pageVO = PageUtils.getPageVO();
        // ๆฅ่ฏขๆกไปถ
        QueryWrapper<GenTable> queryWrapper = new QueryWrapper<>();
        // ๆถ้ดๆฎต
        if (StringUtils.isNotBlank(pageVO.getBeginTime())) {
            queryWrapper.lambda().ge(GenTable::getCreateTime, DateUtils.completionTimeStart(pageVO.getBeginTime()));
        }
        if (StringUtils.isNotBlank(pageVO.getEndTime())) {
            queryWrapper.lambda().le(GenTable::getCreateTime, DateUtils.completionTimeEnd(pageVO.getEndTime()));
        }
        // ๆๅบ
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
            return RES.no("่ฏท้ๆฉ้่ฆๆไฝ็ๆฐๆฎ");
        }
        String[] tableNames = Convert.toStrArray(tables);
        List<GenTable> list = this.baseMapper.selectDbTableListByNames(tableNames);
        if (CollectionUtil.isEmpty(list)) {
            return RES.no("่กจ็ปๆไธๅญๅจ");
        }
        Integer userId = StpUtil.getLoginIdAsInt();
        for (GenTable table : list) {
            String tableName = table.getTableName();
            GenUtils.initTable(table, userId);
            int row = this.baseMapper.insert(table);
            if (row > 0) {
                // ไฟๅญๅไฟกๆฏ
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
        // ๆฅ่ฏข่กจไฟกๆฏ
        GenTableVO table = this.baseMapper.getGenTableVO(tableId);
        // ่ฎพ็ฝฎไธปๅญ่กจไฟกๆฏ
        setSubTable(table);
        // ่ฎพ็ฝฎไธป้ฎๅไฟกๆฏ
        setPkColumn(table);
        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // ่ทๅๆจกๆฟๅ่กจ
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // ๆธฒๆๆจกๆฟ
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
     * ่ฎพ็ฝฎไปฃ็ ็ๆๅถไป้้กนๅผ
     *
     * @param genTable ่ฎพ็ฝฎๅ็็ๆๅฏน่ฑก
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
        // ๆฅ่ฏข่กจไฟกๆฏ
        GenTableVO table = this.baseMapper.selectGenTableByName(tableName);
        // ่ฎพ็ฝฎไธปๅญ่กจไฟกๆฏ
        setSubTable(table);
        // ่ฎพ็ฝฎไธป้ฎๅไฟกๆฏ
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // ่ทๅๆจกๆฟๅ่กจ
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            if (!StringUtils.containsAny(template, "sql.vm", "api.js.vm", "index.vue.vm", "index-tree.vue.vm")) {
                // ๆธฒๆๆจกๆฟ
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, CommonConstants.UTF8);
                tpl.merge(context, sw);
                try {
                    String path = getGenPath(table, template);
                    FileUtils.writeStringToFile(new File(path), sw.toString(), CommonConstants.UTF8);
                } catch (IOException e) {
                    throw new CustomException("ๆธฒๆๆจกๆฟๅคฑ่ดฅ๏ผ่กจๅ๏ผ" + table.getTableName());
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
            throw new CustomException("ๅๆญฅๆฐๆฎๅคฑ่ดฅ๏ผๅ่กจ็ปๆไธๅญๅจ");
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
     * ่ทๅไปฃ็ ็ๆๅฐๅ
     *
     * @param table    ไธๅก่กจไฟกๆฏ
     * @param template ๆจกๆฟๆไปถ่ทฏๅพ
     * @return ็ๆๅฐๅ
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
                return RES.no("ๆ ็ผ็ ๅญๆฎตไธ่ฝไธบ็ฉบ");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_PARENT_CODE))) {
                return RES.no("ๆ ็ถ็ผ็ ๅญๆฎตไธ่ฝไธบ็ฉบ");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_NAME))) {
                return RES.no("ๆ ๅ็งฐๅญๆฎตไธ่ฝไธบ็ฉบ");
            } else if (GenConstants.TPL_SUB.equals(genTable.getTplCategory())) {
                if (StringUtils.isEmpty(genTable.getSubTableName())) {
                    return RES.no("ๅณ่ๅญ่กจ็่กจๅไธ่ฝไธบ็ฉบ");
                } else if (StringUtils.isEmpty(genTable.getSubTableFkName())) {
                    return RES.no("ๅญ่กจๅณ่็ๅค้ฎๅไธ่ฝไธบ็ฉบ");
                }
            }
        }
        return RES.ok();
    }

    @Override
    public RES delete(Integer[] ids) {
        if (null == ids || 0 == ids.length) {
            return RES.no("่ฏท้ๆฉ้่ฆๆไฝ็ๆฐๆฎ");
        }
        LambdaQueryWrapper<GenTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(GenTable::getTableId, Arrays.asList(ids));
        this.baseMapper.delete(queryWrapper);
        return RES.ok();
    }

    /**
     * ๆฅ่ฏข่กจไฟกๆฏๅนถ็ๆไปฃ็ 
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
        // ๆฅ่ฏข่กจไฟกๆฏ
        GenTableVO table = this.baseMapper.selectGenTableByName(tableName);
        // ่ฎพ็ฝฎไธปๅญ่กจไฟกๆฏ
        setSubTable(table);
        // ่ฎพ็ฝฎไธป้ฎๅไฟกๆฏ
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // ่ทๅๆจกๆฟๅ่กจ
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            // ๆธฒๆๆจกๆฟ
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CommonConstants.UTF8);
            tpl.merge(context, sw);
            try {
                // ๆทปๅ ๅฐzip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, CommonConstants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("ๆธฒๆๆจกๆฟๅคฑ่ดฅ๏ผ่กจๅ๏ผ" + table.getTableName(), e);
            }
        }
    }

    /**
     * ่ฎพ็ฝฎไธปๅญ่กจไฟกๆฏ
     *
     * @param table ไธๅก่กจไฟกๆฏ
     */
    public void setSubTable(GenTableVO table) {
        String subTableName = table.getSubTableName();
        if (StringUtils.isNotEmpty(subTableName)) {
            table.setSubTable(this.baseMapper.selectGenTableByName(subTableName));
        }
    }

    /**
     * ่ฎพ็ฝฎไธป้ฎๅไฟกๆฏ
     *
     * @param table ไธๅก่กจไฟกๆฏ
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
