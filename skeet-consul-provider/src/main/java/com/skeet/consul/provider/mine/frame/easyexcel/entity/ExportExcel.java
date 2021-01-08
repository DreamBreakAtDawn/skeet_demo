package com.skeet.consul.provider.mine.frame.easyexcel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/1/7 10:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportExcel {

    private Integer age;

    private String name;

    private String favorite;
}
