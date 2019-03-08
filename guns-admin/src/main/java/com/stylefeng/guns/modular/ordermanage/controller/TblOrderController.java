package com.stylefeng.guns.modular.ordermanage.controller;

import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.logisticsmanage.service.ITblLogisticsService;
import com.stylefeng.guns.modular.ordermanage.service.ITblOrderService;
import com.stylefeng.guns.modular.ordermanage.vo.TblOrderVo;
import com.stylefeng.guns.modular.system.model.TblOrder;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.warpper.CategoriesWarpper;
import com.stylefeng.guns.util.PoiUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-31 18:28:37
 */
@Controller
@RequestMapping("/tblOrder")
public class TblOrderController extends BaseController {

    private String PREFIX = "/ordermanage/tblOrder/";

    Logger logger = LoggerFactory.getLogger(TblOrderController.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Workbook workbook = new HSSFWorkbook();

    private String downloadFileName = "";

    static Gson gson = new Gson();

    @Autowired
    private ITblOrderService tblOrderService;

    @Autowired
    private ITblLogisticsService tblLogisticsService;

    @Autowired
    private IUserService userService;

    @Value("${logistics.pdf-path}")
    private String filePath;

    /**
     * 跳转到订单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblOrder.html";
    }

    /**
     * 跳转到添加订单管理
     */
    @RequestMapping("/tblOrder_add")
    public String tblOrderAdd(Model model) {
        // 物流单下拉选项
        List<Map<String, Object>> logisticsMapList = tblLogisticsService.selectIdAndCodeList();
        model.addAttribute("logisticsList", logisticsMapList);
        return PREFIX + "tblOrder_add.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/tblOrder_update/{tblOrderId}")
    public String tblOrderUpdate(@PathVariable Long tblOrderId, Model model) {
        TblOrder tblOrder = tblOrderService.selectById(tblOrderId);
        // 物流单下拉选项
        List<Map<String, Object>> logisticsMapList = tblLogisticsService.selectIdAndCodeList();
        model.addAttribute("logisticsList", logisticsMapList);

        model.addAttribute("item", tblOrder);
        LogObjectHolder.me().set(tblOrder);
        return PREFIX + "tblOrder_edit.html";
    }

    /**
     * 跳转到添加货品管理导出
     */
    @RequestMapping("/tblOrder_import")
    public String tblOrderImport() {
        return PREFIX + "tblOrder_import.html";
    }

    /**
     * 跳转到添加货品管理导出
     */
    @RequestMapping("/tblOrder_uploadLogisticsPdf")
    public String tblOrderUploadLogistics() {
        return PREFIX + "tblOrder_uploadLogisticsPdf.html";
    }

    /**
     * 跳转到添加货品管理导出
     */
    @RequestMapping("/tblOrder_export")
    public String tblCommodityExport() {
        return PREFIX + "tblOrder_export.html";
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String code, @RequestParam(required = false) String sku, @RequestParam(required = false) String address, @RequestParam(required = false) String logisticsCode, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {
        Page<TblOrder> page = new PageFactory<TblOrder>().defaultPage();
        Integer createUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(createUserId);
        if (ToolUtil.isEmpty(user.getSupplierShiro()) || user.getSupplierShiro().equals(0)) {
            createUserId = -999;
        }
        List<Map<String, Object>> list = this.tblOrderService.selectOrderList(page, code, sku, address, logisticsCode, beginTime, endTime, createUserId);
        page.setRecords((List<TblOrder>) new CategoriesWarpper(list).warp());
        return super.packForBT(page);
    }

    /**
     * 新增订单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblOrder tblOrder) {
        Integer createUserId = ShiroKit.getUser().getId();
        tblOrder.setCreateUserId(createUserId);
        tblOrderService.insert(tblOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestBody List<Long> ids) {
        tblOrderService.deleteBatchIds(ids);
        return SUCCESS_TIP;
    }

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TblOrder tblOrder) {
        tblOrderService.updateById(tblOrder);
        return SUCCESS_TIP;
    }

    /**
     * 订单管理详情
     */
    @RequestMapping(value = "/detail/{tblOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("tblOrderId") Long tblOrderId) {
        return tblOrderService.selectById(tblOrderId);
    }

    /**
     * 导入货品
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public void importPoi(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) {
        List<TblOrderVo> list = PoiUtils.importExcel(file, 1, 1, TblOrderVo.class);
        List<TblOrder> insertList = new ArrayList<>();
        Map<String, String> logisticsMap = tblLogisticsService.selectCodeAndIdMap();
        Integer createUserId = ShiroKit.getUser().getId();
        //组装存库数据
        list.forEach(item -> {

            TblOrder order = tblOrderService.selectOrderByCode(item.getCode());
            if (ToolUtil.isEmpty(order)) {
                order = new TblOrder();
            }
            order.setCode(item.getCode());
            order.setSku(item.getSku());
            order.setCommodityDetails(item.getCommodityDetails());
            order.setQuantity(item.getQuantity());
            order.setWeight(item.getWeight());
            order.setTransactionDate(sdf.format(item.getTransactionDate()));
            order.setCountry(item.getCountry());
            order.setProvince(item.getProvince());
            order.setCity(item.getCity());
            order.setCounty(item.getCounty());
            order.setDetailAddress(item.getDetailAddress());
            order.setZipcode(item.getZipcode());
            order.setRecipientName(item.getRecipientName());
            order.setRecipientPhone(item.getRecipientPhone());
            order.setBelongPlatform(item.getBelongPlatform());
            order.setOrderAmount(item.getOrderAmount());
            order.setCreateUserId(createUserId);
            order.setLogisticsCode(item.getLogisticsCode());
            if (ToolUtil.isNotEmpty(logisticsMap.get(item.getLogisticsName()))) {
                order.setLogisticsId(item.getLogisticsCode());
            }
            insertList.add(order);
        });
        if (insertList.size() > 0) {
            tblOrderService.insertOrUpdateBatch(insertList);
        }
        logger.info("导入成功！");
    }

    /**
     * 导入货品
     */
    @RequestMapping(value = "/uploadLogisticsPdf", method = RequestMethod.POST)
    @ResponseBody
    public void uploadLogisticsPdf(@RequestParam(value = "file", required = true) MultipartFile file, @RequestParam(required = true) String orderId, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        File fileDir = new File(filePath.trim());
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String nowSecond = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String saveFileName = fileName.substring(0, fileName.lastIndexOf(".pdf"));
        String saveFilePath = filePath + File.separator + saveFileName + "_" + nowSecond + ".pdf";
        TblOrder order = tblOrderService.selectById(orderId);
        try {
            // 获取上传文件的输入流
            InputStream in = file.getInputStream();
            //创建一个文件输出流
            FileOutputStream out = new FileOutputStream(saveFilePath);
            //创建一个缓冲区
            byte buffer[] = new byte[1024];
            //判断输入流中的数据是否已经读完的标识
            int len = 0;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while ((len = in.read(buffer)) > 0) {
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                out.write(buffer, 0, len);
            }
            //关闭输入流
            in.close();
            //关闭输出流
            out.close();
            //更新物流面单信息
            order.setLogisticsPdfName(saveFileName + "_" + nowSecond);
            tblOrderService.updateById(order);

        } catch (IOException e) {
            logger.error("文件{}有问题!!!", fileName);
            e.printStackTrace();
        }
    }

    /**
     * 物流面单文件是否存在
     * @param tblOrderId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/checkLogisticsPdf/{tblOrderId}", method = RequestMethod.POST)
    @ResponseBody
    public Object checkLogisticsPdf(@PathVariable("tblOrderId") Long tblOrderId, HttpServletRequest request, HttpServletResponse response) {
        TblOrder order = tblOrderService.selectById(tblOrderId);
        String fileName = order.getLogisticsPdfName();
        String saveFilePath = filePath + File.separator + fileName + ".pdf";
        File file = new File(saveFilePath);
        if (!file.exists()) {
            Tip tip = new SuccessTip();
            tip.setCode(404);
            tip.setMessage(fileName + "不存在！可能已被删除");
            return tip;
        }
        downloadFileName = fileName + ".pdf";
        return SUCCESS_TIP;
    }

    /**
     * 下载物流面单文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/downloadLogisticsPdf")
    @ResponseBody
    public void downloadLogisticsPdf(HttpServletRequest request, HttpServletResponse response) {
        String saveFilePath = filePath.trim() + File.separator + downloadFileName;
        File file = new File(saveFilePath);
        try {
            InputStream fis = new BufferedInputStream(new FileInputStream(saveFilePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(downloadFileName.getBytes(), "iso-8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            logger.error("下载: {} 失败！", downloadFileName);
            logger.error("Bad things: {}", e.getMessage());
            e.getStackTrace();
        }
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
            String tableName = "订单列表_";
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(tableName.getBytes(), "iso-8859-1") + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls");
            tempWb.write(os);
            os.close();
        } catch (Exception e) {
            logger.error("导出: {} 失败！", "订单列表");
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
            Integer createUserId = ShiroKit.getUser().getId();
            User user = userService.selectById(createUserId);
            if (ToolUtil.isEmpty(user.getSupplierShiro()) || user.getSupplierShiro().equals(0)) {
                createUserId = -999;
            }
            List<TblOrderVo> list = tblOrderService.selectOrderVoList(String.valueOf(conditionMap.get("code")), conditionMap.get("sku").toString(), conditionMap.get("address").toString(), conditionMap.get("logisticsCode").toString(), conditionMap.get("beginTime").toString(), conditionMap.get("endTime").toString(), createUserId, startPage, pageSize);
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            mapList.add(PoiUtils.getExportMap(list, "订单列表", TblOrderVo.class));
            workbook = PoiUtils.exportExcel(mapList, ExcelType.HSSF);
        }

    }
}
