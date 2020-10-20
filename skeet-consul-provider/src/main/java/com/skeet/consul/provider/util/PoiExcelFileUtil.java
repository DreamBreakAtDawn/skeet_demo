package com.skeet.consul.provider.util;

import jdk.nashorn.internal.objects.annotations.Property;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Desc:
 *
 * @author chengsj
 * @data 2019/5/23 16:34
 **/
public class PoiExcelFileUtil {

    public static final String FILE_PATH = "E:\\";
    public static final String FILE_NAME = "target";
    public static final String FILE_FULPATHNAME = FILE_PATH + FILE_NAME;
    public static final String FILE_SUFFIX_XLS = ".xls";
    public static final String FILE_SUFFIX_XLSX = ".xlsx";

    public static Cell setCellTypeFormat(Cell cell, Workbook workbook) {

        DataFormatter dataFormatter = new DataFormatter();
        // setCellStyle(CellStyle)应该是用来设置数据写入excel文件时的类型（好像是）
//        官方文档:
//        Sheet#setDefaultColumnStyle
//        public void setDefaultColumnStyle(int column, CellStyle style)
//        Sets the default column style for a given column. POI will only apply this style to new cells added to the sheet.
//        Specified by:
//        setDefaultColumnStyle in interface Sheet
//        Parameters:
//        column - the column index
//        style - the style to set
        // setCellType(CellType)应该是用来设置从excel文件读取的数据的类型（错了）
        CellStyle textStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        //设置单元格格式为"文本"
        cell.setCellStyle(textStyle);
        cell.setCellType(CellType.STRING);
        return cell;
    }

    public static String getStringValue(Cell cell, Workbook workbook) {
        if (cell == null) {
            return null;
        }
        return setCellTypeFormat(cell, workbook).getStringCellValue();
    }

    public static File loadDefaultFile() {
        File xlsFile = new File(FILE_FULPATHNAME + FILE_SUFFIX_XLS);
        if (!xlsFile.exists()) {
            xlsFile = new File(FILE_FULPATHNAME + FILE_SUFFIX_XLSX);
        }
        return xlsFile;
    }

    public static File loadFile(String fileName) {
        return new File(fileName);
    }

    public static Sheet loadSheet(File xlsFile) throws Exception {
        Workbook workbook = WorkbookFactory.create(xlsFile);
        return workbook.getSheetAt(0);
    }

    /**
     * 覆盖写入文件
     *
     * @param lst      集合
     * @param fileName 文件名称
     */
    @SuppressWarnings("all")
    public static void writeToFileXlsx(List lst, String fileName) {
        if (CollectionUtils.isEmpty(lst)) {
            System.out.println("集合为空，不写入数据");
            return;
        }

        StopWatch watch = new StopWatch("数据写入文件");
        watch.start("写入数据至Excel");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "信息");
        CellStyle cellStyle = creatDefaultCellStyle(workbook);
        FileOutputStream fileoutputStream = null;
        try {
            Object o = lst.get(0);
            Class clazz = o.getClass();
            Field[] fields = clazz.getDeclaredFields();

            // 第一行为列标题
            Row row = sheet.createRow(0);
            for (int i = 0; i < fields.length; i++) {
                Field ff = fields[i];
                Property annotation = ff.getAnnotation(Property.class);
                if (Objects.nonNull(annotation)) {
                    row.createCell(i).setCellStyle(cellStyle);
                    row.createCell(i).setCellValue(annotation.name());
                }
            }

            // 从第二行开始为实际的内容
            for (int x = 0; x < lst.size(); x++) {
                Object item = lst.get(x);
                row = sheet.createRow(x + 1);
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    PropertyDescriptor descriptor = new PropertyDescriptor(f.getName(), clazz);
                    Method readMethod = descriptor.getReadMethod();
                    Object invoke = readMethod.invoke(item);
                    if (Objects.nonNull(invoke)) {
                        row.createCell(i).setCellStyle(cellStyle);
                        setRowValue(row, i, invoke);
                    }
                }
            }

            File file = FileUtil.getSingleFile(fileName);
            fileName = file.getPath();

            fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
            watch.stop();
            System.out.println(watch);
            System.out.println(String.format("已写入数据至文件【%s】中", fileName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(fileoutputStream)) {
                try {
                    fileoutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setRowValue(Row row, int i, Object invoke) {
        if (invoke instanceof Integer) {
            row.createCell(i).setCellValue((Integer) invoke);
        } else if (invoke instanceof BigDecimal) {
            row.createCell(i).setCellValue(getDoubleValue((BigDecimal) invoke));
        } else {
            row.createCell(i).setCellValue(objectToString(invoke));
        }
    }

    private static double getDoubleValue(BigDecimal obj) {
        return Double.parseDouble(obj.stripTrailingZeros().toPlainString());
    }

    /**
     * 追加写入文件
     *
     * @param lst      集合
     * @param fileName 文件名称
     */
    @SuppressWarnings("all")
    // TODO 写入文件时报空指针异常，待处理
    public static void writeToFileXlsxAppend(List lst, String fileName) {
        FileOutputStream fileoutputStream = null;
        Workbook workbook = null;
        Sheet sheet = null;
        int lastRowNum = 0;
        File xlsFile = new File(fileName);
        try {
            if (!xlsFile.exists() || xlsFile.length() == 0) {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet();
                workbook.setSheetName(0, "信息");

            } else {
                workbook = WorkbookFactory.create(xlsFile);
                sheet = workbook.getSheetAt(0);
                //文件最后一行
                lastRowNum = sheet.getLastRowNum();
            }
            CellStyle cellStyle = creatDefaultCellStyle(workbook);

            Object o = lst.get(0);
            Class clazz = o.getClass();
            Field[] fields = clazz.getDeclaredFields();

            Row row = null;

            // 如果文件没有内容，则第一行加上标题
            if (lastRowNum == 0) {
                // 第一行为列标题
                row = sheet.createRow(0);
                for (int i = 0; i < fields.length; i++) {
                    Field ff = fields[i];
                    Property annotation = ff.getAnnotation(Property.class);
                    if (Objects.nonNull(annotation)) {
                        row.createCell(i).setCellStyle(cellStyle);
                        row.createCell(i).setCellValue(annotation.name());
                    }
                }
            }
            // 追加写入内容
            for (int x = 0; x < lst.size(); x++) {
                Object item = lst.get(x);
                row = sheet.createRow(lastRowNum + x + 1);
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    PropertyDescriptor descriptor = new PropertyDescriptor(f.getName(), clazz);
                    Method readMethod = descriptor.getReadMethod();
                    Object invoke = readMethod.invoke(item);
                    if (Objects.nonNull(invoke)) {
                        row.createCell(i).setCellStyle(cellStyle);
                        setRowValue(row, i, invoke);
                    }
                }
            }

            if (Objects.isNull(fileoutputStream)) {
                fileoutputStream = new FileOutputStream(xlsFile);
            }
            workbook.write(fileoutputStream);
            fileoutputStream.close();
            System.out.println(String.format("已写入数据至文件【%s】中", fileName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(fileoutputStream)) {
                try {
                    fileoutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String objectToString(Object obj) {
        if (obj instanceof Date) {
            SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
            return format.format((Date) obj);
        }

        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).stripTrailingZeros().toPlainString();
        }

        return obj.toString();
    }

    private static CellStyle creatDefaultCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    public static void mergeFile(List<String> filePath) {

    }

//    public static void writeToFile() {
//        //定义表头
//        String[] title = {"序号", "姓名", "年龄"};
////创建excel工作簿
//        HSSFWorkbook workbook = new HSSFWorkbook();
////创建工作表sheet
//        HSSFSheet sheet = workbook.createSheet();
////创建第一行
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell = null;
////插入第一行数据的表头
//        for (int i = 0; i < title.length; i++) {
//            cell = row.createCell(i);
//            cell.setCellValue(title[i]);
//        }
////写入数据
//        for (int i = 1; i <= 10; i++) {
//            HSSFRow nrow = sheet.createRow(i);
//            HSSFCell ncell = nrow.createCell(0);
//            ncell.setCellValue("" + i);
//            ncell = nrow.createCell(1);
//            ncell.setCellValue("user" + i);
//            ncell = nrow.createCell(2);
//            ncell.setCellValue("24");
//        }
////创建excel文件
//        File file = new File("f://poi.xlsx");
//        try {
//            file.createNewFile();
//            //将excel写入
//            FileOutputStream stream = FileUtils.openOutputStream(file);
//            workbook.write(stream);
//            stream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
