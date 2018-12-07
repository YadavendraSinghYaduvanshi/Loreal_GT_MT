package com.cpm.dailyentry;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.xmlGetterSetter.BrandGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;
import com.cpm.xmlGetterSetter.WindowSKUEntryGetterSetter;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;

public class SKUEntryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,ObservableScrollViewCallbacks, View.OnClickListener {

    GSKDatabase db;
    private SharedPreferences preferences;
    String store_cd, visit_date, username, intime, store_type_cd, state_cd;

    Spinner spinner_category, spinner_sku, spinner_brand;

    String window_cd, sku_hold;

    EditText et_stock, et_onetosix, et_seventotwelve, et_abovethirteen;
    Button btn_save;
    ObservableListView lv;
    //LinearLayout heading;

    ArrayList<BrandGetterSetter> brand_list;
    ArrayList<SkuMasterGetterSetter> sku_list;
    ArrayList<CategoryMasterGetterSetter> categorylist;

    private ArrayAdapter<CharSequence> categoryAdapter, skuAdapter, brandAdapter;

    String category_cd, category, brand_cd, brand, sku_cd, sku, stock, window_brand_cd,
    window_image;
    Boolean  existOrnot;

    ArrayList<WindowSKUEntryGetterSetter> skuEntryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skuentry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spinner_category = (Spinner) findViewById(R.id.spincategory);
        spinner_sku = (Spinner) findViewById(R.id.spinsku);
        spinner_brand = (Spinner) findViewById(R.id.spinbrand);
        et_stock = (EditText) findViewById(R.id.et_stock);
        et_onetosix = (EditText) findViewById(R.id.et_onetosix);
        et_seventotwelve = (EditText) findViewById(R.id.et_seventotwelve);
        et_abovethirteen = (EditText) findViewById(R.id.et_abovethirteen);
        lv = (ObservableListView) findViewById(R.id.lv_sku);

        btn_save = (Button) findViewById(R.id.btn_sku_add);

        window_cd = getIntent().getStringExtra(CommonString.KEY_WINDOW_CD);
        sku_hold = getIntent().getStringExtra(CommonString.KEY_SKU_HOLD);
        window_brand_cd = getIntent().getStringExtra(CommonString.KEY_BRAND_CD);
        existOrnot = getIntent().getBooleanExtra(CommonString.KEY_EXISTS, true);
        window_image = getIntent().getStringExtra(CommonString.KEY_WINDOW_IMAGE);

        btn_save.setOnClickListener(this);

        db = new GSKDatabase(getApplicationContext());
        db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);

        visit_date = preferences.getString(CommonString1.KEY_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        state_cd = preferences.getString(CommonString1.KEY_STATE_CD, null);
        store_type_cd = preferences.getString(CommonString1.KEY_STORE_TYPE_CD, null);

        categoryAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);
        skuAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);
        brandAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);

        categoryAdapter.add("Select Category");

        categorylist = db.getCategoryAllData(window_brand_cd, sku_hold, store_type_cd,state_cd);

        for (int i = 0; i < categorylist.size(); i++) {

            categoryAdapter.add(categorylist.get(i).getCategory().get(0));

        }

        spinner_category.setAdapter(categoryAdapter);

        skuAdapter.add("Select SKU");
        spinner_sku.setAdapter(skuAdapter);

        brandAdapter.add("Select Brand");
        spinner_brand.setAdapter(brandAdapter);


        spinner_category.setOnItemSelectedListener(this);
        spinner_brand.setOnItemSelectedListener(this);
        spinner_sku.setOnItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(skuEntryList.size()>0){

                    long l = db.InsertWindowsData(store_cd, visit_date, username, window_cd, existOrnot, window_image,"0");

                    db.InsertSKUEntry(skuEntryList, window_cd, store_cd);

                    finish();
                }
                else{
                    Snackbar.make(view, "First add atleast one sku data",Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

    /*@Override
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
    }*/

    @Override
    public void onBackPressed() {
        /*Intent i = new Intent(this, DailyEntryScreen.class);
		startActivity(i);*/

      /*  finish();


        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);*/
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {

            case R.id.spincategory:

                if (position != 0) {

                    category_cd = categorylist.get(position-1).getCategory_cd().get(0);
                    category = categorylist.get(position-1).getCategory().get(0);

                    brandAdapter.clear();


                    brandAdapter.add("Select Brand");

                    brand_list = db.getBrandsData(window_brand_cd, sku_hold, category_cd, state_cd, store_type_cd);

                    for (int i = 0; i < brand_list.size(); i++) {

                        brandAdapter.add(brand_list.get(i).getBrand().get(0));
                    }

                    spinner_brand.setAdapter(brandAdapter);
                }
                else{
                    category_cd="";
                    category = "";
                }

                break;

            case R.id.spinbrand:

                if (position != 0) {

                    brand_cd = brand_list.get(position-1).getBrand_cd().get(0);
                    brand = brand_list.get(position-1).getBrand().get(0);

                    skuAdapter.clear();

                    skuAdapter.add("Select SKU");

                    sku_list = db.getSKUData(brand_cd, sku_hold, state_cd, store_type_cd);

                    for (int i = 0; i < sku_list.size(); i++) {

                        skuAdapter.add(sku_list.get(i).getSku().get(0));

                    }

                    spinner_sku.setAdapter(skuAdapter);
                }
                else{

                    brand = "";
                    brand_cd = "";
                }



                break;
            case R.id.spinsku:

                if (position != 0) {
                    sku_cd = sku_list.get(position-1).getSku_cd().get(0);
                    sku = sku_list.get(position-1).getSku().get(0);
                }
                else{
                    sku_cd = "";
                    sku = "";
                }

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.btn_sku_add){

            String stock, onetosix, seventotwelve, abovethirteen;

            if (category_cd == null || (category_cd.equals(""))) {

                Snackbar.make(v, "Please select a category", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else if (sku_cd.equals("") || (sku_cd == null)) {

                Snackbar.make(v, "Please select an asset", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else if (brand_cd.equals("") || (brand_cd == null)) {

                Snackbar.make(v, "Please select an brand", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {

                stock = et_stock.getText().toString().replaceAll("[&^<>{}'$]", "");
                onetosix = et_onetosix.getText().toString().replaceAll("[&^<>{}'$]", "");
                seventotwelve = et_seventotwelve.getText().toString().replaceAll("[&^<>{}'$]", "");
                abovethirteen = et_abovethirteen.getText().toString().replaceAll("[&^<>{}'$]", "");

                if (stock.equals("")) {
                    Snackbar.make(v, "Please fill stock data", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {/*if (onetosix.equals("") || seventotwelve.equals("") || abovethirteen.equals("")) {
                        Snackbar.make(view, "Please fill freshness data", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else{*/

                    onetosix = "0";
                    seventotwelve = "0";
                    abovethirteen = "0";

                    WindowSKUEntryGetterSetter skuEntryGetterSetter = new WindowSKUEntryGetterSetter();
                    skuEntryGetterSetter.setCategory_cd(category_cd);
                    skuEntryGetterSetter.setCategory(category);
                    skuEntryGetterSetter.setBrand_cd(brand_cd);
                    skuEntryGetterSetter.setBrand(brand);
                    skuEntryGetterSetter.setSku_cd(sku_cd);
                    skuEntryGetterSetter.setSku(sku);
                    skuEntryGetterSetter.setStock(stock);
                    skuEntryGetterSetter.setOnetosix(onetosix);
                    skuEntryGetterSetter.setSeventotwelve(seventotwelve);
                    skuEntryGetterSetter.setAbovethirteen(abovethirteen);

                    skuEntryList.add(skuEntryGetterSetter);

                  /*  db.InsertWindowsData(store_cd, visit_date, username, window_cd, existOrnot, window_image);

                    db.InsertSKUEntry( store_cd,  category_cd,  brand_cd,  sku_cd,  window_cd,
                            stock,  onetosix,  seventotwelve,  abovethirteen);
*/
                    spinner_category.setSelection(0);
                    spinner_brand.setSelection(0);
                    spinner_sku.setSelection(0);

                    category_cd = category = brand_cd = brand = sku_cd = sku = "";

                    et_stock.setText("");
                    et_onetosix.setText("");
                    et_seventotwelve.setText("");
                    et_abovethirteen.setText("");



//                        db.InsertwindowsData();
                    // if(skuEntryList.size()>0){

                    //heading.setVisibility(View.VISIBLE);

                    lv.setAdapter(new EntryAdaptor(SKUEntryActivity.this, skuEntryList));
                    lv.setVisibility(View.VISIBLE);
                    //}

                    lv.invalidate();

                }

            }


        }
    }

    private class EntryAdaptor extends BaseAdapter{

        ArrayList myList = new ArrayList();
        LayoutInflater inflater;
        Context context;

        public EntryAdaptor(Context context, ArrayList myList) {
            this.myList = myList;
            this.context = context;
            inflater = LayoutInflater.from(this.context);
        }

        @Override
        public int getCount() {
            return skuEntryList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewStockHolder holder = null;

            if (convertView == null) {
                holder = new ViewStockHolder();

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_sku_entry_layout, null);

                holder.tvcategory = (TextView) convertView
                        .findViewById(R.id.tv_category);
                holder.tvsku= (TextView) convertView
                        .findViewById(R.id.tv_sku);
                holder.tvbrand= (TextView) convertView
                        .findViewById(R.id.tv_brand);
                holder.tvstock= (TextView) convertView
                        .findViewById(R.id.tv_stock);
                holder.delete = (Button) convertView
                        .findViewById(R.id.btndelete);

                holder.tvonetosix = (TextView) convertView
                        .findViewById(R.id.tv_onetosix);

                holder.tvseventotwelve = (TextView) convertView
                        .findViewById(R.id.tv_seventotwelve);

                holder.tvabovethiteen = (TextView) convertView
                        .findViewById(R.id.tv_abovethirteen);

               /* holder.layout = (LinearLayout) convertView
                        .findViewById(R.id.poihead);*/

            /*    holder.delete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                        RelativeLayout layout=(RelativeLayout) v.getParent();
                        int id = layout.getId();

                        // int id = lv.getId();

                        db.deleteCompetitionPOIRow(id);
                        skuEntryList.remove(position);
                        notifyDataSetChanged();
                        Snackbar.make(v, "Data deleted", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                });*/


                convertView.setTag(holder);

            } else {
                holder = (ViewStockHolder) convertView.getTag();
            }



			/*holder.id.setId(position);
			holder.stock.setId(position);
			holder.sku.setId(position);
			holder.sales.setId(position);*/

            //holder.layout.setId(Integer.parseInt(poilist.get(position).getId()));
           holder.tvcategory.setText(skuEntryList.get(position).getCategory());
            holder.tvbrand.setText(skuEntryList.get(position).getBrand());
            holder.tvsku.setText(skuEntryList.get(position).getSku());
            holder.tvstock.setText(skuEntryList.get(position).getStock());
            holder.tvonetosix.setText(skuEntryList.get(position).getOnetosix());
            holder.tvseventotwelve.setText(skuEntryList.get(position).getSeventotwelve());
            holder.tvabovethiteen.setText(skuEntryList.get(position).getAbovethirteen());

            return convertView;
        }


        public class ViewStockHolder {
            TextView tvcategory;
            TextView tvsku;
            TextView tvbrand;
            TextView tvstock;
            TextView tvonetosix;
            TextView tvseventotwelve;
            TextView tvabovethiteen;
            Button delete;
            LinearLayout layout;
        }

    }

}
