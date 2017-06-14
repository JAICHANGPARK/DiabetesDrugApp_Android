package com.dreamwalker.diabetesdrugapp;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DrugMainAdapter drugMainAdapter;
    private List<DrugMainData> drugList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        drugList = new ArrayList<>();
        drugMainAdapter = new DrugMainAdapter(this, drugList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(drugMainAdapter);

        prepareData();

        try {
            Glide.with(this).load(R.drawable.cover_2).into((ImageView)findViewById(R.id.backdrop));
        }catch (Exception ex){
            ex.printStackTrace();
        }


//        mCardImage = (ImageView)findViewById(R.id.cardImage);
//        mCardTitle = (TextView)findViewById(R.id.txtName);
//        mCardDetail = (TextView)findViewById(R.id.txtDetail);
//
//        int imageResource = getResources().getIdentifier("@drawable/diabetes_main_01",null,this.getPackageName());
//        mCardImage.setImageResource(imageResource);

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void prepareData(){
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
}
