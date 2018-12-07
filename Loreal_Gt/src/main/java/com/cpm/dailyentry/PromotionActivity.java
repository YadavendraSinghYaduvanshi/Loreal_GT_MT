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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.MappingPromotion2GetterSetter;
import com.cpm.GetterSetter.NonPromotionReasonGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;
import com.cpm.utilities.CommonFunctions;

import java.io.File;
import java.util.ArrayList;

public class PromotionActivity extends AppCompatActivity {

    GSKDatabase db;
    private SharedPreferences preferences;
    String store_cd, metadata_global;

    String visit_date, username;

    ArrayList<MappingPromotion2GetterSetter> mappingPromotion;
    ArrayList<NonPromotionReasonGetterSetter> nonPromotionReason;

    String _pathforcheck, intime, path, image = "";
    int global_position = 0;

    Context context;
    Activity activity;
    ValueAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        context = this;
        activity = this;

        db = new GSKDatabase(getApplicationContext());
        db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");
        visit_date = preferences.getString(CommonString1.KEY_VISIT_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);

        mappingPromotion = db.getPromotionInsertedData(store_cd);
        if (mappingPromotion.size() == 0) {
            mappingPromotion = db.getMapppingPromotionData(store_cd);
        }

        nonPromotionReason = db.getNonPromotionReasonData();
        NonPromotionReasonGetterSetter nonPromotionReasonGetterSetter = new NonPromotionReasonGetterSetter();
        nonPromotionReasonGetterSetter.setPREASON("Select Reason");
        nonPromotionReasonGetterSetter.setPREASON_CD("0");
        nonPromotionReason.set(0, nonPromotionReasonGetterSetter);

        if (mappingPromotion.size() > 0) {
            adapter = new ValueAdapter(context, mappingPromotion);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    recyclerView.invalidate();
                    recyclerView.clearFocus();
                }
            });

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    long id = db.insertPromotionData(store_cd, mappingPromotion);
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

    private class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.ViewHolder> {
        private LayoutInflater inflator;
        ArrayList<MappingPromotion2GetterSetter> data = new ArrayList<>();

        public ValueAdapter(Context context, ArrayList<MappingPromotion2GetterSetter> data) {
            inflator = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public ValueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = inflator.inflate(R.layout.item_promotion_mapping, parent, false);
            ValueAdapter.ViewHolder holder = new ValueAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ValueAdapter.ViewHolder holder, final int position) {

            final MappingPromotion2GetterSetter mappingPromo = mappingPromotion.get(position);

            holder.textView.setText(mappingPromo.getPROMO().get(0));

            ArrayAdapter<String> companyAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
            for (int i = 0; i < nonPromotionReason.size(); i++) {
                companyAdapter.add(nonPromotionReason.get(i).getPREASON().get(0));
            }
            companyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spin_remark.setAdapter(companyAdapter);

            holder.spin_remark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0) {
                        mappingPromo.setReason(nonPromotionReason.get(position).getPREASON().get(0));
                        mappingPromo.setReason_cd(nonPromotionReason.get(position).getPREASON_CD().get(0));
                    } else {
                        mappingPromo.setReason_cd("0");
                        mappingPromo.setReason("");
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            if (mappingPromo.getImage().equalsIgnoreCase("")) {
                holder.camerabtn.setBackgroundResource(R.drawable.camera);
            } else {
                holder.camerabtn.setBackgroundResource(R.drawable.camera_done);
            }

            holder.camerabtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intime = CommonFunctions.getCurrentTime();
                    global_position = position;
                    _pathforcheck = store_cd + "_" + "Promotion_" + mappingPromo.getID().get(0) + "_" + visit_date.replace("/", "") + "_" + intime.replace(":", "") + ".jpg";
                    path = CommonString1.FILE_PATH + _pathforcheck;
                    //CommonFunctions.startCameraActivity(activity, path);
                    CommonFunctions.startAnncaCameraActivity(context, path);
                    //CommonFunctions.startMaterialCameraActivity((Activity) context, _pathforcheck, CommonString1.FILE_PATH);
                }
            });

            holder.isExist_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mappingPromo.setExist(isChecked);
                        holder.camerabtn.setVisibility(View.VISIBLE);
                        holder.spin_remark.setVisibility(View.GONE);
                        mappingPromo.setReason("");
                        mappingPromo.setReason_cd("0");
                        //adapter.notifyDataSetChanged();

                    } else {
                        mappingPromo.setExist(isChecked);
                        holder.camerabtn.setVisibility(View.GONE);
                        holder.spin_remark.setVisibility(View.VISIBLE);
                        if (mappingPromo.getImage() != null && !mappingPromo.getImage().equalsIgnoreCase("")) {
                            new File(CommonString1.FILE_PATH + mappingPromo.getImage()).delete();
                        }

                        mappingPromo.setImage("");
                        //adapter.notifyDataSetChanged();
                    }
                    //recyclerView.invalidate();
                }
            });

            int pos = 0;
            for (int i = 0; i < nonPromotionReason.size(); i++) {
                if (mappingPromo.getReason_cd().equals(nonPromotionReason.get(i).getPREASON_CD().get(0))) {
                    pos = i;
                    break;
                }
            }

            if (mappingPromo.isExist()) {
                holder.camerabtn.setVisibility(View.VISIBLE);
                holder.spin_remark.setVisibility(View.GONE);
            } else {
                holder.camerabtn.setVisibility(View.GONE);
                holder.spin_remark.setVisibility(View.VISIBLE);
            }
            holder.isExist_switch.setChecked(mappingPromo.isExist());
            holder.spin_remark.setSelection(pos);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            ToggleButton isExist_switch;
            ImageButton camerabtn;
            Spinner spin_remark;
            //LinearLayout cameraRemarkLL;

            ViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.textview);
                isExist_switch = (ToggleButton) view.findViewById(R.id.isExist_switch);
                camerabtn = (ImageButton) view.findViewById(R.id.camerabtn);
                spin_remark = (Spinner) view.findViewById(R.id.spin_remark);
                //cameraRemarkLL = (LinearLayout) view.findViewById(R.id.cameraRemarkLL);
            }


        }
    }

    boolean validate() {
        boolean isValidate = true;
        for (int i = 0; i < mappingPromotion.size(); i++) {
            if (mappingPromotion.get(i).isExist()) {
                if (mappingPromotion.get(i).getImage().equalsIgnoreCase("")) {
                    AlertMessage.showToastMessage(context, "Please click image");
                    isValidate = false;
                    break;
                }
            } else {
                if (mappingPromotion.get(i).getReason().equalsIgnoreCase("")) {
                    AlertMessage.showToastMessage(context, "Please select a reason");
                    isValidate = false;
                    break;
                }
            }
        }

        return isValidate;
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
                            mappingPromotion.get(global_position).setImage(image);
                            String metadata = CommonFunctions.getMetadataAtImagesFromPref(metadata_global, "Promotion Image");
                            CommonFunctions.addMetadataAndTimeStampToImage(context, CommonString1.FILE_PATH + _pathforcheck, metadata);
                            adapter.notifyDataSetChanged();
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
