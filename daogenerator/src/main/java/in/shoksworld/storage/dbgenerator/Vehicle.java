package in.shoksworld.storage.dbgenerator;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/**
 * Prepares VEHICLE entity
 */

public class Vehicle {
    private String ENTITY_VEHICLE = "Vehicle";
    private String VEHICLE_NAME = "vehicleName";
    private String VEHICLE_TYPE = "vehicleType";
    private String PRICE = "price";

    public Entity prepareVehiclesEntity(Schema schema) {
        Entity vehiclesEntity = schema.addEntity(ENTITY_VEHICLE);
        vehiclesEntity.addIdProperty();
        vehiclesEntity.addStringProperty(VEHICLE_NAME);
        vehiclesEntity.addStringProperty(VEHICLE_TYPE);
        vehiclesEntity.addIntProperty(PRICE);

        return vehiclesEntity;
    }
}
