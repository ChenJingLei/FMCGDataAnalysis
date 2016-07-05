package app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cjl20 on 2016/7/5.
 */
@Entity
@Table(name = "ProductType")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProductTypeId;//主键

    @Column(name = "ptfathertype", columnDefinition = "VARCHAR(50)", length = 50)
    private String fathertype;//父类别

    @Column(name = "pttype", columnDefinition = "VARCHAR(50)", length = 50)
    private String type;//子类别

//    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE },
//            fetch = FetchType.EAGER,mappedBy = "ProductType")
//    private Set<ProductItem> productItems = new HashSet<>();

    public ProductType() {
    }

    public Integer getProductTypeId() {
        return ProductTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        ProductTypeId = productTypeId;
    }

    public String getFathertype() {
        return fathertype;
    }

    public void setFathertype(String fathertype) {
        this.fathertype = fathertype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Set<ProductItem> getProductItems() {
//        return productItems;
//    }
//
//    public void setProductItems(Set<ProductItem> productItems) {
//        this.productItems = productItems;
//    }

    @Override
    public String toString() {
        return "ProductType{" +
                "ProductTypeId=" + ProductTypeId +
                ", fathertype='" + fathertype + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
