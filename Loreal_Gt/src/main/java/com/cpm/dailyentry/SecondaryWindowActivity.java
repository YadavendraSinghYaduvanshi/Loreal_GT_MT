package com.cpm.dailyentry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.xmlGetterSetter.WindowListGetterSetter;

import java.util.Collections;
import java.util.List;

public class SecondaryWindowActivity extends AppCompatActivity {

    RecyclerView rec_window;
    ValueAdapter adapter;

    GSKDatabase db;
    private SharedPreferences preferences;
    String store_cd, visit_date, username, intime, store_type_cd, state_cd;
    String _pathforcheck, _path, str, img1 = "";
    ImageView icon_window;

    List<WindowListGetterSetter> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_window);
        declaration();
    }

    @Override
    protected void onResume() {
        super.onResume();

        data = db.getWindowListData(state_cd, store_type_cd);

        if (data.size() > 0) {
            adapter = new ValueAdapter(getApplicationContext(), data);
            rec_window.setAdapter(adapter);
            rec_window.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
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
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.MyViewHolder> {

        private LayoutInflater inflator;
        List<WindowListGetterSetter> data = Collections.emptyList();

        public ValueAdapter(Context context, List<WindowListGetterSetter> data) {
            inflator = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public ValueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

            View view = inflator.inflate(R.layout.item_window_list_layout, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ValueAdapter.MyViewHolder viewHolder, final int position) {

            final WindowListGetterSetter current = data.get(position);
            viewHolder.tv_window.setText(current.getWindow());

            viewHolder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (db.isWindowSkuDataFilled(store_cd, current.getWindow_cd().get(0))) {

                        Snackbar.make(v, "Data already filled", Snackbar.LENGTH_SHORT).show();
                    } else {

                        Intent in = new Intent(getApplicationContext(), ChecklistActivity.class);

                        in.putExtra(CommonString.KEY_WINDOW_CD, current.getWindow_cd().get(0));
                        in.putExtra(CommonString.KEY_SKU_HOLD, current.getSku_hold());
                        in.putExtra(CommonString.KEY_BRAND_CD, current.getBrand_cd());
                        in.putExtra(CommonString.KEY_EXISTS, current.islisted());
                        in.putExtra(CommonString.KEY_WINDOW_IMAGE, current.getWindow_image());
                        in.putExtra("OBJECT", current);

                        startActivity(in);

                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                    }

                }
            });

            if (db.isWindowSkuDataFilled(store_cd, current.getWindow_cd().get(0))) {
                viewHolder.window_icon.setBackgroundResource(R.drawable.window_icon_done);
            } else {
                viewHolder.window_icon.setBackgroundResource(R.drawable.window_icon);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv_window;
            LinearLayout parent;
            ImageView window_icon;


            public MyViewHolder(View itemView) {
                super(itemView);
                tv_window = (TextView) itemView.findViewById(R.id.tv_window);
                parent = (LinearLayout) itemView.findViewById(R.id.lay_window);
                window_icon = (ImageView) itemView.findViewById(R.id.icon_window);
            }
        }

    }

    void declaration() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Window");
        db = new GSKDatabase(getApplicationContext());
        db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        visit_date = preferences.getString(CommonString1.KEY_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        state_cd = preferences.getString(CommonString1.KEY_STATE_CD, null);
        store_type_cd = preferences.getString(CommonString1.KEY_STORE_TYPE_CD, null);
        rec_window = (RecyclerView) findViewById(R.id.rec_window);
        icon_window = (ImageView) findViewById(R.id.icon_window);

    }


}
