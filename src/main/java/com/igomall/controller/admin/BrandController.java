/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: QKg3iMyMn/hvf3I1B1xNmvPEVOgLK4Ad
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

import com.igomall.Pageable;
import com.igomall.Results;
import com.igomall.entity.Brand;
import com.igomall.service.BrandService;

/**
 * Controller - 品牌
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminBrandController")
@RequestMapping("/admin/brand")
public class BrandController extends BaseController {

	@Resource
	private BrandService brandService;

	/**
	 * 添加
	 */
	@GetMapping("/add")
	public String add(ModelMap model) {
		model.addAttribute("types", Brand.Type.values());
		return "admin/brand/add";
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	public ResponseEntity<?> save(Brand brand) {
		if (!isValid(brand)) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		if (Brand.Type.TEXT.equals(brand.getType())) {
			brand.setLogo(null);
		} else if (StringUtils.isEmpty(brand.getLogo())) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		brand.setProducts(null);
		brand.setProductCategories(null);
		brandService.save(brand);
		return Results.OK;
	}

	/**
	 * 编辑
	 */
	@GetMapping("/edit")
	public String edit(Long id, ModelMap model) {
		model.addAttribute("types", Brand.Type.values());
		model.addAttribute("brand", brandService.find(id));
		return "admin/brand/edit";
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public ResponseEntity<?> update(Brand brand) {
		if (!isValid(brand)) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		if (Brand.Type.TEXT.equals(brand.getType())) {
			brand.setLogo(null);
		} else if (StringUtils.isEmpty(brand.getLogo())) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		brandService.update(brand, "products", "productCategories");
		return Results.OK;
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", brandService.findPage(pageable));
		return "admin/brand/list";
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> delete(Long[] ids) {
		brandService.delete(ids);
		return Results.OK;
	}

}