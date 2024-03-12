package com.apple.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.web.dao.IndexDAO;
import com.apple.web.util.Util;

@Service
public class IndexService {
	@Autowired
	private IndexDAO indexDAO;
	@Autowired
	private Util util;

	public List<Map<String, Object>> boardList() {
		return indexDAO.boardList();
	}

	public Map<String, Object> boardDetail(int no) {
		return indexDAO.boardDetail(no);
	}

	public Map<String, Object> detail(int no) {
		return indexDAO.detail(no);
	}

	public List<Map<String, Object>> freeboard(int cate) {
		return indexDAO.freeboard(cate);
	}

	public int write(Map<String, Object> map) {
		// 우리 db에 있는 mid와 ip를 세팅
		map.put("mid", util.getSession().getAttribute("mid"));
		map.put("ip", util.getIP());
		return indexDAO.write(map);
	}

	public int postDel(int no) {
		return indexDAO.postDel(no);
	}

	public List<Map<String, Object>> menu() {
		return indexDAO.menu();
	}

	public int postUpdate(Map<String, Object> map) {
		map.put("mid", util.getSession().getAttribute("mid"));
		return indexDAO.postUpdate(map);
	}

	public String getCateName(int cate) {
		return indexDAO.getCateName(cate);
	}
}
