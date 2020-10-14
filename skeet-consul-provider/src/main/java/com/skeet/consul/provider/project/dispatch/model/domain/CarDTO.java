package com.skeet.consul.provider.project.dispatch.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/11/19 15:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    /**
     * 车型 （BC大车； LC小车, CarMetre4.2； CarMetre7.6，CarMetre9.6）
     */
    private String carType;

    /**
     * 趟数
     */
    private Integer tripNum;
    /**
     * 是否首单 Y - 是，N - 否
     */
    private String firstOrder;
    /**
     * 唯一编号
     */
    private String collectCarNo;
}
