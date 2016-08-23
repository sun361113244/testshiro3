import com.alibaba.fastjson.JSON;
import hdfs.service.HDFSService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sys.entity.ETreeNode;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" , "classpath:spring-mybatis.xml"})
public class testHDFSService
{
    @Resource
    private HDFSService hdfsService;

    @Test
    public void testlistDirectoryStructs() throws IOException
    {
        ETreeNode node = hdfsService.listDirectoryStructs();
        System.out.println(JSON.toJSONString(node));
    }
}
