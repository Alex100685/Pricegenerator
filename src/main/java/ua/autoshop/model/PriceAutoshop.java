package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 09.10.2015.
 */

@Entity
@Table(name="price_autoshop")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PriceAutoshop extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="brand")
    private String brand;

    @Column(name="retail_price")
    private Double retailPrice;

    @Column(name="available")
    private String available;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
