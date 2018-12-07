package com.cpm.dailyentry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Constants.AlertandMessages;
import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.COMPETITORGetterSetter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class COMPETITORActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    boolean checkflag = true;
    String Error_Message;
    static int currentapiVersion = 1;
    String _pathforcheck, _path, str;
    Button btnsave;
    ListView ListView;
    EditText Editpro;
    ListView list2;
    ImageView imagebutton;
    Context context;
    String img1 = "";
    private ArrayList<COMPETITORGetterSetter> list = new ArrayList<>();
    ArrayList<COMPETITORGetterSetter> categorylist = new ArrayList<COMPETITORGetterSetter>();
    private Button addbtn, save_btn;
    GSKDatabase db;
    Spinner spinner, spinner_exists;
    List<String> spinnerdatalist = new ArrayList<String>();
    private SharedPreferences preferences;
    String store_cd;
    String visit_date, username, intime, metadata_global;
    private ArrayAdapter<CharSequence> ExistAdapter;
    ArrayList<COMPETITORGetterSetter> secPlaceData = new ArrayList<>();
    ArrayAdapter<String> adapter;
    boolean ischangedflag = false;
    LinearLayout cameralayout, competitorLLayout, remark_ll;
    List<Integer> checkHeaderArray = new ArrayList<Integer>();
    String company_cd = "0", company = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        declaration();
        prepareList();
        setSavedDataToListView();
    }

    void prepareList() {
        spinnerdatalist.add("Select COMPETITOR");
        categorylist = db.getCOMPETITORData();
        for (int i = 0; i < categorylist.size(); i++) {
            spinnerdatalist.add(categorylist.get(i).getCOMPANY());
        }
        adapter = new ArrayAdapter<String>(COMPETITORActivity.this, R.layout.spinner_custom_item, spinnerdatalist);
        adapter.setDropDownViewResource(R.layout.spinner_custom_item);

        ExistAdapter = new ArrayAdapter<CharSequence>(context, R.layout.spinner_custom_item);
        ExistAdapter.add("Select Exists");
        ExistAdapter.add("YES");
        ExistAdapter.add("NO");
        spinner_exists.setAdapter(ExistAdapter);
        spinner.setAdapter(adapter);
    }

    public void setSavedDataToListView() {
        try {
            list = db.getCOMPETITORData(store_cd);
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).getSpinnerExists_CD().equalsIgnoreCase("0")) {
                        spinner_exists.setSelection(2);
                    } else {
                        COMPETITORGetterSetter secdata = new COMPETITORGetterSetter();
                        secdata.setEdText(list.get(i).getEdText());
                        secdata.setCOMPANY_CD(list.get(i).getCOMPANY_CD());
                        secdata.setCOMPANY(list.get(i).getCOMPANY());
                        secdata.setImage(list.get(i).getImage());
                        secdata.setSpinnerExists_CD(list.get(i).getSpinnerExists_CD());
                        secdata.setID(list.get(i).getID());

                        secPlaceData.add(secdata);
                    }
                }
                Collections.reverse(secPlaceData);
                list2.setAdapter(new MyAdaptor());
                list2.invalidateViews();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching",
                    e.toString());
        }
    }

    public void setAddedDataToListView(ArrayList<COMPETITORGetterSetter> secPlaceData) {
        try {
            if (secPlaceData.size() > 0) {
                if (secPlaceData.get(0).getSpinnerExists_CD().equalsIgnoreCase("0")) {
                    spinner_exists.setSelection(2);
                } else {
                    Collections.reverse(secPlaceData);
                    list2.setAdapter(new MyAdaptor());
                    list2.invalidateViews();
                }
            } else {
                list2.setAdapter(new MyAdaptor());
                list2.invalidateViews();
            }


        } catch (Exception e) {
            Log.d("Exception when fetching",
                    e.toString());
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_Add) {
            list2.clearFocus();
            list2.invalidateViews();
            if (validate()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to add").setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        db.open();
                                        COMPETITORGetterSetter secdata = new COMPETITORGetterSetter();
                                        secdata.setCOMPANY(company);
                                        secdata.setSpinnerExists(spinner_exists.getSelectedItem().toString());
                                        secdata.setCOMPANY_CD(company_cd);
                                        String strLong1 = Long.toString(spinner_exists.getSelectedItemId());
                                        if (strLong1.equals("1")) {
                                            strLong1 = "1";
                                        }
                                        secdata.setSpinnerExists_CD(strLong1);
                                        secdata.setImage(img1);
                                        secdata.setEdText(Editpro.getText().toString().replaceAll("[-@.?/|=+_#%:;^*()!&^<>{},'$0]", ""));
                                        secPlaceData.add(secdata);

                                        setAddedDataToListView(secPlaceData);

                                        Editpro.setText("");
                                        img1 = "";
                                        imagebutton.setBackgroundResource(R.drawable.camera);
                                        spinner.setSelection(0);
                                        spinner_exists.setSelection(0);
                                        cameralayout.setVisibility(View.VISIBLE);
                                        list.clear();
                                        dialog.dismiss();
                                    }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                AlertandMessages.showToastMsg(context, Error_Message);
            }
        }

        if (id == R.id.save_btn) {
            if (validate_save()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to save").setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        getMid();
                                        String strLong1;
                                        if (secPlaceData.size() > 0) {
                                            strLong1 = secPlaceData.get(0).getSpinnerExists_CD();
                                            if (strLong1.equals("1")) {
                                                strLong1 = "1";
                                            }
                                        } else {
                                            strLong1 = "0";
                                        }
                                        db.open();
                                        if (db.insertCOMPETITORDATA(store_cd, secPlaceData, list, strLong1)) {
                                            AlertandMessages.showToastMsg(context, "Data has been saved");
                                            ((Activity) context).finish();
                                        } else {
                                            AlertandMessages.showToastMsg(context, "Error in Data Saving");
                                        }
                                    }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                AlertandMessages.showToastMsg(context, Error_Message);
            }
        }

        if (id == R.id.imageButton) {
            _pathforcheck = store_cd + "competitorImage" + visit_date.replace("/", "") + getCurrentTime().replace(":", "") + ".jpg";
            _path = CommonString1.FILE_PATH + _pathforcheck;
            //startCameraActivity();
            CommonFunctions.startAnncaCameraActivity(context, _path);
            //CommonFunctions.startMaterialCameraActivity((Activity) context, _pathforcheck, CommonString1.FILE_PATH);
        }
    }

    private class MyAdaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return secPlaceData.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("NewApi")
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.competition_item, null);

                holder.tvCOMPANY = (TextView) convertView.findViewById(R.id.txt_cAMPANY);
                holder.TViMAGE = (TextView) convertView.findViewById(R.id.txt_iMAGE);
                holder.TVremark = (TextView) convertView.findViewById(R.id.txt_remark);
                holder.delRow = (ImageView) convertView.findViewById(R.id.imgDelRow);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvCOMPANY.setText(secPlaceData.get(position).getCOMPANY());
            holder.TViMAGE.setText(secPlaceData.get(position).getImage());
            holder.TVremark.setText(secPlaceData.get(position).getEdText());


            holder.delRow.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to Delete")
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {

                                            String listid = secPlaceData.get(position).getID();
                                            db.RemoveCOM(listid);
                                            notifyDataSetChanged();
                                            secPlaceData.remove(position);
                                            notifyDataSetChanged();

                                            if (secPlaceData.size() == 0) {
                                                spinner.setEnabled(true);
                                                spinner_exists.setEnabled(true);
                                                Editpro.setEnabled(true);
                                                addbtn.setEnabled(true);
                                            }

                                            list2.setAdapter(new MyAdaptor());
                                            list2.invalidateViews();
                                        }
                                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });


            holder.tvCOMPANY.setId(position);
            holder.TViMAGE.setId(position);
            holder.TVremark.setId(position);
            holder.delRow.setId(position);

            if (!checkflag) {
                boolean tempflag = false;
                if (tempflag) {
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
                }
            }
            return convertView;
        }
    }


    private class ViewHolder {
        TextView tvCOMPANY, TViMAGE, TVremark;
        ImageView delRow;
        ImageView camraImage;
        CardView cardView;
    }


    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());
        return cdate;
    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (ischangedflag) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(CommonString1.ONBACK_ALERT_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.empty_menu, menu);
        return true;
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


    public long checkMid() {
        return db.CheckMid(visit_date, store_cd);
    }

    public long getMid() {
        long mid = 0;
        mid = checkMid();
        if (mid == 0) {
            CoverageBean cdata = new CoverageBean();
            cdata.setStoreId(store_cd);
            cdata.setVisitDate(visit_date);
            cdata.setUserId(username);
            cdata.setInTime(intime);
            cdata.setOutTime(getCurrentTime());
            cdata.setReason("");
            cdata.setReasonid("0");
            cdata.setLatitude("0.0");
            cdata.setLongitude("0.0");
            mid = db.InsertCoverageData(cdata);
        }
        return mid;
    }

    public boolean validate() {

        boolean flag = true;
        if (spinner_exists.getSelectedItem().toString().equalsIgnoreCase("YES")) {
            if (((spinner.getSelectedItem().toString().equalsIgnoreCase("Select COMPETITOR")))) {
                Error_Message = "Please select Competitor Name from dropdown list";
                flag = false;
            } else if (img1.equals("")) {
                imagebutton.setBackgroundResource(R.drawable.camera_not_done);
                String validateYesNo = spinner.getSelectedItem().toString();
                Error_Message = "Please take " + validateYesNo + " image";
                flag = false;
            } else if (Editpro.getText().toString().equals("")) {
                Editpro.setHint("EMPTY");
                Editpro.setHintTextColor(getResources().getColor(R.color.red));
                Error_Message = "Please fill Remarks";
                flag = false;
            }
        } else if (((spinner_exists.getSelectedItem().toString().equalsIgnoreCase("Select Exists")))) {
            Error_Message = "Please select Competitor Exists from dropdown list";
            flag = false;
        } else {
            Error_Message = "window can't be add at option 'No'.You can save directly";
            flag = false;
        }
        return flag;
    }

    public boolean validate_save() {

        boolean flag = true;
        if (spinner_exists.getSelectedItem().toString().equalsIgnoreCase("NO")) {
            flag = true;
        } else if (secPlaceData.size() == 0) {
            Error_Message = "Please add the data first";
            flag = false;
        }
        return flag;
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spin_exists) {
            String window = spinner_exists.getSelectedItem().toString();
            if (position != 0) {
                if (window.equalsIgnoreCase("Select COMPETITOR")) {
                    cameralayout.setVisibility(View.GONE);
                    competitorLLayout.setVisibility(View.GONE);
                    remark_ll.setVisibility(View.GONE);
                    img1 = "";
                    imagebutton.setBackgroundResource(R.drawable.camera);
                } else if (window.equalsIgnoreCase("NO")) {
                    cameralayout.setVisibility(View.GONE);
                    competitorLLayout.setVisibility(View.GONE);
                    remark_ll.setVisibility(View.GONE);
                    img1 = "";
                    imagebutton.setBackgroundResource(R.drawable.camera);
                    secPlaceData.clear();
                    setAddedDataToListView(secPlaceData);
                } else {
                    cameralayout.setVisibility(View.VISIBLE);
                    competitorLLayout.setVisibility(View.VISIBLE);
                    remark_ll.setVisibility(View.VISIBLE);
                }
            }
        } else if (parent.getId() == R.id.spin_category) {
            if (position != 0) {
                company_cd = categorylist.get(position - 1).getCOMPANY_CD();
                company = categorylist.get(position - 1).getCOMPANY();
            } else {
                company_cd = "0";
                company = "";
            }
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    protected void startCameraActivity() {

        try {
            Log.i("MakeMachine", "startCameraActivity()");
            File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(intent, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;
            case -1:
                if (_pathforcheck != null && !_pathforcheck.equals("")) {
                    if (new File(str + _pathforcheck).exists()) {
                        imagebutton.setBackgroundResource(R.drawable.camera_done);
                        String metadata = CommonFunctions.getMetadataAtImagesFromPref(metadata_global, "Competitor Image");
                        CommonFunctions.addMetadataAndTimeStampToImage(context, str + _pathforcheck, metadata);
                        img1 = _pathforcheck;
                        _pathforcheck = "";
                    }
                }
                break;
        }

    }

    void declaration() {
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentapiVersion = android.os.Build.VERSION.SDK_INT;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_exists = (Spinner) findViewById(R.id.spin_exists);
        spinner = (Spinner) findViewById(R.id.spin_category);
        Editpro = (EditText) findViewById(R.id.et_quantity);
        imagebutton = (ImageView) findViewById(R.id.imageButton);
        cameralayout = (LinearLayout) findViewById(R.id.cameralayout);
        competitorLLayout = (LinearLayout) findViewById(R.id.competitor_name_ll);
        remark_ll = (LinearLayout) findViewById(R.id.remark_ll);
        //savebtn = (Button) findViewById(R.id.btn_Save);
        addbtn = (Button) findViewById(R.id.btn_Add);
        save_btn = (Button) findViewById(R.id.save_btn);
        list2 = (ListView) findViewById(R.id.listView1);
        str = CommonString1.FILE_PATH;
        db = new GSKDatabase(getApplicationContext());
        db.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, "");
        visit_date = preferences.getString(CommonString1.KEY_VISIT_DATE, "");
        username = preferences.getString(CommonString1.KEY_USERNAME, "");
        intime = preferences.getString(CommonString1.KEY_STORE_IN_TIME, "");
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");

        spinner_exists.setOnItemSelectedListener(this);
        addbtn.setOnClickListener(this);
        save_btn.setOnClickListener(this);
        imagebutton.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
    }

}
