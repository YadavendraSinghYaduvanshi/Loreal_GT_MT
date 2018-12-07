package com.cpm.dailyentry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;
import com.cpm.xmlGetterSetter.BrandGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StockAvailabilityForChannel2Activity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    ExpandableListView expandableListView;
    SharedPreferences preferences;
    ExpandableListAdapter expandableListAdapter;
    GSKDatabase db;
    Context context;
    String store_cd;
    ArrayList<Integer> error_position_header = new ArrayList<>();
    ArrayList<Integer> error_position_child = new ArrayList<>();
    ArrayList<BrandGetterSetter> brandList;
    ArrayList<BrandGetterSetter> listDataHeader;
    HashMap<BrandGetterSetter, List<SkuMasterGetterSetter>> listDataChild;
    boolean errorFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_availability_for_channel2);
        declaration();
        prepareListData();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                if (validate()) {
                    long id = db.insertStockChannel2Data(store_cd, listDataHeader, listDataChild);
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


    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<BrandGetterSetter> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<BrandGetterSetter, List<SkuMasterGetterSetter>> _listDataChild;

        public ExpandableListAdapter(Context context, List<BrandGetterSetter> listDataHeader, HashMap<BrandGetterSetter, List<SkuMasterGetterSetter>> listChildData) {
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

            final SkuMasterGetterSetter childText = (SkuMasterGetterSetter) getChild(groupPosition, childPosition);
            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

            txtListChild.setText(childText.getSku_str());
            edtListChild.setText(childText.getQuantity());

            _listDataChild
                    .get(listDataHeader.get(groupPosition))
                    .get(childPosition).setSku_str(childText.getSku_str());

            _listDataChild
                    .get(listDataHeader.get(groupPosition))
                    .get(childPosition).setSku_cd_str(childText.getSku_cd_str());

            edtListChild.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
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

        /*    edtListChild.setText( _listDataChild
                    .get(listDataHeader.get(groupPosition))
                    .get(childPosition).getQuantity());*/

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
                convertView = infalInflater.inflate(R.layout.stock_header_item_view, parent, false);
            }

            final BrandGetterSetter headerTitle = (BrandGetterSetter) getGroup(groupPosition);

            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            final EditText edt_quantity = (EditText) convertView.findViewById(R.id.headerQty_edt);
            CardView cardView = (CardView) convertView.findViewById(R.id.card_view);
            /*ImageView imageView = (ImageView) convertView
                    .findViewById(R.id.camerabtn);*/
            edt_quantity.setVisibility(View.GONE);
            // imageView.setVisibility(View.GONE);

            lblListHeader.setText(headerTitle.getBrand().get(0));

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

            //  edt_quantity.setText(listDataHeader.get(groupPosition).getQuantity());
            if (errorFlag) {
                if (error_position_header.contains(groupPosition)) {
                    cardView.setCardBackgroundColor(Color.RED);
                } else {
                    cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            } else {
                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
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
            for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                if (listDataChild.get(listDataHeader.get(i)).get(j).getQuantity().equalsIgnoreCase("")) {
                    isvalidate = false;
                    errorFlag = true;
                    error_position_header.add(i);
                    error_position_child.add(j);
                    expandableListView.invalidateViews();
                    AlertMessage.showToastMessage(context, "Please fill quantity at line " + (i + 1) + " and brand " + (j + 1) + "");
                    break;
                }
            }
            if (isvalidate == false) {
                break;
            }
        }
        return isvalidate;
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<BrandGetterSetter>();
        ArrayList<SkuMasterGetterSetter> childata = new ArrayList<SkuMasterGetterSetter>();
        listDataChild = new HashMap<BrandGetterSetter, List<SkuMasterGetterSetter>>();
        brandList = db.getSavedBrandForChannel2Data(store_cd);
        listDataHeader.clear();
        listDataChild.clear();

        if (brandList.size() == 0) {
            // Adding child data
            brandList = db.getBrandForChannel2Data();
            for (int i = 0; i < brandList.size(); i++) {
                listDataHeader.add(brandList.get(i));
                childata = db.getChildDataForHeaderChannel2(brandList.get(i).getBrand_cd().get(0));
                if (childata.size() > 0) {
                    listDataChild.put(listDataHeader.get(i), childata); // Header, Child data
                }
            }
            if (listDataHeader.size() > 0) {
                expandableListAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);
                expandableListView.setAdapter(expandableListAdapter);
            }

        } else {
            for (int i = 0; i < brandList.size(); i++) {
                listDataHeader.add(brandList.get(i));
                childata = db.getSavedChildDataForHeaderChannel2(brandList.get(i).getId(), store_cd);
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
                expandableListView.clearFocus();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                expandableListView.clearFocus();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                expandableListView.clearFocus();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertMessage.backpressedAlert(StockAvailabilityForChannel2Activity.this);
    }

    void declaration() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Stock Availability");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        expandableListView = (ExpandableListView) findViewById(R.id.stock_expandable);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        db = new GSKDatabase(context);
        db.open();
        fab.setOnClickListener(this);
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
}
