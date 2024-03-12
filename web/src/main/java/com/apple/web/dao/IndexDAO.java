package com.apple.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IndexDAO {

	List<Map<String, Object>> boardList();

	Map<String, Object> boardDetail(int no);

	Map<String, Object> detail(int no);

	List<Map<String, Object>> freeboard(int cate);

	int write(Map<String, Object> map);

	int postDel(int no);

	List<Map<String, Object>> menu();

	int postUpdate(Map<String, Object> map);

	String getCateName(int cate);

}
