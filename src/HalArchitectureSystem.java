public class HalArchitectureSystem {

    private final HalConfigReader configReader = new HalConfigReader();


    public void setUp(String filePath){
        configReader.readConfigurationFile(filePath);
    }




}
