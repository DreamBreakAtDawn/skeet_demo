package com.skeet.consul.provider.mine.aop.component;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/9/8 14:47
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.skeet.consul.provider.mine.aop.entity.InstanceFromImportSelector"};
    }
}
