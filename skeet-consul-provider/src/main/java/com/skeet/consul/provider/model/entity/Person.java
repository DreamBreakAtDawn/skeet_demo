package com.skeet.consul.provider.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/9 18:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person extends Human {

    private String name;

    @JsonIgnore
    private Integer age;

    private String hate;

//    @JsonIgnore
    private String getMyAge() {
        return "age";
    }
}
