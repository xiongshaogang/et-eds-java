package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.ClienterBindOptionLog;

public interface IClienterBindOptionLogDao {
    int insert(ClienterBindOptionLog record);
    List<ClienterBindOptionLog> selectList(Long businessId,long clienterId);
}