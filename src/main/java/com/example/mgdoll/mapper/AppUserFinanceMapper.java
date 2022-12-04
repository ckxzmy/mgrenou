package com.example.mgdoll.mapper;

import com.example.mgdoll.model.AppUserFinance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppUserFinanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppUserFinance record);

    AppUserFinance selectByPrimaryKey(Integer id);

    List<AppUserFinance> selectAll();

    int updateByPrimaryKey(AppUserFinance record);

    AppUserFinance selectByAppUserId(@Param("userId") String userId);

    void recharge(AppUserFinance finance);
}