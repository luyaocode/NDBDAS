package com.asurplus.tool.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.asurplus.system.vo.TableInfo;
import com.asurplus.common.annotation.SysLog;
import com.asurplus.common.enums.BusinessType;
import com.asurplus.common.utils.Convert;
import com.asurplus.common.utils.RES;
import com.asurplus.tool.entity.GenTable;
import com.asurplus.tool.entity.GenTableColumn;
import com.asurplus.tool.service.GenTableColumnService;
import com.asurplus.tool.service.GenTableService;
import com.asurplus.tool.vo.GenTableUpdateVO;
import com.asurplus.tool.vo.GenTableVO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成业务表 前端控制器
 * </p>
 *
 * @author lizhou
 * @since 2021-07-21
 */
@RestController
@RequestMapping("/tool/gen-table")
public class GenTableController {

    @Autowired
    private GenTableService genTableService;
    @Autowired
    private GenTableColumnService genTableColumnService;

    @SaCheckPermission("tool:gen:list")
    @GetMapping("list")
    public TableInfo list(GenTable genTable) {
        return genTableService.list(genTable);
    }

    @SaCheckPermission("tool:gen:list")
    @GetMapping("dbList")
    public TableInfo dbList(GenTable genTable) {
        return genTableService.dbList(genTable);
    }

    @SysLog(title = "代码生成", type = BusinessType.IMPORT)
    @SaCheckPermission("tool:gen:import")
    @PostMapping("importTable")
    public RES saveImportTable(String tables) {
        return genTableService.saveImportTable(tables);
    }

    @SaCheckPermission("tool:gen:preview")
    @GetMapping("/preview/{tableId}")
    public RES previewCode(@PathVariable("tableId") Integer tableId) {
        return genTableService.previewCode(tableId);
    }

    @SaCheckPermission("tool:gen:query")
    @GetMapping(value = "/{talbleId}")
    public RES getInfo(@PathVariable Integer talbleId) {
        GenTableVO table = genTableService.selectGenTableById(talbleId);
        List<GenTableVO> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(talbleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return RES.ok(map);
    }

    /**
     * 生成代码zip格式
     *
     * @param response
     * @param tables
     * @throws IOException
     */
    @SysLog(title = "代码生成", type = BusinessType.GENCODE)
    @SaCheckPermission("tool:gen:code")
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"Asurplus.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

    /**
     * 生成代码（自定义路径）
     */
    @SysLog(title = "代码生成", type = BusinessType.GENCODE)
    @SaCheckPermission("tool:gen:code")
    @GetMapping("/genCode/{tableName}")
    public RES genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return RES.ok();
    }

    @SysLog(title = "代码生成", type = BusinessType.UPDATE)
    @SaCheckPermission("tool:gen:edit")
    @PutMapping
    public RES update(@RequestBody GenTableUpdateVO genTable) {
        return genTableService.update(genTable);
    }

    @SysLog(title = "代码生成", type = BusinessType.DELETE)
    @SaCheckPermission("tool:gen:del")
    @DeleteMapping("/{ids}")
    public RES delete(@PathVariable Integer[] ids) {
        return genTableService.delete(ids);
    }

    /**
     * 同步数据库
     */
    @SysLog(title = "代码生成", type = BusinessType.UPDATE)
    @SaCheckPermission("tool:gen:code")
    @GetMapping("/synchDb/{tableName}")
    public RES updateSynchDb(@PathVariable("tableName") String tableName) {
        genTableService.updateSynchDb(tableName);
        return RES.ok();
    }

}
