package in.shoksworld.greendao.storage.dbgenerator;

import in.shoksworld.greendao.GreenDao;

/**
 * Helper class for database operations
 */

public class DbHelper {
    public boolean addVehicle(Vehicle vehicle){
        try {
            VehicleDao vehicleDao = GreenDao.getInstance().getDaoSession().getVehicleDao();
            vehicleDao.insert(vehicle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
