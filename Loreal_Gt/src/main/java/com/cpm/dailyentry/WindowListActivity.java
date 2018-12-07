package com.cpm.dailyentry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.xmlGetterSetter.WindowGetterSetter;

import java.util.ArrayList;


public class WindowListActivity extends AppCompatActivity {

    GSKDatabase db;
    ArrayList<WindowGetterSetter> windowList;

    RecyclerView recyclerView;
    WindowAdapter windowAdapter;
    LinearLayout parent_linear,nodata_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Window Planogram");

        nodata_linear = (LinearLayout) findViewById(R.id.no_data_lay);
       // parent_linear = (LinearLayout) findViewById(R.id.parent_linear);

        db=new GSKDatabase(getApplicationContext());
        db.open();

        windowList = db.getWindowListData();

        recyclerView=(RecyclerView) findViewById(R.id.rec_window);

        if(windowList.size()>0){

            windowAdapter=new WindowAdapter();
            recyclerView.setAdapter(windowAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        }
        else
        {
            recyclerView.setVisibility(View.GONE);
          //  parent_linear.setBackgroundColor((getResources().getColor(R.color.grey_light)));
            nodata_linear.setVisibility(View.VISIBLE);
        }

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home){

            // NavUtils.navigateUpFromSameTask(this);
            finish();

            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

        }

        return super.onOptionsItemSelected(item);
    }

    class WindowAdapter extends RecyclerView.Adapter<WindowAdapter.ViewHolder>{

        private LayoutInflater inflator;

        WindowAdapter(){
            inflator = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=inflator.inflate(R.layout.window_list_item,parent,false);

            ViewHolder holder=new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final WindowGetterSetter current=windowList.get(position);

            //viewHolder.txt.setText(current.txt);

            holder.tv_window.setText(current.getWindow());
            holder.parent_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getApplicationContext(), WindowPlanogramActivity.class);
                    in.putExtra(CommonString.KEY_WINDOW,current.getWindow());
                    in.putExtra(CommonString.KEY_WINDOW_IMAGE,current.getPlanogram());
                    startActivity(in);
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                }
            });

        }

        @Override
        public int getItemCount() {
            return windowList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView tv_window;
            RelativeLayout parent_layout;

            public ViewHolder(View itemView) {
                super(itemView);
                tv_window = (TextView) itemView.findViewById(R.id.tv_window);
                parent_layout = (RelativeLayout) itemView.findViewById(R.id.parent_layout);
            }
        }
    }

}
