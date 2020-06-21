/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: OanG9IpTj99ZMfhCDQJ13jOzY6Sa0Qgs
 */
package com.igomall.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.Pageable;
import com.igomall.service.DistributionCommissionService;

/**
 * Controller - 分销佣金
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminDistributionCommissionController")
@RequestMapping("/admin/distribution_commission")
public class DistributionCommissionController extends BaseController {

	@Resource
	private DistributionCommissionService distributionCommissionService;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", distributionCommissionService.findPage(pageable));
		return "admin/distribution_commission/list";
	}

}