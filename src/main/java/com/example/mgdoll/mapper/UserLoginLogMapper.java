package com.example.mgdoll.mapper;

import com.example.mgdoll.model.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLoginLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(UserLoginLog record);

    UserLoginLog selectByPrimaryKey(Long logId);

    List<UserLoginLog> selectAll();

    int updateByPrimaryKey(UserLoginLog record);
}