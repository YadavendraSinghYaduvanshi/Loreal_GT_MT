package com.cpm.dailyentry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.message.AlertMessage;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.MappingAssetGetterSetter;
import com.cpm.xmlGetterSetter.POSMDATAGetterSetter;
import com.cpm.xmlGetterSetter.windowsChildData;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//import com.cpm.xmlGetterSetter.StockNewGetterSetter;
//import com.cpm.xmlGetterSetter.PromotionInsertDataGetterSetter;
//import com.cpm.xmlGetterSetter.StockNewGetterSetter;

public class POSMActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemSelectedListener {

    boolean checkflag = true;
    List<Integer> checkHeaderArray = new ArrayList<Integer>();
    Context context;
    Button btnSave;
    ListView listView02;
    List<windowsChildData> listDataHeader;
    HashMap<windowsChildData, List<windowsChildData>> listDataChild;
    ArrayList<windowsChildData> brandData;
    ArrayList<windowsChildData> skuData;
    boolean update_flag = false;
    ArrayList<MappingAssetGetterSetter> checkList;
    String strmsg = "";
    //ArrayList<MappingAssetGetterSetter> checklistInsertDataGetterSetters=new MappingAssetGetterSetter();
    String validateYesNo;
    List<MappingAssetGetterSetter> checklistInsertDataGetterSetters = new ArrayList<MappingAssetGetterSetter>();
    List<POSMDATAGetterSetter> checkAddData = new ArrayList<POSMDATAGetterSetter>();
    Snackbar snackbar;
    private ArrayList<POSMDATAGetterSetter> list = new ArrayList<POSMDATAGetterSetter>();
    ArrayList<POSMDATAGetterSetter> secPlaceData = new ArrayList<POSMDATAGetterSetter>();
    List<POSMDATAGetterSetter> datalist = new ArrayList<POSMDATAGetterSetter>();
    GSKDatabase db;
    String store_cd, visit_date, username, intime, WINDOWS_CD;
    private SharedPreferences preferences;

    ImageView img;

    boolean ischangedflag = false;
    String Error_Message;
    String _pathforcheck, _path, str;
    Spinner spinner;
    private String image1 = "";
    String reason_id;
    String img1 = "";
    static int grp_position = -1;
    static int child_position = -1;
    ArrayAdapter<String> adapter;
    List<String> spinnerdatalist = new ArrayList<String>();
    String a1, STATE_CD, STORE_TYPE_CD, categoryspinervalue, metadata_global;
    List<windowsChildData> skulist;
    String str1;
    EditText Editpro;
    ListView list2;
    ImageView imagebutton;

    private Button savebtn, addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.posm_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = new GSKDatabase(getApplicationContext());
        db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        visit_date = preferences.getString(CommonString1.KEY_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        intime = db.getCoverageSpecificData(store_cd).get(0).getInTime();
        STORE_TYPE_CD = preferences.getString(CommonString1.KEY_STORE_TYPE_CD, "");
        STATE_CD = preferences.getString(CommonString1.KEY_STATE_CD, "");
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");
        //CATEGORY_CD= preferences.getString(CommonString1.KEY_CATEGORY_CD, "");

        str = CommonString1.FILE_PATH;

        //prepareListData();

        spinner = (Spinner) findViewById(R.id.spin_category);
        Editpro = (EditText) findViewById(R.id.et_quantity);

        imagebutton = (ImageView) findViewById(R.id.imageButton);

        //savebtn = (Button) findViewById(R.id.btn_Save);
        addbtn = (Button) findViewById(R.id.btn_Add);
        list2 = (ListView) findViewById(R.id.listView1);

        datalist = db.getPOSMDATA(store_cd, STORE_TYPE_CD);

        a1 = "Select POSM";
        spinnerdatalist.add(a1);

        for (int i = 0; i < datalist.size(); i++) {
            spinnerdatalist.add(datalist.get(i).getPOSM().get(0));

        }

        adapter = new ArrayAdapter<String>(POSMActivity.this, android.R.layout.simple_spinner_item, spinnerdatalist);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(POSMActivity.this);

        addbtn.setOnClickListener(this);

        imagebutton.setOnClickListener(this);


        setDataToListView();

    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();

        if (id == R.id.btn_Add) {
            list2.clearFocus();

            list2.invalidateViews();

            if (validation()) {
                //if (str1!="") {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to save")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {

                                        db.open();


                                        POSMDATAGetterSetter secdata = new POSMDATAGetterSetter();


                                        categoryspinervalue = spinner.getSelectedItem().toString();

                                        secdata.setPOSM(spinner.getSelectedItem().toString());


                                        long str = spinner.getSelectedItemId();

                                        String strLong = Long.toString(str);

                                        secdata.setPOSM_CD(strLong);

                                        secdata.setImage_Url(img1);

                                        secdata.setEdText(Editpro.getText().toString().replaceAll("[-@.?/|=+_#%:;^*()!&^<>{},'$0]", ""));

                                        //(Editpro.getText().toString().replaceAll("[-@.?/|=+_#%:;^*()!&^<>{},'$1234567890]);
                                        secPlaceData.add(secdata);

                                        getMid();
                                        //db.deletePromotionData(store_cd);
                                        long id1 = db.insertPOSM(store_cd, secPlaceData, list, img1);
                                        if (id1 > 0) {
                                            Toast.makeText(getApplicationContext(), "Data has been saved", Toast.LENGTH_SHORT).show();
                                        } else {
                                            AlertMessage.showToastMessage(getApplicationContext(), "Error in Data Saving");
                                        }

                                        Editpro.setText("");
                                        Editpro.setHint("");
                                        img1 = "";

                                        imagebutton.setBackgroundResource(R.drawable.camera);
                                        spinner.setSelection(0);
                                        secPlaceData.clear();
                                        list.clear();
                                        setDataToListView();
                                        //	finish();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();

                alert.show();

            } else {
                //listAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), Error_Message, Toast.LENGTH_SHORT).show();
            }
        }
        //}

        if (id == R.id.imageButton) {
            _pathforcheck = store_cd + "Posmimage" + visit_date.replace("/", "") + getCurrentTime().replace(":", "") + ".jpg";
            _path = CommonString1.FILE_PATH + _pathforcheck;
            //startCameraActivity();
            CommonFunctions.startAnncaCameraActivity(context, _path);
            //CommonFunctions.startMaterialCameraActivity((Activity) context, _pathforcheck, CommonString1.FILE_PATH);
        }


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
            cdata.setStatus(CommonString1.KEY_CHECK_IN);
            mid = db.InsertCoverageData(cdata);

        }

        return mid;
    }


    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());

       /* String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);*/

        return cdate;

    }


    @Override
    public void onBackPressed() {
        /*Intent i = new Intent(this, StoreEntry.class);
        startActivity(i);*/

        if (ischangedflag) {

            AlertDialog.Builder builder = new AlertDialog.Builder(
                    POSMActivity.this);
            builder.setMessage(CommonString1.ONBACK_ALERT_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {

                                    finish();

                                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
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

    protected void startCameraActivity() {

        try {
            Log.i("MakeMachine", "startCameraActivity()");
            File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);

            Intent intent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

            startActivityForResult(intent, 0);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;

            case -1:

                if (_pathforcheck != null && !_pathforcheck.equals("")) {
                    if (new File(str + _pathforcheck).exists()) {

                        imagebutton.setBackgroundResource(R.drawable.camera_done);
                        String metadata = CommonFunctions.getMetadataAtImagesFromPref(metadata_global, "Posm Image");
                        CommonFunctions.addMetadataAndTimeStampToImage(context, str + _pathforcheck, metadata);
                        image1 = _pathforcheck;
                        img1 = _pathforcheck;

                        _pathforcheck = "";
                        //	Toast.makeText(getApplicationContext(), ""+image1, Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }


    public void onItemSelected(AdapterView<?> arg0, View v, int position,
                               long arg3) {


        switch (arg0.getId()) {

            case R.id.spin_category:

                validateYesNo = spinner.getSelectedItem().toString();


                if ((position != 0)) {

                    POSMDATAGetterSetter secdata = new POSMDATAGetterSetter();


                    //categoryspinerid=


                    //secdata.setCategory_id();
                    //secdata.setCategory_name(categoryspinervalue);

                    //secPlaceData.add(secdata);


                }
                break;


        }


    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    public void setDataToListView() {
        try {


            list = db.getPOSMData(store_cd);

            if (list.size() > 0) {

                for (int i = 0; i < list.size(); i++) {
                    POSMDATAGetterSetter secdata = new POSMDATAGetterSetter();


                    secdata.setEdText(list.get(i).getEdText());

                    secdata.setPOSM_CD(list.get(i).getPOSM_CD().get(0));

                    secdata.setPOSM(list.get(i).getPOSM().get(0));

                    secdata.setImage_Url(list.get(i).getImage_Url());


                    secdata.setID(list.get(i).getID());

                    secPlaceData.add(secdata);


                }


                Collections.reverse(secPlaceData);
                list2.setAdapter(new MyAdapter());
                list2.invalidateViews();


            }


			/*if(list.size()>=0){

				list=db.getPOSM(store_cd);

			}

			else if(list.size()==0)
			{
				list=db.deleteTable(store_cd,CATEGORY_CD);
			}


			else{
*/                //savebtn.setText("Update");
            //}
        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
        }

    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return secPlaceData.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }


        public View getView(final int position, View convertView,
                            final ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater
                        .inflate(R.layout.secondaryplac_mt_adapter, null);

                holder.POSMName = (TextView) convertView.findViewById(R.id.txt_category);
                //	holder.brandname = (TextView) convertView.findViewById(R.id.txt_brand);
                holder.ImageURL = (TextView) convertView.findViewById(R.id.txt_quantity);
                holder.Editno = (TextView) convertView.findViewById(R.id.txt_remark);
                holder.delRow = (ImageView) convertView.findViewById(R.id.imgDelRow);


                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.POSMName.setText(secPlaceData.get(position).getPOSM().get(0));

            holder.ImageURL.setText(secPlaceData.get(position).getImage_Url());
            holder.Editno.setText(secPlaceData.get(position).getEdText());


            holder.delRow.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(POSMActivity.this);
                    builder.setMessage("Are you sure you want to Delete")
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {

                                            String listid = secPlaceData.get(position).getID();


							/*final CrownOtherPromotioanlGetterSetter user=secPlaceData.get(position);

							final String	user_id=user.getID();*/


                                            db.remove(listid);
                                            notifyDataSetChanged();
                                            secPlaceData.remove(position);
                                            notifyDataSetChanged();


                                            if (secPlaceData.size() == 0) {

                                                spinner.setEnabled(true);

                                                Editpro.setEnabled(true);
                                                addbtn.setEnabled(true);

                                            }

                                            list2.setAdapter(new MyAdapter());
                                            list2.invalidateViews();


                                        }
                                    })
                            .setNegativeButton("No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();

                    alert.show();


                }
            });


            //holder.Models.setText(salesData.get(position).getProduct());
            //holder.Type.setText(salesData.get(position).getType());
            //holder.unitsold.setText(salesData.get(position).getUnitSold());

            holder.POSMName.setId(position);
            //holder.brandname.setId(position);
            holder.ImageURL.setId(position);
            holder.Editno.setId(position);
            holder.delRow.setId(position);

            return convertView;
        }

        private class ViewHolder {

            TextView POSMName, brandname, ImageURL, Editno;
            ImageView delRow;


        }


    }


    boolean validation()

    {
        boolean flag = true;
        if (((spinner.getSelectedItem().toString().equalsIgnoreCase("Select POSM")))) {
            //Toast.makeText(getApplicationContext(), "Please select ImageType from dropdown list", Toast.LENGTH_SHORT).show();

            Error_Message = "Please select POSM Name from dropdown list";

            flag = false;

        } else if (img1.equals("")) {

            imagebutton.setBackgroundResource(R.drawable.camera_not_done);
            validateYesNo = spinner.getSelectedItem().toString();
            Error_Message = "Please take " + validateYesNo + " image";
            flag = false;

        } else if (Editpro.getText().toString().equals("0")) {


            Editpro.setHintTextColor(getResources().getColor(R.color.red));
            Error_Message = "Number Should be Greater than Zero";
            flag = false;

        } else if (Editpro.getText().toString().equals("")) {
            Editpro.setHint("EMPTY");
            Editpro.setHintTextColor(getResources().getColor(R.color.red));
            Error_Message = "Please Fill Data";
            flag = false;
        } else {
            flag = true;
        }
        return flag;

    }


}
