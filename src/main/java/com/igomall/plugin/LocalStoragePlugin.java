
package com.igomall.plugin;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.igomall.Setting;
import com.igomall.util.SystemUtils;

/**
 * Plugin - 本地文件存储
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Component("localStoragePlugin")
public class LocalStoragePlugin extends StoragePlugin {

	@Resource
	private ServletContext servletContext;

	@Override
	public String getName() {
		return "本地文件存储";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return "好源++";
	}

	@Override
	public String getSiteUrl() {
		return "localhost";
	}

	@Override
	public String getInstallUrl() {
		return null;
	}

	@Override
	public String getUninstallUrl() {
		return null;
	}

	@Override
	public String getSettingUrl() {
		return "/admin/plugin/local_storage/setting";
	}

	@Override
	public void upload(String path, File file, String contentType) {
		File destFile = new File(servletContext.getRealPath("/"), path);
		try {
			FileUtils.moveFile(file, destFile);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public String getUrl(String path) {
		Setting setting = SystemUtils.getSetting();
		return setting.getSiteUrl() + path;
	}

}