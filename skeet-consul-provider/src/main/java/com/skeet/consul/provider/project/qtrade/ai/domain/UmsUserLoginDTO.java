package com.skeet.consul.provider.project.qtrade.ai.domain;

import com.skeet.consul.provider.util.JsonUtils;
import lombok.Data;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/20 15:34
 */
@Data
public class UmsUserLoginDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public static void main(String[] args) {
        UmsUserLoginDTO dto = new UmsUserLoginDTO();
        dto.setUsername("skeet");
        System.out.println(JsonUtils.beanToJson(dto));
    }
}
