package com.skeet.common.api.util;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/21 20:14
 */
public class TimeUtil {

    public static void main(String[] args) {
        getDateRegion(20200201, 20200312);
    }

    public static List<Integer> getDateRegion(Integer fromSnapshotTime, Integer toSnapshotTime) {
        List<Integer> results = Lists.newArrayList();
        for (int i = fromSnapshotTime; i <= toSnapshotTime; i++) {
            if (needJumpToNextMonth(i)) {
                i = nextMonth(i);
            }
            results.add(i);
        }
        return results;
    }

    public static boolean needJumpToNextMonth(int time) {
        int year = time / 10000;
        int month = time / 100 % 100;
        int day = time % 100;

        boolean flag = false;

        if (day < 29) {
            return flag;
        }

        if (day > 31) {
            flag = true;
        } else if (day == 31) {
            if (Arrays.asList(2, 4, 6, 9, 11).contains(month)) {
                flag = true;
            }
        } else if (day == 30) {
            if (month == 2) {
                flag = true;
            }
        } else if (day == 29) {
            if (month == 2) {
                if (!(year % 4 == 0 && year % 100 != 0) && year % 400 != 0) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    public static int nextMonth(int time) {
        int year = time / 10000;
        int month = time / 100 % 100;
        int day = 1;

        if (month < 12) {
            month = month + 1;
        } else {
            year = year + 1;
            month = 1;
        }

        return year * 10000 + month * 100 + day;
    }
}
