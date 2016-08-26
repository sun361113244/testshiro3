package spider.service.impl;

import org.springframework.stereotype.Service;
import spider.dao.FetchedToolsDao;
import spider.entity.FetchedTools;
import spider.service.FetchedToolsService;

import javax.annotation.Resource;

@Service("FetchedToolsService")
public class FetchedToolsServiceImpl implements FetchedToolsService
{
    @Resource
    private FetchedToolsDao fetchedToolsDao;

    public int insertFetchedTools(FetchedTools fetchedTools)
    {
        return fetchedToolsDao.insertFetchedTools(fetchedTools);
    }
}
