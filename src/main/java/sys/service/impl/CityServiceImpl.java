package sys.service.impl;

import org.springframework.stereotype.Service;
import sys.entity.Area;
import sys.entity.City;
import sys.entity.Province;
import sys.mapper.AreaMapper;
import sys.mapper.CityMapper;
import sys.mapper.ProvinceMapper;
import sys.service.CityService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by charles on 16/8/18.
 */
@Service
public class CityServiceImpl implements CityService
{
    @Resource
    private CityMapper cityMapper;
    @Resource
    private ProvinceMapper provinceMapper;
    @Resource
    private AreaMapper areaMapper;

    public List<Province> getAllProvince() {
        return provinceMapper.list();
    }

    public List<City> getCityByProvinceId(String id) {
        return cityMapper.getCityByProvinceId(id);
    }

    public List<Area> getAreaByCityId(String id) {
        return areaMapper.getAreaByCityId(id);
    }
}
