package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spider.service.FetchedToolsService;
import util.SpringContextHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" , "classpath:spring-mybatis.xml"})
public class testIOC
{
    @Test
    public void test1()
    {
        FetchedToolsService fetchedToolsService = SpringContextHolder.getBean("FetchedToolsService");
        System.out.println("aaa");
    }
}
