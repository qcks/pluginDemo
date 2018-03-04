package qckiss.com.proxyapp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * PluginManger
 *
 * @author qckiss
 * @date 2018-3-4
 */

public class PluginManger {
    private static PluginManger pluginManger = new PluginManger();
    private DexClassLoader dexClassLoader;
    private Resources resources;
    private Context context;
    private String entryActivityName;
    public static PluginManger getInstance(){
        return pluginManger;
    }
    private PluginManger(){

    }
    public void loadPath(String path){
        File dexOutFile = context.getDir("dex",Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(path, dexOutFile.getAbsolutePath(), null, context.getClassLoader());
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path,PackageManager.GET_ACTIVITIES);
        entryActivityName = packageInfo.activities[0].name;


        /**
         * Create a new Resources object on top of an existing set of assets in an
         * AssetManager.
         *
         * @deprecated Resources should not be constructed by apps.
         * See {@link android.content.Context#createConfigurationContext(Configuration)}.
         *
         * @param assets Previously created AssetManager.
         * @param metrics Current display metrics to consider when
         *                selecting/computing resource values.
         * @param config Desired device configuration to consider when
         *               selecting/computing resource values (optional).
         */

        AssetManager assets = null;
        try {
            assets =AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath",String.class);
            addAssetPath.invoke(assets,path);
            resources =  new Resources(assets, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
            Log.d("resources == null?",(resources==null)+"");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public String getEntryActivityName() {
        return entryActivityName;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }
}
