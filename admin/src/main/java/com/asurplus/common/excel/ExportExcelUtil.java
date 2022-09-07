package com.asurplus.common.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.asurplus.common.consts.CommonConstants;
import com.asurplus.common.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @version V1.0
 * @Description: Excel导出工具类
 * @date 2018/10/31 19:16
 */
public class ExportExcelUtil {

    /**
     * 导出
     *
     * @param list           数据集合
     * @param title          内容标题
     * @param sheetName      excel名称
     * @param pojoClass      实体类
     * @param fileName       导出的文件名
     * @param isCreateHeader 是否创建excel表头
     */
    public static void exportExcel(List<?> list,
                                   String title,
                                   String sheetName,
                                   Class<?> pojoClass,
                                   String fileName,
                                   boolean isCreateHeader) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, exportParams);
    }

    /**
     * 导出
     *
     * @param list      数据集合
     * @param title     内容标题
     * @param sheetName excel名称
     * @param pojoClass 实体类
     * @param fileName  导出的文件名
     */
    public static void exportExcel(List<?> list,
                                   String title,
                                   String sheetName,
                                   Class<?> pojoClass,
                                   String fileName) {
        defaultExport(list, pojoClass, fileName, new ExportParams(title, sheetName));
    }

    /**
     * 导出 无内容标题和excel表名
     *
     * @param list
     * @param pojoClass
     * @param fileName
     */
    public static void exportExcel(List<?> list,
                                   Class<?> pojoClass,
                                   String fileName) {
        defaultExport(list, pojoClass, fileName, new ExportParams());
    }

    /**
     * 导出 无内容标题和excel表名
     *
     * @param list
     * @param pojoClass
     * @param fileName
     */
    public static void exportExcel(List<?> list,
                                   Class<?> pojoClass,
                                   String fileName,
                                   String title) {
        defaultExport(list, pojoClass, fileName, new ExportParams(title, null));
    }

    /**
     * 导出
     *
     * @param list     数据集合
     * @param fileName 导出的文件名
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName) {
        defaultExport(list, fileName);
    }

    /**
     * 导出
     *
     * @param list         数据集合
     * @param pojoClass    实体类
     * @param fileName     导出的文件名
     * @param exportParams
     */
    private static void defaultExport(List<?> list,
                                      Class<?> pojoClass,
                                      String fileName,
                                      ExportParams exportParams) {
        // 自定义字典查询
        exportParams.setDictHandler(new IExcelDictHandlerImpl());
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            doExport(fileName, workbook);
        }
    }

    /**
     * 导出
     *
     * @param list     数据集合
     * @param fileName 导出的文件名
     */
    private static void defaultExport(List<Map<String, Object>> list, String fileName) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) {
            doExport(fileName, workbook);
        }
    }

    /**
     * 导出
     *
     * @param fileName 导出的文件名
     * @param workbook 工作表
     */
    private static void doExport(String fileName, Workbook workbook) {
        try {
            HttpServletResponse response = ServletUtils.getResponse();
            response.reset();
            response.setCharacterEncoding(CommonConstants.UTF8);
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, CommonConstants.UTF8) + ".xls");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 导入
     *
     * @param filePath
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(String filePath,
                                          Integer titleRows,
                                          Integer headerRows,
                                          Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 导入
     *
     * @param file
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file,
                                          Integer titleRows,
                                          Integer headerRows,
                                          Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}