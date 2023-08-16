package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.dao.entity.Disease;

import java.util.List;

@Mapper
public interface DiseaseDao {
    List<Disease> getDiseaseList();
}
