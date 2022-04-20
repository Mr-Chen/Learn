package com.imooc.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements TextWatcher, Inputtips.InputtipsListener {

    private static final String TAG = "POIS";
    private static final String BJ_CODE = "110000";

    private EditText mEditText;
    private RecyclerView mRecyclerView;
    private MyRVAdapter adapter;

    private PoiSearch.Query query;
    private PoiSearch poiSearch;

    private List<Tip> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mEditText = findViewById(R.id.edit);
        mRecyclerView = findViewById(R.id.rv);

        mEditText.addTextChangedListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        adapter = new MyRVAdapter(this, list);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                PermissionUtils.checkPermission(SearchActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);


                Poi end = new Poi(list.get(position).getName(),
                        new LatLng(list.get(position).getPoint().getLongitude(),
                                list.get(position).getPoint().getLatitude()),
                        list.get(position).getPoiID());

                AmapNaviParams params = new AmapNaviParams(null, null, end, AmapNaviType.DRIVER, AmapPageType.ROUTE);
                // 启动组件
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);

            }
        });

      /*  query = new PoiSearch.Query(charSequence.toString(),"",BJ_CODE);
        query.setPageSize(15);
        query.setPageNum(2);
        query.setCityLimit(true);

//        构造 PoiSearch 对象，并设置监听。
        try {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
        } catch (AMapException e) {
            e.printStackTrace();
        }

        poiSearch.searchPOIAsyn();*/


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {


    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (charSequence.toString().isEmpty()) {
            adapter.setData(null);
            return;
        }

        //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
        InputtipsQuery inputquery = new InputtipsQuery(charSequence.toString(), BJ_CODE);
        inputquery.setCityLimit(true);//限制在当前城市

//        3、构造 Inputtips 对象，并设置监听。
        Inputtips inputTips = new Inputtips(SearchActivity.this, inputquery);
        inputTips.setInputtipsListener(this);

        inputTips.requestInputtipsAsyn();


    }


    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        adapter.setData(list);

    }
}