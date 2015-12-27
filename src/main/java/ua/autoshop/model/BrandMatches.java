package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 05.12.2015.
 */

@Entity
@Table(name="brand_matches")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class BrandMatches extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="price_brand")
    private String priceBrand;

    @Column(name="price_brand_match")
    private String priceBrandMatch;

    @Column(name="cut_from_articule")
    private String cutFromArticule;

    public String getCutFromArticule() {
        return cutFromArticule;
    }

    public void setCutFromArticule(String cutFromArticule) {
        this.cutFromArticule = cutFromArticule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriceBrand() {
        return priceBrand;
    }

    public void setPriceBrand(String priceBrand) {
        this.priceBrand = priceBrand;
    }

    public String getPriceBrandMatch() {
        return priceBrandMatch;
    }

    public void setPriceBrandMatch(String priceBrandMatch) {
        this.priceBrandMatch = priceBrandMatch;
    }
}
