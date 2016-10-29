import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpHost;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import pipeline.SQLPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.net.URL;
import java.util.List;

/**
 * Created by cjl20 on 2016/10/27.
 */
public class JDRepo implements PageProcessor {

    private Site site = Site.me().setHttpProxy(new HttpHost("119.28.19.222", 8888)).setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0").setRetrySleepTime(3).setSleepTime(5000);

    private static final String LIST_URL = "http://so\\.m\\.jd\\.com/ware/searchList\\.action\\?=&_format_=json&c1=12259&c2=12260&categoryId=9439&page=\\d+&sort=1&stock=1";

    @Override
    public void process(Page page) {
        // get the num of pages
        System.out.println("URL" + page.getUrl().toString());
        System.out.println("HTML" + page.getHtml().toString());
        MultiMap<String> values = new MultiMap<String>();
        if (page.getUrl().regex(LIST_URL).match()) {
            String value = new JsonPathSelector("$.value").select(page.getRawText());
            System.out.println((Integer.valueOf(new JsonPathSelector("$.wareCount").select(value)) / 10 + 1));
            List<String> wareList = new JsonPathSelector("$.wareList").selectList(value);
            if (CollectionUtils.isNotEmpty(wareList)) {
                for (String ware : wareList) {
                    page.putField("wname", new JsonPathSelector("$.wname").select(ware));
                    page.putField("totalCount", new JsonPathSelector("$.totalCount").select(ware));
                    page.putField("good", new JsonPathSelector("$.good").select(ware));
                    page.putField("catid", new JsonPathSelector("$.catid").select(ware));
                    page.putField("cid1", new JsonPathSelector("$.cid1").select(ware));
                    page.putField("cid2", new JsonPathSelector("$.cid2").select(ware));
                    String wareId = new JsonPathSelector("$.wareId").select(ware);
                    page.putField("wareId", wareId);
                    page.putField("imageurl", new JsonPathSelector("$.imageurl").select(ware));
                    page.putField("jdPrice", new JsonPathSelector("$.jdPrice").select(ware));
                    page.addTargetRequest("http://item.m.jd.com/ware/getDetailCommentList.json?wareId=" + wareId);
                }
                UrlEncoded.decodeTo(page.getUrl().get(), values, "UTF-8", 1000);
                int pageIndex = Integer.valueOf(values.getString("page"));
                if (pageIndex <= (Integer.valueOf(new JsonPathSelector("$.wareCount").select(value)) / 10 + 1)) {
                    page.addTargetRequest("http://so.m.jd.com/ware/searchList.action?=&_format_=json&c1=12259&c2=12260&categoryId=9439&page=" + (pageIndex + 1) + "&sort=1&stock=1");
                }
            }
        } else {
            UrlEncoded.decodeTo(page.getUrl().get(), values, "UTF-8", 1000);
            String wareId = values.getString("wareId");
            if (wareId != null) {
                page.putField("wareId", wareId);
                page.putField("allCnt", new JsonPathSelector("$.wareDetailComment.allCnt").select(page.getRawText()));
                page.putField("badCnt", new JsonPathSelector("$.wareDetailComment.badCnt").select(page.getRawText()));
                page.putField("goodCnt", new JsonPathSelector("$.wareDetailComment.goodCnt").select(page.getRawText()));
                page.putField("normalCnt", new JsonPathSelector("$.wareDetailComment.normalCnt").select(page.getRawText()));
                page.putField("pictureCnt", new JsonPathSelector("$.wareDetailComment.pictureCnt").select(page.getRawText()));
                page.putField("showPicCnt", new JsonPathSelector("$.wareDetailComment.showPicCnt").select(page.getRawText()));
            } else {
                page.setSkip(true);
            }
        }

//            page.putField("title(标题)", page.getHtml().xpath("//div[@id='J_Title']/h3/text()").toString());
//            page.putField("price（价格）", page.getHtml().xpath("//em[@id='J_PromoPriceNum']/text()").toString());
//            page.putField("saleNum（销量）", page.getHtml().xpath("//a[@id='J_ReviewTabTrigger']/strong/text()").toString());
//            page.putField("evaluateNum（评价数）", page.getHtml().xpath("//strong[@id='J_SellCounter']/text()").toString());
//            page.putField("specification（详情）", page.getHtml().xpath("//div[@id='J_Title']/p/text()").toString());
//            page.addTargetRequests(page.getHtml().links().all());


//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='public ']/strong/a/text()").toString());
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws Exception {

        Spider s = Spider.create(new JDRepo())
                .addUrl("http://so.m.jd.com/ware/searchList.action?=&_format_=json&c1=12259&c2=12260&categoryId=9439&page=1&sort=1&stock=1")
                .addPipeline(new FilePipeline("D:\\SpringProject\\data\\webmagic\\test\\"))
                .addPipeline(new SQLPipeline())
                //.setDownloader(new SeleniumDownloader("D:\\SpringProject\\chromedriver_win32\\chromedriver.exe"))
                .thread(5);
        SpiderMonitor.instance().register(s);
        s.start();

    }
}
