import java.util.Map;

public class DataStorage {

    private Map<String, Object> outerExchangeRateMap;
    private Map<String, Object> innerExchangeRateMap;


    DataStorage() {
        ApiRequest fileBuild = new ApiRequest();
        outerExchangeRateMap = (Map<String, Object>) fileBuild.pullData();
        innerExchangeRateMap = (Map<String, Object>) outerExchangeRateMap.get("rates");
    }


    public Map<String, Object> getInnerExchangeRateMap() {
        return innerExchangeRateMap;
    }


    public Map<String, Object> getOuterExchangeRateMap() {
        return outerExchangeRateMap;
    }


    public void setOuterExchangeRateMap(Map<String, Object> outerExchangeRateMap) {
        this.outerExchangeRateMap = outerExchangeRateMap;
    }


    public void setInnerExchangeRateMap(Map<String, Object> innerExchangeRateMap) {
        this.innerExchangeRateMap = innerExchangeRateMap;
    }
}






