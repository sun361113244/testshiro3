package spider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spider.entity.FetchedTools;
import spider.service.FetchedToolsService;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" , "classpath:spring-mybatis.xml"})
public class testSpiderDao
{
    @Resource
    private FetchedToolsService fetchedToolsService;

    @Test
    public void testInsert()
    {
        FetchedTools fetchedTools = new FetchedTools();
        fetchedTools.setToolArea("aaaa");
        fetchedTools.setIsfree(1);

        fetchedToolsService.insertFetchedTools(fetchedTools);
    }
}
