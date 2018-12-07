package com.cpm.dailyentry;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.AnswerChecklistGetterSetter;
import com.cpm.xmlGetterSetter.ChecklistGetterSetter;
import com.cpm.xmlGetterSetter.WindowListGetterSetter;
import com.cpm.xmlGetterSetter.WindowNonReasonGetterSetter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ChecklistActivity extends AppCompatActivity {

    RecyclerView rec_checklist;

    String window_cd, sku_hold, brand_cd, window_image = "";
    ChecklistAdapter adapter;
    Spinner reason_spinner;
    ToggleButton switch_exists;
    ImageButton image_window, refimage;
    LinearLayout lay_refimageCamera, lay_reason;
    GSKDatabase db;
    Context context;
    String _pathforcheck = "", _path = "", str, gallery_package, img1 = "";
    Uri outputFileUri;
    ArrayList<ChecklistGetterSetter> data;
    HashMap<ChecklistGetterSetter, ArrayList<AnswerChecklistGetterSetter>> listDataChild;
    ArrayAdapter reason_adapter;
    String store_cd, visit_date, username, metadata_global;
    String selectedAnswerId = "0";
    private SharedPreferences preferences;
    WindowListGetterSetter current;
    ArrayList<WindowNonReasonGetterSetter> reasondata;
    ArrayList<ChecklistGetterSetter> answered_list = new ArrayList<>();
    FloatingActionButton fab;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        declaration();
        reasondata = db.getWindowNonReasonData();
        reason_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        reason_adapter.add("Select Reason");
        for (int i = 0; i < reasondata.size(); i++) {
            reason_adapter.add(reasondata.get(i).getWREASON().get(0));
        }
        reason_spinner.setAdapter(reason_adapter);
        reason_spinner.setOnItemSelectedListener(onItemSelectedListener);
        image_window.setOnClickListener(onClickListener);
        refimage.setOnClickListener(onClickListener);

        switch_exists.setChecked(true);
        lay_reason.setVisibility(View.GONE);
        switch_exists.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lay_refimageCamera.setVisibility(View.VISIBLE);
                    lay_reason.setVisibility(View.GONE);
                    rec_checklist.setVisibility(View.VISIBLE);
                    selectedAnswerId = "0";
                    reason_spinner.setSelection(0);
                } else {
                    lay_refimageCamera.setVisibility(View.GONE);
                    lay_reason.setVisibility(View.VISIBLE);
                    rec_checklist.setVisibility(View.GONE);
                    if (!window_image.equalsIgnoreCase("")) {
                        if (new File(CommonString1.FILE_PATH + window_image).exists()) {
                            new File(CommonString1.FILE_PATH + window_image).delete();
                        }
                        window_image = "";
                    }
                    image_window.setBackgroundResource(R.drawable.camera_icon);

                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validatedata()) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ChecklistActivity.this);
                    builder1.setMessage(
                            "Are you sure you want to save your data?")
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        @SuppressWarnings("resource")
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            try {
                                                long commonid = db.InsertWindowsData(store_cd, visit_date, username, window_cd, switch_exists.isChecked(), window_image, selectedAnswerId);
                                                AlertMessage.showMessage("Data Saved", ChecklistActivity.this);
                                                if (switch_exists.isChecked()) {
                                                    db.InsertCheckListData(store_cd, username, window_cd, answered_list, commonid);
                                                }
                                                finish();
                                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }

                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert1 = builder1.create();
                    alert1.show();
                }
            }
        });

        data = db.getChecklistData(window_cd);

        listDataChild = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {

            ChecklistGetterSetter answered_temp = new ChecklistGetterSetter();
            answered_temp.setCHECKLIST_CD(data.get(i).getChecklist_cd());
            answered_temp.setANSWER_CD("0");
            answered_list.add(answered_temp);

            ArrayList<AnswerChecklistGetterSetter> ans = new ArrayList<AnswerChecklistGetterSetter>();
            ans = db.getChecklistAnswerData(data.get(i).getChecklist_cd());
            AnswerChecklistGetterSetter ans_temp = new AnswerChecklistGetterSetter();
            ans_temp.setAnswer("-Select-");
            ans_temp.setAnswer_cd("0");
            ans.add(0, ans_temp);
            listDataChild.put(data.get(i), ans);
        }

        adapter = new ChecklistAdapter(getApplicationContext(), data);
        rec_checklist.setAdapter(adapter);
        rec_checklist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if (current.getWindow_image() != null && !current.getWindow_image().equals("")) {
            image_window.setBackgroundResource(R.drawable.camera_icon_done);
        } else {
            image_window.setBackgroundResource(R.drawable.camera_icon);
        }


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.refimage:
                    String str = current.getPlanogram_image();

                    Intent in = new Intent(ChecklistActivity.this, PlanogramActivity.class);
                    in.putExtra("Plalogram", str);
                    startActivity(in);
                    break;
                case R.id.image_window:
                    if (switch_exists.isChecked()) {

                        _pathforcheck = store_cd + "_" + current.getWindow_cd().get(0) + "WINDOW" + "Image" + visit_date.replace("/", "") + getCurrentTime().replace(":", "") + ".jpg";
                        _path = CommonString1.FILE_PATH + _pathforcheck;
                        //startCameraActivity();
                        CommonFunctions.startAnncaCameraActivity(context, _path);
                        //CommonFunctions.startMaterialCameraActivity(activity, _pathforcheck, CommonString1.FILE_PATH);
                    }
                    break;
            }

        }
    };


    public Boolean validatedata() {
        boolean isgood = true;
        if (switch_exists.isChecked()) {
            if (window_image == null || window_image.equalsIgnoreCase("")) {
                isgood = false;
                AlertMessage.showMessage("Please click image", this);
            }

            if (isgood)
                for (int i = 0; i < data.size(); i++) {
                    if (answered_list.get(i).getANSWER_CD().equalsIgnoreCase("0")) {
                        isgood = false;
                        AlertMessage.showMessage("Please fill data in checklist", ChecklistActivity.this);
                        break;
                    }
                }
        } else {
            if (selectedAnswerId.equalsIgnoreCase("0")) {
                isgood = false;
                AlertMessage.showMessage("Please select reason", this);
            }
        }

        return isgood;
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position != 0) {
                selectedAnswerId = reasondata.get(position - 1).getWREASON_CD().get(0);
            } else {
                selectedAnswerId = "0";
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

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
            outputFileUri = Uri.fromFile(file);

            String defaultCameraPackage = "";
            final PackageManager packageManager = getPackageManager();
            List<ApplicationInfo> list = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
            for (int n = 0; n < list.size(); n++) {
                if ((list.get(n).flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                    Log.e("TAG", "Installed Applications  : " + list.get(n).loadLabel(packageManager).toString());
                    Log.e("TAG", "package name  : " + list.get(n).packageName);
                    //temp value in case camera is gallery app above jellybean
                    if (list.get(n).loadLabel(packageManager).toString().equalsIgnoreCase("Gallery")) {
                        gallery_package = list.get(n).packageName;
                    }
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if (list.get(n).loadLabel(packageManager).toString().equalsIgnoreCase("Camera")) {
                            defaultCameraPackage = list.get(n).packageName;
                            break;
                        }
                    } else {
                        if (list.get(n).loadLabel(packageManager).toString().equalsIgnoreCase("Camera")) {
                            defaultCameraPackage = list.get(n).packageName;
                            break;
                        }
                    }
                }
            }

            Intent intent = new Intent(
                    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            intent.setPackage(defaultCameraPackage);
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            intent.setPackage(gallery_package);
            startActivityForResult(intent, 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;
            case -1:
                if (_pathforcheck != null && !_pathforcheck.equals("")) {
                    //     if (new File(str + _pathforcheck).exists()) {
                    if (!window_image.equalsIgnoreCase("")) {
                        if (new File(CommonString1.FILE_PATH + window_image).exists()) {
                            new File(CommonString1.FILE_PATH + window_image).delete();
                        }
                        window_image = "";
                    }
                    String metadata = CommonFunctions.getMetadataAtImagesFromPref(metadata_global, "Window Image");
                    CommonFunctions.addMetadataAndTimeStampToImage(context, CommonString1.FILE_PATH + _pathforcheck, metadata);
                    img1 = _pathforcheck;
                    window_image = _pathforcheck;
                    _pathforcheck = "";
                    image_window.setBackgroundResource(R.drawable.camera_icon_done);
                    //  }
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.MyViewHolder> {

        private LayoutInflater inflator;

        List<ChecklistGetterSetter> data = Collections.emptyList();

        public ChecklistAdapter(Context context, List<ChecklistGetterSetter> data) {

            inflator = LayoutInflater.from(context);
            this.data = data;

        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflator.inflate(R.layout.item_checklist, parent, false);

            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.mItem = data.get(position);
            holder.tv_checklist.setText(holder.mItem.getChecklist());
            holder.ans = listDataChild.get(holder.mItem);
            holder.customAdapter = new CustomSpinnerAdapter(getApplicationContext(), holder.ans);
            holder.spinner.setAdapter(holder.customAdapter);
            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    answered_list.get(position).setANSWER_CD(holder.ans.get(pos).getAnswer_cd());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            for (int i = 0; i < holder.ans.size(); i++) {
                if (holder.ans.get(i).getAnswer_cd().equalsIgnoreCase(answered_list.get(position).getANSWER_CD())) {
                    holder.spinner.setSelection(i);
                    break;
                }
            }

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv_checklist;
            Spinner spinner;
            ChecklistGetterSetter mItem;
            ArrayList<AnswerChecklistGetterSetter> ans;
            CustomSpinnerAdapter customAdapter;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv_checklist = (TextView) itemView.findViewById(R.id.tv_checklist);
                spinner = (Spinner) itemView.findViewById(R.id.spin_checklist_ans);
            }
        }
    }

    public class CustomSpinnerAdapter extends BaseAdapter {
        Context context;
        // int flags[];
        ArrayList<AnswerChecklistGetterSetter> ans;
        LayoutInflater inflter;

        public CustomSpinnerAdapter(Context applicationContext, ArrayList<AnswerChecklistGetterSetter> ans) {
            this.context = applicationContext;
            //this.flags = flags;
            this.ans = ans;
            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return ans.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.custom_spinner_item, null);
            //ImageView icon = (ImageView) view.findViewById(R.id.imageView);
            TextView names = (TextView) view.findViewById(R.id.tv_ans);
            //icon.setImageResource(flags[i]);
            names.setText(ans.get(i).getAnswer());
            return view;
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

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }


    void declaration() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        switch_exists = (ToggleButton) findViewById(R.id.switch_exists);
        reason_spinner = (Spinner) findViewById(R.id.reason_spinner);
        image_window = (ImageButton) findViewById(R.id.image_window);
        refimage = (ImageButton) findViewById(R.id.refimage);
        lay_refimageCamera = (LinearLayout) findViewById(R.id.lay_refimageCamera);
        lay_reason = (LinearLayout) findViewById(R.id.lay_reason);
        db = new GSKDatabase(getApplicationContext());
        db.open();
        activity = this;
        context = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        visit_date = preferences.getString(CommonString1.KEY_VISIT_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");
        reasondata = new ArrayList<>();
        window_cd = getIntent().getStringExtra(CommonString.KEY_WINDOW_CD);
        sku_hold = getIntent().getStringExtra(CommonString.KEY_SKU_HOLD);
        brand_cd = getIntent().getStringExtra(CommonString.KEY_BRAND_CD);
        window_image = getIntent().getStringExtra(CommonString.KEY_WINDOW_IMAGE);
        current = (WindowListGetterSetter) getIntent().getSerializableExtra("OBJECT");
        getSupportActionBar().setTitle(current.getWindow());
        rec_checklist = (RecyclerView) findViewById(R.id.rec_checklist);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sku_icon));
    }


}
