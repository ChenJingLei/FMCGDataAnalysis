package app.model;

import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cjl20 on 2016/7/4.
 */
public class Query {

    private String Appkey;
    private String Sign;
    private String Keyword;
    private long TimeStamp;
    private int SiteId;
    private int PageIndex;
    private int PageSize;

    public Query() {
    }

    public String getAppkey() {
        return Appkey;
    }

    public void setAppkey(String appkey) {
        Appkey = appkey;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }

    public long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        TimeStamp = timeStamp;
    }

    public int getSiteId() {
        return SiteId;
    }

    public void setSiteId(int siteId) {
        SiteId = siteId;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public String generateSign(String appSecret) throws Exception
    {
        //按key(参数名称)进行排序
        TreeMap<String,Object> paramTreeMap = new TreeMap<>();
        paramTreeMap.put("Keyword", Keyword);
        paramTreeMap.put("TimeStamp", TimeStamp);
        paramTreeMap.put("SiteId", SiteId);
        paramTreeMap.put("PageIndex", PageIndex);
        paramTreeMap.put("PageSize", PageSize);

        StringBuilder builder = new StringBuilder();
        builder.append(Appkey);
        for (Object o : paramTreeMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            builder.append(entry.getValue());
        }
        builder.append(appSecret);
        return DigestUtils.md5Hex(builder.toString());
    }

    @Override
    public String toString() {
        return "Query{" +
                "Appkey='" + Appkey + '\'' +
                ", Sign='" + Sign + '\'' +
                ", Keyword='" + Keyword + '\'' +
                ", TimeStamp=" + TimeStamp +
                ", SiteId=" + SiteId +
                ", PageIndex=" + PageIndex +
                ", PageSize=" + PageSize +
                '}';
    }
}
