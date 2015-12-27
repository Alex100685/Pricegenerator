package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 12.12.2015.
 */
@Entity
@Table(name="price_elit_original")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PriceElitOriginal extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="articule")
    private String articule;

    @Column(name="code_elit")
    private String codeElit;

    @Column(name="brand")
    private String brand;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private String price;

    @Column(name="available")
    private String available;

    @Column(name="supply_condition")
    private String supplyCondition;

    public String getSupplyCondition() {
        return supplyCondition;
    }

    public void setSupplyCondition(String supplyCondition) {
        this.supplyCondition = supplyCondition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticule() {
        return articule;
    }

    public void setArticule(String articule) {
        this.articule = articule;
    }

    public String getCodeElit() {
        return codeElit;
    }

    public void setCodeElit(String codeElit) {
        this.codeElit = codeElit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
