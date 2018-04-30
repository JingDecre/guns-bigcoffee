package com.stylefeng.guns.modular.commoditymanage.controller;

import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCategoriesService;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCommodityService;
import com.stylefeng.guns.modular.commoditymanage.vo.TblCommodityVo;
import com.stylefeng.guns.modular.suppliermanage.service.ITblSupplierService;
import com.stylefeng.guns.modular.system.model.TblCommodity;
import com.stylefeng.guns.modular.system.warpper.CommodityWarpper;
import com.stylefeng.guns.util.PoiUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 货品管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-31 10:03:32
 */
@Controller
@RequestMapping("/tblCommodity")
public class TblCommodityController extends BaseController {

    private String PREFIX = "/commoditymanage/tblCommodity/";

    Logger logger = LoggerFactory.getLogger(TblCommodityController.class);

    private Workbook workbook = new HSSFWorkbook();

    static Gson gson = new Gson();

    @Autowired
    private ITblCommodityService tblCommodityService;

    @Autowired
    private ITblSupplierService tblSupplierService;

    @Autowired
    private ITblCategoriesService tblCategoriesService;

    /**
     * 跳转到货品管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblCommodity.html";
    }

    /**
     * 跳转到添加货品管理
     */
    @RequestMapping("/tblCommodity_add")
    public String tblCommodityAdd(Model model) {
        List<Map<String, Object>> supplierList = tblSupplierService.selectIdAndNameList();
        model.addAttribute("supplierList", supplierList);
        return PREFIX + "tblCommodity_add.html";
    }

    /**
     * 跳转到修改货品管理
     */
    @RequestMapping("/tblCommodity_update/{tblCommodityId}")
    public String tblCommodityUpdate(@PathVariable Integer tblCommodityId, Model model) {
        TblCommodity tblCommodity = tblCommodityService.selectById(tblCommodityId);
        List<Map<String, Object>> supplierList = tblSupplierService.selectIdAndNameList();
        String categoriesName = tblCategoriesService.selectById(tblCommodity.getCategoriesId()).getName();
        model.addAttribute("categoriesName", categoriesName);
        model.addAttribute("supplierList", supplierList);
        model.addAttribute("item", tblCommodity);
        LogObjectHolder.me().set(tblCommodity);
        return PREFIX + "tblCommodity_edit.html";
    }

    /**
     * 跳转到添加货品管理导出
     */
    @RequestMapping("/tblCommodity_import")
    public String tblCommodityImport() {
        return PREFIX + "tblCommodity_import.html";
    }

    /**
     * 跳转到添加货品管理导出
     */
    @RequestMapping("/tblCommodity_export")
    public String tblCommodityExport() {
        return PREFIX + "tblCommodity_export.html";
    }

    /**
     * 获取货品管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String categoriesName, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {
        Page<TblCommodity> page = new PageFactory<TblCommodity>().defaultPage();
        List<Map<String, Object>> list = tblCommodityService.selectCommodityList(page, name, categoriesName, beginTime, endTime);
        page.setRecords((List<TblCommodity>) new CommodityWarpper(list).warp());
        return super.packForBT(page);
    }

    /**
     * 新增货品管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblCommodity tblCommodity) {
        tblCommodity.setCreatetime(new Date());
        tblCommodity.setUpdatetime(new Date());
        tblCommodityService.insert(tblCommodity);
        return SUCCESS_TIP;
    }

    /**
     * 删除货品管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tblCommodityId) {
        tblCommodityService.deleteById(tblCommodityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改货品管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TblCommodity tblCommodity) {
        tblCommodity.setUpdatetime(new Date());
        tblCommodityService.updateById(tblCommodity);
        return SUCCESS_TIP;
    }

    /**
     * 货品管理详情
     */
    @RequestMapping(value = "/detail/{tblCommodityId}")
    @ResponseBody
    public Object detail(@PathVariable("tblCommodityId") Integer tblCommodityId) {
        return tblCommodityService.selectById(tblCommodityId);
    }

    /**
     * 导入货品
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public void importPoi(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) {
        List<TblCommodityVo> list = PoiUtils.importExcel(file, 1, 1, TblCommodityVo.class);
        List<TblCommodity> insertList = new ArrayList<>();
        //获取外键id
        Map<String, String> categoriesMap = tblCategoriesService.getCategoriesIdAndName();
        Map<String, String> supplierMap = tblSupplierService.getNameAndIdMap();
        //组装存库数据
        list.forEach(item -> {
            TblCommodity tblCommodity = new TblCommodity();
            tblCommodity.setSku(item.getSku());
            tblCommodity.setEsname(item.getEsname());
            tblCommodity.setCnname(item.getCnname());
            if (ToolUtil.isNotEmpty(categoriesMap.get(item.getCategoriesName()))) {
                tblCommodity.setCategoriesId(categoriesMap.get(item.getCategoriesName()));
            } else {
                tblCommodity.setCategoriesId("1");
            }
            tblCommodity.setSpu(item.getSpu());
            tblCommodity.setStock(item.getStock().toString());
            tblCommodity.setTitle(item.getTitle());
            tblCommodity.setDiscountPrice(item.getDiscountPrice());
            tblCommodity.setOriginPrice(item.getOriginPrice());
            tblCommodity.setColor(item.getColor());
            tblCommodity.setProductSize(item.getProductSize());
            tblCommodity.setWeight(item.getWeight());
            tblCommodity.setPackageSize(item.getPackageSize());
            tblCommodity.setBrands(item.getBrands());
            tblCommodity.setDesc(item.getDesc());
            if (ToolUtil.isNotEmpty(supplierMap.get(item.getSupplierCnName()))) {
                tblCommodity.setSupplierId(supplierMap.get(item.getSupplierCnName()));
            } else {
                tblCommodity.setSupplierId("1");
            }
            tblCommodity.setPurchasePrice(item.getPurchasePrice());
            tblCommodity.setCreatetime(new Date());
            tblCommodity.setUpdatetime(new Date());
            insertList.add(tblCommodity);
        });
        if (insertList.size() > 0) {
            tblCommodityService.insertBatch(insertList);
        }
        logger.info("导入成功！");
    }

    /**
     * 导出货品
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void exportPoi(HttpServletRequest request, HttpServletResponse response) {
        OutputStream os = null;
        Workbook tempWb = workbook;
        workbook = null;
        try {
            os = response.getOutputStream();
            response.setContentType("application/download");
            String tableName = "货品列表_";
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(tableName.getBytes(), "iso-8859-1") + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls");
            tempWb.write(os);
            os.close();
        } catch (Exception e) {
            logger.error("导出: {} 失败！", "货品列表");
            logger.error("Bad things: {}", e.getMessage());
            e.getStackTrace();
        }
    }

    /**
     * 导出表格
     */
    @RequestMapping(value = "/generateExcel", method = RequestMethod.POST)
    @ResponseBody
    public void generateExcel(@RequestBody Map<String, String> param) {
        if (ToolUtil.isNotEmpty(param)) {
            //查询参数
            Map conditionMap = gson.fromJson(param.get("condition"), new TypeToken<Map>() {
            }.getType());
            //导出列名
            Integer startPage = ToolUtil.isEmpty(conditionMap.get("startPage")) ? 0 : Integer.valueOf(conditionMap.get("startPage").toString());
            Integer pageSize = ToolUtil.isEmpty(conditionMap.get("pageSize")) ? 55000 : Integer.valueOf(conditionMap.get("pageSize").toString());
            List<TblCommodityVo> list = tblCommodityService.selectCommodityVoList(conditionMap.get("name").toString(), conditionMap.get("categoriesName").toString(), conditionMap.get("beginTime").toString(), conditionMap.get("endTime").toString(), startPage, pageSize);
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            mapList.add(PoiUtils.getExportMap(list, "货品列表", TblCommodityVo.class));
            workbook = PoiUtils.exportExcel(mapList, ExcelType.HSSF);
        }

    }

}
