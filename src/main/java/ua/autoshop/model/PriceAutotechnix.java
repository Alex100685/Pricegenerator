package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 08.10.2015.
 */

@Entity
@Table(name="price_autotechnix")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PriceAutotechnix extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="brand")
    private String brand;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private String price;

    @Column(name="available_kiev2")
    private String availableKiev2;

    @Column(name="available_kiev1")
    private String availableKiev1;

    @Column(name="available_rovno")
    private String availableRovno;

    @Column(name="available_khmelnitskiy")
    private String availableKhmelnitskiy;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailableKiev2() {
        return availableKiev2;
    }

    public void setAvailableKiev2(String availableKiev2) {
        this.availableKiev2 = availableKiev2;
    }

    public String getAvailableKiev1() {
        return availableKiev1;
    }

    public void setAvailableKiev1(String availableKiev1) {
        this.availableKiev1 = availableKiev1;
    }

    public String getAvailableRovno() {
        return availableRovno;
    }

    public void setAvailableRovno(String availableRovno) {
        this.availableRovno = availableRovno;
    }

    public String getAvailableKhmelnitskiy() {
        return availableKhmelnitskiy;
    }

    public void setAvailableKhmelnitskiy(String availableKhmelnitskiy) {
        this.availableKhmelnitskiy = availableKhmelnitskiy;
    }
}
