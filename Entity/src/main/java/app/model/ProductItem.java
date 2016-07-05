package app.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Column(name = "detailUrl", columnDefinition = "VARCHAR(MAX)")
    private String DetailUrl;//商品详情链接地址

    @Column(name = "imgUrl", columnDefinition = "VARCHAR(MAX)")
    private String ImgUrl;//商品图片url地址

    @Column(name = "sellNum", columnDefinition = "int")
    private int SellNum;//商品销量

    @Column(name = "platform", columnDefinition = "VARCHAR(50)", length = 50)
    private String Platform;//商品所属电商平台

    @Column(name = "time", columnDefinition = "int")
    private int Time;

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

    public int getSellNum() {
        return SellNum;
    }

    public void setSellNum(int sellNum) {
        SellNum = sellNum;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
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
                "ProductId=" + ProductId +
                ", ProductSiteId='" + ProductSiteId + '\'' +
                ", Title='" + Title + '\'' +
                ", Brand='" + Brand + '\'' +
                ", Price=" + Price +
                ", DetailUrl='" + DetailUrl + '\'' +
                ", ImgUrl='" + ImgUrl + '\'' +
                ", SellNum=" + SellNum +
                ", Platform='" + Platform + '\'' +
                ", Time=" + Time +
                '}';
    }
}
