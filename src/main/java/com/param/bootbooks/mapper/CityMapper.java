package com.param.bootbooks.mapper;

import com.param.bootbooks.pojo.Book;
import com.param.bootbooks.pojo.City;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhoujingyu（976944083@qq.com）
 */
@Mapper
public interface CityMapper {

    @Select("select * from city where id=#{id}")
    City queryCityById(@Param("cityId") Long id);

    int addCity(City city);

    @Delete("delete * from city where id=#{id}")
    int deleteCityById(@Param("cityId") Long id);

    int updateCity(City book);

    List<City> queryCityByName(@Param("cityName") String name);

    List<City> queryAllCity();
}
