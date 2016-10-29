package app;

import app.model.Good;
import app.pipeline.SQLPipeline;
import app.repository.GoodRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpHost;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.List;

/**
 * Created by cjl20 on 2016/10/29.
 */

@SpringBootApplication
public class TestWebMagicFMCGAlgorithmApplication implements PageProcessor {

    public static void main(String[] args) {
        SpringApplication.run(TestWebMagicFMCGAlgorithmApplication.class);
    }

    private Site site = Site.me().setHttpProxy(new HttpHost("119.28.19.222", 8888)).setRetrySleepTime(3).setSleepTime(60 * 1000);

    private static final String LIST_URL = "http://so\\.m\\.jd\\.com/ware/searchList\\.action\\?_format_=json&c1=12259&c2=12260&categoryId=9439&page=\\d+&sort=1&stock=1";

    @Autowired
    private GoodRepository repository;

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
                    Good good = new Good();
                    String wareId = new JsonPathSelector("$.wareId").select(ware);
                    good.setWareId(wareId);
                    good.setWname(new JsonPathSelector("$.wname").select(ware));
                    good.setTotalCount(new JsonPathSelector("$.totalCount").select(ware));
                    good.setGood(new JsonPathSelector("$.good").select(ware));
                    good.setCatid(new JsonPathSelector("$.catid").select(ware));
                    good.setCid1(new JsonPathSelector("$.cid1").select(ware));
                    good.setCid2(new JsonPathSelector("$.cid2").select(ware));
                    good.setImageurl(new JsonPathSelector("$.imageurl").select(ware));
                    good.setJdPrice(new JsonPathSelector("$.jdPrice").select(ware));
                    repository.save(good);
                    page.putField("Ware", good);

                    page.putField("wname", new JsonPathSelector("$.wname").select(ware));
                    page.putField("totalCount", new JsonPathSelector("$.totalCount").select(ware));
                    page.putField("good", new JsonPathSelector("$.good").select(ware));
                    page.putField("catid", new JsonPathSelector("$.catid").select(ware));
                    page.putField("cid1", new JsonPathSelector("$.cid1").select(ware));
                    page.putField("cid2", new JsonPathSelector("$.cid2").select(ware));
                    page.putField("wareId", wareId);
                    page.putField("imageurl", new JsonPathSelector("$.imageurl").select(ware));
                    page.putField("jdPrice", new JsonPathSelector("$.jdPrice").select(ware));


                    page.addTargetRequest("http://item.m.jd.com/ware/getDetailCommentList.json?wareId=" + wareId);
                }
                UrlEncoded.decodeTo(page.getUrl().get(), values, "UTF-8", 1000);
                int pageIndex = Integer.valueOf(values.getString("page"));
                if (pageIndex <= (Integer.valueOf(new JsonPathSelector("$.wareCount").select(value)) / 10 + 1)) {
                    page.addTargetRequest("http://so.m.jd.com/ware/searchList.action?_format_=json&c1=12259&c2=12260&categoryId=9439&page=" + (pageIndex + 1) + "&sort=1&stock=1");
                }
            }
        } else {
            UrlEncoded.decodeTo(page.getUrl().get(), values, "UTF-8", 1000);
            String wareId = values.getString("http://item.m.jd.com/ware/getDetailCommentList.json?wareId");
            Long id = repository.findOneByWareId(wareId).getId();
            if (wareId != null && id != null) {
                Good good = new Good();
                good.setId(id);
                good.setWareId(wareId);
                good.setAllCnt(new JsonPathSelector("$.wareDetailComment.allCnt").select(page.getRawText()));
                good.setBadCnt(new JsonPathSelector("$.wareDetailComment.badCnt").select(page.getRawText()));
                good.setGoodCnt(new JsonPathSelector("$.wareDetailComment.goodCnt").select(page.getRawText()));
                good.setNormalCnt(new JsonPathSelector("$.wareDetailComment.normalCnt").select(page.getRawText()));
                good.setPictureCnt(new JsonPathSelector("$.wareDetailComment.pictureCnt").select(page.getRawText()));
                good.setShowPicCnt(new JsonPathSelector("$.wareDetailComment.showPicCnt").select(page.getRawText()));
                repository.save(good);
                page.putField("Ware", good);


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
    }

    @Override
    public Site getSite() {
        return site;
    }


    @Bean
    public CommandLineRunner getData() {
        return (args) -> {
            Spider s = Spider.create(this)
                    .addUrl("http://so.m.jd.com/ware/searchList.action?_format_=json&c1=12259&c2=12260&categoryId=9439&page=1&sort=1&stock=1")
                    .addPipeline(new FilePipeline("D:\\SpringProject\\data\\webmagic\\test\\"))
                    //.setDownloader(new SeleniumDownloader("D:\\SpringProject\\chromedriver_win32\\chromedriver.exe"))
                    .thread(5);
            SpiderMonitor.instance().register(s);
            s.start();
        };
    }
}
