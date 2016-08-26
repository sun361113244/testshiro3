package spider.util;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.List;

public class HTMLParseUtil {

    @SuppressWarnings({ "unchecked", "serial" })
    public static <T extends TagNode> List<T> parseTags(String inputHTML, final Class<T> tagType, final String attributeName, final String attributeValue){
        //创建一个HTML解析器
        Parser parser = new Parser();
        NodeList tagList = null;
        try {
            parser.setInputHTML(inputHTML);
            //它会自动检测文件内部<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            //parser.setEncoding("UTF-8");
            tagList = parser.parse(
                    new NodeFilter(){
                        public boolean accept(Node node){
                            //这里不需要if(node instanceof TagNode),因为上面已经定义了TagNode类型的泛型T
                            if(node.getClass()==tagType){
                                //Node类型的实现类中只有TagNode才能getAttribute()获取属性值,所以要将之打回原形
                                T t = (T)node;
                                //若传入的属性名是null,则认为是不需要查找指定属性值的标签,而是单纯的查找某类型T的标签
                                //if(null == attributeName){
                                //  return true;
                                //}
                                if(null!=attributeValue && attributeValue.equals(t.getAttribute(attributeName))){
                                    return true;
                                }
                            }
                            return false;
                        }
                    }
            );
        } catch (ParserException e) {
            System.out.println("解析HTML文本时发生异常:" + e.getMessage());
        }
        List<T> tags = new ArrayList<T>();
        for(int i=0; i<tagList.size(); i++){
            T t = (T)tagList.elementAt(i); //提取真实tag
            tags.add(t);
        }
        return tags;
    }



    public static <T extends TagNode> T parseTag(String inputHTML, final Class<T> tagType, final String attributeName, final String attributeValue){
        List<T> tagList = parseTags(inputHTML, tagType, attributeName, attributeValue);
        if(null!=tagList && tagList.size()>0){
            return tagList.get(0);
        }else{
            return null;
        }
    }
}