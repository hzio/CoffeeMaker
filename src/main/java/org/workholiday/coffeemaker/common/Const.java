package org.workholiday.coffeemaker.common;

/**
 * Function:
 * Author:   哲别
 * Mail:     zhaohevip@gmail.com
 * Date:     15:02 07/15/2017
 * Version:  1.0
 */
public interface Const {

    /** 模板位置目录 */
    String TEMPLATE_LOCATION_DIR       = "template";

    String TEMPLATE_NAME_SUFFIX        = "FileTemplate.vm";

    /** Lombok插件 */
    String LOMBOK_ANNOTATION_DATA      = "lombok.Data";

    /** Spring注解 */
    String SPRING_ANNOTATION_SERVICE         = "org.springframework.stereotype.Service";
    String SPRING_ANNOTATION_AUTOWIRED       = "org.springframework.beans.factory.annotation.Autowired";
    String SPRING_ANNOTATION_CONTROLLER      = "org.springframework.web.bind.annotation.Controller";
    String SPRING_ANNOTATION_REST_CONTROLLER = "org.springframework.web.bind.annotation.RestController";
    String SPRING_ANNOTATION_REQUEST_MAPPING = "org.springframework.web.bind.annotation.RequestMapping";
    String SPRING_ANNOTATION_REQUEST_BODY    = "org.springframework.web.bind.annotation.RequestBody";
    String SPRING_ANNOTATION_RESPONSE_BODY   = "org.springframework.web.bind.annotation.ResponseBody";
    String SPRING_ANNOTATION_REQUEST_METHOD  = "org.springframework.web.bind.annotation.RequestMethod";

}
