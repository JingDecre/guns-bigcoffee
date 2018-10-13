package com.stylefeng.guns.modular.brushmanage.controller;

import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.brushmanage.service.IBrushService;
import com.stylefeng.guns.modular.brushmanage.vo.BrushVo;
import com.stylefeng.guns.modular.logisticsmanage.service.ITblLogisticsService;
import com.stylefeng.guns.modular.system.model.Brush;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.warpper.BrushWarpper;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 刷单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-31 22:07:03
 */
@Controller
@RequestMapping("/brush")
public class BrushController extends BaseController {

    private String PREFIX = "/brushmanage/brush/";

    Logger logger = LoggerFactory.getLogger(BrushController.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Workbook workbook = new HSSFWorkbook();

    static Gson gson = new Gson();


    @Autowired
    private IBrushService brushService;

    @Autowired
    private ITblLogisticsService tblLogisticsService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到刷单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "brush.html";
    }

    /**
     * 跳转到添加刷单管理
     */
    @RequestMapping("/brush_add")
    public String brushAdd(Model model) {
        // 物流单下拉选项
        List<Map<String, Object>> logisticsMapList = tblLogisticsService.selectIdAndCodeList();
        model.addAttribute("logisticsList", logisticsMapList);
        return PREFIX + "brush_add.html";
    }

    /**
     * 跳转到修改刷单管理
     */
    @RequestMapping("/brush_update/{brushId}")
    public String brushUpdate(@PathVariable Long brushId, Model model) {
        Brush brush = brushService.selectById(brushId);
        List<Map<String, Object>> logisticsMapList = tblLogisticsService.selectIdAndCodeList();
        model.addAttribute("logisticsList", logisticsMapList);
        model.addAttribute("item",brush);
        LogObjectHolder.me().set(brush);
        return PREFIX + "brush_edit.html";
    }

    /**
     * 跳转到添加货品管理导出
     */
    @RequestMapping("/brush_import")
    public String tblCommodityImport() {
        return PREFIX + "brush_import.html";
    }

    /**
     * 跳转到添加货品管理导出
     */
    @RequestMapping("/brush_export")
    public String tblCommodityExport() {
        return PREFIX + "brush_export.html";
    }

    /**
     * 获取刷单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {
        Page<Brush> page = new PageFactory<Brush>().defaultPage();
        Integer createUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(createUserId);
        if (ToolUtil.isEmpty(user.getSupplierShiro()) || user.getSupplierShiro().equals(0)) {
            createUserId = -999;
        }
        List<Map<String, Object>> list = brushService.selectBrushList(page, name, beginTime, endTime, createUserId);
        page.setRecords((List<Brush>) new BrushWarpper(list).warp());
        return super.packForBT(page);
    }

    /**
     * 新增刷单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Brush brush) {
        Integer id = ShiroKit.getUser().getId();
        brush.setOperateTime(new Date());
        brush.setCreateUserId(id);
        brushService.insert(brush);
        return SUCCESS_TIP;
    }

    /**
     * 删除刷单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestBody List<Long> ids) {
        brushService.deleteBatchIds(ids);
        return SUCCESS_TIP;
    }

    /**
     * 修改刷单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Brush brush) {
        brush.setOperateTime(new Date());
        brushService.updateById(brush);
        return SUCCESS_TIP;
    }

    /**
     * 刷单管理详情
     */
    @RequestMapping(value = "/detail/{brushId}")
    @ResponseBody
    public Object detail(@PathVariable("brushId") Long brushId) {
        return brushService.selectById(brushId);
    }

    /**
     * 导入货品
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public void importPoi(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) {
        List<BrushVo> list = PoiUtils.importExcel(file, 1, 1, BrushVo.class);
        List<Brush> insertList = new ArrayList<>();
        Map<String, String> logisticsMap = tblLogisticsService.selectCodeAndIdMap();
        Integer createUserId = ShiroKit.getUser().getId();
        //组装存库数据
        list.forEach(item -> {
            Brush brush = new Brush();
            brush.setOperateTime(item.getOperateTime());
            brush.setWhetherSuccess(item.getWhetherSuccess());
            brush.setPlatformAccount(item.getPlatformAccount());
            brush.setCustomerInfo(item.getCustomerInfo());
            brush.setSearchWay(item.getSearchWay());
            brush.setOrderAmount(item.getOrderAmount());
            brush.setCommentContent(item.getCommentContent());
            brush.setCommentPictureOne(item.getCommentPictureOne());
            brush.setCommentPictureTwo(item.getCommentPictureTwo());
            brush.setCommentPictureThree(item.getCommentPictureThree());
            brush.setCreateUserId(createUserId);
            if (ToolUtil.isNotEmpty(logisticsMap.get(item.getLogisticsCode()))) {
                brush.setLogisticsId(item.getLogisticsCode());
            }
            insertList.add(brush);
        });
        if(insertList.size() > 0){
            brushService.insertBatch(insertList);
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
            String tableName = "刷单列表_";
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(tableName.getBytes(), "iso-8859-1") + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls");
            tempWb.write(os);
            os.close();
        } catch (Exception e) {
            logger.error("导出: {} 失败！", "刷单列表");
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
            Integer createUserId = ShiroKit.getUser().getId();
            User user = userService.selectById(createUserId);
            if (ToolUtil.isEmpty(user.getSupplierShiro()) || user.getSupplierShiro().equals(0)) {
                createUserId = -999;
            }
            //导出列名
            Integer startPage = ToolUtil.isEmpty(conditionMap.get("startPage")) ? 0 : Integer.valueOf(conditionMap.get("startPage").toString());
            Integer pageSize = ToolUtil.isEmpty(conditionMap.get("pageSize")) ? 55000 : Integer.valueOf(conditionMap.get("pageSize").toString());
            List<BrushVo> list = brushService.selectBrushVoList(String.valueOf(conditionMap.get("name")), conditionMap.get("beginTime").toString(), conditionMap.get("endTime").toString(), createUserId, startPage, pageSize);
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            mapList.add(PoiUtils.getExportMap(list, "刷单列表", BrushVo.class));
            workbook = PoiUtils.exportExcel(mapList, ExcelType.HSSF);
        }

    }
}
