package com.tencent;

import com.tencent.configuration.ProjectLogoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 */
@ComponentScans(value = {
        @ComponentScan(value = "com.tencent", useDefaultFilters = true)
})
@SpringBootApplication
@MapperScans(value = {@MapperScan(value = "com.tencent.mapper")})
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class TxApplicationCli {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.OFF)
                .listeners(new ProjectLogoConfiguration())
                .sources(TxApplicationCli.class)
                .web(WebApplicationType.SERVLET)
                .run(args);

    }
}
