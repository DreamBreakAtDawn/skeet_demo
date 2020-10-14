package com.skeet.consul.provider.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/9 18:00
 */
@Data
@Builder
public class Person {

    private String name;

    private Integer age;
}
