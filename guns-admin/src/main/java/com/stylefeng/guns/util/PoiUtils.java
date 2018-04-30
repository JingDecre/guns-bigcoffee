package com.stylefeng.guns.util;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: PoiUtils</p>
 * <p>Description: </p>
 *
 * @author decre
 * @version 1.0.0
 * @date 2018/4/25 0025 19:05
 */
public class PoiUtils {

    /**
     * 导出excel文件
     */
    public static Workbook exportExcel(List<Map<String, Object>> list, ExcelType type) {
        Workbook workbook;
        if (ExcelType.HSSF.equals(type)) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        for (Map<String, Object> map : list) {
            ExcelExportService service = new ExcelExportService();
            ExportParams params = (ExportParams) map.get("params");
            Class<?> entry = (Class<?>) map.get("entity");
            Collection<?> data = (Collection<?>) map.get("data");
            service.createSheet(workbook, params, entry, data);
        }
        return workbook;
    }

    /**
     * 获取导入表数据集
     *
     * @param file
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<?> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取导出表格的工作簿
     *
     * @param dataList
     * @param tableName
     * @param pojoClass
     * @return
     */
    public static Map<String, Object> getExportMap(List<?> dataList, String tableName, Class<?> pojoClass) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(NormalExcelConstants.CLASS, pojoClass);
        map.put(NormalExcelConstants.FILE_NAME, tableName + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        ExportParams ep = new ExportParams(tableName, "sheet_" + ((int) 1000 * Math.random()));
        map.put(NormalExcelConstants.PARAMS, ep);
        map.put(NormalExcelConstants.DATA_LIST, dataList);
        return map;
    }

}
