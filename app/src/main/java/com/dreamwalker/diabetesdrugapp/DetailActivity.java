package com.dreamwalker.diabetesdrugapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    //private TextView txtPos, txtName, txtDetail;

    private RecyclerView recyclerView;
    private DrugMainAdapter drugMainAdapter;
    private List<DrugMainData> drugList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.e(TAG, "DetailActivity onCreate !");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        drugList = new ArrayList<>();
        drugMainAdapter = new DrugMainAdapter(this,drugList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(drugMainAdapter);
//        txtPos = (TextView) findViewById(R.id.txt_posision);
//        txtName = (TextView) findViewById(R.id.txt_name);
//        txtDetail = (TextView) findViewById(R.id.txt_detail);

        Intent intent = getIntent();
        Log.e(TAG, "intent 수신");
        int pos = getIntent().getExtras().getInt("position");
        String drugName = getIntent().getExtras().getString("original_name");
        String drugDetail = getIntent().getExtras().getString("detail");

//        txtPos.setText(String.valueOf(pos));
//        txtName.setText(drugName);
//        txtDetail.setText(drugDetail);

        switch (pos){
            case 0:
                sulfoneData();
                break;
        }

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop_detail));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initCollapsingToolbar(){

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_detail);
        collapsingToolbarLayout.setTitle(" ");

        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.appbar_detail);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;


            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    isShow = true;
                }else if(isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }
    private void sulfoneData(){
        int[] covers = new int[]{R.drawable.diabetes_main_01,
                R.drawable.diabetes_main_02,
                R.drawable.diabetes_main_03,
                R.drawable.diabetes_main_04,
                R.drawable.diabetes_main_05};

        DrugMainData drugData = new DrugMainData("설폰요소계", "인슐린 분비 촉진제",covers[3]);
        drugList.add(drugData);
        drugData = new DrugMainData("비구아나이드계", "인슐린 저항성 개선제",covers[0]);
        drugList.add(drugData);
        drugData = new DrugMainData("알파-글루코시다제 제해제", "식후 혈당 강하제",covers[3]);
        drugList.add(drugData);
        drugData = new DrugMainData("티아졸리딘디온계", "인슐림 감수성 개선제",covers[0]);
        drugList.add(drugData);
        drugData = new DrugMainData("메글리티나이드계", "속효성 인슐린 분비 촉진제",covers[3]);
        drugList.add(drugData);
        drugData = new DrugMainData("DPP-4 억제제", "인크레틴 억제제",covers[0]);
        drugList.add(drugData);
        drugData = new DrugMainData("SGLP 2 억제제", "포도당 재흡수 저해제",covers[3]);
        drugList.add(drugData);
        drugData = new DrugMainData("엑세나이트", "인크레틴 유사체",covers[4]);
        drugList.add(drugData);
        drugData = new DrugMainData("초속효성 인슐린", "인슐린 요법",covers[2]);
        drugList.add(drugData);
        drugData = new DrugMainData("속효성 인슐린", "인슐린 요법",covers[4]);
        drugList.add(drugData);
        drugData = new DrugMainData("중간형 인슐린", "인슐린 요법",covers[2]);
        drugList.add(drugData);
        drugData = new DrugMainData("장시간 인슐린", "인슐린 요법",covers[4]);
        drugList.add(drugData);
        drugData = new DrugMainData("혼합형 인슐린", "인슐린 요법",covers[2]);
        drugList.add(drugData);
        drugMainAdapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int position = parent.getChildAdapterPosition(view); // item 위치
            int column = position % spanCount; // item 열

            if (includeEdge){
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount){
                    outRect.top = spacing;

                }
                outRect.bottom = spacing;

            }else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp){
        Resources resources = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,resources.getDisplayMetrics()));
    }


}
