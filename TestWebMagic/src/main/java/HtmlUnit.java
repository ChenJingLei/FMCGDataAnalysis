
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.List;

/**
 * Created by cjl20 on 2016/7/12.
 */
public class HtmlUnit {

    public static void main(String[] args) throws Exception {
//        //创建一个WebDriver实例
//        WebDriver driver = new HtmlUnitDriver(true);
//        // 访问google
//
//        driver.get("https://item.taobao.com/item.htm?spm=a21bn.7911784.1998931287.3.XOPRU1&id=526158113140");
////        driver.get("https://www.baidu.com");
//        // 找到文本框
////        WebElement element = driver.findElement(By.id("kw"));
//        //输入搜索关键字
////        element.sendKeys("Selenium");
//        //提交表单 WebDriver会自动从表单中查找提交按钮并提交
////        element.submit();
//        // 检查页面title
//        System.out.println("Page title is: " + driver.getTitle());
//        // google查询结果是通过javascript动态呈现的.
//        // 设置页面等待10秒超时
//        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
////                return d.getTitle().toLowerCase().startsWith("selenium");
//                return true;
//            }
//        });
//        //显示查询结果title
//        System.out.println("Page title is: " + driver.getTitle());
//        System.out.println("12344444-->" + driver.findElement(By.id("J_RateCounter")).getText());
//
//        //关闭浏览器
//        driver.quit();

        Date date = new Date();
        System.out.println("time------>" + date.getTime());
        // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
        WebClient webclient = new WebClient();

//        ProxyConfig proxyConfig = new ProxyConfig();
//        proxyConfig.setProxyHost("58.222.254.11");
//        proxyConfig.setProxyPort(8080);
//        webclient.getOptions().setProxyConfig(proxyConfig);
        // 这里是配置一下不加载css和javaScript,配置起来很简单，是不是
        webclient.getOptions().setCssEnabled(false);
        webclient.getOptions().setActiveXNative(false);
        webclient.setAjaxController(new NicelyResynchronizingAjaxController());
        webclient.getOptions().setJavaScriptEnabled(true);

        // 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可
        HtmlPage htmlpage = webclient.getPage("https://item.taobao.com/item.htm?spm=a21bn.7911784.1998931287.3.XOPRU1&id=526158113140");
        webclient.waitForBackgroundJavaScript(1000 * 1);
        webclient.setJavaScriptTimeout(0);

//        // 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”
//        final HtmlForm form = htmlpage.getFormByName("f");
//        // 同样道理，获取”百度一下“这个按钮
//        final HtmlSubmitInput button = form.getInputByValue("百度一下");
//        // 得到搜索框
//        final HtmlTextInput textField = form.getInputByName("q1");
//        // 最近周星驰比较火呀，我这里设置一下在搜索框内填入”周星驰“
//        textField.setValueAttribute("周星驰");
//        // 输入好了，我们点一下这个按钮
//        final HtmlPage nextPage = button.click();
        // 我把结果转成String
        String result = htmlpage.asXml();
        System.out.println("===================================================================================");
        System.out.println(result);
        System.out.println("===================================================================================");
        System.out.println("time------>" + new Date().getTime());
        System.out.println("time------>" + (new Date().getTime() - date.getTime()));
    }
}
