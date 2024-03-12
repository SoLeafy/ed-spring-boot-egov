package com.apple.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.web.dao.MemberDAO;


@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public Map<String, Object> login(Map<String, Object> map) {
		return memberDAO.login(map);
	}

	public Map<String, Object> myInfo(String id) {
		return memberDAO.myInfo(id);
	}
}
