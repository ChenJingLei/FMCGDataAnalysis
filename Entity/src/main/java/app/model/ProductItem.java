package app.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cjl20 on 2016/7/4.
 */
@Entity
@Table(name = "ProductItem")
public class ProductItem {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "productId", columnDefinition = "VARCHAR(32)", length = 32)
    private String ProductId; //商品标识Id

    @Column(name = "productSiteId", columnDefinition = "VARCHAR(50)", length = 50)
    private String ProductSiteId;//商品在电商平台中的ID

    @Column(name = "title", columnDefinition = "VARCHAR(50)", length = 50)
    private String Title;//商品标题

    @Column(name = "brand", columnDefinition = "VARCHAR(50)", length = 50)
    private String Brand;//商品品牌

    @Column(name = "price", columnDefinition = "double")
    private double Price;// 商品价格

    @Column(name = "detailUrl", columnDefinition = "TEXT")
    private String DetailUrl;//商品详情链接地址

    @Column(name = "imgUrl", columnDefinition = "TEXT")
    private String ImgUrl;//商品图片url地址

    @Column(name = "saleNum", columnDefinition = "int")
    private int SaleNum;//商品销量

    @Column(name = "evaluateNum", columnDefinition = "int")
    private int EvaluateNum;//商品评价数

    @Column(name = "specification", columnDefinition = "VARCHAR(50)", length = 50)
    private String Specification;//规格

    @Column(name = "platform", columnDefinition = "VARCHAR(50)", length = 50)
    private String Platform;//商品所属电商平台

    @Column(name = "time", columnDefinition = "BIGINT")
    private long Time = new Date().getTime();//抓取时间

    @Column(name = "productTypeId", columnDefinition = "int")
    private Integer ProductTypeId;//主键
//    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
//    @JoinColumn(referencedColumnName = "ProductTypeId")
//    private ProductType ProductType;//主键

    public ProductItem() {
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductSiteId() {
        return ProductSiteId;
    }

    public void setProductSiteId(String productSiteId) {
        ProductSiteId = productSiteId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getDetailUrl() {
        return DetailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        DetailUrl = detailUrl;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public int getSaleNum() {
        return SaleNum;
    }

    public void setSaleNum(int saleNum) {
        SaleNum = saleNum;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    public Integer getProductTypeId() {
        return ProductTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        ProductTypeId = productTypeId;
    }

    public int getEvaluateNum() {
        return EvaluateNum;
    }

    public void setEvaluateNum(int evaluateNum) {
        EvaluateNum = evaluateNum;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }
//    public app.model.ProductType getProductType() {
//        return ProductType;
//    }
//
//    public void setProductType(app.model.ProductType productType) {
//        ProductType = productType;
//    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "ProductId='" + ProductId + '\'' +
                ", ProductSiteId='" + ProductSiteId + '\'' +
                ", Title='" + Title + '\'' +
                ", Brand='" + Brand + '\'' +
                ", Price=" + Price +
                ", DetailUrl='" + DetailUrl + '\'' +
                ", ImgUrl='" + ImgUrl + '\'' +
                ", SaleNum=" + SaleNum +
                ", EvaluateNum=" + EvaluateNum +
                ", Specification=" + Specification +
                ", Platform='" + Platform + '\'' +
                ", Time=" + Time +
                ", ProductTypeId=" + ProductTypeId +
                '}';
    }
}
