package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
    对爬取的页面进行提取和渲染
 */
public class JsoupUtil {
    //添加的信息头
    private static String front = "<!doctype html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<meta charset=\"utf-8\">\n" +
            "\t<title>课表系统</title>\n" +
            "</head>\n" +
            "<body>";

    //添加的信息尾
    private static String tail = "<h1><a href=\"login.html\">点击此处</a>退出登录</h1>" +
            "</body>" +
            "</html>";

    /**
     * 在爬取的页面中提取课表信息
     * @param srcHTML 需要解析的页面
     * @return 返回解析后的页面
     */
    public static String tableExtract(String srcHTML){
        String tableHTML = null;
        //解析网页代码，获取DOM对象
        Document doc = Jsoup.parse(srcHTML);
        //获取用户名字
        String name = doc.getElementsByClass("main-per-name").first()
                .getElementsByTag("b").first().text();
        //设置欢迎语
        String helloStr = "<h1>欢迎<span style=\"color:blue;\">" + name + "</span>访问，您的课表信息如下</h1>";
        //通过table标签提取出课表
        tableHTML = doc.getElementsByTag("table").first().toString();
        return front + helloStr + tableHTML + tail;
    }
}
