package com.skeet.consul.provider.mine.frame.easyexcel.strategy;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description 相同列连续的相同内容合并为一个单元格
 * @Author chengsj
 * @Date 2021/1/8 11:04
 */
public class SameContentMergeStrategy extends AbstractMergeStrategy {

    private List<Integer> columnIndexes;

    public SameContentMergeStrategy(Integer... columnIndexes) {
        for (Integer columnIndex : columnIndexes) {
            Objects.requireNonNull(columnIndex);

            if (columnIndex < 0) {
                throw new IllegalArgumentException("ColumnIndex must be greater than 0");
            }
        }

        this.columnIndexes = Arrays.asList(columnIndexes);
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, int relativeRowIndex) {
        int rowIndex = cell.getRowIndex();
        Integer columnIndex = head.getColumnIndex();
        if (!columnIndexes.contains(columnIndex) || relativeRowIndex < 1) {
            return;
        }

        int sameCount = this.getSameCount(sheet, cell, rowIndex, columnIndex);

        if (sameCount == 0) {
            return;
        }

        this.removeMergedRegion(sheet, rowIndex - 1, columnIndex);

        CellRangeAddress cellRangeAddress = new CellRangeAddress(
                rowIndex - sameCount, rowIndex, columnIndex, columnIndex);
        sheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 如果单元格为合并后的单元格，则将该单元格拆分成多个单个的单元格
     *
     * @param sheet       工作表
     * @param rowIndex    行索引
     * @param columnIndex 列索引
     */
    private void removeMergedRegion(Sheet sheet, int rowIndex, Integer columnIndex) {
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (int i = 0; i < mergedRegions.size(); i++) {
            CellRangeAddress cellRangeAddress = mergedRegions.get(i);
            if (cellRangeAddress.isInRange(rowIndex, columnIndex)) {
                sheet.removeMergedRegion(i);
                break;
            }
        }
    }

    /**
     * 获取相同内容的单元格数量（与同一列且在该当前单元格所在行之前的单元格作比较）
     *
     * @param sheet       工作表
     * @param cell        单元格
     * @param rowIndex    行索引
     * @param columnIndex 列索引
     * @return 相同内容的单元格数量
     */
    private int getSameCount(Sheet sheet, Cell cell, int rowIndex, Integer columnIndex) {
        int sameCount = 0;
        int anotherRowIndex = rowIndex - 1;

        String thisValue = CellFormat.GENERAL_FORMAT.apply(cell).text;

        while (anotherRowIndex >= 0) {

            Cell anotherCell = CellUtil.getCell(sheet.getRow(anotherRowIndex--), columnIndex);
            String anotherValue = CellFormat.GENERAL_FORMAT.apply(anotherCell).text;
            if (!Objects.equals(thisValue, anotherValue)) {
                break;
            }

            sameCount++;
        }

        return sameCount;
    }
}
