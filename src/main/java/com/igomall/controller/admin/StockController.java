/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: qLy8YOD9dII5glZhAjXCUTvHE91TODll
 */
package com.igomall.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.Pageable;
import com.igomall.service.StockLogService;

/**
 * Controller - 库存
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminStockController")
@RequestMapping("/admin/stock")
public class StockController extends BaseController {

	@Resource
	private StockLogService stockLogService;

	/**
	 * 记录
	 */
	@GetMapping("/log")
	public String log(Pageable pageable, ModelMap model) {
		model.addAttribute("page", stockLogService.findPage(pageable));
		return "admin/stock/log";
	}

}