package com.skeet.consul.provider.modules.project.qtrade.ai.domain;

import com.skeet.consul.provider.util.JsonUtils;
import lombok.Data;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/20 15:34
 */
@Data
public class UmsUserRegisterDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public static void main(String[] args) {
        UmsUserRegisterDTO dto = new UmsUserRegisterDTO();
        dto.setUsername("张三");
        dto.setPassword("123456");
        System.out.println(JsonUtils.beanToJson(dto));
    }
}
