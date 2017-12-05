package org.workholiday.coffeemaker.util;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workholiday.coffeemaker.common.Const;
import org.workholiday.coffeemaker.wrapper.AbstractFileWrapper;

import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * Function: 模板工具类
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     01:28 06/10/2017
 * Version:  1.0
 */
public class VelocityUtil {

    private static Logger logger = LoggerFactory.getLogger(VelocityUtil.class);

    private VelocityUtil() {
        // to avoid creating instance
    }


    public static String parse(String templateFileName, AbstractFileWrapper data) {

        // 初始化模板引擎
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        Properties props = new Properties();

        URL resource = VelocityUtil.class.getClassLoader().getResource(".");
        Objects.requireNonNull(resource);

        // 设置模板目录
        String basePath = resource.getPath() + Const.TEMPLATE_LOCATION_DIR;
        props.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,  basePath);
        //设置velocity的编码
        props.setProperty(Velocity.ENCODING_DEFAULT, StandardCharsets.UTF_8.name());
        props.setProperty(Velocity.INPUT_ENCODING,   StandardCharsets.UTF_8.name());
        props.setProperty(Velocity.OUTPUT_ENCODING,  StandardCharsets.UTF_8.name());
        engine.init(props);

        Template template = engine.getTemplate(templateFileName);
        // 设置变量
        VelocityContext ctx = new VelocityContext();

        ctx.put("data", data);

        StringWriter writer = new StringWriter();
        template.merge(ctx, writer);

        return writer.toString();


    }

}
