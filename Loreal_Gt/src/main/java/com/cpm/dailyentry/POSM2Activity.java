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
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.NonPOSMReasonGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.POSMDATAGetterSetter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class POSM2Activity extends AppCompatActivity implements View.OnClickListener {

    GSKDatabase db;
    SharedPreferences preferences;
    String store_cd, visit_date, username, STORE_TYPE_CD, STATE_CD, metadata_global;
    ArrayList<POSMDATAGetterSetter> datalist;
    Context context;
    FloatingActionButton fab;
    RecyclerView posmRecyclerView;
    String _pathforcheck, _path, img1 = "";
    int global_position = 0;
    POSMListAdapter adapter;
    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posm2);
        declaration();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        visit_date = preferences.getString(CommonString1.KEY_VISIT_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        STORE_TYPE_CD = preferences.getString(CommonString1.KEY_STORE_TYPE_CD, "");
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");
        STATE_CD = preferences.getString(CommonString1.KEY_STATE_CD, "");
        fab.setOnClickListener(this);
        preparelist();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                if (validate()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Are you sure you want to save")
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {

                                            db.open();
                                            //db.deletePromotionData(store_cd);
                                            long id1 = db.insertPOSM2Data(store_cd, datalist);
                                            if (id1 > 0) {
                                                Toast.makeText(getApplicationContext(),
                                                        "Data has been saved", Toast.LENGTH_SHORT).show();
                                            } else {
                                                AlertMessage.showToastMessage(getApplicationContext(), "Error in Data Saving");
                                            }
                                            finish();
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
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }

                break;
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


    void preparelist() {

        datalist = db.getPOSM2data(store_cd);
        if (datalist.size() == 0) {
            datalist = db.getPOSMDATA(store_cd, STORE_TYPE_CD);
        }

        adapter = new POSMListAdapter(context, datalist);
        posmRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        posmRecyclerView.setAdapter(adapter);

    }


    class POSMListAdapter extends RecyclerView.Adapter<POSM2Activity.ViewHolder> {

        LayoutInflater inflater;
        ArrayList<POSMDATAGetterSetter> posmList;

        POSMListAdapter(Context context, ArrayList<POSMDATAGetterSetter> posmList) {
            inflater = LayoutInflater.from(context);
            this.posmList = posmList;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.item_posm_list_2, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            if (holder != null) {
                final POSMDATAGetterSetter posmdata = posmList.get(position);
                holder.textView.setText(posmList.get(position).getPOSM().get(0));

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line);
                final ArrayList<NonPOSMReasonGetterSetter> list = db.getNonPosmReasonData();
                for (int i = 0; i < list.size(); i++) {
                    arrayAdapter.add(list.get(i).getPREASON().get(0));
                }
                holder.spinner.setAdapter(arrayAdapter);

                holder.aSwitch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((ToggleButton) v).isChecked()) {
                            posmdata.setExist(true);
                            holder.ll_image.setVisibility(View.VISIBLE);
                            holder.ll_reason.setVisibility(View.GONE);
                            posmdata.setReason_CD("-1");
                            posmdata.setReason("");
                        } else {
                            posmdata.setExist(false);
                            holder.ll_image.setVisibility(View.GONE);
                            holder.ll_reason.setVisibility(View.VISIBLE);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

                holder.imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        _pathforcheck = store_cd + "Posmimage" + visit_date.replace("/", "") + getCurrentTime().replace(":", "") + ".jpg";
                        _path = CommonString1.FILE_PATH + _pathforcheck;
                        global_position = position;
                        CommonFunctions.startAnncaCameraActivity(context, _path);
                        //startCameraActivity();
                        //CommonFunctions.startMaterialCameraActivity((Activity) context, _pathforcheck, CommonString1.FILE_PATH);

                    }
                });

                holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        posmdata.setReason_CD(list.get(position).getPREASON_CD().get(0));
                        posmdata.setReason(list.get(position).getPREASON().get(0));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                if (!img1.equalsIgnoreCase("") && global_position == position) {
                    posmdata.setImage_Url(img1);
                    img1 = "";
                }


                if (posmdata.isExist()) {
                    holder.aSwitch.setChecked(true);
                    holder.ll_image.setVisibility(View.VISIBLE);
                    holder.ll_reason.setVisibility(View.GONE);
                    if (posmdata.getImage_Url().equalsIgnoreCase("")) {
                        holder.imageButton.setBackgroundResource(R.drawable.camera);
                    } else {
                        holder.imageButton.setBackgroundResource(R.drawable.camera_green);
                    }

                } else {
                    holder.aSwitch.setChecked(false);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPREASON_CD().get(0).equalsIgnoreCase(posmdata.getReason_CD())) {
                            holder.spinner.setSelection(i);
                            break;
                        }
                    }
                    holder.ll_image.setVisibility(View.GONE);
                    holder.ll_reason.setVisibility(View.VISIBLE);
                    if (posmdata.getImage_Url() != null && !posmdata.getImage_Url().equalsIgnoreCase("")) {
                        if (new File(CommonString1.FILE_PATH + posmdata.getImage_Url()).exists()) {
                            new File(CommonString1.FILE_PATH + posmdata.getImage_Url()).delete();
                        }
                        posmdata.setImage_Url("");
                    }

                }

            }
        }

        @Override
        public int getItemCount() {
            return posmList.size();
        }


    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageButton imageButton;
        Spinner spinner;
        ToggleButton aSwitch;
        LinearLayout ll_image, ll_reason;

        ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.txt_posm);
            imageButton = (ImageButton) v.findViewById(R.id.cameraImage);
            spinner = (Spinner) v.findViewById(R.id.reasonSpinner);
            aSwitch = (ToggleButton) v.findViewById(R.id.switch_exists);
            ll_image = (LinearLayout) v.findViewById(R.id.ll_image);
            ll_reason = (LinearLayout) v.findViewById(R.id.ll_reason);
        }
    }

    void declaration() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        posmRecyclerView = (RecyclerView) findViewById(R.id.posmRecyclerList);
        context = this;
        db = new GSKDatabase(getApplicationContext());
        db.open();

    }

    boolean validate() {
        boolean isValid = true;
        for (int i = 0; i < datalist.size(); i++) {
            POSMDATAGetterSetter data = datalist.get(i);
            if (data.isExist()) {
                if (data.getImage_Url().equalsIgnoreCase("")) {
                    isValid = false;
                    msg = "Please click image";
                    break;
                }
            } else {
                if (data.getReason_CD().equalsIgnoreCase("-1")) {
                    isValid = false;
                    msg = "Please select reason";
                    break;
                }
            }
        }

        return isValid;
    }

    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());

        return cdate;

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
                    if (new File(CommonString1.FILE_PATH + _pathforcheck).exists()) {

                        //  imagebutton.setBackgroundResource(R.drawable.camera_done);
                        String metadata = CommonFunctions.getMetadataAtImagesFromPref(metadata_global, "Posm Image");
                        CommonFunctions.addMetadataAndTimeStampToImage(context, CommonString1.FILE_PATH + _pathforcheck, metadata);
                        img1 = _pathforcheck;
                        _pathforcheck = "";
                        //posmRecyclerView.invalidate();
                        adapter.notifyDataSetChanged();
                        //	Toast.makeText(getApplicationContext(), ""+image1, Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
