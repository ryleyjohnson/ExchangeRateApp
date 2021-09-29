import java.util.Map;

public class DataStorage {


    private Map<String, Object> map1;
    private Map<String, Object> map2;

    DataStorage() {
        ApiRequest fileBuild = new ApiRequest();
        map1 = (Map<String, Object>) fileBuild.pullData();
        map2 = (Map<String, Object>) map1.get("rates");
    }


    public Map<String, Object> getMap2() {
        return map2;
    }
    public Map<String, Object> getMap1() {
        return map1;
    }

    public void setMap1(Map<String, Object> map1) {
        this.map1 = map1;
    }

    public void setMap2(Map<String, Object> map2) {
        this.map2 = map2;
    }

}






