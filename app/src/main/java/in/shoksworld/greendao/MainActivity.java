package in.shoksworld.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in.shoksworld.greendao.storage.dbgenerator.Vehicle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addVehicleButton;
    private Button showAllVehiclesButton;
    private Button submitVehicleInfoButton;
    private EditText vehicleNameEditText;
    private EditText vehicleTypeEditText;
    private EditText vehicleCostEditText;
    private RecyclerView vehiclesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeEventListeners();
        addNewVehicleViews(false);
    }

    private void initializeViews() {
        addVehicleButton = (Button) findViewById(R.id.add_vehicle);
        showAllVehiclesButton = (Button) findViewById(R.id.get_all_vehicles);
        submitVehicleInfoButton = (Button) findViewById(R.id.submit_button);
        vehicleNameEditText = (EditText) findViewById(R.id.vehicle_name_edittext);
        vehicleTypeEditText = (EditText) findViewById(R.id.vehicle_type_edittext);
        vehicleCostEditText = (EditText) findViewById(R.id.vehicle_cost_edittext);
        vehiclesRecycler = (RecyclerView) findViewById(R.id.vehicles_recycler);
    }

    private void initializeEventListeners() {
        addVehicleButton.setOnClickListener(this);
        submitVehicleInfoButton.setOnClickListener(this);
        showAllVehiclesButton.setOnClickListener(this);
    }

    private void addNewVehicleViews(boolean show) {
        if (show) {
            submitVehicleInfoButton.setVisibility(View.VISIBLE);
            vehicleNameEditText.setVisibility(View.VISIBLE);
            vehicleTypeEditText.setVisibility(View.VISIBLE);
            vehicleCostEditText.setVisibility(View.VISIBLE);
        } else {
            submitVehicleInfoButton.setVisibility(View.GONE);
            vehicleNameEditText.setVisibility(View.GONE);
            vehicleTypeEditText.setVisibility(View.GONE);
            vehicleCostEditText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_vehicle:
                addNewVehicleViews(true);
                break;
            case R.id.submit_button:
                validateVehicleDetails();
                break;
            case R.id.get_all_vehicles:
                addNewVehicleViews(false);
                break;
        }
    }

    private void validateVehicleDetails() {
        if (vehicleNameEditText.getText().toString().equals("") ||
                vehicleTypeEditText.getText().toString().equals("") ||
                vehicleCostEditText.getText().toString().equals("")) {
            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_LONG).show();
        } else {
            Vehicle vehicleInfo = new Vehicle(null, vehicleNameEditText.getText().toString(),
                    vehicleTypeEditText.getText().toString(),
                    Integer.valueOf(vehicleCostEditText.getText().toString()));
            if (GreenDao.getInstance().getDbHelper().addVehicle(vehicleInfo)) {
                Toast.makeText(this, "Vehicle added", Toast.LENGTH_LONG).show();
                addNewVehicleViews(false);
            } else {
                Toast.makeText(this, "Vehicle addition failed. Try again.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
