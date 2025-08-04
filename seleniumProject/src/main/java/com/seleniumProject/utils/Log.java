package com.seleniumProject.utils;

import com.seleniumProject.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Log extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Log.class);


    public static void fail(String message) {
        logger.error(message);
        getTest().fail(message);
    }

    public static void warning(String message) {
        logger.warn(message);
        getTest().warning(message);
    }

    public static void info(String message) {
        logger.info(message);
        getTest().info(message);
    }

    public static void info(List<String> message) {
        logger.info(message);
        getTest().info(String.valueOf(message));
    }

    public static void error(String message, IOException e) {
        logger.warn(message);
        getTest().warning(message + "\n" + e.getMessage());
    }




}
