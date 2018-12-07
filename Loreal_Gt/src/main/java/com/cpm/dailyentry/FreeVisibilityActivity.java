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
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.DisplayMasterGetterSetter;
import com.cpm.GetterSetter.FreeVisibilityGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.CompanyGetterSetter;

import java.io.File;
import java.util.ArrayList;


public class FreeVisibilityActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    Spinner sp_company, sp_category, sp_display;
    RecyclerView recyclerView;
    EditText edt_quantity;
    GSKDatabase db;
    FloatingActionButton fab_save, fab_add;
    String company_cd, category_cd, display_cd;
    Context context;
    Activity activity;
    String store_cd, _pathforcheck = "", _path = "", image = "", flag_operation = "save", coverage_id;
    int editPosition;
    private SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String state_cd, username, visit_date, storetype_cd, visitDate,metadata_global;
    ArrayList<CompanyGetterSetter> companyList;
    ArrayList<CategoryMasterGetterSetter> categoryList;
    ArrayList<DisplayMasterGetterSetter> displayList;
    ArrayAdapter<String> companyAdapter, categoryAdapter, displayAdapter;
    FreeVisibilityGetterSetter freeVisibilityGetterSetter;
    ArrayList<FreeVisibilityGetterSetter> recyclerList = new ArrayList<FreeVisibilityGetterSetter>();
    ValueAdapter adapter;
    Long removedID = 0L;
    ImageView camera_imgV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_visibility);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Free Visibility");
        declaration();
        prepareList();

    }

    public void declaration() {
        sp_company = (Spinner) findViewById(R.id.company_spinner);
        sp_category = (Spinner) findViewById(R.id.category_spinner);
        sp_display = (Spinner) findViewById(R.id.display_spinner);
        edt_quantity = (EditText) findViewById(R.id.qty_edittext);
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_save = (FloatingActionButton) findViewById(R.id.fab_save);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_free);
        camera_imgV = (ImageView) findViewById(R.id.camera_imgV);
        activity = this;
        db = new GSKDatabase(this);
        db.open();
        context = this;
        fab_add.setTag("save");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        visitDate = preferences.getString(CommonString1.KEY_VISIT_DATE, null);
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");
        camera_imgV.setOnClickListener(this);
    }

    void prepareList() {
        companyList = db.getCompanyData();
        //------------for Company Master List---------------
        companyAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        for (int i = 0; i < companyList.size(); i++) {
            companyAdapter.add(companyList.get(i).getCompany().get(0));
        }
        companyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_company.setAdapter(companyAdapter);
        //------------------------------------------------

        //------------for Category List---------------
        categoryList = db.getCategoryMasterData(false);
        categoryAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        for (int i = 0; i < categoryList.size(); i++) {
            categoryAdapter.add(categoryList.get(i).getCategory().get(0));
        }
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(categoryAdapter);
        //------------------------------------------------


        //------------for Display Type List---------------
        displayList = db.getDisplayMasterData();
        displayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        for (int i = 0; i < displayList.size(); i++) {
            displayAdapter.add(displayList.get(i).getDISPLAY().get(0));
        }
        displayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_display.setAdapter(displayAdapter);
        //------------------------------------------------


        recyclerList = db.getFreeVisibilityData(store_cd);

        if (recyclerList.size() > 0) {
            adapter = new ValueAdapter(context, recyclerList);
            adapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);

        }

        sp_company.setOnItemSelectedListener(this);
        sp_category.setOnItemSelectedListener(this);
        sp_display.setOnItemSelectedListener(this);

        fab_add.setOnClickListener(this);
        fab_save.setOnClickListener(this);
    }


    private class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.MyViewHolder> {
        private LayoutInflater inflator;
        ArrayList<FreeVisibilityGetterSetter> data = new ArrayList<FreeVisibilityGetterSetter>();

        public ValueAdapter(Context context, ArrayList<FreeVisibilityGetterSetter> data) {
            inflator = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public ValueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = inflator.inflate(R.layout.item_free_visibility, parent, false);
            ValueAdapter.MyViewHolder holder = new ValueAdapter.MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ValueAdapter.MyViewHolder viewHolder, final int position) {

            viewHolder.companytxt.setText(data.get(position).getCompany());
            viewHolder.categorytxt.setText(data.get(position).getCategory());
            viewHolder.displaytxt.setText(data.get(position).getDisplay());
            viewHolder.quantitytxt.setText(data.get(position).getQuantity());

            viewHolder.edit.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popup = new PopupMenu(context, v);
                            //Inflating the Popup using xml file
                            popup.getMenuInflater().inflate(R.menu.poupup_menu, popup.getMenu());
                            //registering popup with OnMenuItemClickListener
                            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                public boolean onMenuItemClick(MenuItem item) {
                                    switch (item.getItemId()) {
                                        case R.id.edit:
                                            Runnable editTask = new Runnable() {
                                                @Override
                                                public void run() {

                                                    for (int i = 0; i < companyList.size(); i++) {
                                                        if (companyList.get(i).getCompany_cd().get(0).equalsIgnoreCase(recyclerList.get(position).getCompany_cd())) {
                                                            sp_company.setSelection(i);
                                                            break;
                                                        }
                                                    }
                                                    for (int i = 0; i < categoryList.size(); i++) {
                                                        if (categoryList.get(i).getCategory_cd().get(0).equalsIgnoreCase(recyclerList.get(position).getCategory_cd())) {
                                                            sp_category.setSelection(i);
                                                            break;
                                                        }
                                                    }

                                                    for (int i = 0; i < displayList.size(); i++) {
                                                        if (displayList.get(i).getDISPLAY_CD().get(0).equalsIgnoreCase(recyclerList.get(position).getDisplay_cd())) {
                                                            sp_display.setSelection(i);
                                                            break;
                                                        }
                                                    }

                                                    if (!recyclerList.get(position).getImage().equalsIgnoreCase("")) {
                                                        image = recyclerList.get(position).getImage();
                                                        camera_imgV.setImageResource(R.drawable.camera_green);
                                                    }

                                                    if (!recyclerList.get(position).getQuantity().equalsIgnoreCase("")) {
                                                        edt_quantity.setText(recyclerList.get(position).getQuantity());
                                                    }


                                                    fab_add.setImageResource(R.drawable.editimage);
                                                    fab_add.setTag("edit");
                                                    flag_operation = "edit";
                                                    editPosition = position;
                                                }
                                            };
                                            AlertMessage.editorDeleteAlert(activity, "Do you want to edit?", editTask);

                                            break;
                                        case R.id.delete:
                                            Runnable deleteTask = new Runnable() {
                                                @Override
                                                public void run() {
                                                    //flag_operation = "delete";
                                                    if (recyclerList.get(position).getId() != null) {
                                                        removedID = Long.parseLong(recyclerList.get(position).getId());
                                                    }
                                                    recyclerList.remove(position);
                                                    recyclerView.removeViewAt(position);
                                                    adapter.notifyDataSetChanged();
                                                    /*adapter = new ValueAdapter(getApplicationContext(), recyclerList);
                                                    adapter.notifyDataSetChanged();
                                                    recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                                                    recyclerView.setAdapter(adapter);*/
                                                }
                                            };
                                            AlertMessage.editorDeleteAlert(activity, "Are you sure to delete?", deleteTask);
                                            break;
                                    }
                                    return true;
                                }
                            });

                            popup.show();//showing popup menu

                        }
                    }
            );


        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView companytxt, categorytxt, displaytxt, quantitytxt;
            ImageView edit;

            public MyViewHolder(View itemView) {
                super(itemView);
                companytxt = (TextView) itemView.findViewById(R.id.txt_company);
                categorytxt = (TextView) itemView.findViewById(R.id.txt_category);
                displaytxt = (TextView) itemView.findViewById(R.id.txt_display);
                quantitytxt = (TextView) itemView.findViewById(R.id.txt_quantity);
                edit = (ImageView) itemView.findViewById(R.id.edit);
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


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.camera_imgV:
                String intime = CommonFunctions.getCurrentTime();
                _pathforcheck = store_cd + "_FREE_VISI_" + "_" + visitDate.replace("/", "") + "_" + intime.replace(":", "") + ".jpg";
                _path = CommonString1.FILE_PATH + _pathforcheck;
                CommonFunctions.startAnncaCameraActivity(context, _path);
                //CommonFunctions.startCameraActivity(activity, _path);
                //CommonFunctions.startMaterialCameraActivity((Activity) context, _pathforcheck, CommonString1.FILE_PATH);
                break;
            case R.id.fab_add:
                if (validate()) {

                    freeVisibilityGetterSetter = new FreeVisibilityGetterSetter();

                    freeVisibilityGetterSetter.setCompany_cd(company_cd);
                    freeVisibilityGetterSetter.setCompany(sp_company.getSelectedItem().toString());
                    freeVisibilityGetterSetter.setCategory_cd(category_cd);
                    freeVisibilityGetterSetter.setCategory(sp_category.getSelectedItem().toString());
                    freeVisibilityGetterSetter.setDisplay_cd(display_cd);
                    freeVisibilityGetterSetter.setDisplay(sp_display.getSelectedItem().toString());
                    freeVisibilityGetterSetter.setQuantity(edt_quantity.getText().toString());
                    freeVisibilityGetterSetter.setImage(image);

                    if (flag_operation.equalsIgnoreCase("save")) {
                        recyclerList.add(freeVisibilityGetterSetter);
                        flag_operation = "save";
                    } else if (flag_operation.equalsIgnoreCase("edit")) {
                        recyclerList.remove(editPosition);
                        recyclerList.add(editPosition, freeVisibilityGetterSetter);
                        fab_add.setImageResource(R.drawable.add_btn);
                        fab_add.setTag("save");
                        flag_operation = "save";
                    }
                    adapter = new ValueAdapter(context, recyclerList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(adapter);
                    clearFields();
                }
                break;
            case R.id.fab_save:
                if (recyclerList.size() > 0) {
                    if (flag_operation.equalsIgnoreCase("edit")) {

                        if (fab_add.getTag().toString().equalsIgnoreCase("edit")) {
                            AlertMessage.showToastMessage(context, "Please add the edited data first");
                        } else {
                            long id = db.insertFreeVisibilityData(recyclerList, store_cd);
                            if (id > 0) {
                                AlertMessage.showToastMessage(context, "Data has been saved");
                                finish();
                            } else {
                                AlertMessage.showToastMessage(context, "Data not saved");
                                finish();
                            }
                        }
                    } else if (flag_operation.equalsIgnoreCase("save")) {
                        if (fab_add.getTag().toString().equalsIgnoreCase("save")) {
                            db.insertFreeVisibilityData(recyclerList, store_cd);
                            AlertMessage.showToastMessage(context, "Data has been saved");
                            finish();
                        }
                    }
                   /* else if (flag_operation.equalsIgnoreCase("delete")) {
                        db.deleteFreeVisibilityData(removedID);
                        AlertMessage.showToastMessage(context, "Data has been deleted");
                        removedID = 0L;
                        flag_operation = "save";
                        finish();
                    }*/
                } else {
                    AlertMessage.showToastMessage(context, "please add the data first");
                   /* if (flag_operation.equalsIgnoreCase("delete") && recyclerList.size() == 0 && removedID > 0L) {
                        db.deleteFreeVisibilityData(removedID);
                        AlertMessage.showToastMessage(context, "Data has been deleted");
                        removedID = 0L;
                        flag_operation = "save";
                        finish();
                    } else {
                        AlertMessage.showToastMessage(context, "please add the data first");
                    }
*/
                }
                break;
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.company_spinner:
                company_cd = companyList.get(position).getCompany_cd().get(0);
                break;
            case R.id.category_spinner:
                category_cd = categoryList.get(position).getCategory_cd().get(0);
                break;
            case R.id.display_spinner:
                display_cd = displayList.get(position).getDISPLAY_CD().get(0);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                            camera_imgV.setImageResource(R.drawable.camera_green);
                            String metadata = CommonFunctions.getMetadataAtImagesFromPref(metadata_global, "Free Visibility");
                            CommonFunctions.addMetadataAndTimeStampToImage(context, CommonString1.FILE_PATH + _pathforcheck,metadata);
                            _pathforcheck = "";
                            //image = "";
                        }
                    }

                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackPressed() {
        if (recyclerList.size() > 0) {
            AlertMessage.backpressedAlert(activity);
        } else {
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
    }

    private boolean validate() {
        boolean isgood = true;
        if (sp_company.getSelectedItemPosition() == 0) {
            isgood = false;
            AlertMessage.showToastMessage(context, "Please select company");
        } else if (sp_category.getSelectedItemPosition() == 0) {
            isgood = false;
            AlertMessage.showToastMessage(context, "Please select category");
        } else if (sp_display.getSelectedItemPosition() == 0) {
            isgood = false;
            AlertMessage.showToastMessage(context, "Please select display");
        } else if (edt_quantity.getText().toString().equalsIgnoreCase("")) {
            isgood = false;
            AlertMessage.showToastMessage(context, "Please fill quantity");
        } else if (image.equalsIgnoreCase("")) {
            isgood = false;
            AlertMessage.showToastMessage(context, "Please click image");
        }

        return isgood;
    }


    private void clearFields() {
        sp_company.setSelection(0);
        sp_category.setSelection(0);
        sp_display.setSelection(0);
        edt_quantity.setText("");
        image = "";
        camera_imgV.setImageResource(R.drawable.camera);
    }
}
