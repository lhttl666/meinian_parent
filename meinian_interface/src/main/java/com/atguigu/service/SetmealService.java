package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {

     Setmeal findById(Integer id);


    void add(Integer[] travelgroupIds, Setmeal setmeal);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    List getSetmeal();

    Setmeal getSetmealById(Integer id);

    List<Map> getSetmealReport();
}
















