package com.cpm.Loreal_GT;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.cpm.GetterSetter.EmpSalaryGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;

import java.util.ArrayList;


public class EmpSalaryActivity extends AppCompatActivity {

    TextView empName;
    TextView empCode;
    TextView salMonth_txt;
    TextView salYear_txt;
    TextView payment_txt;
    TextView present_txt;
    TextView holiday_txt;
    TextView totalEarn_txt;
    TextView pf_txt;
    TextView esi_txt;
    TextView profTax_txt;
    TextView lwf_txt;
    TextView mis_txt;
    TextView tds_txt;
    TextView incentive_txt;
    TextView takeHome_txt;
    GSKDatabase database;
    ArrayList<EmpSalaryGetterSetter> empList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_salary);
        declaration();

        empList = database.getEmpSalaryData();
        if(empList.size()>0)
        {
            empName.setText(empList.get(0).getEMP_NAME());
            empCode.setText(empList.get(0).getECODE());
            salMonth_txt.setText(empList.get(0).getSALARY_MONTH());
            salYear_txt.setText(empList.get(0).getSALARY_YEAR());
            payment_txt.setText(empList.get(0).getPAYMENT_MODE());
            present_txt.setText(empList.get(0).getPRESENT_DAYS());
            holiday_txt.setText(empList.get(0).getNATIONAL_H());
            totalEarn_txt.setText(empList.get(0).getTOTAL_EARNING());
            pf_txt.setText(empList.get(0).getPF());
            esi_txt.setText(empList.get(0).getESI());
            profTax_txt.setText(empList.get(0).getPT());
            lwf_txt.setText(empList.get(0).getLWF());
            mis_txt.setText(empList.get(0).getMIS_DEDUCTION());
            tds_txt.setText(empList.get(0).getTDS());
            incentive_txt.setText(empList.get(0).getINCENTIVE());
            takeHome_txt.setText(empList.get(0).getTAKE_HOME());
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

        }

        return super.onOptionsItemSelected(item);
    }

    void declaration()
    {


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Employee Salary");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        empName = (TextView)findViewById(R.id.empName);
        empCode = (TextView)findViewById(R.id.empCode);
        salMonth_txt = (TextView)findViewById(R.id.salMonth_txt);
        salYear_txt = (TextView)findViewById(R.id.salYear_txt);
        payment_txt = (TextView)findViewById(R.id.payment_txt);
        present_txt = (TextView)findViewById(R.id.present_txt);
        holiday_txt = (TextView)findViewById(R.id.holiday_txt);
        totalEarn_txt = (TextView)findViewById(R.id.totalEarn_txt);
        pf_txt = (TextView)findViewById(R.id.pf_txt);
        esi_txt = (TextView)findViewById(R.id.esi_txt);
        profTax_txt = (TextView)findViewById(R.id.profTax_txt);
        lwf_txt = (TextView)findViewById(R.id.lwf_txt);
        mis_txt = (TextView)findViewById(R.id.mis_txt);
        tds_txt = (TextView)findViewById(R.id.tds_txt);
        incentive_txt = (TextView)findViewById(R.id.incentive_txt);
        takeHome_txt = (TextView)findViewById(R.id.takeHome_txt);
        database = new GSKDatabase(this);
        database.open();
    }
}
