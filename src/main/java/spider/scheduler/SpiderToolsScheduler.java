package spider.scheduler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import spider.ToolsCrawler.BigDataToolsCrawler;
import util.Config;

public class SpiderToolsScheduler
{
    private String SPIDER_TOOLS_AREA_BIGDATA_URL = Config.TOOLS_AREA_BIGDATA_URL;

    private String CRAWL_STORAGE_FOLDER = "./crawl_storage";

    public void start() throws Exception
    {
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(CRAWL_STORAGE_FOLDER);
        config.setMaxDepthOfCrawling(1);

        PageFetcher pageFetcher = new PageFetcher(config);

        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        controller.addSeed(SPIDER_TOOLS_AREA_BIGDATA_URL);

        controller.start(BigDataToolsCrawler.class, 1);
    }
}
