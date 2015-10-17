package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 08.10.2015.
 */

@Entity
@Table(name="price_vlad")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class PriceVlad extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="inside_code")
    private String insideCode;

    @Column(name="brand")
    private String brand;

    @Column(name="full_name")
    private String fullName;

    @Column(name="articule")
    private String articule;

    @Column(name="price")
    private String price;

    @Column(name="left_by_default")
    private String leftByDefault;

    @Column(name="left_total")
    private String leftTotal;

    @Column(name="comment")
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInsideCode() {
        return insideCode;
    }

    public void setInsideCode(String insideCode) {
        this.insideCode = insideCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getArticule() {
        return articule;
    }

    public void setArticule(String articule) {
        this.articule = articule;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLeftByDefault() {
        return leftByDefault;
    }

    public void setLeftByDefault(String leftByDefault) {
        this.leftByDefault = leftByDefault;
    }

    public String getLeftTotal() {
        return leftTotal;
    }

    public void setLeftTotal(String leftTotal) {
        this.leftTotal = leftTotal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
