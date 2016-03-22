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

    @Column(name="income_price")
    private Double incomePrice;

    @Column(name="wholesale_price")
    private Double wholesalePrice;

    @Column(name="tomarket_retail")
    private Double retailTomarket;

    @Column(name="tomarket_wholesale")
    private Double wholesaleToMarket;

    @Column(name="retail_price")
    private Double retailPrice;

    @Column(name="available")
    private String available;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="supplier")
    private String supplier;

    @Column(name="shelf")
    private String shelf;

    @Column(name="category")
    private String category;

    @Column(name="additional_information")
    private String additionalInformation;

    @Column(name="picture")
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(Double incomePrice) {
        this.incomePrice = incomePrice;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Double getRetailTomarket() {
        return retailTomarket;
    }

    public void setRetailTomarket(Double retailTomarket) {
        this.retailTomarket = retailTomarket;
    }

    public Double getWholesaleToMarket() {
        return wholesaleToMarket;
    }

    public void setWholesaleToMarket(Double wholesaleToMarket) {
        this.wholesaleToMarket = wholesaleToMarket;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

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

    public Double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(Double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

}
