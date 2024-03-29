package com.ej.files.service.sum;

import com.ej.files.entity.sum.SumFile;

import java.util.List;

public interface SumFileService {

    List<SumFile> getList(SumFile sumFile);

    void save(SumFile sumFile);

    void update(SumFile sumFile);

    void delete(String id);

    SumFile findById(String id);


}
