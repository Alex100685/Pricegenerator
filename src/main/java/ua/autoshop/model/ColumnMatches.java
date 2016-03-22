package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 31.01.2016.
 */

@Entity
@Table(name="column_matches")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ColumnMatches extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="brand_match")
    private Integer brandMatch;

    @Column(name="income_price_match")
    private Integer incomePriceMatch;

    @Column(name="wholesale_price_match")
    private Integer wholesalePriceMatch;

    @Column(name="retail_price_match")
    private Integer retailPriceMatch;

    @Column(name="available_match")
    private Integer availableMatch;

    @Column(name="code_match")
    private Integer codeMatch;

    @Column(name="tomarket_wholesale_price_match")
    private Integer tomarketWholesaleMatchMatch;

    @Column(name="tomarket_retail_price_match")
    private Integer tomarketRetailMatch;

    @Column(name="name_match")
    private Integer nameMatch;

    @Column(name="supplier_match")
    private Integer supplierMatch;

    @Column(name="shelf_match")
    private Integer shelfMatch;

    @Column(name="category_match")
    private Integer categoryMatch;

    @Column(name="additional_information_match")
    private Integer additionalInformationMatch;

    @Column(name="supply_condition")
    private Integer supplyCondition;

    @Column(name="picture")
    private Integer pictureMatch;

    @Column(name="currency")
    private Integer currencyMatch;

    public Integer getCurrencyMatch() {
        return currencyMatch;
    }

    public void setCurrencyMatch(Integer currencyMatch) {
        this.currencyMatch = currencyMatch;
    }

    public Integer getPictureMatch() {
        return pictureMatch;
    }

    public void setPictureMatch(Integer pictureMatch) {
        this.pictureMatch = pictureMatch;
    }

    public Integer getSupplyCondition() {
        return supplyCondition;
    }

    public void setSupplyCondition(Integer supplyCondition) {
        this.supplyCondition = supplyCondition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrandMatch() {
        return brandMatch;
    }

    public void setBrandMatch(Integer brandMatch) {
        this.brandMatch = brandMatch;
    }

    public Integer getIncomePriceMatch() {
        return incomePriceMatch;
    }

    public void setIncomePriceMatch(Integer incomePriceMatch) {
        this.incomePriceMatch = incomePriceMatch;
    }

    public Integer getWholesalePriceMatch() {
        return wholesalePriceMatch;
    }

    public void setWholesalePriceMatch(Integer wholesalePriceMatch) {
        this.wholesalePriceMatch = wholesalePriceMatch;
    }

    public Integer getRetailPriceMatch() {
        return retailPriceMatch;
    }

    public void setRetailPriceMatch(Integer retailPriceMatch) {
        this.retailPriceMatch = retailPriceMatch;
    }

    public Integer getAvailableMatch() {
        return availableMatch;
    }

    public void setAvailableMatch(Integer availableMatch) {
        this.availableMatch = availableMatch;
    }

    public Integer getCodeMatch() {
        return codeMatch;
    }

    public void setCodeMatch(Integer codeMatch) {
        this.codeMatch = codeMatch;
    }

    public Integer getTomarketRetailMatch() {
        return tomarketRetailMatch;
    }

    public void setTomarketRetailMatch(Integer tomarketRetailMatch) {
        this.tomarketRetailMatch = tomarketRetailMatch;
    }

    public Integer getNameMatch() {
        return nameMatch;
    }

    public void setNameMatch(Integer nameMatch) {
        this.nameMatch = nameMatch;
    }

    public Integer getShelfMatch() {
        return shelfMatch;
    }

    public void setShelfMatch(Integer shelfMatch) {
        this.shelfMatch = shelfMatch;
    }

    public Integer getAdditionalInformationMatch() {
        return additionalInformationMatch;
    }

    public void setAdditionalInformationMatch(Integer additionalInformationMatch) {
        this.additionalInformationMatch = additionalInformationMatch;
    }

    public Integer getCategoryMatch() {
        return categoryMatch;
    }

    public void setCategoryMatch(Integer categoryMatch) {
        this.categoryMatch = categoryMatch;
    }

    public Integer getSupplierMatch() {
        return supplierMatch;
    }

    public void setSupplierMatch(Integer supplierMatch) {
        this.supplierMatch = supplierMatch;
    }

    public Integer getTomarketWholesaleMatchMatch() {
        return tomarketWholesaleMatchMatch;
    }

    public void setTomarketWholesaleMatchMatch(Integer tomarketWholesaleMatchMatch) {
        this.tomarketWholesaleMatchMatch = tomarketWholesaleMatchMatch;
    }
}
