package org.example;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
@Component
public class KeelungSightsCrawler {


    public Sight[] getItems(String keyword) {
        List<Sight> sightList = new ArrayList<>();
        try {
            // 連接到網頁並抓取 HTML 文件
            Document doc = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").get();
            //Elements links = doc.select(".box");
            Elements h4Elements = doc.select("div.box > h4:containsOwn(" + keyword + "區)");
            //System.out.println("hihih");
            for (Element h4 : h4Elements) {
                // 获取 <h4> 元素后面的第一个 <ul> 元素
                Element nextSibling = h4.nextElementSibling();
                if (nextSibling != null && nextSibling.tagName().equals("ul")) {
                    // 输出 <ul> 元素中的 <li> 项目中的 <a> 标签的内容和 href 属性
                    Elements liElements = nextSibling.select("li a");
                    for (Element a : liElements) {


                        // 如果链接是相对路径，转换为绝对路径
                        String link = a.absUrl("href");

                        //System.out.println("Link text: " + linkText);
                        //System.out.println("Href: " + link);

                        // 连接到提取到的链接并抓取 HTML 文档
                        Document linkedDoc = Jsoup.connect(link).get();
                        Elements zonetag = linkedDoc.select("span[property=rdfs:label]");
                        String zone = zonetag.text();
                        // System.out.println("zone: " + zone);
                        Elements metaTags = linkedDoc.select("meta[itemprop]");
                        /*String nam =   metaTags.attr("content");
                        System.out.println("nam: " + nam);*/
                        String nam = "", img = "", des = "", addr = "";
                        for (Element metaTag : metaTags) {
                            // String content  =  metaTag.select("meta[itemprop=name]").attr("content");
                            String itemprop = metaTag.attr("itemprop");
                            String content = metaTag.attr("content");
                            switch (itemprop) {
                                case "name":
                                    // 处理 name 属性
                                    nam = content;
                                    //System.out.println("Name: " + content);
                                    break;

                                case "image":
                                    // 处理 address 属性
                                    img = content;
                                    //System.out.println("image: " + content);
                                    break;
                                case "description":
                                    // 处理 address 属性
                                    des = content;
                                    //System.out.println("description: " + content);
                                    break;
                                case "address":
                                    // 处理 address 属性
                                    addr = content;
                                    //System.out.println("address: " + content);
                                    break;
                                default:
                                    // 处理其他属性
                                    //System.out.println("Other itemprop: " + itemprop + ", Content: " + content);
                                    break;
                            }
                        }
                        Sight s1 = new Sight(nam, keyword + "區", zone, img, des, addr);

                        sightList.add(s1);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sightList.toArray(new Sight[0]);
    }
}