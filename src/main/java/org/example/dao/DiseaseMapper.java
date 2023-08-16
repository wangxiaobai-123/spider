package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.dao.DiseaseDao;
import org.example.dao.entity.Disease;

import java.util.List;

public class DiseaseMapper implements DiseaseDao {
    @Override
    public List<Disease> getDiseaseList() {
        return null;
    }
}
