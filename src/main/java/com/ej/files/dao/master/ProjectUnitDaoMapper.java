package com.ej.files.dao.master;

import com.ej.files.entity.master.ProjectUnit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProjectUnitDaoMapper {

    List<ProjectUnit> getList(ProjectUnit projectUnit);

    void save(ProjectUnit projectUnit);

    void update(ProjectUnit projectUnit);

    void delete(String id);

}
