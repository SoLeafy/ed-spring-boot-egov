package com.apple.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminDAO {

	public List<Map<String, Object>> menu();

	public int addMenu(Map<String, Object> map);
}
