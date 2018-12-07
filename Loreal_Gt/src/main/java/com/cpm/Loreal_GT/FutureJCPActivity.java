package com.cpm.Loreal_GT;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.JourneyPlanPreviousGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.dailyentry.FoodStore;
import com.cpm.message.AlertMessage;
import com.cpm.xmlHandler.XMLHandlers;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;


public class FutureJCPActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton fab;
    ImageButton calenderBtn;
    TextView txt_date;
    RecyclerView futureJcpList;
    Calendar c;
    int year;
    int month;
    int day;
    SharedPreferences preferences;
    String _UserId;
    ProgressDialog progressDialog;
    int eventType;
    JourneyPlanPreviousGetterSetter journeyPlanPreviousGetterSetter;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_jcp);
        declaration();

        calenderBtn.setOnClickListener(this);
        fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iconCalender:
                c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                showDatePickerDialog(year, month, day);
                break;
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

        }

    }

    protected void showDatePickerDialog(int year, int month, int day) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, pickerListener, year, month, day);
        // ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("year", "id", "android")).setVisibility(View.GONE);
        //datePickerDialog.findViewById(Resources.getSystem().getIdentifier("year", "id", "android")).setVisibility(View.GONE);
        datePickerDialog.setTitle("");
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            futureJcpList.removeAllViewsInLayout();
            futureJcpList.invalidate();
            year = selectedYear;
            month = selectedMonth + 1;
            day = selectedDay;

            String day_str = String.valueOf(day);
            day_str = "00" + day_str;
            day_str = day_str.substring(day_str.length() - 2, day_str.length());


            String month_str = String.valueOf(month);
            month_str = "00" + month_str;
            month_str = month_str.substring(month_str.length() - 2, month_str.length());

            String yeat_str = String.valueOf(year);

            txt_date.setText(new StringBuilder().append(month_str).append("/").append(day_str).append("/").append(yeat_str)
            );
            new Task().execute(txt_date.getText().toString());

        }
    };


    void declaration() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        calenderBtn = (ImageButton) findViewById(R.id.iconCalender);
        txt_date = (TextView) findViewById(R.id.txt_date);
        futureJcpList = (RecyclerView) findViewById(R.id.futureJcpList);
        context = this;

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        _UserId = preferences.getString(CommonString1.KEY_USERNAME, "");
        progressDialog = new ProgressDialog(FutureJCPActivity.this);
    }

    // AsyncTask asyncTask = new AsyncTask<String, String, String>() {
    class Task extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Fetching Data..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            XmlPullParserFactory factory = null;
            try {
                factory = XmlPullParserFactory
                        .newInstance();

                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                SoapSerializationEnvelope envelope;
                HttpTransportSE androidHttpTransport;
                SoapObject request;

                // Brand Master data
                request = new SoapObject(CommonString1.NAMESPACE,
                        CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "JOURNEY_SEARCH:" + params[0]);
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);

                androidHttpTransport.call(
                        CommonString1.SOAP_ACTION_UNIVERSAL, envelope);
                Object resultFuturedata = (Object) envelope.getResponse();

                if (resultFuturedata.toString() != null) {

                    xpp.setInput(new StringReader(resultFuturedata.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();

                    journeyPlanPreviousGetterSetter = XMLHandlers.JCPFutureXML(xpp, eventType);

                }
                return "Success";
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                return "failure";
            } catch (SoapFault soapFault) {
                soapFault.printStackTrace();
                return "failure";
            } catch (IOException e) {
                e.printStackTrace();
                return "failure";
            } catch (Exception e) {
                e.printStackTrace();
                return "failure";
            }
        }


        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            if (o.equalsIgnoreCase("Success")) {
                if (journeyPlanPreviousGetterSetter.getSTORE_CD().size() > 0) {

                    MyListAdapter adapter = new MyListAdapter(context, journeyPlanPreviousGetterSetter);
                    futureJcpList.setLayoutManager(new LinearLayoutManager(context));
                    futureJcpList.setAdapter(adapter);

                } else {
                    AlertMessage.showToastMessage(context, "No JCP on this Date");
                }
            } else {
                AlertMessage.showToastMessage(context, "Failure,No JCP");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {

            // NavUtils.navigateUpFromSameTask(this);
            finish();

            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

        }

        return super.onOptionsItemSelected(item);
    }


    class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
        LayoutInflater layoutInflater;
        JourneyPlanPreviousGetterSetter journeyPlanPreviousGetterSetter;

        MyListAdapter(Context context, JourneyPlanPreviousGetterSetter journeyPlanPreviousGetterSetter) {
            layoutInflater = LayoutInflater.from(context);
            this.journeyPlanPreviousGetterSetter = journeyPlanPreviousGetterSetter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.item_future_jcp_list, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            if (holder != null) {
                holder.txt_store_cd.setText(journeyPlanPreviousGetterSetter.getSTORE_CD().get(position));
                holder.txt_keyacct.setText(journeyPlanPreviousGetterSetter.getKEYACCOUNT().get(position));
                holder.txt_storename.setText(journeyPlanPreviousGetterSetter.getSTORENAME().get(position));
                holder.txt_city.setText(journeyPlanPreviousGetterSetter.getCITY().get(position));
                holder.txt_storetype.setText(journeyPlanPreviousGetterSetter.getSTORETYPE().get(position));
                if ((position % 2) == 0) {
                    holder.ll_itemfutureJCP.setBackgroundColor(getResources().getColor(R.color.color1));
                } else {
                    holder.ll_itemfutureJCP.setBackgroundColor(getResources().getColor(R.color.color7));
                }
            }
        }


        @Override
        public int getItemCount() {
            return journeyPlanPreviousGetterSetter.getSTORE_CD().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txt_store_cd, txt_keyacct, txt_storename, txt_city, txt_storetype;
            LinearLayout ll_itemfutureJCP;

            public ViewHolder(View view) {
                super(view);
                txt_store_cd = (TextView) view.findViewById(R.id.txt_store_cd);
                txt_keyacct = (TextView) view.findViewById(R.id.txt_keyacct);
                txt_storename = (TextView) view.findViewById(R.id.txt_storename);
                txt_city = (TextView) view.findViewById(R.id.txt_city);
                txt_storetype = (TextView) view.findViewById(R.id.txt_storetype);
                ll_itemfutureJCP = (LinearLayout) view.findViewById(R.id.ll_itemfutureJCP);
            }
        }
    }


}
