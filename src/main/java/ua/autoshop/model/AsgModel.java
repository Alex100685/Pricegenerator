package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 26.03.2016.
 */
@Entity
@Table(name="asg")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class AsgModel extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "code")
    String code;

    @Column(name = "brand")
    String brand;

    @Column(name = "name")
    String name;

    @Column(name = "available")
    String available;

    @Column(name = "income_price")
    String incomePrice;

    @Column(name = "articule")
    String articule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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
}
