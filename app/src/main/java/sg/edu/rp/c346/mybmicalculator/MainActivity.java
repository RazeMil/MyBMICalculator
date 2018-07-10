package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    EditText etWeight;
    EditText etHeight;
    Button btnCalculate;
    Button btnReset;
    TextView tvDate;
    TextView tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight=findViewById(R.id.editTextWeight);
        etHeight=findViewById(R.id.editTextHeight);
        btnCalculate=findViewById(R.id.buttonCalculate);
        btnReset=findViewById(R.id.buttonReset);
        tvDate=findViewById(R.id.textViewDate);
        tvBMI=findViewById(R.id.textViewBMI);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);



                float strW = Float.parseFloat(etWeight.getText().toString());
                float strH = Float.parseFloat(etHeight.getText().toString());
                float BMI = ((strW/(strH*strH))*10000);

                tvDate.setText("Last Calculated Date:"+datetime);
                tvBMI.setText("Last Calculated BMI:"+BMI);

                }

        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDate.setText("Last Calculated Date:");
                tvBMI.setText("Last Calculated BMI:");
                etHeight.setText("");
                etWeight.setText("");


            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();


        float strW = Float.parseFloat(etWeight.getText().toString());
        float strH = Float.parseFloat(etHeight.getText().toString());
        float BMI = Float.parseFloat(tvBMI.getText().toString());
        Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
        String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putFloat("weight",strW);
        prefEdit.putFloat("height",strH);
        prefEdit.putString("date",datetime);
        prefEdit.putFloat("bmi",BMI);
        prefEdit.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        float msg = prefs.getFloat("weight",0);
        float msg1 = prefs.getFloat("height",0);
        String msg2 = prefs.getString("date","");
        float msg3 = prefs.getFloat("bmi",0);
        etWeight.setText(Float.toString(msg));
        etHeight.setText(Float.toString(msg1));
        tvDate.setText("Last Calculated Date:"+msg2);
        tvBMI.setText("Last Calculated BMI:"+Float.toString(msg3));

        Toast toast = Toast.makeText(getApplicationContext(),"Welcome Back !",Toast.LENGTH_LONG);
        toast.show();
    }
}
