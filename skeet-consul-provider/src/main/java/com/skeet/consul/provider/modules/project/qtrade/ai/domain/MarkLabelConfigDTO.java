package com.skeet.consul.provider.modules.project.qtrade.ai.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/20 14:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkLabelConfigDTO {

    /**
     * 标签ID
     */
    private String labelId;

    /**
     * 项目ID
     */
    private String projectId;

    /**
     * 标签颜色
     */
    private String color;

    /**
     * 标签快捷键
     */
    private String shortcutKey;

    /**
     * 标签要素
     */
    private String element;

    /**
     * 标签类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
