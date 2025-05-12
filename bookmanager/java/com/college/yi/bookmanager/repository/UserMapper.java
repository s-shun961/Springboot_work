package com.college.yi.bookmanager.repository;

import org.apache.ibatis.annotations.Mapper;

import com.college.yi.bookmanager.model.User;

@Mapper
public interface UserMapper {
	
	//ユーザー情報取得
	User selectUserByUsername(String username);
	//登録
	int createUser(User user);
	
	//更新
	int updateUser(User user);
	
	//削除
	int deleteUser(String username);
}
