package com.cpm.dailyentry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.JourneyPlanPreviousGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.message.AlertMessage;
import com.cpm.upload.UploadPreviousStoreDataActivity;
import com.cpm.xmlGetterSetter.NonWorkingReasonGetterSetter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class PreviousStoreDataActivity extends AppCompatActivity {

    FloatingActionButton fab;
    AlertDialog alert;
    GSKDatabase db;
    Context context;
    ArrayList<JourneyPlanPreviousGetterSetter> jcpPrevList;
    String user_name, intime = "";
    SharedPreferences preferences;
    ArrayList<String> listDataHeader;
    HashMap<String, ArrayList<JourneyPlanPreviousGetterSetter>> listDataChild;
    ExpandableListView expandableListView;
    ArrayAdapter adapter;
    ArrayList<NonWorkingReasonGetterSetter> reasonList;
    ExpandableListAdapter expandableListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_store_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setdeclaration();
        prepareList();

        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                View currentFocus = ((Activity) context).getCurrentFocus();
                if (currentFocus != null) {
                    currentFocus.clearFocus();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Do you want to save the data ")
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog,
                                                int id) {
                                            alert.getButton(
                                                    AlertDialog.BUTTON_POSITIVE)
                                                    .setEnabled(false);
                                            boolean isInserted = true;
                                            for (int i = 0; i < listDataHeader.size(); i++) {
                                                for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                                                    JourneyPlanPreviousGetterSetter jcp = listDataChild.get(listDataHeader.get(i)).get(j);
                                                    CoverageBean cdata = new CoverageBean();
                                                    cdata.setStoreId(jcp.getSTORE_CD().get(0));
                                                    cdata.setVisitDate(jcp.getVISITDATE().get(0));
                                                    cdata.setUserId(user_name);
                                                    cdata.setInTime(intime);
                                                    cdata.setOutTime("0:00:00");
                                                    cdata.setChannel_cd(jcp.getCHANNEL_CD().get(0));
                                                    cdata.setReason(jcp.getREASON_STR());
                                                    cdata.setReasonid(jcp.getREASON_CD());
                                                    cdata.setLatitude("0.0");
                                                    cdata.setLongitude("0.0");
                                                    cdata.setImage("");
                                                    cdata.setRemark("");
                                                    cdata.setStatus(CommonString1.STORE_STATUS_LEAVE);
                                                    long id2 = db.InsertPreviousCoverageData(cdata);
                                                    if (id2 == 0) {
                                                        isInserted = false;
                                                        AlertMessage.showToastMessage(context, "Error while inserting store no." + j);
                                                        break;
                                                    }
                                                }
                                                if (isInserted == false) {
                                                    break;
                                                }
                                            }
                                            if (isInserted == true) {
                                                Intent intent = new Intent(context, UploadPreviousStoreDataActivity.class);
                                                intent.putExtra(CommonString.TAG_INTENT_DATA, "FromPrevStore");
                                                startActivity(intent);
                                                AlertMessage.showToastMessage(context, "Data Inserted Successfully");
                                                finish();
                                            }

                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog,
                                                int id) {
                                            dialog.cancel();
                                        }
                                    });

                    alert = builder.create();
                    alert.show();

                } else {
                    AlertMessage.showToastMessage(context, "Please select reason for all stores");
                }

            }
        });
    }

    public void prepareList() {
        jcpPrevList = db.getPreviousStoreData(null);
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        listDataHeader.clear();
        listDataChild.clear();


        if (jcpPrevList.size() > 0) {

            for (int i = 0; i < jcpPrevList.size(); i++) {
                String str1 = jcpPrevList.get(i).getVISITDATE().get(0);
                if (listDataHeader.size() == 0) {
                    listDataHeader.add(jcpPrevList.get(i).getVISITDATE().get(0));
                }
                if (listDataHeader.size() > 0) {
                    for (int j = 0; j < listDataHeader.size(); j++) {
                        if (!listDataHeader.get(j).equalsIgnoreCase(str1)) {
                            listDataHeader.add(jcpPrevList.get(i).getVISITDATE().get(0));
                        }
                    }
                }
            }

            for (int j = 0; j < listDataHeader.size(); j++) {
                ArrayList<JourneyPlanPreviousGetterSetter> jcpChildList = db.getPreviousStoreData(listDataHeader.get(j));
                listDataChild.put(listDataHeader.get(j), jcpChildList);
            }
        }

        expandableListAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);
        expandableListView.setAdapter(expandableListAdapter);

        for (int j = 0; j < listDataHeader.size(); j++) {
            expandableListView.expandGroup(j);
        }

    }

    public void setdeclaration() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        expandableListView = (ExpandableListView) findViewById(R.id.ExpandView_PrevData);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        user_name = preferences.getString(CommonString1.KEY_USERNAME, null);
        intime = getCurrentTime();
        jcpPrevList = new ArrayList<>();
        context = this;
        db = new GSKDatabase(context);
        db.open();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, ArrayList<JourneyPlanPreviousGetterSetter>> _listDataChild;

        public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, ArrayList<JourneyPlanPreviousGetterSetter>> listChildData) {
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

            final JourneyPlanPreviousGetterSetter childText = (JourneyPlanPreviousGetterSetter) getChild(groupPosition, childPosition);
            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.item_child_prev_data, null, false);

                holder = new ViewHolder();
                holder.storename_txt = (TextView) convertView.findViewById(R.id.lblListItem);
                holder.reason_spn = (Spinner) convertView.findViewById(R.id.reason_spn);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.storename_txt.setText(childText.getSTORENAME().get(0));

            if (childText.getREASON().get(0).equalsIgnoreCase("ALL")) {
                reasonList = db.getNonWorkingData(true);
                adapter = new ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line);
                for (int i = 0; i < reasonList.size(); i++) {
                    adapter.add(reasonList.get(i).getReason().get(0));
                }
                holder.reason_spn.setAdapter(adapter);
            } else {
                reasonList = db.getNonWorkingData(false);
                adapter = new ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line);
                for (int i = 0; i < reasonList.size(); i++) {
                    adapter.add(reasonList.get(i).getReason().get(0));
                }
                holder.reason_spn.setAdapter(adapter);
            }

            holder.reason_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    childText.setREASON_STR(reasonList.get(position).getReason().get(0));
                    childText.setREASON_CD(reasonList.get(position).getReason_cd().get(0));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

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
                convertView = infalInflater.inflate(R.layout.item_header_prev_data, parent, false);
            }

            final String headerTitle = (String) getGroup(groupPosition);

            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            CardView cardView = (CardView) convertView.findViewById(R.id.card_view);
            lblListHeader.setText(headerTitle);

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

        class ViewHolder {
            TextView storename_txt;
            Spinner reason_spn;
        }

    }

    public boolean validate() {
        boolean isvalidate = true;
        for (int i = 0; i < listDataHeader.size(); i++) {
            for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                JourneyPlanPreviousGetterSetter jcp = listDataChild.get(listDataHeader.get(i)).get(j);
                if (jcp.getREASON_CD().equalsIgnoreCase("") || jcp.getREASON_CD().equalsIgnoreCase("-1")) {
                    isvalidate = false;
                    break;
                }
            }
            if (isvalidate == false) {
                break;
            }
        }

        return isvalidate;
    }

    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:mmm");
        String cdate = formatter.format(m_cal.getTime());
       /* String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);*/
        return cdate;

    }


}
