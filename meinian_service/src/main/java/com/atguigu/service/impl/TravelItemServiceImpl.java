package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass =TravelItemService.class )
@Transactional
public class TravelItemServiceImpl implements TravelItemService {


    @Autowired
    TravelItemDao travelItemDao;

    @Override
    public List<TravelItem> findAll() {
        return travelItemDao.findAll();
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    @Override
    public TravelItem getById(Integer id) {
        return travelItemDao.getById(id);
    }

    @Override
    public void delete(Integer id) {
        //查自由行关联表中是否存在关联数据，如果存在，就抛出异常，不进行删除
        long count= travelItemDao.findCountByTravelItemId(id);
        if (count>0){
            throw new RuntimeException("删除自由行失败，因为存在关联数据，先解除关系，再删除");
        }
        travelItemDao.delete(id);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //分页插件
        //开启分页功能
        //limit ?,? 第一个问号表示开始索引，第二个问号表示查询的条数
        //limit(currentPage-1)*pageSize,pageSize
        PageHelper.startPage(currentPage,pageSize);
        Page page= travelItemDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }



    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }
}




















