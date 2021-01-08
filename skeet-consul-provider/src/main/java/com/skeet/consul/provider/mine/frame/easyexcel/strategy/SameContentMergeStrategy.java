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
 * @Description
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

        if (sameCount <= 1) {
            return;
        }

        this.removeMergedRegion(sheet, rowIndex - 1, columnIndex);

        CellRangeAddress cellRangeAddress = new CellRangeAddress(
                rowIndex - sameCount, rowIndex, columnIndex, columnIndex);
        sheet.addMergedRegion(cellRangeAddress);
    }

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
