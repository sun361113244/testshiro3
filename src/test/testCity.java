import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sys.entity.Area;
import sys.entity.City;
import sys.entity.Province;
import sys.service.CityService;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" , "classpath:spring-mybatis.xml"})
public class testCity
{
    @Resource
    private CityService cityService;


    public List<String> loadTempCities() throws IOException
    {
        List<String> tempCityList = new ArrayList<String>();

        File clusterResultFile = new File("/Users/charles/Desktop/tmp_city.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(clusterResultFile));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String lineTxt = null;
        while((lineTxt = bufferedReader.readLine()) != null)
        {
            String[] tmp = lineTxt.split(",");

            tempCityList.add(tmp[0]);
        }
        inputStreamReader.close();
        return tempCityList;
    }
    @Test
    public void test2() throws IOException
    {
        List<String> tempList = loadTempCities();
        for(String elem : tempList)
        {
            System.out.println(elem);
        }
    }

    @Test
    public void test1() throws IOException
    {
        List<String> cityString = new ArrayList<String>();
        List<String> areaString = new ArrayList<String>();


        List<Province> provinces = cityService.getAllProvince();

        for(Province province : provinces)
        {
            List<City> cities = cityService.getCityByProvinceId(province.getProvinceid());
            for(City city : cities)
            {
                cityString.add(province.getProvince() + "-" + city.getCity());

                List<Area> areas = cityService.getAreaByCityId(city.getCityid());

                for(Area area : areas)
                {
                    areaString.add(province.getProvince() + "-" + city.getCity() + "-" + area.getArea());
                }
            }
        }

        List<String> tempList = loadTempCities();
        List<String> resultList = new ArrayList<String>();
        for(String elem : tempList)
        {
            boolean cityContains = false;
            for(String city1 : cityString)
            {
                if(city1.contains(elem))
                {
                    resultList.add(elem + "," + city1);
                    cityContains = true;
                    break;
                }
            }
            if(!cityContains)
            {
                for(String area1 : areaString)
                {
                    if(area1.contains(elem))
                    {
                        resultList.add(elem + "," + area1);
                        cityContains = true;
                        break;
                    }
                }
            }
        }


        for(String elem : resultList)
        {
            String[] tmp = elem.split(",");
            System.out.println(String.format("update zhaopin set city = '%s' where city = '%s';" ,tmp[1] , tmp[0]));
        }
        System.out.println(resultList.size());
//        System.out.println(cityString.size());
//        for(String str : cityString)
//        {
//            System.out.println(str);
//        }
//        System.out.println(areaString.size());
//        for(String str : areaString)
//        {
//            System.out.println(str);
//        }
    }
}
