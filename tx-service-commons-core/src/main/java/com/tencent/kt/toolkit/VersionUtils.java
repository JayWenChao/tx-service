package com.tencent.kt.toolkit;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class VersionUtils {


    public static String VERSION;
    /**
     * 获取当前version
     */
    private static final String VERSION_DEFAULT = "@project.version@";


    static {
        try (InputStream in = VersionUtils.class.getClassLoader().getResourceAsStream("tx-version.txt")) {
            Properties props = new Properties();
            props.load(in);
            String val = props.getProperty("version");
            if (val != null && !VERSION_DEFAULT.equals(val)) {
                VERSION = val;
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }
}

