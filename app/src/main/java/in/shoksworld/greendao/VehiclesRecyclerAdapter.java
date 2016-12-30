package in.shoksworld.greendao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.shoksworld.greendao.storage.dbgenerator.Vehicle;

/**
 * Adapter class to populate vehicles list
 */

public class VehiclesRecyclerAdapter extends RecyclerView.Adapter<VehiclesRecyclerAdapter.ViewHolder> {
    private List<Vehicle> vehicleList = new ArrayList<>();
    private VehicleModifyListener vehicleModifyListener;
    public VehiclesRecyclerAdapter(List<Vehicle> vehicleList,
                                   VehicleModifyListener vehicleModifyListener ) {
        this.vehicleList = vehicleList;
        this.vehicleModifyListener = vehicleModifyListener;
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    @Override
    public VehiclesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vehicle_list_item,
                viewGroup, false);
        VehiclesRecyclerAdapter.ViewHolder viewHolder = new VehiclesRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VehiclesRecyclerAdapter.ViewHolder viewHolder, final int position) {
        final Vehicle vehicleInfo = vehicleList.get(position);
        viewHolder.vehicleNameTextView.setText(vehicleInfo.getVehicleName());
        viewHolder.vehicleCostTextView.setText(String.valueOf(vehicleInfo.getPrice()));
        viewHolder.vehicleTypeTextView.setText(vehicleInfo.getVehicleType());
        viewHolder.removeVehicleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GreenDao.getInstance().getDbHelper().removeVehicle(vehicleInfo);
                vehicleList.remove(position);
                notifyDataSetChanged();
            }
        });
        viewHolder.editVehicleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicleModifyListener.onModifyClicked(vehicleInfo);
            }
        });
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView vehicleNameTextView;
        private TextView vehicleTypeTextView;
        private TextView vehicleCostTextView;
        private ImageView removeVehicleIcon;
        private ImageView editVehicleIcon;
        public ViewHolder(View item) {
            super(item);

            vehicleCostTextView = (TextView) item.findViewById(R.id.vehicle_cost_textview);
            vehicleTypeTextView = (TextView) item.findViewById(R.id.vehicle_type_textview);
            vehicleNameTextView = (TextView) item.findViewById(R.id.vehicle_name_textview);
            removeVehicleIcon = (ImageView) item.findViewById(R.id.remove_vehicle_icon);
            editVehicleIcon = (ImageView) item.findViewById(R.id.modify_vehicle_icon);
        }
    }

    public interface VehicleModifyListener {
        void onModifyClicked(Vehicle vehicle);
    }
}
