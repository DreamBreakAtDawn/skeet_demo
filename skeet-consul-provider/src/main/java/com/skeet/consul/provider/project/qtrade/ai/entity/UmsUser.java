package com.skeet.consul.provider.project.qtrade.ai.entity;

import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/20 15:30
 */
@Data
public class UmsUser {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户所属部门
     */
    private String department;

    /**
     * 用户角色
     */
    private String role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
