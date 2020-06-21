/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: KCodXagnBcJs4ImshCa49UnNrj833dNP
 */
package com.igomall.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.igomall.entity.Admin;
import com.igomall.entity.Member;
import com.igomall.entity.PointLog;
import com.igomall.security.CurrentUser;
import com.igomall.service.MemberService;
import com.igomall.service.PointLogService;

/**
 * Controller - 积分
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminPointController")
@RequestMapping("/admin/point")
public class PointController extends BaseController {

	@Resource
	private PointLogService pointLogService;
	@Resource
	private MemberService memberService;

	/**
	 * 会员选择
	 */
	@GetMapping("/member_select")
	public ResponseEntity<?> memberSelect(String keyword) {
		List<Map<String, Object>> data = new ArrayList<>();
		if (StringUtils.isEmpty(keyword)) {
			return ResponseEntity.ok(data);
		}
		List<Member> members = memberService.search(keyword, null, null);
		for (Member member : members) {
			Map<String, Object> item = new HashMap<>();
			item.put("id", member.getId());
			item.put("name", member.getUsername());
			item.put("point", member.getPoint());
			data.add(item);
		}
		return ResponseEntity.ok(data);
	}

	/**
	 * 调整
	 */
	@GetMapping("/adjust")
	public String adjust() {
		return "admin/point/adjust";
	}

	/**
	 * 调整
	 */
	@PostMapping("/adjust")
	public ResponseEntity<?> adjust(Long memberId, long amount, String memo, @CurrentUser Admin currentUser) {
		Member member = memberService.find(memberId);
		if (member == null) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		if (amount == 0) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		if (member.getPoint() == null || member.getPoint() + amount < 0) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		memberService.addPoint(member, amount, PointLog.Type.ADJUSTMENT, memo);
		return Results.OK;
	}

	/**
	 * 记录
	 */
	@GetMapping("/log")
	public String log(Long memberId, Pageable pageable, ModelMap model) {
		Member member = memberService.find(memberId);
		if (member != null) {
			model.addAttribute("member", member);
			model.addAttribute("page", pointLogService.findPage(member, pageable));
		} else {
			model.addAttribute("page", pointLogService.findPage(pageable));
		}
		return "admin/point/log";
	}

}