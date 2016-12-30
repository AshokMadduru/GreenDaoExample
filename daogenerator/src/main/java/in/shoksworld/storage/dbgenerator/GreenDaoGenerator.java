package in.shoksworld.storage.dbgenerator;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Schema;

/**
 * Generates Database Object classes
 */
public class GreenDaoGenerator {
    private static String PACKAGE_NAME = "in.shoksworld.greendao.storage.dbgenerator";
    private static String OUTPUT_LOCATION = "./app/src/main/java";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, PACKAGE_NAME);
        new Vehicle().prepareVehiclesEntity(schema);
        new DaoGenerator().generateAll(schema, OUTPUT_LOCATION);
    }
}
