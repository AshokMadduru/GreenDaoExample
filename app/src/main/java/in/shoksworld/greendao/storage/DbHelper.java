package in.shoksworld.greendao.storage;

import java.util.ArrayList;
import java.util.List;

import in.shoksworld.greendao.GreenDao;
import in.shoksworld.greendao.storage.dbgenerator.Vehicle;
import in.shoksworld.greendao.storage.dbgenerator.VehicleDao;

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

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        try {
            vehicleList = GreenDao.getInstance().getDaoSession().getVehicleDao()
                    .queryBuilder()
                    .orderDesc(VehicleDao.Properties.Id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicleList;
    }

    public boolean updateVehicleInfo(Vehicle vehicle) {
        try {
            GreenDao.getInstance().getDaoSession().getVehicleDao().update(vehicle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeVehicle(Vehicle vehicle) {
        try {
            GreenDao.getInstance().getDaoSession().getVehicleDao().delete(vehicle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
