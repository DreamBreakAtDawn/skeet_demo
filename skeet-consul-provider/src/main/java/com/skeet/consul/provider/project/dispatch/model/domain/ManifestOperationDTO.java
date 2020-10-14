package com.skeet.consul.provider.project.dispatch.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Desc:TMS操作数据对象-柜号/航空单号维度
 *
 * @author chengsj
 * @date 2019/11/12 14:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManifestOperationDTO {

    public static final String TRANSPORT_TYPE_SEA = "ST";
    public static final String TRANSPORT_TYPE_AIR = "AI";

    /**
     * 柜子/主单id
     */
    private String manifestId;

    /**
     * 柜号/主单号
     */
    private String manifestNo;

    /**
     * 柜型
     */
    private String cabinetType;

    /**
     * 运输类型（ST海运 ；AI空运）
     */
    private String transportType;

    /**
     * ic对象
     */
    private List<ICDTO> icDto;

    /**
     * 清关天数
     */
    private Integer clearanceDays;

    /**
     * 清关卡板数
     */
    private Integer clearancePlateNum;

    /**
     * 卡车集合
     */
    private List<CarDTO> carDTOs;

    /**
     * 发运完成时间（时间+时区）
     */
    private String departuredTime;

    /**
     * 清关完成时间 （时间+时区）
     */
    private String clearanceFinishTime;

    /**
     * 操作节点，如清关：CL,发运：SP,起飞：TF
     */
    private String operationNode;

    /**
     * 预计离港时间
     */
    private Date planLeaveHarbourTime;
    /**
     * 预计到港时间
     */
    private Date planArriveHarbourTime;
    /**
     * 实际送仓时间
     */
    private Date actualArriveTime;
    /**
     * 是否清关查验
     */
    private String isClearanceCheck;
    /**
     * 清关查验备注
     */
    private String clearanceTrackingRemark;
    /**
     *  是否报关查验
     */
    private String isDeclareCheck;
    /**
     * 报关查验备注
     */
    private String declareTrackingRemark;

}
