package com.param.bootbooks.service;

import com.param.bootbooks.pojo.Book;
import com.param.bootbooks.pojo.City;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoujingyu（976944083@qq.com）
 * @date 2022/2/22 9:06 AM
 */
@Service
public interface CityService {

    /**
     * 添加city
     * @param city city
     * @return 添加如果
     */
    int addCity(City city);

    /**
     * 依据id删除city
     * @param id city的id
     * @return 删除结果
     */
    int deleteCityById(Long id);

    /**
     * 修改city
     * @param city city
     * @return 修改结果
     */
    int updateCity(City city);

    /**
     * 根据id查找city
     * @param id city的id
     * @return 查找到的city
     */
    City queryCityById(Long id);

    /**
     * 根据city的名字查找
     * @param name city的名字
     * @return 查找结果的集合
     */
    List<City> queryCityByName(String name);

    /**
     * 查找所有city
     * @return 所有city的集合
     */
    List<City> queryAllCity();
}
