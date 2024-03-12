package com.apple.web.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberDAO {

	Map<String, Object> login(Map<String, Object> map);

	Map<String, Object> myInfo(String id);

}
