package com.skeet.consul.provider.mine.loadclass;

import com.skeet.consul.provider.mine.loadclass.entity.LoadClass;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/29 17:01
 */
public class MainClass {

    public static void main(String[] args) throws Exception {
//        System.out.println(LoadClass.PROPERTY);
//        System.out.println(LoadClass.getName());
//        System.out.println(Class.forName("com.skeet.consul.provider.mine.loadclass.entity.LoadClass"));
        System.out.println(LoadClass.class.getClassLoader());
        System.out.println(String.class.getClassLoader().getParent());
    }
}
