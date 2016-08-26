package spider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spider.ToolsCrawler.BigDataToolsCrawler;
import spider.scheduler.SpiderToolsScheduler;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml" , "classpath:spring-mybatis.xml"})
public class testSpiderToolsScheduler
{

    @Test
    public void test1() throws Exception
    {
        SpiderToolsScheduler spiderToolsScheduler = new SpiderToolsScheduler();

        spiderToolsScheduler.start();
    }

    @Test
    public void test2()
    {
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\" /> \n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" /> \n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"chrome=1\" /> \n" +
                "  <title>网络趋势分析-199IT数据导航网站--Hao.199it.com</title> \n" +
                "  <link rel=\"shortcut icon\" href=\"favicon.ico\" /> \n" +
                "  <link href=\"http://hao.199it.com/static/css/reset-common927.css\" rel=\"stylesheet\" /> \n" +
                "  <link href=\"http://hao.199it.com/static/css/common-baidu_search0927.css?v=1109\" rel=\"stylesheet\" /> \n" +
                "  <link href=\"http://hao.199it.com/static/css/sug.css\" rel=\"stylesheet\" /> \n" +
                "  <link href=\"http://hao.199it.com/static/css/text_common-content_mouseoverout0927.css\" rel=\"stylesheet\" />\n" +
                "  <link href=\"http://hao.199it.com/static/css/extra.css\" rel=\"stylesheet\" /> \n" +
                "  <script type=\"text/javascript\">  \n" +
                "if(/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(navigator.userAgent))){ \n" +
                "    if(window.location.href.indexOf(\"?mobile\")<0){ \n" +
                "        try{ \n" +
                "            if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){ \n" +
                "                window.location.href=\"/mobile\"+location.pathname; \n" +
                "            }\n" +
                "        }catch(e){} \n" +
                "    } \n" +
                "} \n" +
                "</script> \n" +
                "  <script src=\"http://hao.199it.com/static/js/jquery.min.js\"></script> \n" +
                "  <style>\n" +
                ".nav_bar_ul>li{line-height: 24px;}\n" +
                ".skiptranslate>.goog-te-gadget-simple{background-color:transparent;border:1px solid #aaa;padding:0 0;margin:0 0;margin-bottom:3px;background-color:#fff;}\n" +
                "</style> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <div id=\"doc\"> \n" +
                "   <div id=\"hd\"> \n" +
                "    <div id=\"topbar\"> \n" +
                "     <div class=\"crumb-con\"> \n" +
                "      <div class=\"web-tool\"> \n" +
                "       <ul class=\"nav_bar_ul\"> \n" +
                "        <li class=\"set-index\"> <a class=\"set_home\" id=\"setHome\" href=\"http://hao.199it.com\" onclick=\"return false\">设为主页</a>&nbsp;| </li> \n" +
                "        <li class=\"feedback\" style=\"padding-left:0px;\"> <a href=\"http://hao.199it.com/admin/index.php?action=index/toolSubmit\" target=\"_blank\">工具提交入口</a>&nbsp; </li> \n" +
                "        <li style=\"padding-right:10px;\"> |&nbsp;<a href=\"http://hao.199it.com\" target=\"_blank\">返回首页</a> </li> \n" +
                "        <li> \n" +
                "         <div id=\"google_translate_element\"></div> </li> \n" +
                "       </ul> \n" +
                "      </div> \n" +
                "     </div> \n" +
                "    </div> \n" +
                "    <div id=\"masthead\"> \n" +
                "     <div class=\"masthead-con\"> \n" +
                "      <div class=\"logo-container\" style=\"width:100%;\"> \n" +
                "       <h1> <a class=\"index-logo\" href=\"/\" title=\"首页\">首页</a> \n" +
                "        <div>\n" +
                "         <img style=\"margin-top:5px;margin-left:10px;\" src=\"/static/imgs/banner/banner_1440706059.gif\" width=\"468\" height=\"60\" alt=\"大数据导航\" />\n" +
                "        </div> </h1> \n" +
                "      </div> \n" +
                "     </div> \n" +
                "    </div> \n" +
                "    <div id=\"nav\"> \n" +
                "    </div> \n" +
                "   </div> \n" +
                "   <div class=\"subnav\" style=\"clear:both;width:960px;margin:0 auto;padding-top:10px;padding-bottom:10px;\"> \n" +
                "    <div class=\"crumb-nav\"> \n" +
                "     <span class=\"cur-pos\"> 当前位置： </span> \n" +
                "     <a href=\"/\">主页</a> &gt; \n" +
                "     <a href=\"javascript:location.reload();\"> 网络趋势分析 </a> \n" +
                "    </div> \n" +
                "   </div> \n" +
                "   <div id=\"bd\"> \n" +
                "    <div class=\"mod-content clearfix\"> \n" +
                "     <div class=\"content-con content-con-first\"> \n" +
                "      <h2 class=\"content-title\">免费工具<span class=\"content-title-des\">(5)</span></h2> \n" +
                "      <ul class=\"content-link\"> \n" +
                "       <li> <h3>\n" +
                "         <div>\n" +
                "          <a href=\"http://index.baidu.com/\" target=\"_blank\" class=\"text-con\" _itemid=\"231\">百度指数</a>\n" +
                "         </div></h3> </li> \n" +
                "       <li> <h3>\n" +
                "         <div>\n" +
                "          <a href=\"http://trends.baidu.com/\" target=\"_blank\" class=\"text-con\" _itemid=\"232\">百度预测</a>\n" +
                "         </div></h3> </li> \n" +
                "       <li> <h3>\n" +
                "         <div>\n" +
                "          <a href=\"http://www.google.com/trends/explore\" target=\"_blank\" class=\"text-con\" _itemid=\"233\">Google趋势</a>\n" +
                "         </div></h3> </li> \n" +
                "       <li> <h3>\n" +
                "         <div>\n" +
                "          <a href=\"http://index.so.com/\" target=\"_blank\" class=\"text-con\" _itemid=\"234\">好搜指数</a>\n" +
                "         </div></h3> </li> \n" +
                "       <li> <h3>\n" +
                "         <div>\n" +
                "          <a href=\"https://research.antgroup.com/research/consumeScale.htm\" target=\"_blank\" class=\"text-con\" _itemid=\"1376\">网络消费指数</a>\n" +
                "         </div></h3> </li> \n" +
                "      </ul> \n" +
                "     </div> \n" +
                "    </div> \n" +
                "    <div class=\"mod-content clearfix\"> \n" +
                "     <div class=\"content-con \"> \n" +
                "      <h2 class=\"content-title\">收费工具<span class=\"content-title-des\">(1)</span></h2> \n" +
                "      <ul class=\"content-link\"> \n" +
                "       <li> <h3>\n" +
                "         <div>\n" +
                "          <a href=\"http://data.weibo.com/index\" target=\"_blank\" class=\"text-con\" _itemid=\"486\">微博指数</a>\n" +
                "         </div></h3> </li> \n" +
                "      </ul> \n" +
                "     </div> \n" +
                "    </div> \n" +
                "    <span class=\"rc-tl round\"></span> \n" +
                "    <span class=\"rc-tr round\"></span> \n" +
                "    <span class=\"rc-bl round\"></span> \n" +
                "    <span class=\"rc-br round\"></span> \n" +
                "   </div> \n" +
                "   <div id=\"ft\"> \n" +
                "    <p class=\"ft_link\">199IT数据工具导航网站2011-2014 199IT.COM 京ICP备14049259号-1</p> \n" +
                "    <p style=\"padding-bottom:40px;padding-top:10px;text-align:center;\">联系我们：cncster@gmail.com</p> \n" +
                "    <div style=\"display:none\"> \n" +
                "     <script type=\"text/javascript\" src=\"js/tj.js\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "</script> \n" +
                "    </div> \n" +
                "   </div> \n" +
                "  </div> \n" +
                "  <div style=\"position:absolute;width:300px;z-index:7777;display:none;\" id=\"item_tooltips\"> \n" +
                "   <p style=\"position:relative;text-align:center;color:#ddd;padding:0 0;margin:0 0;font-size:20px;height:10px;z-index:8888;top:-4px;\">△</p> \n" +
                "   <div id=\"tooltips_content\" style=\"border:1px solid #ddd;border-radius:3px;background-color:#fff;z-index:9999;padding:5px 5px;box-shadow: 3px 3px 5px #ccc;\"></div> \n" +
                "  </div> \n" +
                "  <script>window._bd_share_config={\"common\":{\"bdSnsKey\":{},\"bdText\":\"\",\"bdMini\":\"2\",\"bdMiniList\":false,\"bdPic\":\"\",\"bdStyle\":\"0\",\"bdSize\":\"16\"},\"slide\":{\"type\":\"slide\",\"bdImg\":\"2\",\"bdPos\":\"right\",\"bdTop\":\"98.5\"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script> \n" +
                "  <script>\n" +
                "var jsfile = \"http://hao.199it.com/item_contents.js\";\n" +
                "var global_item_contents = {};\n" +
                "$.get(jsfile, {time:Date.parse(new Date())}, function(rs){\n" +
                "}, 'script');\n" +
                "\n" +
                "/* 绑定事件 */\n" +
                "$('.mod-content').find('a').hover(function(){\n" +
                "    if( !$(this).attr('_itemid') ){\n" +
                "        return false;\n" +
                "    }\n" +
                "\n" +
                "    var item_id = $(this).attr('_itemid');\n" +
                "    if( ! (item_id in global_item_contents) ){\n" +
                "        return false;\n" +
                "    }\n" +
                "    var content = global_item_contents[item_id];\n" +
                "    if( !content ){\n" +
                "        return false;\n" +
                "    }\n" +
                "\n" +
                "    var offset = $(this).offset();\n" +
                "    $('#item_tooltips').show();\n" +
                "    $('#item_tooltips').css('top', offset.top + 30 + 'px');\n" +
                "    $('#item_tooltips').css('left', offset.left + $(this).width() - 160 + 'px');\n" +
                "\n" +
                "    // 获得item内容。\n" +
                "    $('#tooltips_content').html(content);\n" +
                "});\n" +
                "$('.mod-content').find('a').mouseout(function(){\n" +
                "    $('#item_tooltips').hide();\n" +
                "});\n" +
                "</script> \n" +
                "  <script type=\"text/javascript\">\n" +
                "function googleTranslateElementInit() {\n" +
                "  new google.translate.TranslateElement({pageLanguage: 'zh-CN', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');\n" +
                "}\n" +
                "</script>\n" +
                "  <script type=\"text/javascript\" src=\"//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit\"></script>    \n" +
                " </body>\n" +
                "</html>";

        BigDataToolsCrawler bigDataToolsCrawler = new BigDataToolsCrawler();
        bigDataToolsCrawler.fetchToolsInfo(content);
    }
}
