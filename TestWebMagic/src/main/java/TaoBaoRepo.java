import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by cjl20 on 2016/7/11.
 */
public class TaoBaoRepo implements PageProcessor {
    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("https://www.taobao.com/market/(\\w+)/.*").all());
//        List<String> list = page.getHtml().links().regex("https://www.taobao.com/market/(\\w+)/.*").all();
//        for (String str : list) {
//            System.out.println(str);
//        }
        //System.out.println(page.getHtml().get());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Override
    public Site getSite() {
        return null;
    }

    public static void main(String[] args) {
        Spider.create(new TaoBaoRepo()).addUrl("http://item.m.jd.com/ware/getDetailCommentList.json?wareId=1156550").thread(5).run();
    }

}
