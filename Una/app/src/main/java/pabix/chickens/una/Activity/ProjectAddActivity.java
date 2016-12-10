package pabix.chickens.una.Activity;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

import pabix.chickens.una.HTTPConnection.AddProject;
import pabix.chickens.una.HTTPConnection.Successful;
import pabix.chickens.una.Management.URLManager;
import pabix.chickens.una.Management.UserManager;
import pabix.chickens.una.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectAddActivity extends AppCompatActivity {

    private TextView textView;
    private TextView content,projectName,tag;
    private LinearLayout linearLayout;
    private String endDate;
    private final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        textView = (TextView)findViewById(R.id.period);
        content = (TextView)findViewById(R.id.contents);
        projectName = (TextView)findViewById(R.id.projectName);
        tag = (TextView)findViewById(R.id.tags);
        linearLayout = (LinearLayout)findViewById(R.id.periods);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(ProjectAddActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        Button btn = (Button)findViewById(R.id.send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignUp();
            }
        });


    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            endDate = year+"-"+monthOfYear+"-"+dayOfMonth;
            Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
            textView.setText(year + "년" + monthOfYear + "월" + dayOfMonth +"일");
        }
    };

    private void doSignUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLManager.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AddProject doSignup = retrofit.create(AddProject.class);

        Call<List<Successful>> call = doSignup.doSignup(
                UserManager.getInstance().getId(),projectName.getText().toString(),UserManager.getInstance().getName(),content.getText().toString(),calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE),endDate,1,3,tag.getText().toString());

        call.enqueue(new Callback<List<Successful>>() {
            @Override
            public void onResponse(Call<List<Successful>> call, Response<List<Successful>> response) {
                Toast.makeText(getApplicationContext(), "신청되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<List<Successful>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
