package com.example.mgdoll.mapper;

import com.example.mgdoll.model.AccountToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountToken record);

    AccountToken selectByPrimaryKey(Integer id);

    List<AccountToken> selectAll();

    int updateByPrimaryKey(AccountToken record);

    List<Date> getLastTimeByPhone(@Param("phone") String phone, @Param("token") String token);

    void updateToken(@Param("phone") String phone, @Param("token") String token, @Param("date") Date date);

    void updateTokenTest(@Param("phone") String phone, @Param("date") Date date);
}