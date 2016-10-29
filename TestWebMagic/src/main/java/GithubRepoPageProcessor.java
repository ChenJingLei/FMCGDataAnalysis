import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;
import java.util.List;

/**
 * Created by cjl20 on 2016/7/11.
 */
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetrySleepTime(3).setSleepTime(100);

    @Override
    public void process(Page page) {

        //https://www.taobao.com/market/nvzhuang/yurong.php?spm=a21bo.7723600.8224.2.GoMv1D
//        System.out.println(page.getHtml().toString());
        page.putField("title(标题)",page.getHtml().xpath("//div[@id='J_Title']/h3/text()").toString());
        page.putField("price（价格）",page.getHtml().xpath("//em[@id='J_PromoPriceNum']/text()").toString());
        page.putField("saleNum（销量）",page.getHtml().xpath("//a[@id='J_ReviewTabTrigger']/strong/text()").toString());
        page.putField("evaluateNum（评价数）",page.getHtml().xpath("//strong[@id='J_SellCounter']/text()").toString());
        page.putField("specification（详情）",page.getHtml().xpath("//div[@id='J_Title']/p/text()").toString());
        page.addTargetRequests(page.getHtml().links().all());



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
//        Spider.create(new GithubRepoPageProcessor()).addPipeline(new JsonFilePipeline("D:\\sd\\")).addUrl("https://github.com/code4craft").thread(5).run();
        Date date = new Date();
        System.out.println("time------>" + date.getTime());
        Spider s = Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://item.taobao.com/item.htm?spm=a21bn.7911784.1998931287.3.XOPRU1&id=526158113140")
                .addPipeline(new FilePipeline("D:\\SpringProject\\data\\webmagic\\test\\"))
                .setDownloader(new SeleniumDownloader("D:\\SpringProject\\chromedriver_win32\\chromedriver.exe"))
                .thread(5);
        SpiderMonitor.instance().register(s);
        s.start();

        System.out.println("time------>" + new Date().getTime());
        System.out.println("time------>" + (new Date().getTime() - date.getTime()));
    }
}
