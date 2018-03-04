package qckiss.com.pluginapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import qckiss.com.proxyinteface.IProxyActivity;

/**
 * BaseActivity
 *
 * @author qckiss
 * @date 2017-12-20
 */
public class BaseActivity extends Activity implements IProxyActivity{
    /***
     * 日志输出标志
     **/
//    protected String TAG = this.getClass().getSimpleName();

    protected Activity injectContext;//注入的
    @Override
    public Resources getResources() {
        if(injectContext == null){
            return  super.getResources();
        }else{
            return  injectContext.getResources();
        }
    }

    @Override
    public void onAttach(Activity activity){
        injectContext = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(injectContext == null){
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if(injectContext == null){
            super.startActivityForResult(intent, requestCode);
        }else{
            injectContext.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void onStart() {
        if(injectContext == null){
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if(injectContext == null){
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if(injectContext == null){
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if(injectContext == null){
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if(injectContext == null){
            super.onDestroy();
        }
    }
    @Override
    public void setContentView(@LayoutRes int layoutId) {
        if(injectContext == null){
            super.setContentView(layoutId);
        }else{
            injectContext.setContentView(layoutId);
        }
    }

    @Override
    public View findViewById(@IdRes int id) {
        if(injectContext == null){
            return super.findViewById(id);
        }else{
            return injectContext.findViewById(id);
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if(injectContext == null){
            return super.getClassLoader();
        }else{
            return injectContext.getClassLoader();
        }
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if(injectContext == null){
            return super.getLayoutInflater();
        }else{
            return injectContext.getLayoutInflater();
        }
    }

    @Override
    public Window getWindow() {
        if(injectContext == null){
            return super.getWindow();
        }else{
            return injectContext.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if(injectContext == null){
            return super.getWindowManager();
        }else{
            return injectContext.getWindowManager();
        }
    }

    @Override
    public void startActivity(Intent intent) {

        if(injectContext == null){
            super.startActivity(intent);
        }else{
            Intent newIntent = new Intent();
            intent.putExtra(CLASSNAME,intent.getComponent().getClassName());
            injectContext.startActivity(intent);
        }
    }


    @Override
    public Context getBaseContext() {
        if(injectContext == null){
            return super.getBaseContext();
        }else{
            return injectContext;
        }
    }
}