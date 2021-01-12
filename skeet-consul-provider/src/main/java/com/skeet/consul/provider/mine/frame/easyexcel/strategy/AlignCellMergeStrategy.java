package com.skeet.consul.provider.mine.frame.easyexcel.strategy;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import com.skeet.consul.provider.util.SheetUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description 对齐指定列的单元格
 * @Author chengsj
 * @Date 2021/1/8 11:04
 */
public class AlignCellMergeStrategy extends AbstractMergeStrategy {

    /**
     * 要对齐的参考列
     */
    private Integer alignColumnIndex;

    /**
     * 对齐合并单元格的列
     */
    private List<Integer> columnIndexes;

    public AlignCellMergeStrategy(Integer alignColumnIndex, Integer... columnIndexes) {
        if (alignColumnIndex < 0) {
            throw new IllegalArgumentException("AlignColumnIndex must be greater than 0");
        }

        for (Integer columnIndex : columnIndexes) {
            Objects.requireNonNull(columnIndex);

            if (columnIndex < 0) {
                throw new IllegalArgumentException("ColumnIndex must be greater than 0");
            }
        }

        this.alignColumnIndex = alignColumnIndex;
        this.columnIndexes = Arrays.asList(columnIndexes);
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, int relativeRowIndex) {
        int rowIndex = cell.getRowIndex();
        Integer columnIndex = head.getColumnIndex();
        if (!columnIndexes.contains(columnIndex) || relativeRowIndex < 1) {
            return;
        }

        int sameCount = this.getSameCount(sheet, rowIndex);

        if (sameCount <= 0) {
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
     * 获取相同内容的单元格数量（拿作比较的列的单元格的行首与当前行作比较）
     *
     * @param sheet       工作表
     * @param rowIndex    行索引
     * @return 相同内容的单元格数量
     */
    private int getSameCount(Sheet sheet, int rowIndex) {
        CellRangeAddress cellRangeAddress = SheetUtil.getCellRangeAddress(sheet, rowIndex, alignColumnIndex);
        if (Objects.isNull(cellRangeAddress)) {
            return 0;
        }

        return rowIndex - cellRangeAddress.getFirstRow();
    }
}
