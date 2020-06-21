/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: 69hsCeu/97ZmrvbFH4vD860ABqe7UvVJ
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

import com.igomall.Pageable;
import com.igomall.Results;
import com.igomall.entity.StoreCategory;
import com.igomall.service.StoreCategoryService;

/**
 * Controller - 店铺分类
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminStoreCategoryController")
@RequestMapping("/admin/store_category")
public class StoreCategoryController extends BaseController {

	@Resource
	private StoreCategoryService storeCategoryService;

	/**
	 * 检查用户名是否存在
	 */
	@GetMapping("/check_name")
	public @ResponseBody boolean checkName(String name) {
		return StringUtils.isNotEmpty(name) && !storeCategoryService.nameExists(name);
	}

	/**
	 * 添加
	 */
	@GetMapping("/add")
	public String add(ModelMap model) {
		return "admin/store_category/add";
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	public ResponseEntity<?> save(StoreCategory storeCategory) {
		if (!isValid(storeCategory)) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		storeCategory.setStores(null);
		storeCategoryService.save(storeCategory);
		return Results.OK;
	}

	/**
	 * 编辑
	 */
	@GetMapping("/edit")
	public String edit(Long id, ModelMap model) {
		model.addAttribute("storeCategory", storeCategoryService.find(id));
		return "admin/store_category/edit";
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public ResponseEntity<?> update(StoreCategory storeCategory) {
		if (!isValid(storeCategory)) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		storeCategoryService.update(storeCategory, "stores");
		return Results.OK;
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", storeCategoryService.findPage(pageable));
		return "admin/store_category/list";
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> delete(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				StoreCategory storeCategory = storeCategoryService.find(id);
				if (storeCategory != null && storeCategory.getStores() != null && !storeCategory.getStores().isEmpty()) {
					return Results.unprocessableEntity("admin.storeCategory.deleteExistNotAllowed", storeCategory.getName());
				}
			}
			long totalCount = storeCategoryService.count();
			if (ids.length >= totalCount) {
				return Results.unprocessableEntity("common.deleteAllNotAllowed");
			}
			storeCategoryService.delete(ids);
		}
		return Results.OK;
	}

}