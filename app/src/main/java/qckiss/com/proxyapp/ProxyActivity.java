package qckiss.com.proxyapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import qckiss.com.proxyinteface.IProxyActivity;

import static qckiss.com.proxyinteface.IProxyActivity.CLASSNAME;

/**
 * 装载所有插件Activity的容器
 */
public class ProxyActivity extends Activity {
    /**
     * 插件的入口activity名
     */
    public String entryActivityName;
    private String className;
    private IProxyActivity iProxyActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra(CLASSNAME);
        Class activityClass = null;
        try {
            activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            iProxyActivity = (IProxyActivity) constructor.newInstance(new Object[]{});
            Bundle bundle = new Bundle();
            iProxyActivity.onAttach(this);
            iProxyActivity.onCreate(bundle);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        iProxyActivity.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        iProxyActivity.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        iProxyActivity.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        iProxyActivity.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iProxyActivity.onDestroy();
    }

    @Override
    public void startActivity(Intent intent) {
        String toActivityName = intent.getStringExtra(CLASSNAME);
        Intent newIntent = new Intent(this,ProxyActivity.class);
        newIntent.putExtra(CLASSNAME,toActivityName);
        super.startActivity(newIntent);
    }

    @Override
    public Resources getResources() {
        return PluginManger.getInstance().getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManger.getInstance().getDexClassLoader();
    }
}
