package ua.autoshop.controller;

/**
 * Created by Пользователь on 08.10.2015.
 */

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.*;
import ua.autoshop.utils.savers.impl.csv.PriceAutotechnixReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    @Qualifier("manager")
    Manager manager;

    @Autowired
    Dao<PriceAutoshop> daoPriceAshop;

    @Autowired
    Dao<User> daoUser;

    @Autowired
    Dao<PriceElitOriginal> daoPriceElitOriginal;

    @Autowired
    Dao<PriceUnicTrade> daoPriceUnicTrade;

    @Autowired
    Dao <PriceGerasimenko> daoPriceG;

    @Autowired
    Dao <PriceVlad> daoPriceV;

    @Autowired
    Dao <PriceTomarket> daoPriceT;

    @Autowired
    Dao <PriceAutotechnix> daoPriceA;

    @Autowired
    Dao <PriceIntercarsi> daoPriceI;

    @Autowired
    Dao <Updates> daoUpdates;

    @Autowired
    Dao <PriceGenstar> daoPriceGenstar;

    @Autowired
    Dao <PriceAmperis> daoPriceAmperis;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    private PriceAutotechnixReader autotechnixReaderReader;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "login";
    }

    private final String TEMP_DIR = "java.io.tmpdir";

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView listClients(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String password2
    ) {

        if (!password.equals(password2)) {
            return new ModelAndView("login", "errMsg", "Password and confirm password values are not equal!");
        }

        if (daoUser.findByName(login) != null) {
            return new ModelAndView("login", "errMsg", "Account with entered login is already exists. Please, enter another login!");
        }
        // generate the "salt" value for password encoding
        byte[] saltBytes = KeyGenerators.secureRandom(2).generateKey();
        String salt = DatatypeConverter.printHexBinary(saltBytes);
        // creating password for smf database
        password = passwordEncoder.encodePassword(password, salt);
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setSalt(salt);
        daoUser.save(user);
        return new ModelAndView("login");
    }


    @RequestMapping("/admin/")
    public ModelAndView listDocs() {
        Updates [] updateArray = manager.getAllUpdates();
        return new ModelAndView("index", "updates", updateArray);
    }

    @RequestMapping("/admin/productSearch")
    public ModelAndView productSearch() {
        List <PriceAutoshop> autoshopPriceList = new ArrayList<>();
        return new ModelAndView("searchpage", "mainprice", autoshopPriceList);
    }

    @RequestMapping("/admin/searchByPrice")
    public ModelAndView searchByPrice(
            @RequestParam(value="pattern") String pattern
    ) {
        List <PriceAutoshop> autoshopPriceList = manager.findAutoshopPriceByPrice(pattern);
        return new ModelAndView("searchpage", "mainprice", autoshopPriceList);
    }

    @RequestMapping("/admin/searchByCode")
    public ModelAndView searchByCode(
            @RequestParam(value="pattern") String pattern
    ) {
        List <PriceAutoshop> autoshopPriceList = manager.findAutoshopPriceByCode(pattern);
        return new ModelAndView("searchpage", "mainprice", autoshopPriceList);
    }

    @RequestMapping("/admin/searchByName")
    public ModelAndView searchByName(
            @RequestParam(value="pattern") String pattern
    ) {
        List <PriceAutoshop> autoshopPriceList = manager.findAutoshopPriceByName(pattern);
        return new ModelAndView("searchpage", "mainprice", autoshopPriceList);
    }

    @RequestMapping("/admin/updatePriceIntercars")
    public ModelAndView updatePriceIntercars(
            @RequestParam(value="file") MultipartFile file
    ) {
        if(!file.isEmpty()) {
            try {
                daoPriceI.cleanTable();
                List<PriceIntercarsi> priceList = (List<PriceIntercarsi>) manager.readPrice(file, "i");
                manager.saveAllPriceIntercarsi(priceList);
                manager.saveUpdate("Интеркарс");
            }catch(Exception e){
                manager.saveUpdateFail("Интеркарс");
                e.printStackTrace();
            }
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }

    @RequestMapping("/admin/updatePriceUnicTrade")
    public ModelAndView updatePriceUnicTrade(
            @RequestParam(value="file") MultipartFile file
    ) {
                if(!file.isEmpty()) {
                try {
                daoPriceUnicTrade.cleanTable();
                List<PriceUnicTrade> priceList = (List<PriceUnicTrade>) manager.readPrice(file, "unic");
                manager.saveAllPriceUnicTrade(priceList);
                manager.saveUpdate("Юник ТРЕЙД");
                }catch(Exception e){
                manager.saveUpdateFail("Юник ТРЕЙД");
                e.printStackTrace();
            }
        }
    return new ModelAndView("index", "updates", manager.getAllUpdates());
    }

    @RequestMapping("/admin/updatePriceElitOrignal")
    public ModelAndView updatePriceElitOrignal(
            @RequestParam(value="file") MultipartFile file
    ) {
        if(!file.isEmpty()) {
            try {
                daoPriceElitOriginal.cleanTable();
                List<PriceElitOriginal> priceList = (List<PriceElitOriginal>) manager.readPrice(file, "eo");
                manager.saveAllPriceElitOriginal(priceList);
                manager.saveUpdate("Элит ОРИГИНАЛ");
            }catch(Exception e){
                manager.saveUpdateFail("Элит ОРИГИНАЛ");
                e.printStackTrace();
            }
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }


    @RequestMapping("/admin/updatePriceAutotechnix")
    public ModelAndView updatePriceAutotechnix(
            @RequestParam(value="file") MultipartFile file
    ) {
        if(!file.isEmpty()) {
            try {
                daoPriceA.cleanTable();
                List<PriceAutotechnix> priceList = (List<PriceAutotechnix>) manager.readPrice(file, "a");
                manager.saveAllPriceAutotechix(priceList);
                manager.saveUpdate("Автотехникс");
            }catch(Exception e){
                manager.saveUpdateFail("Автотехникс");
                e.printStackTrace();
            }
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }

    @RequestMapping("/admin/updatePriceVlad")
    public ModelAndView updatePriceVlad(
            @RequestParam(value="file") MultipartFile file
    ) {
        if(!file.isEmpty()) {
            try {
                daoPriceV.cleanTable();
                List<PriceVlad> priceList = (List<PriceVlad>) manager.readPrice(file, "v");
                manager.saveAllPriceVlad(priceList);
                manager.saveUpdate("Влад");
            }catch(Exception e){
                manager.saveUpdateFail("Влад");
                e.printStackTrace();
            }
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }

    @RequestMapping("/admin/updatePriceElit")
    public ModelAndView updatePriceElit(
            @RequestParam(value="file") MultipartFile file
    ) {
        if(!file.isEmpty()) {
            try {
                daoPriceG.cleanTable();
                List<PriceGerasimenko> priceList = (List<PriceGerasimenko>) manager.readPrice(file, "e");
                manager.saveAllPriceElit(priceList);
                manager.saveUpdate("Элит");
            }catch(Exception e){
                manager.saveUpdateFail("Элит");
                e.printStackTrace();
            }
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }

    @RequestMapping("/admin/updatePriceGenstar")
    public ModelAndView updatePriceGenstar(
            @RequestParam(value="file") MultipartFile file
    ) {
        if(!file.isEmpty()) {
            try {
                daoPriceGenstar.cleanTable();
                List<PriceGenstar> priceList = (List<PriceGenstar>) manager.readPrice(file, "g");
                manager.saveAllPriceGenstar(priceList);
                manager.saveUpdate("Генстар");
            }catch(Exception e){
                manager.saveUpdateFail("Генстар");
                e.printStackTrace();
            }
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }

    @RequestMapping("/admin/updatePriceAmperis")
     public ModelAndView updatePriceAmperis(
            @RequestParam(value="file") MultipartFile file
    ) {
        if(!file.isEmpty()) {
            try {
                daoPriceAmperis.cleanTable();
                List<PriceAmperis> priceList = (List<PriceAmperis>) manager.readPrice(file, "amp");
                manager.saveAllPriceAmperis(priceList);
                manager.saveUpdate("Амперис");
            }catch(Exception e){
                manager.saveUpdateFail("Амперис");
                e.printStackTrace();
            }
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }

    @RequestMapping("/admin/saveComment")
    public ModelAndView saveComment(
            @RequestParam(value="comment") String comment
    ) {
        Comment com = manager.getComment();
        if(com == null){
            com = new Comment();
        }
        com.setText(comment);
        manager.saveComment(com);
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }

    @RequestMapping("/admin/updatePriceTomarket")
    public ModelAndView updatePriceTomarket(
            @RequestParam(value="file") MultipartFile file
    ) {
        if(!file.isEmpty()) {
            try {
                daoPriceT.cleanTable();
                List<PriceTomarket> priceList = (List<PriceTomarket>) manager.readPrice(file, "to");
                manager.saveAllPriceTomarket(priceList);
                manager.saveUpdate("ТОМАРКЕТ");
            }catch(Exception e){
                manager.saveUpdateFail("ТОМАРКЕТ");
                e.printStackTrace();
            }
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }


    @RequestMapping("/admin/refreshPrice")
    public ModelAndView refreshPrice() {
        try {
            manager.getAllMargin();
            daoPriceAshop.cleanTable();
            manager.createCommonPriceInDB();
            Margin margin = new Margin();
            margin.setPriceName("xlsx");
            daoPriceAshop.iterateAllAndSaveToMainTable(margin);

        }catch(Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }
    @RequestMapping("/admin/refreshPriceCsv")
    public ModelAndView refreshPriceCsv() {
        try {
            manager.getAllMargin();
            daoPriceAshop.cleanTable();
            manager.createCommonPriceInDB();
            Margin margin = new Margin();
            margin.setPriceName("csv");
            daoPriceAshop.iterateAllAndSaveToMainTable(margin);

        }catch(Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }


    @RequestMapping("/admin/marginAdministration")
    public ModelAndView marginAdministration() {
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginAutotechix")
     public ModelAndView setMarginAutotechix(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Автотехникс");
        Double less = lessThanHundred;
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginAutotechixWholesale")
    public ModelAndView setMarginAutotechixWholesale(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Автотехникс ОПТ");
        Double less = lessThanHundred;
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginIntercars")
    public ModelAndView setMarginIntercars(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Интеркарс");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginIntercarsWholesale")
    public ModelAndView setMarginIntercarsWholesale(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Интеркарс ОПТ");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginVlad")
    public ModelAndView setMarginVlad(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Влад");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginVladWholesale")
    public ModelAndView setMarginVladWholesale(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Влад ОПТ");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginElit")
     public ModelAndView setMarginElit(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Элит");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginElitWholesale")
    public ModelAndView setMarginElitWholesale(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Элит ОПТ");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginGenstar")
    public ModelAndView setMarginGenstar(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Генстар");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginGenstarWholesale")
    public ModelAndView setMarginGenstarWholesale(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Генстар ОПТ");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginAmperis")
    public ModelAndView setMarginAmperis(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Амперис");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginAmperisWholesale")
    public ModelAndView setMarginAmperisWholesale(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Амперис ОПТ");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }


    @RequestMapping("/admin/setMarginTomarket")
    public ModelAndView setMarginTomarket(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("ТОМАРКЕТ РОЗНИЦА");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginTomarketWholesale")
    public ModelAndView setMarginTomarketWholesale(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("ТОМАРКЕТ ОПТ");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginUnicTrade")
    public ModelAndView setMarginUnicTrade(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Юник ТРЕЙД РОЗНИЦА");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginUnicTradeWholesale")
    public ModelAndView setMarginUnicTradeWholesale(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Юник ТРЕЙД ОПТ");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginElitOriginal")
    public ModelAndView setMarginElitOriginal(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Элит ОРИГИНАЛ РОЗНИЦА");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping("/admin/setMarginElitOriginalWholesale")
    public ModelAndView setMarginElitOriginalWholesale(
            @RequestParam(value="lessThanHundred") Double lessThanHundred,
            @RequestParam(value="lessThanHundredFixed") Double lessThanHundredFixed,
            @RequestParam(value="fromHundredToTreeHundred") Double fromHundredToTreeHundred,
            @RequestParam(value="fromHundredToTreeHundredFixed") Double fromHundredToTreeHundredFixed,
            @RequestParam(value="fromTreeHundredToFiveHundred") Double fromTreeHundredToFiveHundred,
            @RequestParam(value="fromTreeHundredToFiveHundredFixed") Double fromTreeHundredToFiveHundredFixed,
            @RequestParam(value="fromFiveHundredToThousand") Double fromFiveHundredToThousand,
            @RequestParam(value="fromFiveHundredToThousandFixed") Double fromFiveHundredToThousandFixed,
            @RequestParam(value="fromThousandToFiveThousands") Double fromThousandToFiveThousands,
            @RequestParam(value="fromThousandToFiveThousandsFixed") Double fromThousandToFiveThousandsFixed,
            @RequestParam(value="fromFiveToTenThousands") Double fromFiveToTenThousands,
            @RequestParam(value="fromFiveToTenThousandsFixed") Double fromFiveToTenThousandsFixed,
            @RequestParam(value="fromTenToTwentyThousands") Double fromTenToTwentyThousands,
            @RequestParam(value="fromTenToTwentyThousandsFixed") Double fromTenToTwentyThousandsFixed,
            @RequestParam(value="fromTwentyToFifty") Double fromTwentyToFifty,
            @RequestParam(value="fromTwentyToFiftyFixed") Double fromTwentyToFiftyFixed,
            @RequestParam(value="fromFiftyToHundred") Double fromFiftyToHundred,
            @RequestParam(value="fromFiftyToHundredFixed") Double fromFiftyToHundredFixed,
            @RequestParam(value="aboveHundredThousands") Double aboveHundredThousands,
            @RequestParam(value="aboveHundredThousandsFixed") Double aboveHundredThousandsFixed,
            @RequestParam(value="usdCurrencyRate") Double usdCurrencyRate
    ) {
        Margin margin = manager.getMarginByName("Элит ОРИГИНАЛ ОПТ");
        margin.setLessThanHundred(lessThanHundred);
        margin.setLessThanHundredFixed(lessThanHundredFixed);
        margin.setFromHundredToTreeHundred(fromHundredToTreeHundred);
        margin.setFromHundredToTreeHundredFixed(fromHundredToTreeHundredFixed);
        margin.setFromTreeHundredToFiveHundred(fromTreeHundredToFiveHundred);
        margin.setFromTreeHundredToFiveHundredFixed(fromTreeHundredToFiveHundredFixed);
        margin.setFromFiveHundredToThousand(fromFiveHundredToThousand);
        margin.setFromFiveHundredToThousandFixed(fromFiveHundredToThousandFixed);
        margin.setFromThousandToFiveThousands(fromThousandToFiveThousands);
        margin.setFromThousandToFiveThousandsFixed(fromThousandToFiveThousandsFixed);
        margin.setFromFiveToTenThousands(fromFiveToTenThousands);
        margin.setFromFiveToTenThousandsFixed(fromFiveToTenThousandsFixed);
        margin.setFromTenToTwentyThousands(fromTenToTwentyThousands);
        margin.setFromTenToTwentyThousandsFixed(fromTenToTwentyThousandsFixed);
        margin.setFromTwentyToFifty(fromTwentyToFifty);
        margin.setFromTwentyToFiftyFixed(fromTwentyToFiftyFixed);
        margin.setFromFiftyToHundred(fromFiftyToHundred);
        margin.setFromFiftyToHundredFixed(fromFiftyToHundredFixed);
        margin.setAboveHundredThousands(aboveHundredThousands);
        margin.setAboveHundredThousandsFixed(aboveHundredThousandsFixed);
        margin.setUsdCurrencyRate(usdCurrencyRate);
        manager.saveMargin(margin);
        return new ModelAndView("retailAdministration", "margin", manager.getAllMargin());
    }

    @RequestMapping(value = "/admin/downloadPrice")
    public void downloadFile(
            HttpServletResponse response
    ) {
        String tempDir = System.getProperty(TEMP_DIR);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(tempDir+"/output.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("Content-Transfer-Encoding:binary");
        response.setContentType("Content-Type:application/octet-stream");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(d);
        response.setHeader("Content-Disposition", "attachment; filename=Autoshop "+date+".xlsx");
        response.setContentLength((int) bytes.length);
        OutputStream os;
        BufferedOutputStream bos;
        try {
            os = response.getOutputStream();
            bos = new BufferedOutputStream(os);
            bos.write(bytes);
            bos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @RequestMapping(value = "/admin/downloadPriceAvtoXCatalogTomarket")
    public void downloadPriceAvtoXCatalogTomarket(
            HttpServletResponse response
    ) {
        String tempDir = System.getProperty(TEMP_DIR);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(tempDir+"/outputto.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("Content-Transfer-Encoding:binary");
        response.setContentType("Content-Type:application/octet-stream");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(d);
        response.setHeader("Content-Disposition", "attachment; filename=AvtoXCatalogTomarket "+date+".xlsx");
        response.setContentLength((int) bytes.length);
        OutputStream os;
        BufferedOutputStream bos;
        try {
            os = response.getOutputStream();
            bos = new BufferedOutputStream(os);
            bos.write(bytes);
            bos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @RequestMapping(value = "/admin/downloadPriceAvtoXCatalogTomarketCsv")
    public void downloadPriceAvtoXCatalogTomarketCsv(
            HttpServletResponse response
    ) {
        String tempDir = System.getProperty(TEMP_DIR);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(tempDir+"/outputto.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("Content-Transfer-Encoding:binary");
        response.setContentType("Content-Type:application/octet-stream");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(d);
        response.setHeader("Content-Disposition", "attachment; filename=AvtoXCatalogTomarket "+date+".csv");
        response.setContentLength((int) bytes.length);
        OutputStream os;
        BufferedOutputStream bos;
        try {
            os = response.getOutputStream();
            bos = new BufferedOutputStream(os);
            bos.write(bytes);
            bos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @RequestMapping(value = "/admin/downloadPriceAvtoXCatalogAuto")
    public void downloadPriceAvtoXCatalogTomarketAuto(
            HttpServletResponse response
    ) {
        String tempDir = System.getProperty(TEMP_DIR);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(tempDir+"/outputauto.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("Content-Transfer-Encoding:binary");
        response.setContentType("Content-Type:application/octet-stream");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(d);
        response.setHeader("Content-Disposition", "attachment; filename=AvtoXCatalogAuto "+date+".xlsx");
        response.setContentLength((int) bytes.length);
        OutputStream os;
        BufferedOutputStream bos;
        try {
            os = response.getOutputStream();
            bos = new BufferedOutputStream(os);
            bos.write(bytes);
            bos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @RequestMapping(value = "/admin/downloadPriceAvtoXCatalogAutoCsv")
    public void downloadPriceAvtoXCatalogAutoCsv(
            HttpServletResponse response
    ) {
        String tempDir = System.getProperty(TEMP_DIR);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(tempDir+"/outputauto.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("Content-Transfer-Encoding:binary");
        response.setContentType("Content-Type:application/octet-stream");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(d);
        response.setHeader("Content-Disposition", "attachment; filename=AvtoXCatalogAuto "+date+".csv");
        response.setContentLength((int) bytes.length);
        OutputStream os;
        BufferedOutputStream bos;
        try {
            os = response.getOutputStream();
            bos = new BufferedOutputStream(os);
            bos.write(bytes);
            bos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @RequestMapping(value = "/admin/downloadPriceCsv")
    public void downloadFileCsv(
            HttpServletResponse response
    ) {
        String tempDir = System.getProperty(TEMP_DIR);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(tempDir+"/output.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("Content-Transfer-Encoding:binary");
        response.setContentType("Content-Type:application/octet-stream");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(d);
        response.setHeader("Content-Disposition", "attachment; filename=Autoshop "+date+".csv");
        response.setContentLength((int) bytes.length);
        OutputStream os;
        BufferedOutputStream bos;
        try {
            os = response.getOutputStream();
            bos = new BufferedOutputStream(os);
            bos.write(bytes);
            bos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @RequestMapping(value = "/admin/brandMatches")
    public ModelAndView setMarginGenstar(){
        return new ModelAndView("brandmatches", "barndmatchesList", manager.getBrandMatches());
    }

    @RequestMapping(value = "/admin/saveMatch")
    public ModelAndView saveMatch(
            @RequestParam (value="from") String from,
            @RequestParam (value="to") String to,
            @RequestParam (value="cut") String cut
    ){
        BrandMatches bm = manager.findBrandMatchByBrandAndTrueBrandAndCut(from, to, cut);
        if(bm==null){
            bm = new BrandMatches();
            bm.setPriceBrand(from.trim());
        }
        if(cut==null){
            bm.setCutFromArticule("");
        }
        else{
            bm.setCutFromArticule(cut.trim());
        }

        bm.setPriceBrandMatch(to.trim());
        manager.saveBrandMatch(bm);

        return new ModelAndView("brandmatches", "barndmatchesList", manager.getBrandMatches());
    }

    @RequestMapping(value = "/admin/deleteMatch")
    public ModelAndView deleteMatch(
            HttpServletRequest request
    ){
        String brand = request.getParameter("brand");
        String trueBrand = request.getParameter("trueBrand");
        String cut = request.getParameter("cut");
        BrandMatches bm = manager.findBrandMatchByBrandAndTrueBrandAndCut(brand, trueBrand, cut);
        manager.deleteBrandMatch(bm);

        return new ModelAndView("brandmatches", "barndmatchesList", manager.getBrandMatches());
    }

    @RequestMapping(value = "/admin/updateColumnMatches")
    public ModelAndView updateColumnMatches(HttpServletRequest request){
        String columnMatchName = request.getParameter("name");
        Class clazz = null;
        try {
            clazz = Class.forName("ua.autoshop.model."+columnMatchName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ColumnMatches columnMatches = manager.getColumnMatches(clazz);
        return new ModelAndView("columnMatches", "columnMatches", columnMatches);
    }

    @RequestMapping(value = "/admin/saveColumnMatch")
    public ModelAndView saveColumnMatch(
            @ModelAttribute(value="matchName") String matchName,
            @ModelAttribute(value="name") String name,
            @ModelAttribute(value="brand") String brand,
            @ModelAttribute(value="category") String category,
            @ModelAttribute(value="incomingprice") String incomingprice,
            @ModelAttribute(value="wholesaleprice") String wholesaleprice,
            @ModelAttribute(value="retailprice") String retailprice,
            @ModelAttribute(value="available") String available,
            @ModelAttribute(value="code") String code,
            @ModelAttribute(value="shelf") String shelf,
            @ModelAttribute(value="supplier") String supplier,
            @ModelAttribute(value="supplycondition") String supplyCondition,
            @ModelAttribute(value="picture") String picture,
            @ModelAttribute(value="currency") String currency,
            HttpServletRequest request){
        Class clazz = null;
        try {
            clazz = Class.forName("ua.autoshop.model."+matchName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ColumnMatches columnMatches = manager.getColumnMatches(clazz);

        columnMatches.setAvailableMatch(prepareValue(available));
        columnMatches.setBrandMatch(prepareValue(brand));
        columnMatches.setCategoryMatch(prepareValue(category));
        columnMatches.setIncomePriceMatch(prepareValue(incomingprice));
        columnMatches.setWholesalePriceMatch(prepareValue(wholesaleprice));
        columnMatches.setRetailPriceMatch(prepareValue(retailprice));
        columnMatches.setNameMatch(prepareValue(name));
        columnMatches.setCodeMatch(prepareValue(code));
        columnMatches.setShelfMatch(prepareValue(shelf));
        columnMatches.setSupplierMatch(prepareValue(supplier));
        columnMatches.setSupplyCondition(prepareValue(supplyCondition));
        columnMatches.setPictureMatch(prepareValue(picture));
        columnMatches.setCurrencyMatch(prepareValue(currency));
        manager.saveColumnMatch(columnMatches);
        columnMatches = manager.getColumnMatches(clazz);
        return new ModelAndView("columnMatches", "columnMatches", columnMatches);
    }

    private Integer prepareValue(String value){
        if(value.equals("")){
            return null;
        }
        else{
           return Integer.parseInt(value)-1;
        }
    }


    @RequestMapping(value = "/admin/deletePrice")
    public ModelAndView deletePrice(
            HttpServletRequest request
    ) {
        String priceName = request.getParameter("name");
        if(priceName.equals("Автотехникс")){
            daoPriceA.cleanTable();
            manager.saveUpdateEmpty("Автотехникс");
        }
        if(priceName.equals("Интеркарс")){
            daoPriceI.cleanTable();
            manager.saveUpdateEmpty("Интеркарс");
        }
        if(priceName.equals("Влад")){
            daoPriceV.cleanTable();
            manager.saveUpdateEmpty("Влад");
        }
        if(priceName.equals("Элит")){
            daoPriceG.cleanTable();
            manager.saveUpdateEmpty("Элит");
        }
        if(priceName.equals("Генстар")){
            daoPriceGenstar.cleanTable();
            manager.saveUpdateEmpty("Генстар");
        }
        if(priceName.equals("Амперис")){
            daoPriceAmperis.cleanTable();
            manager.saveUpdateEmpty("Амперис");
        }
        if(priceName.equals("Томаркет")){
            daoPriceT.cleanTable();
            manager.saveUpdateEmpty("ТОМАРКЕТ");
        }
        if(priceName.equals("Юниктрейд")){
            daoPriceUnicTrade.cleanTable();
            manager.saveUpdateEmpty("Юник ТРЕЙД");
        }
        if(priceName.equals("Элиторигинал")){
            daoPriceElitOriginal.cleanTable();
            manager.saveUpdateEmpty("Элит ОРИГИНАЛ");
        }

        return new ModelAndView("index", "updates", manager.getAllUpdates());
    }


}
