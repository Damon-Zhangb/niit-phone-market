package com.niit.niitphone.config;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该工具类用于文件读取、写入和处理
 *
 * @author 章卜
 * @since 2020-12-16
 */
public class GetFileUtils {
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "GetFileUtils{" +
                "file=" + file +
                '}';
    }

    /**
     * 获取数据
     *
     * @return
     * @throws Exception
     */
    public List<List<String>> getFile() throws Exception {
        //创建输入流,读取Excel
        InputStream is = new FileInputStream(file.getAbsolutePath());
        //jxl提供的Workbook类
        Workbook wb = Workbook.getWorkbook(is);
        //创建sheet对象，一般输入只有一个sheet；多个sheet此处暂不考虑
        Sheet sheet = wb.getSheet(0);
        //得到所有行数
        int rows = sheet.getRows();
        //查看是否读取数据
        /*System.out.println(rows);*/
        //得到所有数据
        List<List<String>> allData = new ArrayList<List<String>>();
        //从第二行开始，因为第一行是列名
        for (int j = 1; j < rows; j++) {
            List<String> oneData = new ArrayList<String>();
            //得到每一行的单元格的数据
            Cell[] cells = sheet.getRow(j);
            for (int k = 0; k < cells.length; k++) {
                oneData.add(cells[k].getContents().trim());
            }
            //存储每一条数据
            allData.add(oneData);
            //打印检查结果
            System.out.println(oneData);
        }
        /**下为结果示例：
         * [[3, 20, adsfsadf, 12321sa, 11111111111, 42, 男, 54, 0, xxx, 王qq]]*/
        return allData;
    }

    /**
     * 处理数据
     *
     * @param allData
     * @return List<List < String>>
     */
    public List<Map<String, Object>> dealData(List<List<String>> allData, Object object) {
        // 获得该类的所有属性
        Class classes = object.getClass();
        Field[] fields = classes.getDeclaredFields();
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

        /**要求：此时表格内每一列的顺序必须和实体类的顺序一致*/
        for (int i = 0; i < allData.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int j = 0; j < allData.get(0).size(); j++) {
                map.put(fields[j].getName(), allData.get(i).get(j));
            }
            results.add(map);
        }
        /*System.out.println(results);*/
        return results;
    }

    /**
     * 写入数据
     * 暂时未完成，空着
     */
    public void writeExcel(List<List<String>> result, String sheetName) {
    }
}
