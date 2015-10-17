package ua.autoshop.model;

import javax.persistence.*;

/**
 * Created by Пользователь on 14.10.2015.
 */
@Entity
@Table(name="margin")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Margin extends BaseModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="price_name")
    private String priceName;

    @Column(name="above_hundred_thousands")
    private Double aboveHundredThousands;
    @Column(name="above_hundred_thousands_fixed")
    private Double aboveHundredThousandsFixed;

    @Column(name="from_twenty_to_fifty_thousands")
    private Double fromTwentyToFifty;
    @Column(name="from_twenty_to_fifty_thousands_fixed")
    private Double fromTwentyToFiftyFixed;

    @Column(name="from_fifty_to_hundred_thousands")
    private Double fromFiftyToHundred;
    @Column(name="from_fifty_to_hundred_thousands_fixed")
    private Double fromFiftyToHundredFixed;

    @Column(name="from_ten_to_twenty_thousands")
    private Double fromTenToTwentyThousands;
    @Column(name="from_ten_to_twenty_thousands_fixed")
    private Double fromTenToTwentyThousandsFixed;

    @Column(name="from_five_to_ten_thousands")
    private Double fromFiveToTenThousands;
    @Column(name="from_five_to_ten_thousands_fixed")
    private Double fromFiveToTenThousandsFixed;

    @Column(name="from_thousand_to_five_thousands")
    private Double fromThousandToFiveThousands;
    @Column(name="from_thousand_to_five_thousands_fixed")
    private Double fromThousandToFiveThousandsFixed;

    @Column(name="from_five_hundred_to_thousand")
    private Double fromFiveHundredToThousand;
    @Column(name="from_five_hundred_to_thousand_Fixed")
    private Double fromFiveHundredToThousandFixed;

    @Column(name="from_three_hundred_to_five_hundred")
    private Double fromTreeHundredToFiveHundred;
    @Column(name="from_three_hundred_to_five_hundred_fixed")
    private Double fromTreeHundredToFiveHundredFixed;

    @Column(name="from_hundred_to_tree_hundred")
    private Double fromHundredToTreeHundred;
    @Column(name="from_hundred_to_tree_hundred_fixed")
    private Double fromHundredToTreeHundredFixed;

    @Column(name="less_than_hundred")
    private Double lessThanHundred;
    @Column(name="less_than_hundred_fixed")
    private Double lessThanHundredFixed;

    @Column(name="usd_currency_rate")
    private Double usdCurrencyRate;

    public Double getUsdCurrencyRate() {
        return usdCurrencyRate;
    }

    public void setUsdCurrencyRate(Double usdCurrencyRate) {
        this.usdCurrencyRate = usdCurrencyRate;
    }

    public Double getAboveHundredThousandsFixed() {
        return aboveHundredThousandsFixed;
    }

    public void setAboveHundredThousandsFixed(Double aboveHundredThousandsFixed) {
        this.aboveHundredThousandsFixed = aboveHundredThousandsFixed;
    }

    public Double getFromTwentyToFiftyFixed() {
        return fromTwentyToFiftyFixed;
    }

    public void setFromTwentyToFiftyFixed(Double fromTwentyToFiftyFixed) {
        this.fromTwentyToFiftyFixed = fromTwentyToFiftyFixed;
    }

    public Double getFromFiftyToHundredFixed() {
        return fromFiftyToHundredFixed;
    }

    public void setFromFiftyToHundredFixed(Double fromFiftyToHundredFixed) {
        this.fromFiftyToHundredFixed = fromFiftyToHundredFixed;
    }

    public Double getFromTenToTwentyThousandsFixed() {
        return fromTenToTwentyThousandsFixed;
    }

    public void setFromTenToTwentyThousandsFixed(Double fromTenToTwentyThousandsFixed) {
        this.fromTenToTwentyThousandsFixed = fromTenToTwentyThousandsFixed;
    }

    public Double getFromFiveToTenThousandsFixed() {
        return fromFiveToTenThousandsFixed;
    }

    public void setFromFiveToTenThousandsFixed(Double fromFiveToTenThousandsFixed) {
        this.fromFiveToTenThousandsFixed = fromFiveToTenThousandsFixed;
    }

    public Double getFromThousandToFiveThousandsFixed() {
        return fromThousandToFiveThousandsFixed;
    }

    public void setFromThousandToFiveThousandsFixed(Double fromThousandToFiveThousandsFixed) {
        this.fromThousandToFiveThousandsFixed = fromThousandToFiveThousandsFixed;
    }

    public Double getFromFiveHundredToThousandFixed() {
        return fromFiveHundredToThousandFixed;
    }

    public void setFromFiveHundredToThousandFixed(Double fromFiveHundredToThousandFixed) {
        this.fromFiveHundredToThousandFixed = fromFiveHundredToThousandFixed;
    }

    public Double getFromTreeHundredToFiveHundredFixed() {
        return fromTreeHundredToFiveHundredFixed;
    }

    public void setFromTreeHundredToFiveHundredFixed(Double fromTreeHundredToFiveHundredFixed) {
        this.fromTreeHundredToFiveHundredFixed = fromTreeHundredToFiveHundredFixed;
    }

    public Double getFromHundredToTreeHundredFixed() {
        return fromHundredToTreeHundredFixed;
    }

    public void setFromHundredToTreeHundredFixed(Double fromHundredToTreeHundredFixed) {
        this.fromHundredToTreeHundredFixed = fromHundredToTreeHundredFixed;
    }

    public Double getLessThanHundredFixed() {
        return lessThanHundredFixed;
    }

    public void setLessThanHundredFixed(Double lessThanHundredFixed) {
        this.lessThanHundredFixed = lessThanHundredFixed;
    }

    public Double getLessThanHundred() {
        return lessThanHundred;
    }

    public void setLessThanHundred(Double lessThanHundred) {
        this.lessThanHundred = lessThanHundred;
    }

    public Double getFromHundredToTreeHundred() {
        return fromHundredToTreeHundred;
    }

    public void setFromHundredToTreeHundred(Double fromHundredToTreeHundred) {
        this.fromHundredToTreeHundred = fromHundredToTreeHundred;
    }

    public Double getFromTreeHundredToFiveHundred() {
        return fromTreeHundredToFiveHundred;
    }

    public void setFromTreeHundredToFiveHundred(Double fromTreeHundredToFiveHundred) {
        this.fromTreeHundredToFiveHundred = fromTreeHundredToFiveHundred;
    }

    public Double getFromThousandToFiveThousands() {
        return fromThousandToFiveThousands;
    }

    public void setFromThousandToFiveThousands(Double fromThousandToFiveThousands) {
        this.fromThousandToFiveThousands = fromThousandToFiveThousands;
    }

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

    public Double getAboveHundredThousands() {
        return aboveHundredThousands;
    }

    public void setAboveHundredThousands(Double aboveHundredThousands) {
        this.aboveHundredThousands = aboveHundredThousands;
    }

    public Double getFromFiftyToHundred() {
        return fromFiftyToHundred;
    }

    public void setFromFiftyToHundred(Double fromFiftyToHundred) {
        this.fromFiftyToHundred = fromFiftyToHundred;
    }

    public Double getFromTwentyToFifty() {
        return fromTwentyToFifty;
    }

    public void setFromTwentyToFifty(Double fromTwentyToFifty) {
        this.fromTwentyToFifty = fromTwentyToFifty;
    }

    public Double getFromTenToTwentyThousands() {
        return fromTenToTwentyThousands;
    }

    public void setFromTenToTwentyThousands(Double fromTenToTwentyThousands) {
        this.fromTenToTwentyThousands = fromTenToTwentyThousands;
    }

    public Double getFromFiveToTenThousands() {
        return fromFiveToTenThousands;
    }

    public void setFromFiveToTenThousands(Double fromFiveToTenThousands) {
        this.fromFiveToTenThousands = fromFiveToTenThousands;
    }

    public Double getFromFiveHundredToThousand() {
        return fromFiveHundredToThousand;
    }

    public void setFromFiveHundredToThousand(Double fromFiveHundredToThousand) {
        this.fromFiveHundredToThousand = fromFiveHundredToThousand;
    }
}

