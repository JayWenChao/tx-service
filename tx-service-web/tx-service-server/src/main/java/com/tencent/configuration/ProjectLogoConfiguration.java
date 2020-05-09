package com.tencent.configuration;

import com.tencent.kt.toolkit.VersionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 */
@Slf4j
@Configuration
public class ProjectLogoConfiguration implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, CommandLineRunner {


    private static final String LOGO_STR = "  ,--.'|_                                                        ,--.'|_   \n" +
            "  |  | :,'                ,---,                          ,---,   |  | :,'  \n" +
            "  :  : ' :            ,-+-. /  |                     ,-+-. /  |  :  : ' :  \n" +
            ".;__,'  /     ,---.  ,--.'|'   |   ,---.     ,---.  ,--.'|'   |.;__,'  /   \n" +
            "|  |   |     /     \\|   |  ,\"' |  /     \\   /     \\|   |  ,\"' ||  |   |    \n" +
            ":__,'| :    /    /  |   | /  | | /    / '  /    /  |   | /  | |:__,'| :    \n" +
            "  '  : |__ .    ' / |   | |  | |.    ' /  .    ' / |   | |  | |  '  : |__  \n" +
            "  |  | '.'|'   ;   /|   | |  |/ '   ; :__ '   ;   /|   | |  |/   |  | '.'| \n" +
            "  ;  :    ;'   |  / |   | |--'  '   | '.'|'   |  / |   | |--'    ;  :    ; \n" +
            "  |  ,   / |   :    |   |/      |   :    :|   :    |   |/        |  ,   /  \n" +
            "   ---`-'   \\   \\  /'---'        \\   \\  /  \\   \\  /'---'          ---`-'   \n" +
            "             `----'               `----'    `----'                         \n";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");


    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.out.println("\033[31;5m" + buildBannerText() + "\033[0m");
    }

    private String buildBannerText() {
        return ProjectLogoConfiguration.LINE_SEPARATOR
                + ProjectLogoConfiguration.LINE_SEPARATOR
                + LOGO_STR
                + ProjectLogoConfiguration.LINE_SEPARATOR
                + " :: tx~~service~~" + VersionUtils.VERSION + " :: "
                + ProjectLogoConfiguration.LINE_SEPARATOR;
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("启动完毕...");
    }
}
