package sys.mapper;

import sys.entity.City;

import java.util.List;

public interface CityMapper extends BaseMapper<City>{
    public List<City> getCityByProvinceId(String id);
}
