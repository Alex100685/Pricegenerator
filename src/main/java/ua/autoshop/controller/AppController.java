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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.*;
import ua.autoshop.utils.savers.impl.csv.PriceAutotechnixReader;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.text.SimpleDateFormat;
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
    Dao <PriceGerasimenko> daoPriceG;

    @Autowired
    Dao <PriceVlad> daoPriceV;

    @Autowired
    Dao <PriceAutotechnix> daoPriceA;

    @Autowired
    Dao <PriceIntercarsi> daoPriceI;

    @Autowired
    Dao <Updates> daoUpdates;

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


    @RequestMapping("/admin/refreshPrice")
    public ModelAndView refreshPrice() {
        try {
            manager.getAllMargin();
            daoPriceAshop.cleanTable();
            manager.createCommonPriceInDB();
            daoPriceAshop.iterateAllAndSaveToMainTable(null);

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
        response.setHeader("Content-Disposition:attachment; filename=", "Autoshop "+date+".xlsx");
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

}
