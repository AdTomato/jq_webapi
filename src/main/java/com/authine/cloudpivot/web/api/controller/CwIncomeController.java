package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.CwIncome;
import com.authine.cloudpivot.web.api.service.ContractService;
import com.authine.cloudpivot.web.api.service.I1uycXsContractListPayService;
import com.authine.cloudpivot.web.api.service.SwConstractAndChangedService;
import com.authine.cloudpivot.web.api.service.XsConstractAndChangedService;
import com.authine.cloudpivot.web.api.utils.ExcelUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author: weiyao
 * @Date: 2020-03-19 13:29
 * @Description: 收入分析表
 */
@RestController
@RequestMapping("/controller/cwIncome")
public class CwIncomeController extends BaseController {

    @Autowired
    ContractService contractService;

    @Autowired
    I1uycXsContractListPayService i1uycXsContractListPayService;
    @Autowired
    XsConstractAndChangedService xsConstractAndChangedService;
    @Autowired
    SwConstractAndChangedService swConstractAndChangedService;

    @GetMapping("/getCwIncomeList")
    public ResponseResult<Object> getCwIncomeList(String startDate,String endDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date strdate = null;
        Date enddate = null;
       if(StringUtils.isNotBlank(startDate)){
           try {
               strdate = format.parse(startDate);
           }catch (Exception e){
               e.printStackTrace();
               return this.getErrResponseResult(null, 404L, "开始时间格式错误");
           }
       }
        if(StringUtils.isNotBlank(endDate)){
            try {
                enddate=format.parse(endDate);
            }catch (Exception e){
                e.printStackTrace();
                return this.getErrResponseResult(null, 404L, "结束时间格式错误");
            }
        }
        List<CwIncome> cwincome = xsConstractAndChangedService.getCwIncomeList(strdate,enddate);
        if (cwincome == null || 0 == cwincome.size()) {
            return this.getErrResponseResult(null, 404L, "没有数据");
        } else {
            return this.getOkResponseResult(cwincome, "获取收入分析列表成功");
        }
    }


    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response, String startDate, String endDate) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date strdate = null;
        Date enddate = null;
        if(StringUtils.isNotBlank(startDate)){
            try {
                strdate = format.parse(startDate);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(StringUtils.isNotBlank(endDate)){
            try {
                enddate=format.parse(endDate);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        List<CwIncome> cwIncomes = xsConstractAndChangedService.getCwIncomeList(strdate,enddate);


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet1");
        setExcelTitle(workbook, sheet);

        int num = setBodyData(workbook, sheet, cwIncomes);
        if (num != 0) {
            // 存在付款金额明细，修改表头
            modifyExcelTitle(sheet, num);
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + new String("收入分析表.xlsx".getBytes("gbk"), "iso8859-1"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        workbook.write(bufferedOutputStream);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    /**
     * @param sheet: excel的sheet
     * @param num:   列数
     * @Author: wangyong
     * @Date: 2020/3/20 11:27
     * @return: void
     * @Description: 修改excel的表头
     */
    private void modifyExcelTitle(XSSFSheet sheet, int num) {
        XSSFRow row1 = sheet.getRow(0);  // 最上面标题
        row1.createCell(23).setCellValue("回款明细");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 23, 23 + num));
        XSSFRow row2 = sheet.getRow(1);
        for (int i = 0; i < num; i++) {
            row2.createCell(22 + i + 1).setCellValue("回款金额" + i);
        }
    }

    /**
     * @Author: wangyong
     * @Date: 2020/3/20 9:03
     * @return: void
     * @Description: 设置表头
     */
    private void setExcelTitle(XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(0);
        row.createCell(0);
        row.createCell(1).setCellValue("合同信息");
        row.createCell(2);
        row.createCell(3);
        row.createCell(4);
        row.createCell(5);
        row.createCell(6);
        row.createCell(7);
        row.createCell(8);
        row.createCell(9);
        row.createCell(10);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 10));
        row.createCell(11).setCellValue("合同金额");
        row.createCell(12);
        row.createCell(13);
        row.createCell(14);
        row.createCell(15);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 11, 15));
        row.createCell(16).setCellValue("验收信息");
        row.createCell(17);
        row.createCell(18);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 16, 18));
        row.createCell(19).setCellValue("回款信息");
        row.createCell(20);
        row.createCell(21);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 19, 22));
        ExcelUtils.setCellStyle(workbook, row);

        List<String> title = new LinkedList<>();
        title.add("序号");
        title.add("合同编号");
        title.add("商务代表");
        title.add("项目简介");
        title.add("客户单位");
        title.add("实际使用方");
        title.add("省");
        title.add("市");
        title.add("行业");
        title.add("类别");
        title.add("签订时间");
        title.add("总金额");
        title.add("硬件金额");
        title.add("软件金额");
        title.add("系统运维");
        title.add("其他");
        title.add("验收时间");
        title.add("是否验收");
        title.add("验收意见");
        title.add("回款总金额");
        title.add("税率");
        title.add("税额");
        title.add("剩余回款金额");
        XSSFRow row1 = ExcelUtils.getTableTtileRow(sheet, 1, title);
        ExcelUtils.setCellStyle(workbook, row1);

    }

    /**
     * @param sheet:     excel的sheet
     * @param cwIncomes: 数据
     * @Author: wangyong
     * @Date: 2020/3/20 9:22
     * @return: int
     * @Description: 设置主体数据
     */
    private int setBodyData(XSSFWorkbook workbook, XSSFSheet sheet, List<CwIncome> cwIncomes) {
        int column = 2;
        Double allMoney = 0D;
        Double softMoney = 0D;
        Double hardwareMoney = 0D;
        Double operationPrice = 0D;
        Double otherMoney = 0D;
        int result = 0;
        if (cwIncomes != null) {
            for (CwIncome cwIncome : cwIncomes) {
                XSSFRow row = sheet.createRow(column);
                // 合同信息
                row.createCell(0).setCellValue(column - 1);
                row.createCell(1).setCellValue(stringCheckNull(cwIncome.getContractNo()));
                row.createCell(2).setCellValue(stringCheckNull(cwIncome.getSalerId()));
                row.createCell(3).setCellValue(stringCheckNull(cwIncome.getProjectName()));
                row.createCell(4).setCellValue(stringCheckNull(cwIncome.getCustName()));
                row.createCell(5).setCellValue(stringCheckNull(cwIncome.getUserName()));
                row.createCell(6).setCellValue(stringCheckNull(cwIncome.getProvince()));
                row.createCell(7).setCellValue(stringCheckNull(cwIncome.getCity()));
                row.createCell(8).setCellValue(stringCheckNull(cwIncome.getIndustry()));
                row.createCell(9).setCellValue(stringCheckNull(cwIncome.getProjectTypeName()));
                if (cwIncome.getSignDate() == null)
                    row.createCell(10).setCellValue("");
                else
                    row.createCell(10).setCellValue(cwIncome.getSignDate());

                // 合同金额
                row.createCell(11).setCellValue(floatCheckNull(cwIncome.getAllMoney()));
                allMoney += floatCheckNull(cwIncome.getAllMoney());
                row.createCell(12).setCellValue(doubleCheckNull(cwIncome.getSoftMoney()));
                softMoney += doubleCheckNull(cwIncome.getSoftMoney());
                row.createCell(13).setCellValue(doubleCheckNull(cwIncome.getHardwareMoney()));
                hardwareMoney += doubleCheckNull(cwIncome.getHardwareMoney());
                row.createCell(14).setCellValue(doubleCheckNull(cwIncome.getOperationPrice()));
                operationPrice += doubleCheckNull(cwIncome.getOperationPrice());
                row.createCell(15).setCellValue(floatCheckNull(cwIncome.getOtherMoney()));
                otherMoney += floatCheckNull(cwIncome.getOtherMoney());

                // 验收信息
                if (cwIncome.getAcceptDates() == null)
                    row.createCell(16).setCellValue("");
                else
                    row.createCell(16).setCellValue(cwIncome.getAcceptDate());
                row.createCell(17).setCellValue(stringCheckNull(cwIncome.getIsAccept()));
                row.createCell(18).setCellValue(stringCheckNull(cwIncome.getAcceptOpinion()));

                // 回款信息
                row.createCell(19).setCellValue(floatCheckNull(cwIncome.getMoneyBackAll()));
                row.createCell(20).setCellValue(floatCheckNull(cwIncome.getTaxPoint()));
                row.createCell(21).setCellValue(floatCheckNull(cwIncome.getTax()));
                row.createCell(22).setCellValue(floatCheckNull(cwIncome.getRemain()));

                if (cwIncome.getMoneyBack() != null) {
                    for (int i = 0; i < cwIncome.getMoneyBack().size(); i++) {
                        row.createCell(22 + i + 1).setCellValue(cwIncome.getMoneyBack().get(i));
                    }
                    if (cwIncome.getMoneyBack().size() > result) {
                        result = cwIncome.getMoneyBack().size();
                    }
                }
                column++;
            }

            XSSFRow row  = createRow(workbook, sheet, 2 + cwIncomes.size(), 0, 23 + result);
            row.getCell(0).setCellValue("合计");
            sheet.addMergedRegion(new CellRangeAddress(column, column, 0, 7));
            row.getCell(11).setCellValue(allMoney);  // 总金额
            row.getCell(12).setCellValue(softMoney);// 硬件金额
            row.getCell(13).setCellValue(hardwareMoney);// 软件金额
            row.getCell(14).setCellValue(operationPrice);// 运维金额
            row.getCell(15).setCellValue(otherMoney);// 其他金额
        }

        return result;
    }

    private XSSFRow createRow(XSSFWorkbook workbook, XSSFSheet sheet, int rowIndex, int start, int end) {
        XSSFRow row = sheet.createRow(rowIndex);
        for (int i = start; i < end; i++) {
            row.createCell(i).setCellStyle(ExcelUtils.getStyle(workbook));
        }
        return row;
    }

    private Double doubleCheckNull(Double d) {
        return d == null ? 0D : d;
    }

    private String stringCheckNull(String s) {
        return s == null ? "" : s;
    }

    private Float floatCheckNull(Float f) {
        return f == null ? 0F : f;
    }


}
