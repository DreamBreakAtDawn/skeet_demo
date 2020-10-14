package com.skeet.consul.provider.mine.aop;

import com.skeet.consul.provider.mine.aop.component.MyImportSelector;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/30 15:05
 */
@Configuration
@ComponentScan("com.skeet.consul.provider.mine.aop")
@EnableAspectJAutoProxy
@Import(MyImportSelector.class)
public class MainConfig {
}
