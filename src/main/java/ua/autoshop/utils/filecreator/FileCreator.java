package ua.autoshop.utils.filecreator;

import ua.autoshop.config.ApplicationContextHolder;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.BrandMatches;
import ua.autoshop.model.Comment;
import ua.autoshop.model.PriceAutoshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Пользователь on 21.10.2015.
 */
public abstract class FileCreator {

    FileCreator(){
        fileCreatorManager = (Manager) ApplicationContextHolder.getContext().getBean("fileCreatorManager");
        List<BrandMatches> brandMatchesList = fileCreatorManager.getBrandMatches();
        for(BrandMatches brandMatch: brandMatchesList){
            BrandMatcherContent bmc = new BrandMatcherContent();
            bmc.setTrueBrand(brandMatch.getPriceBrandMatch());
            bmc.setArtCut(brandMatch.getCutFromArticule());
            brandMatchesMap.put(brandMatch.getPriceBrand(), bmc);
            if(articuleMatchesMap.get(brandMatch.getPriceBrand())==null){
                List <String> cutList = new ArrayList<>();
                if(brandMatch.getCutFromArticule()!=null && !brandMatch.getCutFromArticule().equals("")) {
                    cutList.add(brandMatch.getCutFromArticule());
                }
                articuleMatchesMap.put(brandMatch.getPriceBrand(), cutList);
            }else{
                List <String> cutList = articuleMatchesMap.get(brandMatch.getPriceBrand());
                if(brandMatch.getCutFromArticule()!=null && !brandMatch.getCutFromArticule().equals("")) {
                    cutList.add(brandMatch.getCutFromArticule());
                }
                articuleMatchesMap.put(brandMatch.getPriceBrand(), cutList);
            }
        }
    }



    Manager fileCreatorManager;

    protected Map<String, BrandMatcherContent> brandMatchesMap = new HashMap<>();

    protected Map<String, List <String>> articuleMatchesMap = new HashMap<>();

    protected static final String TEMP_DIR =  "java.io.tmpdir";

    public abstract void prepareForReading(String filename);

    public abstract void prepareForReadingAutoXCatalogTOMarket(String filename);

    public abstract void prepareForReadingAutoXCatalogAutoshopNet(String filename);

    public abstract void createHeaders();

    public abstract void createHeadersAutoXCatalogTOMarket();

    public abstract void createHeadersAutoXCatalogAutoshopNet();

    public abstract void createNextRow(PriceAutoshop price);

    public abstract void createNextRowAutoXCatalogTOMarket(PriceAutoshop price, Comment comment);

    public abstract void createNextRowAutoXCatalogAutoshopNet(PriceAutoshop price);

    public abstract void finishReading();

    public abstract void finishReadingAutoXCatalogTOMarket();

    public abstract void finishReadingAutoXCatalogAutoshopNet();

    public  abstract String getSuffix();

    public Map<String, BrandMatcherContent> getBrandMatchesMap() {
        return brandMatchesMap;
    }

    public void setBrandMatchesMap(Map<String, BrandMatcherContent> brandMatchesMap) {
        this.brandMatchesMap = brandMatchesMap;
    }

    public Map<String, List<String>> getArticuleMatchesMap() {
        return articuleMatchesMap;
    }

    public void setArticuleMatchesMap(Map<String, List<String>> articuleMatchesMap) {
        this.articuleMatchesMap = articuleMatchesMap;
    }
}
