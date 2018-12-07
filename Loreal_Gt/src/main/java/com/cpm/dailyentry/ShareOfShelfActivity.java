package com.cpm.dailyentry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.BrandGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ShareOfShelfActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    ExpandableListView expandableListView;
    SharedPreferences preferences;
    ExpandableListAdapter expandableListAdapter;
    GSKDatabase db;
    Context context;
    String store_cd, visitdate, metadata_global;
    ArrayList<CategoryMasterGetterSetter> categoryList;
    ArrayList<CategoryMasterGetterSetter> listDataHeader;
    HashMap<CategoryMasterGetterSetter, List<BrandGetterSetter>> listDataChild;
    ArrayList<Integer> error_position_header = new ArrayList<>();
    ArrayList<Integer> error_position_child = new ArrayList<>();
    boolean errorFlag;
    String _pathforcheck, intime, path, image = "";
    int global_position = 0, redHeaderPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_of_shelf);
        declaration();
        prepareListData();
    }


    void declaration() {
        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Share of Shelf");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        expandableListView = (ExpandableListView) findViewById(R.id.shareofshelf_expandable);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        visitdate = preferences.getString(CommonString1.KEY_VISIT_DATE, null);
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");
        db = new GSKDatabase(context);
        db.open();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                if (validate()) {
                    long id = db.insertShareShelfHeaderData(store_cd, listDataHeader, listDataChild);
                    if (id > 0) {
                        AlertMessage.showToastMessage(context, "Data Saved");
                        finish();
                    } else {
                        AlertMessage.showToastMessage(context, "Data not Saved");
                    }
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

    @Override
    protected void onResume() {
        super.onResume();
        String s = _pathforcheck;
    }

    @Override
    public void onBackPressed() {
        AlertMessage.backpressedAlert(ShareOfShelfActivity.this);
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<CategoryMasterGetterSetter> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<CategoryMasterGetterSetter, List<BrandGetterSetter>> _listDataChild;

        public ExpandableListAdapter(Context context, List<CategoryMasterGetterSetter> listDataHeader, HashMap<CategoryMasterGetterSetter, List<BrandGetterSetter>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @SuppressLint("NewApi")
        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final BrandGetterSetter childText = (BrandGetterSetter) getChild(groupPosition, childPosition);
            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.item_child_expandable_shareofshelf, null);
                holder = new ViewHolder();
                holder.cardView = (CardView) convertView.findViewById(R.id.card_view);
                holder.item_ll = (LinearLayout) convertView.findViewById(R.id.item_ll);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

//----------------------------------
            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
            EditText edtListChild = (EditText) convertView.findViewById(R.id.quantity_edt);

            txtListChild.setText(childText.getBrand().get(0));
            edtListChild.setText(childText.getQuantity());

            _listDataChild
                    .get(listDataHeader.get(groupPosition))
                    .get(childPosition).setBrand(childText.getBrand().get(0));

            _listDataChild
                    .get(listDataHeader.get(groupPosition))
                    .get(childPosition).setBrand_cd(childText.getBrand_cd().get(0));

            edtListChild.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    redHeaderPosition = -1;
                    EditText edt = (EditText) v;
                    String val = edt.getText().toString();
                    if (val.equalsIgnoreCase("")) {
                        _listDataChild
                                .get(listDataHeader.get(groupPosition))
                                .get(childPosition).setQuantity("");
                    } else {
                        _listDataChild
                                .get(listDataHeader.get(groupPosition))
                                .get(childPosition).setQuantity(val);
                    }
                }
            });




      /*      edtListChild.setText( _listDataChild
                    .get(listDataHeader.get(groupPosition))
                    .get(childPosition).getQuantity());
*/

          /*  if (!checkflag) {
                if (listDataChild
                        .get(listDataHeader.get(groupPosition))
                        .get(childPosition).getSkuChecked() == -1) {
                    holder.item_ll.setBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    holder.item_ll.setBackgroundColor(getResources().getColor(R.color.white));
                }

            }
*/

            if (errorFlag) {
                if (error_position_header.contains(groupPosition) && error_position_child.contains(childPosition)) {
                    holder.item_ll.setBackgroundResource(R.color.red);
                } else {
                    holder.item_ll.setBackgroundResource(R.color.white);
                }
            } else {
                holder.item_ll.setBackgroundResource(R.color.white);
            }


            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.item_header_expandable_shareofshelf, parent, false);
            }

            final CategoryMasterGetterSetter headerTitle = (CategoryMasterGetterSetter) getGroup(groupPosition);

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader);
            EditText edt_quantity = (EditText) convertView
                    .findViewById(R.id.headerQty_edt);
            CardView cardView = (CardView) convertView
                    .findViewById(R.id.card_view);
            LinearLayout linearLayout = (LinearLayout) convertView
                    .findViewById(R.id.ll_header);

            ImageView camerabtn = (ImageView) convertView.findViewById(R.id.camerabtn);

            lblListHeader.setText(headerTitle.getCategory().get(0));
            edt_quantity.setText(headerTitle.getQuantity());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expandableListView.isGroupExpanded(groupPosition)) {
                        expandableListView.collapseGroup(groupPosition);
                    } else {
                        expandableListView.expandGroup(groupPosition);
                    }
                }
            });

            edt_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    EditText edt = (EditText) v;
                    String val = edt.getText().toString();
                    redHeaderPosition = -1;
                    if (val.equalsIgnoreCase("")) {
                        listDataHeader.get(groupPosition).setQuantity("");
                    } else {
                        listDataHeader.get(groupPosition).setQuantity(val);
                    }
                }
            });

            camerabtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String intime = CommonFunctions.getCurrentTime();
                    global_position = groupPosition;
                    _pathforcheck = store_cd + "_" + "SOS_" + groupPosition + "_" + visitdate.replace("/", "") + "_" + intime.replace(":", "") + ".jpg";
                    path = CommonString1.FILE_PATH + _pathforcheck;
                    //CommonFunctions.startMaterialCameraActivity((Activity) context, _pathforcheck, CommonString1.FILE_PATH);
                    CommonFunctions.startAnncaCameraActivity(context, path);
                    //CommonFunctions.startCameraActivity(ShareOfShelfActivity.this, path);
                }
            });

            if (!image.equals("") && groupPosition == global_position) {
                listDataHeader.get(groupPosition).setImage_name(image);
                image = "";
                expandableListAdapter.notifyDataSetChanged();
            }

            if (headerTitle.getImage_name().equals("")) {
                camerabtn.setBackgroundResource(R.drawable.camera);
            } else {
                camerabtn.setBackgroundResource(R.drawable.camera_done);
            }

            if (redHeaderPosition == groupPosition) {
                linearLayout.setBackgroundResource(R.color.red);
            } else {
                linearLayout.setBackgroundResource(R.color.colorPrimary);
            }


            //   edt_quantity.setText(listDataHeader.get(groupPosition).getQuantity());
            if (errorFlag) {
                if (error_position_header.contains(groupPosition)) {
                    linearLayout.setBackgroundColor(Color.RED);
                } else {
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            } else {
                linearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    public class ViewHolder {
        CardView cardView;
        TextView textview;
        LinearLayout item_ll;
    }

    public boolean validate() {
        boolean isvalidate = true;
        expandableListView.clearFocus();
        error_position_header.clear();
        error_position_child.clear();
        errorFlag = false;
        for (int i = 0; i < listDataHeader.size(); i++) {
            if (listDataHeader.get(i).getQuantity().equalsIgnoreCase("")) {
                isvalidate = false;
                errorFlag = true;
                error_position_header.add(i);
                expandableListView.invalidateViews();
                AlertMessage.showToastMessage(context, "Please fill quantity at line " + (i + 1));
                break;
            } else if (listDataHeader.get(i).getImage_name().equalsIgnoreCase("")) {
                isvalidate = false;
                errorFlag = true;
                error_position_header.add(i);
                expandableListView.invalidateViews();
                AlertMessage.showToastMessage(context, "Please click image at line " + (i + 1));
                break;
            } else {
                for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                    if (listDataChild.get(listDataHeader.get(i)).get(j).getQuantity().equalsIgnoreCase("")) {
                        isvalidate = false;
                        errorFlag = true;
                        error_position_header.add(i);
                        error_position_child.add(j);
                        expandableListView.invalidateViews();
                        AlertMessage.showToastMessage(context, "Please fill quantity at Brand " + (j + 1) + " and line " + (i + 1));
                        break;
                    }
                }

                if (isvalidate == false) {
                    break;
                }
            }
        }

        if (isvalidate == true) {
            for (int i = 0; i < listDataHeader.size(); i++) {
                int val = 0;
                for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                    val = val + Integer.parseInt(listDataChild.get(listDataHeader.get(i)).get(j).getQuantity());
                }

                if (val > Integer.parseInt(listDataHeader.get(i).getQuantity())) {
                    isvalidate = false;
                    errorFlag = true;
                    error_position_header.add(i);
                    AlertMessage.showToastMessage(context, "Category value cannot greater than brand value");
                    redHeaderPosition = i;
                    expandableListView.invalidateViews();
                    break;
                }
            }
        }


        return isvalidate;
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<CategoryMasterGetterSetter>();
        ArrayList<BrandGetterSetter> childata = new ArrayList<BrandGetterSetter>();
        listDataChild = new HashMap<CategoryMasterGetterSetter, List<BrandGetterSetter>>();

        categoryList = db.getSavedCategoryMasterData(store_cd);
        listDataHeader.clear();
        listDataChild.clear();

        if (categoryList.size() == 0) {
            // Adding child data
            categoryList = db.getCategoryMasterData(true);
            for (int i = 0; i < categoryList.size(); i++) {
                listDataHeader.add(categoryList.get(i));
                childata = db.getChildDataForHeader(categoryList.get(i).getCategory_cd().get(0));
                if (childata.size() > 0) {
                    listDataChild.put(listDataHeader.get(i), childata); // Header, Child data
                }
            }
            if (listDataHeader.size() > 0) {
                expandableListAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);
                expandableListView.setAdapter(expandableListAdapter);
            }

        } else {
            // Adding child data
            for (int i = 0; i < categoryList.size(); i++) {
                listDataHeader.add(categoryList.get(i));
                childata = db.getSavedChildDataForHeader(categoryList.get(i).getId(), store_cd);
                if (childata.size() > 0) {
                    listDataChild.put(listDataHeader.get(i), childata); // Header, Child data
                }
            }
            if (listDataHeader.size() > 0) {
                expandableListAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);
                expandableListView.setAdapter(expandableListAdapter);
            }
        }

        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                View currentFocus = ((Activity) context).getCurrentFocus();
                if (currentFocus != null) {
                    currentFocus.clearFocus();
                }
                expandableListView.invalidate();
                expandableListView.clearFocus();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

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
                            //displayList.get(global_position).setImage(image);
                            String metadata = CommonFunctions.getMetadataAtImagesFromPref(metadata_global, "Share of Shelf");
                            CommonFunctions.addMetadataAndTimeStampToImage(context, CommonString1.FILE_PATH + _pathforcheck, metadata);
                            expandableListView.invalidate();
                            expandableListAdapter.notifyDataSetChanged();
                            _pathforcheck = "";
                            //image = "";
                        }
                    }

                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
