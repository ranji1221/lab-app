package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.News;
import com.ranji.lab.entity.Regime;
import com.ranji.lab.mapper.RegimeMapper;
import com.ranji.lab.service.prototype.IRegimeService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegimeServiceImpl implements IRegimeService{
    @Resource
    private RegimeMapper regimeMapper;

    @Override
    public int insertRegime(Regime regime) {
        return regimeMapper.insertRegime(regime);
    }

    @Override
    public int updateRegime(Regime regime) {
        return regimeMapper.updateRegime(regime);
    }

    @Override
    @Transactional
    public Map<Object,Object> findAllRegime() {
        List<Regime> all = regimeMapper.findAll();
        int total = regimeMapper.count();
        Map<Object,Object> allRegime = new HashMap<>();
        allRegime.put("data",all);
        allRegime.put("total",total);
        return allRegime;
    }

    @Override
    public Map<Object,Object> findAllRegime(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<Regime> allRegime = regimeMapper.findAll();

        PageInfo<Regime> allRegimePaging = new PageInfo<>(allRegime);
        long total = allRegimePaging.getTotal();

        Map<Object,Object> allRegimeOnPaging = new HashMap<>();
        allRegimeOnPaging.put("data",allRegime);
        allRegimeOnPaging.put("total",total);
        return allRegimeOnPaging;
    }

    @Override
    public Regime findById(int id) {
        return regimeMapper.findById(id);
    }

    @Override
    public Map<Object, Object> findRegimeNextToNext(int regimeId) {
        List<News> regimeNextToNext = regimeMapper.findRegimeNextToNext(regimeId);
        HashMap<Object, Object> regimeNextToNextMap = new HashMap<>();
        regimeNextToNextMap.put("data",regimeNextToNext);
        return regimeNextToNextMap;
    }

    @Override
    public Map<Object, Object> findLikeRegime(String like, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Regime> all = regimeMapper.findLikeRegime(like);

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        Map<Object,Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",total);

        return allMap;
    }
    @Override
    public Map<Object, Object> findLikeRegime(String like) {
        List<Regime> all = regimeMapper.findLikeRegime(like);

        Map<Object,Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",all.size());

        return allMap;
    }

}
