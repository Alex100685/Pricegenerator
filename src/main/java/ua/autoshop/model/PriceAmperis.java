package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 19.10.2015.
 */

@Entity
@Table(name="price_amperis")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PriceAmperis extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="original_code")
    private String originalCode;

    @Column(name="full_code")
    private String fullCode;

    @Column(name="name")
    private String name;

    @Column(name="product_group")
    private String productGroup;

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

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public String getFullCode() {
        return fullCode;
    }

    public void setFullCode(String fullCode) {
        this.fullCode = fullCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
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
