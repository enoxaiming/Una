package pabix.chickens.una.Activity;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

import pabix.chickens.una.R;

public class ProjectAddActivity extends AppCompatActivity {

    private TextView textView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final Calendar calendar = Calendar.getInstance();

        textView = (TextView)findViewById(R.id.period);
        linearLayout = (LinearLayout)findViewById(R.id.periods);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(ProjectAddActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });


    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
            textView.setText(year + "년" + monthOfYear + "월" + dayOfMonth +"일");
        }
    };

}
