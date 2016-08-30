package spider.ToolsCrawler;

import com.sun.tools.corba.se.idl.constExpr.And;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Component;
import spider.entity.FetchedTools;
import spider.service.FetchedToolsService;
import spider.service.impl.FetchedToolsServiceImpl;
import spider.util.HTMLParseUtil;
import util.Config;
import util.SpringContextHolder;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class BigDataToolsCrawler extends WebCrawler
{
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url)
    {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith(Config.TOOLS_AREA_BIGDATA_URL);
    }

    @Override
    public void visit(Page page)
    {
        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
        fetchToolsInfo(htmlParseData.getHtml());

    }

    public void fetchToolsInfo(String htmlParseData)
    {
        FetchedToolsService fetchedToolsService = SpringContextHolder.getBean("FetchedToolsService");

        String content = htmlParseData;
        String toolType = StringUtils.substringBetween(content, "<a href=\"javascript:location.reload();\">", "</a>").trim();

        try
        {
            Parser parser = new Parser();
            parser.setInputHTML(content);
            AndFilter freeFilter = new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","content-con content-con-first"));

            TagNameFilter ulfilter = new TagNameFilter("ul");
            parser.setInputHTML(parser.parse(freeFilter).toHtml());
            NodeList nodelist1 = parser.parse(ulfilter);

            TagNameFilter afilter = new TagNameFilter("a");
            parser.setInputHTML(nodelist1.toHtml().replace("<span style=\"color:red;\">新</span>" ,""));
            NodeList freeTools = parser.parse(afilter);

            for (int i = 0; i < freeTools.size(); i++)
            {
                Node elem = freeTools.elementAt(i);

                FetchedTools fetchedTools = new FetchedTools();
                fetchedTools.setToolArea("数据分析/BI");
                fetchedTools.setToolType(toolType);
                fetchedTools.setToolName(elem.toPlainTextString());
                fetchedTools.setIsExist(1);
                fetchedTools.setIsfree(1);
                fetchedTools.setIsNew(2);
                fetchedTools.setCreateTime(new Date());
                fetchedTools.setUpdateTime(new Date());

                System.out.println(fetchedTools);
                fetchedToolsService.insertFetchedTools(fetchedTools);
            }

            parser.setInputHTML(content);
            AndFilter inchargeFilter = new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","content-con "));
            NodeList nodeList3 = parser.parse(inchargeFilter);
            parser.setInputHTML(nodeList3.toHtml());
            NodeList nodeList2 = parser.parse(ulfilter);
            parser.setInputHTML(nodeList2.toHtml().replace("<span style=\"color:red;\">新</span>" , ""));
            NodeList inchargeTools = parser.parse(afilter);
            for (int i = 0; i < inchargeTools.size(); i++)
            {
                Node elem = inchargeTools.elementAt(i);

                FetchedTools fetchedTools = new FetchedTools();
                fetchedTools.setToolArea("数据分析/BI");
                fetchedTools.setToolType(toolType);
                fetchedTools.setToolName(elem.toPlainTextString());
                fetchedTools.setIsExist(1);
                fetchedTools.setIsfree(2);
                fetchedTools.setIsNew(2);
                fetchedTools.setCreateTime(new Date());
                fetchedTools.setUpdateTime(new Date());

                System.out.println(fetchedTools);
                                fetchedToolsService.insertFetchedTools(fetchedTools);
            }
        } catch (ParserException e)
        {
            e.printStackTrace();
        }
    }
}
