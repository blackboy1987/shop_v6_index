/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: IPtmAaWFXIzmFpYFfENoNDVYPnuVl1jv
 */
package com.igomall.controller.admin;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.Pageable;
import com.igomall.Results;
import com.igomall.service.CouponService;

/**
 * Controller - 优惠券
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminCouponController")
@RequestMapping("/admin/coupon")
public class CouponController extends BaseController {

	@Resource
	private CouponService couponService;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", couponService.findPage(pageable));
		return "admin/coupon/list";
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> delete(Long[] ids) {
		couponService.delete(ids);
		return Results.OK;
	}

}