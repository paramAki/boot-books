package com.param.bootbooks.mapper;

import com.param.bootbooks.pojo.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhoujingyu（976944083@qq.com）
 */
public interface CityMapper {

    /**
     * 通过ID查找城市
     * @param id city id
     * @return city
     */
    @Select("select * from city where CityID=#{id}")
    City queryCityById(@Param("cityId") Long id);

    /**
     * 插入城市记录
     * @param city city
     * @return int值，0为失败，1为成功
     */
    @Insert("insert into ssmbuild.city(CityName, state, country) values (#{name}, #{state}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addCity(City city);

    /**
     * 通过ID删除city记录
     * @param id city id
     * @return int值，0为失败，1为成功
     */
    @Delete("delete from city where CityID=#{id}")
    int deleteCityById(@Param("cityId") Long id);

    /**
     * 修改city记录
     * @param city city
     * @return int值，0为失败，1为成功
     */
    int updateCity(City city);

    /**
     * 通过city name查找city
     * @param name city name
     * @return 模糊查询到的city集合
     */
    @Select("select * from city where CityName like concat('%',#{name},'%')")
    List<City> queryCityByName(@Param("cityName") String name);

    /**
     * 查找所有city记录
     * @return city集合
     */
    List<City> queryAllCity();
}
