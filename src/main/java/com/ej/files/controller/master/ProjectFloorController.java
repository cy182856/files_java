package com.ej.files.controller.master;

import com.alibaba.fastjson.JSON;
import com.ej.files.constant.AjaxResult;
import com.ej.files.entity.master.ProjectFloor;
import com.ej.files.entity.user.User;
import com.ej.files.service.master.ProjectFloorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/project/floor")
public class ProjectFloorController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectFloorService projectFloorService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list(@RequestParam(value = "page",defaultValue = "1") int page,
                           @RequestParam(value = "limit",defaultValue = "10") int limit,
                           ProjectFloor projectFloor){

        AjaxResult ajaxResult = new AjaxResult();
        HashMap map = new HashMap();
        PageHelper.offsetPage((page-1) * limit,limit);
        List<ProjectFloor> list = projectFloorService.getList(projectFloor);
        logger.info("list:"+ JSON.toJSONString(list));
        PageInfo<ProjectFloor> pageInfo = new PageInfo<>(list);
        //计算总页数
        int pageNumTotal = (int) Math.ceil((double)pageInfo.getTotal()/(double)limit);
        if(page > pageNumTotal){
            PageInfo<User> entityPageInfo = new PageInfo<>();
            entityPageInfo.setList(new ArrayList<>());
            entityPageInfo.setTotal(pageInfo.getTotal());
            entityPageInfo.setPageNum(page);
            entityPageInfo.setPageSize(limit);
            map.put("pageInfo",entityPageInfo);
        }else {
            map.put("pageInfo",pageInfo);
        }
        ajaxResult.setCode(20000);
        ajaxResult.setMessage("成功");
        ajaxResult.setData(map);
        logger.info("responseMsg:"+ JSON.toJSONString(ajaxResult));
        return ajaxResult;
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public AjaxResult select(ProjectFloor projectFloor){
        AjaxResult ajaxResult = new AjaxResult();
        HashMap map = new HashMap();
        List<ProjectFloor> list = projectFloorService.getList(projectFloor);
        logger.info("list:"+ JSON.toJSONString(list));
        map.put("list",list);
        ajaxResult.setCode(20000);
        ajaxResult.setMessage("成功");
        ajaxResult.setData(map);
        logger.info("responseMsg:"+ JSON.toJSONString(ajaxResult));
        return ajaxResult;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ProjectFloor projectFloor){
        AjaxResult ajaxResult = new AjaxResult();
        if(projectFloor.getId() != null){
            projectFloorService.update(projectFloor);
        }else{
            projectFloor.setId(System.currentTimeMillis()+"");
            projectFloor.setUpdateTime(new Date());
            projectFloor.setCreateTime(new Date());
            projectFloor.setDeleteFlag(0);
            projectFloorService.save(projectFloor);
        }
        ajaxResult.setCode(20000);
        ajaxResult.setMessage("成功");
        return ajaxResult;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public AjaxResult delete(@RequestParam(required=false, value = "id") String id){
        AjaxResult ajaxResult = new AjaxResult();
        projectFloorService.delete(id);
        ajaxResult.setCode(20000);
        ajaxResult.setMessage("成功");
        return ajaxResult;
    }
}
