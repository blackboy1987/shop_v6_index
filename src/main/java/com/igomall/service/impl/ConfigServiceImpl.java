/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: uoAU527lhmVC9BKtZv8vAY5QFW872DDD
 */
package com.igomall.service.impl;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import com.igomall.Setting;
import com.igomall.service.ConfigService;
import com.igomall.util.SystemUtils;

/**
 * Service - 配置
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class ConfigServiceImpl implements ConfigService {

	@Value("${template.update_delay}")
	private String templateUpdateDelay;
	@Value("${message.cache_seconds}")
	private Integer messageCacheSeconds;

	@Resource
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Resource
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	@Resource
	private FixedLocaleResolver fixedLocaleResolver;

	@Override
	public void init() {
		try {
			Setting setting = SystemUtils.getSetting();
			setting.setSmtpPassword(null);
			setting.setKuaidi100Customer(null);
			setting.setKuaidi100Key(null);
			setting.setCnzzPassword(null);
			setting.setSmsAppId(null);
			setting.setSmsSecretKey(null);
			ProxyFactory proxyFactory = new ProxyFactory(setting);
			proxyFactory.setProxyTargetClass(true);
			proxyFactory.addAdvice(new MethodBeforeAdvice() {

				public void before(Method method, Object[] args, Object target) throws Throwable {
					if (StringUtils.startsWith(method.getName(), "set")) {
						throw new UnsupportedOperationException("Operation not supported");
					}
				}

			});
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			configuration.setSharedVariable("setting", proxyFactory.getProxy());
			configuration.setSharedVariable("locale", setting.getLocale());
			if (setting.getIsDevelopmentEnabled()) {
				configuration.setSetting("template_update_delay", "0");
				reloadableResourceBundleMessageSource.setCacheSeconds(0);
			} else {
				configuration.setSetting("template_update_delay", templateUpdateDelay);
				reloadableResourceBundleMessageSource.setCacheSeconds(messageCacheSeconds);
			}
			fixedLocaleResolver.setDefaultLocale(LocaleUtils.toLocale(String.valueOf(setting.getLocale())));
		} catch (TemplateModelException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}