package com.cpm.dailyentry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.DisplayMasterGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;
import com.cpm.utilities.CommonFunctions;

import java.io.File;
import java.util.ArrayList;


public class PaidVisibilityActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GSKDatabase db;
    ArrayList<DisplayMasterGetterSetter> displayList;
    String store_cd, visitDate, metadata_global;
    SharedPreferences preferences;
    ValueAdapter adapter;
    Context context;
    Activity activity;
    String _pathforcheck, intime, path, image = "";
    int global_position = 0;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_visibility);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Paid Visibility");

        declaration();
        prepareList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            recyclerView.clearFocus();
            if (validate()) {
                long id = db.insertPaidVisibilityData(store_cd, displayList);
                if (id > 0) {
                    AlertMessage.showToastMessage(context, "Data Saved");
                    finish();
                } else {
                    AlertMessage.showToastMessage(context, "Data Not Saved");
                }
            }
            }
        });
    }

    void declaration() {
        context = this;
        activity = this;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        visitDate = preferences.getString(CommonString1.KEY_VISIT_DATE, null);
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        db = new GSKDatabase(context);
        db.open();
    }

    void prepareList() {

        displayList = db.getSavedDisplayDataFromStore(store_cd);
        if (displayList.size() == 0) {
            displayList = db.getDisplayDataFromStore(store_cd);
        }
        adapter = new ValueAdapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    boolean validate() {
        boolean isValidate = true;
        for (int i = 0; i < displayList.size(); i++) {
            if (displayList.get(i).getExist()) {
                if (displayList.get(i).getImage().equalsIgnoreCase("")) {
                    AlertMessage.showToastMessage(context, "Please click image");
                    isValidate = false;
                    break;
                }
            } else {
                if (displayList.get(i).getRemark().equalsIgnoreCase("")) {
                    AlertMessage.showToastMessage(context, "Please fill remark");
                    isValidate = false;
                    break;
                }
            }
        }
        return isValidate;
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


    public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.ViewHolder> {
        LayoutInflater inflater;

        ValueAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_paid_visibility, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.textView.setText(displayList.get(position).getDISPLAY().get(0));
            if (displayList.get(position).getImage().equalsIgnoreCase("")) {
                holder.camerabtn.setBackgroundResource(R.drawable.camera);
            } else {
                holder.camerabtn.setBackgroundResource(R.drawable.camera_done);
            }

            holder.camerabtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intime = CommonFunctions.getCurrentTime();
                    global_position = position;
                    _pathforcheck = store_cd + "_" + "PaidVisibility_" + position + "_" + visitDate.replace("/", "") + "_" + intime.replace(":", "") + ".jpg";
                    path = CommonString1.FILE_PATH + _pathforcheck;
                    //CommonFunctions.startCameraActivity(activity, path);
                    CommonFunctions.startAnncaCameraActivity(context, path);
                    //CommonFunctions.startMaterialCameraActivity((Activity) context, _pathforcheck, CommonString1.FILE_PATH);
                }
            });

            holder.isExist_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (buttonView.isChecked()) {
                        displayList.get(position).setExist(buttonView.isChecked());
                        holder.camerabtn.setVisibility(View.VISIBLE);
                        holder.remark_edt.setVisibility(View.GONE);
                        holder.remark_edt.setText("");

                        if (displayList.get(position).getImage().equalsIgnoreCase("")) {
                            holder.camerabtn.setBackgroundResource(R.drawable.camera);
                        } else {
                            holder.camerabtn.setBackgroundResource(R.drawable.camera_done);
                        }
                    } else {
                        displayList.get(position).setExist(buttonView.isChecked());
                        if (displayList.get(position).getImage() != null && !displayList.get(position).getImage().equalsIgnoreCase("")) {
                            new File(CommonString1.FILE_PATH + displayList.get(position).getImage()).delete();
                        }

                        displayList.get(position).setImage("");
                        holder.camerabtn.setVisibility(View.GONE);
                        holder.remark_edt.setVisibility(View.VISIBLE);
                    }
                }
            });

            holder.remark_edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    displayList.get(position).setRemark(((EditText) v).getText().toString().replaceAll("[&^<>{}'$]", ""));
                }
            });


            if(displayList.get(position).getExist()) {
                holder.camerabtn.setVisibility(View.VISIBLE);
                holder.remark_edt.setVisibility(View.GONE);
                holder.remark_edt.setText("");
                holder.isExist_switch.setChecked(displayList.get(position).getExist());
            } else {
                holder.isExist_switch.setChecked(displayList.get(position).getExist());
                if (displayList.get(position).getImage() != null && !displayList.get(position).getImage().equalsIgnoreCase("")) {
                    new File(CommonString1.FILE_PATH + displayList.get(position).getImage()).delete();
                }
                displayList.get(position).setImage("");
                holder.camerabtn.setVisibility(View.GONE);
                holder.remark_edt.setVisibility(View.VISIBLE);
                holder.remark_edt.setText(displayList.get(position).getRemark());
            }
        }


        @Override
        public int getItemCount() {
            return displayList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            ToggleButton isExist_switch;
            ImageButton camerabtn;
            EditText remark_edt;
            LinearLayout cameraRemarkLL;

            ViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.textview);
                isExist_switch = (ToggleButton) view.findViewById(R.id.isExist_switch);
                camerabtn = (ImageButton) view.findViewById(R.id.camerabtn);
                remark_edt = (EditText) view.findViewById(R.id.remark_edt);
                cameraRemarkLL = (LinearLayout) view.findViewById(R.id.cameraRemarkLL);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CommonString1.CAPTURE_MEDIA) {
            switch (resultCode) {
                case 0:
                    Log.i("MakeMachine", "User cancelled");
                    break;

                case -1:

                    if (_pathforcheck != null && !_pathforcheck.equals("")) {
                        if (new File(CommonString1.FILE_PATH + _pathforcheck).exists()) {
                            image = _pathforcheck;
                            displayList.get(global_position).setImage(image);
                            adapter.notifyDataSetChanged();
                            String metadata = CommonFunctions.getMetadataAtImagesFromPref(metadata_global, "Paid Visibility");
                            CommonFunctions.addMetadataAndTimeStampToImage(context, CommonString1.FILE_PATH + _pathforcheck, metadata);
                            _pathforcheck = "";
                            image = "";
                        }
                    }

                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
