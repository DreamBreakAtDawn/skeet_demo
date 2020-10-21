package com.skeet.consul.provider.project.qtrade.ai.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/21 14:02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkProjectDataEntityDTO {

    private Integer startIndex;

    private Integer endIndex;

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
}
