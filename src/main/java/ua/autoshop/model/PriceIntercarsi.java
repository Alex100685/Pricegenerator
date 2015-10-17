package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 08.10.2015.
 */

@Entity
@Table(name="price_intercarsi")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PriceIntercarsi extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="articule")
    private String articule;

    @Column(name="brand")
    private String brand;

    @Column(name="name")
    private String name;

    @Column(name="retail_price")
    private String retailPrice;

    @Column(name="wholesale_price")
    private String wholesalePrice;

    @Column(name="available_UR1")
    private String availableUr1;

    @Column(name="avialable_Uj0")
    private String availableUj0;

    @Column(name="avialable_two_days")
    private String availableTwoDays;

    @Column(name="articule_one")
    private String articuleOne;

    @Column(name="avialable_Uj7")
    private String availableUj7;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArticule() {
        return articule;
    }

    public void setArticule(String articule) {
        this.articule = articule;
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

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getAvailableUr1() {
        return availableUr1;
    }

    public void setAvailableUr1(String availableUr1) {
        this.availableUr1 = availableUr1;
    }

    public String getAvailableUj0() {
        return availableUj0;
    }

    public void setAvailableUj0(String availableUj0) {
        this.availableUj0 = availableUj0;
    }

    public String getAvailableTwoDays() {
        return availableTwoDays;
    }

    public void setAvailableTwoDays(String availableTwoDays) {
        this.availableTwoDays = availableTwoDays;
    }

    public String getArticuleOne() {
        return articuleOne;
    }

    public void setArticuleOne(String articuleOne) {
        this.articuleOne = articuleOne;
    }

    public String getAvailableUj7() {
        return availableUj7;
    }

    public void setAvailableUj7(String availableUj7) {
        this.availableUj7 = availableUj7;
    }
}
