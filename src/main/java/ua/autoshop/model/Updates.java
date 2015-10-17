package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 09.10.2015.
 */

@Entity
@Table(name="updates")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Updates extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="price_name")
    private String priceName;

    @Column(name="date_of_update")
    private String dateOfUpdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(String dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }
}
