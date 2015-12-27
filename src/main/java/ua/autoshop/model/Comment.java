package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 11.12.2015.
 */

@Entity
@Table(name="comment")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Comment extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="text")
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
