package ua.autoshop.dal.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;

import java.util.List;

/**
 * Created by Пользователь on 09.10.2015.
 */
public interface Manager {

    static final int PORTION = 100;

    public Updates [] getAllUpdates();

    public List<? extends BaseModel> readPrice(MultipartFile file,String index);

    void saveUpdate(String updateName);

    void savePriceAutotechix(PriceAutotechnix priceAutotechnix);

    void saveAllPriceAutotechix(List<PriceAutotechnix> priceList);

    void saveUpdateFail(String updateName);

    void saveUpdateEmpty(String updateName);

    void saveAllPriceIntercarsi(List<PriceIntercarsi> priceList);

    void saveAllPriceVlad(List<PriceVlad> priceList);

    void saveAllPriceElit(List<PriceGerasimenko> priceList);

    public Margin [] getAllMargin();

    Margin getMarginByName(String priceName);

    void saveMargin(Margin margin);

    void createCommonPriceInDB();

    public void iterateAllAndRemoveDuplicates();

    void saveAllPriceGenstar(List <PriceGenstar> price);

    void saveAllPriceAmperis(List<PriceAmperis> priceList);

    List<PriceAutoshop> findAutoshopPriceByPrice(String pattern);

    List<PriceAutoshop> findAutoshopPriceByCode(String pattern);

    List<PriceAutoshop> findAutoshopPriceByName(String pattern);

    void saveAllPriceTomarket(List <PriceTomarket> price);

    void sortCommonTableByArticule();

    List<BrandMatches> getBrandMatches();

    void saveBrandMatch(BrandMatches bm);

    BrandMatches findBrandMatchByName(String name);

    void deleteBrandMatch(BrandMatches bm);

    void saveComment(Comment comment);

    Comment getComment();

    void saveAllPriceUnicTrade(List<PriceUnicTrade> priceList);

    void saveAllPriceElitOriginal(List<PriceElitOriginal> priceList);
}
