package app.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cjl20 on 2016/10/29.
 */

@Entity
@Table(name = "JDProduct")
public class Good {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String wareId;
    private String wname;
    private String totalCount;
    private String good;
    private String catid;
    private String cid1;
    private String cid2;
    private String imageurl;
    private String jdPrice;
    private String allCnt;
    private String badCnt;
    private String goodCnt;
    private String normalCnt;
    private String pictureCnt;
    private String showPicCnt;
    private Date date = new Date();

    public Good() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCid1() {
        return cid1;
    }

    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    public String getCid2() {
        return cid2;
    }

    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getJdPrice() {
        return jdPrice;
    }

    public void setJdPrice(String jdPrice) {
        this.jdPrice = jdPrice;
    }

    public String getAllCnt() {
        return allCnt;
    }

    public void setAllCnt(String allCnt) {
        this.allCnt = allCnt;
    }

    public String getBadCnt() {
        return badCnt;
    }

    public void setBadCnt(String badCnt) {
        this.badCnt = badCnt;
    }

    public String getGoodCnt() {
        return goodCnt;
    }

    public void setGoodCnt(String goodCnt) {
        this.goodCnt = goodCnt;
    }

    public String getNormalCnt() {
        return normalCnt;
    }

    public void setNormalCnt(String normalCnt) {
        this.normalCnt = normalCnt;
    }

    public String getPictureCnt() {
        return pictureCnt;
    }

    public void setPictureCnt(String pictureCnt) {
        this.pictureCnt = pictureCnt;
    }

    public String getShowPicCnt() {
        return showPicCnt;
    }

    public void setShowPicCnt(String showPicCnt) {
        this.showPicCnt = showPicCnt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Good{" +
                "wareId='" + wareId + '\'' +
                ", wname='" + wname + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", good='" + good + '\'' +
                ", catid='" + catid + '\'' +
                ", cid1='" + cid1 + '\'' +
                ", cid2='" + cid2 + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", jdPrice='" + jdPrice + '\'' +
                ", allCnt='" + allCnt + '\'' +
                ", badCnt='" + badCnt + '\'' +
                ", goodCnt='" + goodCnt + '\'' +
                ", normalCnt='" + normalCnt + '\'' +
                ", pictureCnt='" + pictureCnt + '\'' +
                ", showPicCnt='" + showPicCnt + '\'' +
                ", date=" + date +
                '}';
    }
}
