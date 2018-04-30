package com.stylefeng.guns.modular.commoditymanage.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCommodityService;
import com.stylefeng.guns.modular.commoditymanage.vo.TblCommodityVo;
import com.stylefeng.guns.modular.system.dao.TblCommodityMapper;
import com.stylefeng.guns.modular.system.model.TblCommodity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品管理表 服务实现类
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
@Service
public class TblCommodityServiceImpl extends ServiceImpl<TblCommodityMapper, TblCommodity> implements ITblCommodityService {

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    Logger logger = LoggerFactory.getLogger(TblCommodityServiceImpl.class);

    @Resource
    TblCommodityMapper tblCommodityMapper;

    @Override
    public List<Map<String, Object>> selectCommodityList(String name, String categoriesName, String beginTime, String endTime) {
        return tblCommodityMapper.selectCommodityList(name, categoriesName, beginTime, endTime, 0);
    }

    @Override
    public List<Map<String, Object>> selectCommodityList(String name, String categoriesName, String beginTime, String endTime, Integer rowNum) {
        return tblCommodityMapper.selectCommodityList(name, categoriesName, beginTime, endTime, rowNum);
    }

    @Override
    public List<TblCommodityVo> selectCommodityVoList(String name, String categoriesName, String beginTime, String endTime, Integer rowNum) {
        return tblCommodityMapper.selectCommodityVoList(name, categoriesName, beginTime, endTime, rowNum);
    }

    @Override
    public List<String> selectNameByIds(String ids) {
        return tblCommodityMapper.selsectNameByIds(ids);
    }

    @Override
    public void saveImportExcel(MultipartFile mf, List<Map<String, String>> columnMapList) throws Exception {
        Workbook workbook = null;
        String fileName = mf.getOriginalFilename();
        if (fileName.endsWith(XLS)) {
            //2003
            workbook = new HSSFWorkbook(mf.getInputStream());
        } else if (fileName.endsWith(XLSX)) {
            //2007
            workbook = new XSSFWorkbook(mf.getInputStream());
        } else {
            logger.error("导入的{}格式不对！", mf.getOriginalFilename());
            throw new Exception("文件不是Excel文件");
        }

        Sheet sheet = workbook.getSheet("Sheet1");
        int rows = sheet.getLastRowNum();// 指的行数，一共有多少行
        if(rows==0){
            logger.error("导入的{}数据集为空！", mf.getOriginalFilename());
            throw new Exception("请填写数据");
        }

        Row row = sheet.getRow(0);
        if(row.getLastCellNum() != columnMapList.size()) {
            logger.error("导入的{}的列数不对！", mf.getOriginalFilename());
            throw new Exception("请填写数据");
        }
        row.forEach(item -> {

        });
    }
}
