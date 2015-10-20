package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 19.10.2015.
 */

@Entity
@Table(name="price_genstar")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PriceGenstar extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="brand")
    private String brand;

    @Column(name="product_code")
    private String productCode;

    @Column(name="articule")
    private String articule;

    @Column(name="price")
    private String price;

    @Column(name="available")
    private String available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getArticule() {
        return articule;
    }

    public void setArticule(String articule) {
        this.articule = articule;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
