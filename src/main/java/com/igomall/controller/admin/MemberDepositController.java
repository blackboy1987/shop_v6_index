/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: KIp5mXEUcXGhR0tizpHnKXpAQ5VYIWMt
 */
package com.igomall.controller.admin;

import java.math.BigDecimal;
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
import com.igomall.entity.MemberDepositLog;
import com.igomall.security.CurrentUser;
import com.igomall.service.MemberDepositLogService;
import com.igomall.service.MemberService;

/**
 * Controller - 会员预存款
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminMemberDepositController")
@RequestMapping("/admin/member_deposit")
public class MemberDepositController extends BaseController {

	@Resource
	private MemberDepositLogService memberDepositLogService;
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
			item.put("availableBalance", member.getAvailableBalance());
			data.add(item);
		}
		return ResponseEntity.ok(data);
	}

	/**
	 * 调整
	 */
	@GetMapping("/adjust")
	public String adjust() {
		return "admin/member_deposit/adjust";
	}

	/**
	 * 调整
	 */
	@PostMapping("/adjust")
	public ResponseEntity<?> adjust(Long memberId, BigDecimal amount, String memo, @CurrentUser Admin currentUser) {
		Member member = memberService.find(memberId);
		if (member == null) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		if (member.getBalance() == null || member.getAvailableBalance().add(amount).compareTo(BigDecimal.ZERO) < 0) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		memberService.addBalance(member, amount, MemberDepositLog.Type.ADJUSTMENT, memo);
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
			model.addAttribute("page", memberDepositLogService.findPage(member, pageable));
		} else {
			model.addAttribute("page", memberDepositLogService.findPage(pageable));
		}
		return "admin/member_deposit/log";
	}

}