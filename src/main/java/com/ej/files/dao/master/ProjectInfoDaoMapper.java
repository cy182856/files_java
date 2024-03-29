package com.ej.files.dao.master;

import com.ej.files.entity.master.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface ProjectInfoDaoMapper {

    List<ProjectInfo> getList(ProjectInfo projectInfo);

    void save(ProjectInfo projectInfo);

    void update(ProjectInfo projectInfo);

    void delete(String id);

}
