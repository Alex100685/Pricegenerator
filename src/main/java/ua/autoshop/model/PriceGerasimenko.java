package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 08.10.2015.
 */

@Entity
@Table(name="price_gerasimenko")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PriceGerasimenko extends BaseModel {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="active_code")
    private String activeCode;

    @Column(name="catalog_number")
    private String catalogNumber;

    @Column(name="brand")
    private String brand;

    @Column(name="product_description")
    private String productDescription;

    @Column(name="eat_description")
    private String eatDescription;

    @Column(name="client_price")
    private String clientPrice;

    @Column(name="available_on_central_your_branch")
    private String availableOnCentralYourBranch;

    @Column(name="available_on_central")
    private String availableOnCentral;

    @Column(name="available_on_another")
    private String availableOnAnother;

    public String getAvailableOnAnother() {
        return availableOnAnother;
    }

    public void setAvailableOnAnother(String availableOnAnother) {
        this.availableOnAnother = availableOnAnother;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getEatDescription() {
        return eatDescription;
    }

    public void setEatDescription(String eatDescription) {
        this.eatDescription = eatDescription;
    }

    public String getClientPrice() {
        return clientPrice;
    }

    public void setClientPrice(String clientPrice) {
        this.clientPrice = clientPrice;
    }

    public String getAvailableOnCentralYourBranch() {
        return availableOnCentralYourBranch;
    }

    public void setAvailableOnCentralYourBranch(String availableOnCentralYourBranch) {
        this.availableOnCentralYourBranch = availableOnCentralYourBranch;
    }

    public String getAvailableOnCentral() {
        return availableOnCentral;
    }

    public void setAvailableOnCentral(String availableOnCentral) {
        this.availableOnCentral = availableOnCentral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
