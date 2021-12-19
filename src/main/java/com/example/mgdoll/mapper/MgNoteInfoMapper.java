package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgNoteInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgNoteInfoMapper {
    int deleteByPrimaryKey(Integer noteId);

    int insert(MgNoteInfo record);

    MgNoteInfo selectByPrimaryKey(Integer noteId);

    List<MgNoteInfo> selectAll();

    int updateByPrimaryKey(MgNoteInfo record);
}