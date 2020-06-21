package com.igomall.config;

import com.igomall.template.method.CurrencyMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class FreeMarkerConfig {

    @Resource
    private ServletContext servletContext;

    @Resource
    private CurrencyMethod currencyMethod;

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths("classpath:/templates/");

        Properties freemarkerSettings = new Properties();
        freemarkerSettings.setProperty("default_encoding","UTF-8");
        freemarkerSettings.setProperty("url_escaping_charset","UTF-8");
        freemarkerSettings.setProperty("output_format","HTMLOutputFormat");
        freemarkerSettings.setProperty("template_update_delay","0");
        freemarkerSettings.setProperty("tag_syntax","auto_detect");
        freemarkerSettings.setProperty("classic_compatible","true");
        freemarkerSettings.setProperty("number_format","0.######");
        freemarkerSettings.setProperty("boolean_format","true,false");
        freemarkerSettings.setProperty("datetime_format","yyyy-MM-dd");
        freemarkerSettings.setProperty("date_format","yyyy-MM-dd");
        freemarkerSettings.setProperty("time_format","HH:mm:ss");
        freemarkerSettings.setProperty("object_wrapper","freemarker.ext.beans.BeansWrapper");
        freeMarkerConfigurer.setFreemarkerSettings(freemarkerSettings);

        Map<String,Object> freemarkerVariables = new HashMap<>();
        freemarkerVariables.put("base",servletContext.getContextPath());
        /*freemarkerVariables.put("showPowered",);
        freemarkerVariables.put("message",);
        freemarkerVariables.put("abbreviate",);*/
        freemarkerVariables.put("currency",currencyMethod);
        /*freemarkerVariables.put("ad_position",);
        freemarkerVariables.put("article_category_children_list",);
        freemarkerVariables.put("article_category_parent_list",);
        freemarkerVariables.put("article_category_root_list",);
        freemarkerVariables.put("article_list",);
        freemarkerVariables.put("article_tag_list",);
        freemarkerVariables.put("attribute_list",);
        freemarkerVariables.put("brand_list",);
        freemarkerVariables.put("business_attribute_list",);
        freemarkerVariables.put("business_cash_count",);
        freemarkerVariables.put("category_application_count",);
        freemarkerVariables.put("consultation_list",);
        freemarkerVariables.put("distribution_cash_count",);
        freemarkerVariables.put("friend_link_list",);
        freemarkerVariables.put("instant_message_list",);
        freemarkerVariables.put("member_attribute_list",);
        freemarkerVariables.put("navigation_list",);
        freemarkerVariables.put("order_count",);
        freemarkerVariables.put("pagination",);
        freemarkerVariables.put("product_category_children_list",);
        freemarkerVariables.put("product_category_parent_list",);
        freemarkerVariables.put("product_category_root_list",);
        freemarkerVariables.put("product_count",);
        freemarkerVariables.put("product_favorite",);
        freemarkerVariables.put("product_list",);
        freemarkerVariables.put("product_tag_list",);
        freemarkerVariables.put("promotion_list",);
        freemarkerVariables.put("promotion_plugin",);
        freemarkerVariables.put("review_count",);
        freemarkerVariables.put("review_list",);
        freemarkerVariables.put("seo",);
        freemarkerVariables.put("store_ad_image_list",);
        freemarkerVariables.put("store_count",);
        freemarkerVariables.put("store_favorite",);
        freemarkerVariables.put("store_product_category_children_list",);
        freemarkerVariables.put("store_product_category_parent_list",);
        freemarkerVariables.put("store_product_category_root_list",);
        freemarkerVariables.put("store_product_tag_list",);
        freemarkerVariables.put("has_permission_tag",);
        freemarkerVariables.put("has_any_permissions_tag",);*/

        freeMarkerConfigurer.setFreemarkerVariables(freemarkerVariables);



        return freeMarkerConfigurer;
    }

}
