package org.workholiday.coffeemaker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        20:35 07/14/2017
 * Version:     1.0
 * Description: 配置文件工具类
 */
public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static final String CONFIG_FILE = "config.properties";


    private PropertiesUtil() {
        // to avoid creating instance
    }

    /** 配置文件对象 */
    private static Properties props;

    /** PropertiesUtil实例 */
    private static PropertiesUtil instance;

    /**
     * 获取PropertiesUtil实例
     * @return
     */
    public static PropertiesUtil getInstance() {
        if (instance == null) {
            instance = new PropertiesUtil();
        }
        return instance;
    }

    /**
     * 加载properties
     */
    private void loadProperties() {

        ClassLoader loader = PropertiesUtil.class.getClassLoader();
        InputStream in = null;

        try {
            in = loader.getResourceAsStream(CONFIG_FILE);
            props = new Properties();
            props.load(in);
        } catch (IOException e) {
            logger.error("Error loading properties file.", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // ignored
                }
            }
        }
    }

    /**
     * 确保properties不为空
     */
    private void ensure() {
        if (props == null) {
            this.loadProperties();
        }
    }

    /**
     * 获取配置项
     * @param key
     * @return
     */
    public String getProperty(String key) {
        ensure();
        return props.getProperty(key);
    }

}
