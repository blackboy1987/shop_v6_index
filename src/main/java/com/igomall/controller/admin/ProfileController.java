/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: I5nrjTxqKOjUXoxt7ZR/KAMDmcA4wisF
 */
package com.igomall.controller.admin;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igomall.Results;
import com.igomall.entity.Admin;
import com.igomall.security.CurrentUser;
import com.igomall.service.AdminService;

/**
 * Controller - 个人资料
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminProfileController")
@RequestMapping("/admin/profile")
public class ProfileController extends BaseController {

	@Resource
	private AdminService adminService;

	/**
	 * 验证当前密码
	 */
	@GetMapping("/check_current_password")
	public @ResponseBody boolean checkCurrentPassword(String currentPassword, @CurrentUser Admin currentUser) {
		return StringUtils.isNotEmpty(currentPassword) && currentUser.isValidCredentials(currentPassword);
	}

	/**
	 * 编辑
	 */
	@GetMapping("/edit")
	public String edit(@CurrentUser Admin currentUser, ModelMap model) {
		model.addAttribute("admin", currentUser);
		return "admin/profile/edit";
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public ResponseEntity<?> update(String currentPassword, String password, String email, @CurrentUser Admin currentUser) {
		if (!isValid(Admin.class, "email", email)) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		if (StringUtils.isNotEmpty(currentPassword) && StringUtils.isNotEmpty(password)) {
			if (!isValid(Admin.class, "password", password)) {
				return Results.UNPROCESSABLE_ENTITY;
			}
			if (!currentUser.isValidCredentials(currentPassword)) {
				return Results.UNPROCESSABLE_ENTITY;
			}
			currentUser.setPassword(password);
		}
		currentUser.setEmail(email);
		adminService.update(currentUser);
		return Results.OK;
	}

}