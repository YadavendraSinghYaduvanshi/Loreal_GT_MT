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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.NavMenuItemGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.geotag.LocationActivity;
import com.cpm.xmlGetterSetter.MiddayStockInsertData;
import com.cpm.xmlGetterSetter.OpeningStockInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.StockGetterSetter;
import com.cpm.xmlGetterSetter.WindowListGetterSetter;
import com.cpm.xmlGetterSetter.WindowSKUEntryGetterSetter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class StoreEntryForChannel2Activity  extends AppCompatActivity implements OnClickListener{

    GSKDatabase db;
    private SharedPreferences preferences;
    String store_cd,state_cd,store_type_cd;

    boolean food_flag,user_flag=false;

    String user_type="",GEO_TAG="",FIRST_VISIT="";

    private ArrayList<StockGetterSetter> stockData = new ArrayList<StockGetterSetter>();

    HashMap<OpeningStockInsertDataGetterSetter, List<MiddayStockInsertData>> listDataChild;

    List<OpeningStockInsertDataGetterSetter> listDataHeader;
    ArrayList<MiddayStockInsertData> skuData;

    ValueAdapter adapter;
    boolean count;
    RecyclerView recyclerView;
    private ArrayList<WindowListGetterSetter> windowdata = new ArrayList<WindowListGetterSetter>();

    List<WindowSKUEntryGetterSetter> WINDOWSIZE= new ArrayList<WindowSKUEntryGetterSetter>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_entry_for_channel2);

        declaration();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daily Activity Menu");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        recyclerView=(RecyclerView) findViewById(R.id.drawer_layout_recycle);

        adapter=new ValueAdapter(getApplicationContext(),getdata());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }


    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub

        int id=view.getId();

        switch (id) {
            case R.id.performance:

                Intent startPerformance = 	new Intent(StoreEntryForChannel2Activity.this,Performance.class);
                startActivity(startPerformance);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.empty_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==android.R.id.home){
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }

        return super.onOptionsItemSelected(item);
    }

    public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.MyViewHolder>{

        private LayoutInflater inflator;

        List<NavMenuItemGetterSetter> data= Collections.emptyList();

        public ValueAdapter(Context context, List<NavMenuItemGetterSetter> data){

            inflator = LayoutInflater.from(context);
            this.data=data;

        }

        @Override
        public ValueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

            View view=inflator.inflate(R.layout.custom_row,parent,false);

            MyViewHolder holder=new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final ValueAdapter.MyViewHolder viewHolder, final int position) {

            final NavMenuItemGetterSetter current=data.get(position);

            //viewHolder.txt.setText(current.txt);
            viewHolder.icon.setImageResource(current.getIconImg());
            viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(current.getIconImg()==R.drawable.share_of_shelf || current.getIconImg()==R.drawable.share_of_shelf_done){

                       // if(!db.isShareShelfDataFilled(store_cd))
                        {

                            Intent in1=new Intent(getApplicationContext(),ShareOfShelfActivity.class);
                            startActivity(in1);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        }

//                        else{
//                            Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();
//                        }


                    }
                    if(current.getIconImg()==R.drawable.stock_availibilty || current.getIconImg()==R.drawable.stock_availibilty_done){

                       // if(!db.isStockData2Filled(store_cd))
                        {

                            Intent in3=new Intent(getApplicationContext(),StockAvailabilityForChannel2Activity.class);
                            startActivity(in3);

                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        }

//                        else{
//                            Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();
//                        }

                    }


                    if(current.getIconImg()==R.drawable.paid_visibility || current.getIconImg()==R.drawable.paid_visibility_done){

                         if(db.getDisplayDataFromStore(store_cd).size()>0)
                        {
                            Intent in4 = new Intent(getApplicationContext(), PaidVisibilityActivity.class);
                            startActivity(in4);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        }
                        else
                        {
                            Snackbar.make(recyclerView, "No Data", Snackbar.LENGTH_SHORT).show();
                        }

                    }


                    if(current.getIconImg()==R.drawable.free_visibility || current.getIconImg()==R.drawable.free_visibility_done){
                      //   if(!db.isFreeVisibilityFilled(store_cd))
                        {
                            Intent in4 = new Intent(getApplicationContext(), FreeVisibilityActivity.class);
                            startActivity(in4);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        }
                        /*else
                        {
                            Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();
                        }*/
                    }


                    if(current.getIconImg()==R.drawable.promotion || current.getIconImg()==R.drawable.promotion_done){

                        if(db.getMapppingPromotionData(store_cd).size()>0) {
                            Intent in4 = new Intent(getApplicationContext(), PromotionActivity.class);
                            startActivity(in4);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        }
                        else
                        {
                            Snackbar.make(recyclerView, "No Data", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                    if(current.getIconImg()==R.drawable.geotag1 || current.getIconImg()==R.drawable.geotag_done1){

                        if(GEO_TAG.equalsIgnoreCase(CommonString1.KEY_GEO_Y))
                        {
                            Toast.makeText(getApplicationContext(),"GoeTag Already Done",Toast.LENGTH_LONG).show();
                        }

                        else if(db.getGeotaggingData(store_cd).size()>0){

                            if(db.getGeotaggingData(store_cd).get(0).getGEO_TAG().equalsIgnoreCase(CommonString1.KEY_GEO_Y))
                            {
                                Toast.makeText(getApplicationContext(),"GoeTag Already Done",Toast.LENGTH_LONG).show();
                            }
                        }
                       else
                        {
                            Intent in4=new Intent(getApplicationContext(),LocationActivity.class);
                            in4.putExtra(CommonString1.KEY_FROM_STORELIST,false);
                            in4.putExtra(CommonString1.KEY_STORE_CD,store_cd);
                            startActivity(in4);

                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        }

                    }


                    if(current.getIconImg()==R.drawable.closing_stock || current.getIconImg()==R.drawable.closing_stock_done){

                        if(db.isOpeningDataFilled(store_cd)){

                            if(db.isMiddayDataFilled(store_cd)){

                                Intent in2=new Intent(getApplicationContext(),ClosingStock.class);

                                startActivity(in2);

                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                            }
                            else{

                                Snackbar.make(recyclerView,"First fill Midday Stock Data",Snackbar.LENGTH_SHORT).show();
                            }

                        }else{

                            Snackbar.make(recyclerView,"First fill Opening Stock data",Snackbar.LENGTH_SHORT).show();

                        }

                    }

                    if(current.getIconImg()==R.drawable.competition1 || current.getIconImg()==R.drawable.cpmpetition_done1){

                        Intent in7=new Intent(getApplicationContext(),COMPETITORActivity.class);

                        startActivity(in7);

                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    }

                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder{

            //TextView txt;
            ImageView icon;

            public MyViewHolder(View itemView) {
                super(itemView);
                //txt=(TextView) itemView.findViewById(R.id.list_txt);
                icon=(ImageView) itemView.findViewById(R.id.list_icon);
            }
        }

    }

    public List<NavMenuItemGetterSetter> getdata(){
        List<NavMenuItemGetterSetter> data=new ArrayList<>();

        int Shareofshelf, skudata,paidVisibility,freeVisibility,promotion, geotag;

        if(db.isShareShelfDataFilled(store_cd))
        {
            Shareofshelf = R.drawable.share_of_shelf_done;
        }
        else{
            Shareofshelf = R.drawable.share_of_shelf;
        }

        if(db.isStockData2Filled(store_cd))
        {
            skudata = R.drawable.stock_availibilty_done;
        }
        else{
            skudata = R.drawable.stock_availibilty;
        }

        if(db.isPaidVisibilityFilled(store_cd))
        {
            paidVisibility = R.drawable.paid_visibility_done;
        }
        else{
            paidVisibility = R.drawable.paid_visibility;
        }


        if(db.isFreeVisibilityFilled(store_cd))
        {
            freeVisibility = R.drawable.free_visibility_done;
        }
        else{
            freeVisibility = R.drawable.free_visibility;
        }

        if(db.isPromotionDataFilled(store_cd))
        {
            promotion = R.drawable.promotion_done;
        }
        else{
            promotion  = R.drawable.promotion;
        }



        if(db.getGeotaggingData(store_cd).size()>0){

            if(db.getGeotaggingData(store_cd).get(0).getGEO_TAG().equalsIgnoreCase(CommonString1.KEY_GEO_Y)){

                geotag = R.drawable.geotag_done1;
            }

            else{

                geotag = R.drawable.geotag1;
            }
        }

        else{

            if(GEO_TAG.equalsIgnoreCase(CommonString1.KEY_GEO_Y))
            {
                geotag = R.drawable.geotag_done1;
            }
            else
            {
                geotag = R.drawable.geotag1;
            }


        }

      /*  if(user_type.equals("Promoter")){
            //int img[]={Stock, middayImg, windows, assetImg, closingImg, additionalImg, competitionImg};
            int img[]={ middayImg, windows, closingImg, additionalImg, competitionImg};
            for(int i=0;i<img.length;i++){

                NavMenuItemGetterSetter recData=new NavMenuItemGetterSetter();
                recData.setIconImg(img[i]);
                //recData.setIconName(text[i]);
                data.add(recData);
            }
        }
        else*/
        if(user_type.equals("Merchandiser")){
            int img[]={Shareofshelf,skudata,paidVisibility,freeVisibility,promotion,geotag};

            for(int i=0;i<img.length;i++){

                NavMenuItemGetterSetter recData=new NavMenuItemGetterSetter();
                recData.setIconImg(img[i]);
                //recData.setIconName(text[i]);
                data.add(recData);
            }
        }

        return  data;
    }

    public boolean isEachWindowFilled()
    {
        boolean isFilled = true;
        for(int i=0;i<windowdata.size();i++)
        {
            if(!db.getwindowDataForEachWindow(store_cd,windowdata.get(i).getWindow_cd().get(0)))
            {
                isFilled = false;
                break;
            }
        }

        return isFilled;
    }

    void declaration()
    {
        db=new GSKDatabase(getApplicationContext());
        db.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        store_type_cd = preferences.getString(CommonString1.KEY_STORE_TYPE_CD, null);
        state_cd = preferences.getString(CommonString1.KEY_STATE_CD, null);
        food_flag=preferences.getBoolean(CommonString1.KEY_FOOD_STORE, false);
        GEO_TAG=preferences.getString(CommonString1.KEY_GEO_TAG, null);
        user_type = preferences.getString(CommonString1.KEY_USER_TYPE, null);
    }

}
