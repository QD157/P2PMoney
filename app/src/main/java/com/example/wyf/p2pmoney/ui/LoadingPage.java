package com.example.wyf.p2pmoney.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.wyf.p2pmoney.R;
import com.example.wyf.p2pmoney.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by WYF on 2017/3/20.
 */

public abstract class LoadingPage extends FrameLayout {

    private static final int PAGE_LOADING_STATE = 1;
    private static final int PAGE_ERROR_STATE = 2;
    private static final int PAGE_EMPTY_STATE = 3;
    private static final int PAGE_SUCCESS_STATE = 4;
    private final Context context;
    private int currentState = 1;
    private ResultState resultState = null;

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;
    private AsyncHttpClient client = new AsyncHttpClient();

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    //初始化布局
    private void init() {
        if (loadingView == null) {
            loadingView = UIUtils.inflate(R.layout.page_loading);
            addView(loadingView);
        }
        if (emptyView == null) {
            emptyView = UIUtils.inflate(R.layout.page_empty);
            addView(emptyView);
        }
        if (errorView == null) {
            errorView = UIUtils.inflate(R.layout.page_error);
            addView(errorView);
        }
        showPage();
    }

    //布局显示判断
    private void showPage() {
        loadingView.setVisibility(currentState == PAGE_LOADING_STATE ? VISIBLE : INVISIBLE);
        emptyView.setVisibility(currentState == PAGE_EMPTY_STATE ? VISIBLE : INVISIBLE);
        errorView.setVisibility(currentState == PAGE_ERROR_STATE ? VISIBLE : INVISIBLE);
        if(successView == null){
            successView = View.inflate(context, layoutId(), null);
            addView(successView);
        }
        successView.setVisibility(currentState == PAGE_SUCCESS_STATE ? VISIBLE : INVISIBLE);
    }

    public void show(){
        if (currentState != PAGE_LOADING_STATE) {
            currentState = PAGE_LOADING_STATE;
        }
        String url  = url();
        if (TextUtils.isEmpty(url)) {
            resultState = ResultState.SUCCESS;
            resultState.setContent("");
            loadPage();
        }
        client.get(url(), params(), new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                if (!TextUtils.isEmpty(content)) {
                    resultState = ResultState.SUCCESS;
                    resultState.setContent(content);
                }else{
                    resultState = ResultState.EMPTY;
                    resultState.setContent("");
                }
                loadPage();
            }

            @Override
            public void onFailure(Throwable error, String content) {
                resultState = ResultState.ERROR;
                resultState.setContent("");
                loadPage();
            }
        });
    }

    private void loadPage() {
        switch (resultState) {
            case ERROR:
                currentState = 2;
                break;
            case EMPTY:
                currentState = 3;
                break;
            case SUCCESS:
                currentState = 4;
                break;
        }
        showPage();
        if (currentState == PAGE_SUCCESS_STATE){
            onSuccess(resultState, successView);
        }
    }

    public abstract int layoutId();

    public abstract void onSuccess(ResultState resultState,  View successView);

    protected abstract RequestParams params();

    protected abstract String url();

    public enum ResultState{
        ERROR(2), EMPTY(3), SUCCESS(4);
        private int state;
        private String content;

        ResultState(int state){
            this.state = state;
        }

        public void setState(int state){
            this.state = state;
        }

        public int getState(){
            return state;
        }

        public void setContent(String content){
            this.content = content;
        }

        public String getContent(){
            return content;
        }
    }
}
