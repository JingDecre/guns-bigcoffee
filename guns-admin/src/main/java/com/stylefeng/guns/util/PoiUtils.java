package com.stylefeng.guns.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
    public static HSSFWorkbook exportExcel(List<Map<String, String>> columnMapList, List<Map<String, Object>> dataList) throws Exception {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("Sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中 设置文本格式
        HSSFDataFormat df = wb.createDataFormat();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setDataFormat(df.getFormat("@"));
        // 存储每个key值对应得列数
        Map<String, Integer> cellColumnMap = new HashMap<>();

        //创建表头
        AtomicInteger column = new AtomicInteger(0);
        columnMapList.forEach(item -> {
            cellColumnMap.put(item.get("key"), column.get());
            HSSFCell cell = row.createCell(column.get());
            cell.setCellValue(item.get("title"));
            cell.setCellStyle(style);
            column.getAndIncrement();
        });


        //填充数据
        AtomicInteger rowNum = new AtomicInteger(1);
        dataList.forEach(item -> {
            HSSFRow tempRow = sheet.createRow(rowNum.get());
            columnMapList.forEach(map -> {
                HSSFCell cell = tempRow.createCell(cellColumnMap.get(map.get("key")));
                cell.setCellValue(String.valueOf(item.get(map.get("key"))));
                cell.setCellStyle(style);
            });
            rowNum.getAndIncrement();
        });

        //第六步,输出Excel文件
        return wb;
    }

}
