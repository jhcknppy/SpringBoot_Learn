package com.ppy.springioc.config;

import ch.qos.logback.core.CoreConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.ppy.springioc")
@Import(CoreConstants.class)
public class AppConfig {
}
