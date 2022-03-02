package com.param.bootbooks.service.Impl;

import com.param.bootbooks.mapper.CityMapper;
import com.param.bootbooks.pojo.City;
import com.param.bootbooks.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoujingyu（976944083@qq.com）
 * @date 2022/2/21 4:50 PM
 */
@Component("CityServiceImpl")
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;

    @Override
    public int addCity(City city) {
        return cityMapper.addCity(city);
    }

    @Override
    public int deleteCityById(Long id) {
        return cityMapper.deleteCityById(id);
    }

    @Override
    public int updateCity(City city) {
        return cityMapper.updateCity(city);
    }

    @Override
    public City queryCityById(Long id) {
        return cityMapper.queryCityById(id);
    }

    @Override
    public List<City> queryCityByName(String name) {
        return cityMapper.queryCityByName(name);
    }

    @Override
    public List<City> queryAllCity() {
        return cityMapper.queryAllCity();
    }
}
