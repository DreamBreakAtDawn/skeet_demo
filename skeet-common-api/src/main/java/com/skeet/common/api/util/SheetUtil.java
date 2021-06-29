package com.skeet.common.api.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/1/12 17:12
 */
public class SheetUtil {

    /**
     * 获取合并的单元格
     *
     * @param sheet       工作簿
     * @param rowIndex    行索引
     * @param columnIndex 列索引
     * @return 合并的单元格
     */
    public static CellRangeAddress getCellRangeAddress(Sheet sheet, int rowIndex, int columnIndex) {
        for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
            if (mergedRegion.isInRange(rowIndex, columnIndex)) {
                return mergedRegion;
            }
        }

        return null;
    }
}
