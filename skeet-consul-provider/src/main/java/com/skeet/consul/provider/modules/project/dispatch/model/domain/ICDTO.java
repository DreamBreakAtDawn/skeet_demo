package com.skeet.consul.provider.modules.project.dispatch.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Desc:TMS的清关对象-委托单号维度
 *
 * @author chengsj
 * @date 2019/9/2 19:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ICDTO {

    /*-------------------------------- 由TMS提供的字段 --------------------------------*/
    /**
     * 入库单号
     */
    private String ic;

    /*-------------------------------- 业务逻辑使用的字段 --------------------------------*/
    /**
     * 柜子/主单id
     */
    private String manifestId;

    /**
     * 是否使用当前时间作为计费时间请求计费
     */
    private String useCurrentDateAsBillDate;
}
