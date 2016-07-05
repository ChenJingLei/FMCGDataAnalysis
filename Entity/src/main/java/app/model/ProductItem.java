package app.model;

/**
 * Created by cjl20 on 2016/7/4.
 */
public class ProductItem {
    private int ProductId; //商品标识Id
    private String Title;//苹果手机iphone 6 商品标题
    private double Price;// 商品价格
    private String DetailUrl;//商品详情链接地址
    private String ImgUrl;//商品图片url地址
    private int SellNum;//商品销量
    private String Platform;//一号店 商品所属电商平台

    public ProductItem() {
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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

    @Override
    public String toString() {
        return "ResponseResult{" +
                "ProductId=" + ProductId +
                ", Title='" + Title + '\'' +
                ", Price=" + Price +
                ", DetailUrl='" + DetailUrl + '\'' +
                ", ImgUrl='" + ImgUrl + '\'' +
                ", SellNum=" + SellNum +
                ", Platform='" + Platform + '\'' +
                '}';
    }
}
