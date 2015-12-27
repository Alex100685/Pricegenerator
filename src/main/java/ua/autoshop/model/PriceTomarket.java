package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 21.11.2015.
 */

@Entity
@Table(name="price_tomarket")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PriceTomarket extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="id_category")
    private String idCategoty;

    @Column(name="trade_position_number")
    private String tradePositionNumber;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="product_name")
    private String productName;

    @Column(name="brand")
    private String brand;

    @Column(name="articule")
    private String articule;

    @Column(name="income_price")
    private String incomePrice;

    @Column(name="wholesale_price")
    private String wholesalePrice;

    @Column(name="retail_price")
    private String retailPrice;

    @Column(name="inner_cross_codes")
    private String innerCrossCodes;

    @Column(name="inner_cross_codes2")
    private String innerCrossCodes2;

    @Column(name="available_on_stock")
    private String availableOnStock;

    @Column(name="shelf_of_product")
    private String shelfOfProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCategoty() {
        return idCategoty;
    }

    public void setIdCategoty(String idCategoty) {
        this.idCategoty = idCategoty;
    }

    public String getTradePositionNumber() {
        return tradePositionNumber;
    }

    public void setTradePositionNumber(String tradePositionNumber) {
        this.tradePositionNumber = tradePositionNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(String incomePrice) {
        this.incomePrice = incomePrice;
    }

    public String getArticule() {
        return articule;
    }

    public void setArticule(String articule) {
        this.articule = articule;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getInnerCrossCodes() {
        return innerCrossCodes;
    }

    public void setInnerCrossCodes(String innerCrossCodes) {
        this.innerCrossCodes = innerCrossCodes;
    }

    public String getInnerCrossCodes2() {
        return innerCrossCodes2;
    }

    public void setInnerCrossCodes2(String innerCrossCodes2) {
        this.innerCrossCodes2 = innerCrossCodes2;
    }

    public String getAvailableOnStock() {
        return availableOnStock;
    }

    public void setAvailableOnStock(String availableOnStock) {
        this.availableOnStock = availableOnStock;
    }

    public String getShelfOfProduct() {
        return shelfOfProduct;
    }

    public void setShelfOfProduct(String shelfOfProduct) {
        this.shelfOfProduct = shelfOfProduct;
    }
}
