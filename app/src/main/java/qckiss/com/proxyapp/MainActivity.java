package qckiss.com.proxyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import qckiss.com.proxyinteface.IProxyActivity;

/**
 * 宿主activity入口
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManger.getInstance().setContext(this);

    }

    boolean loadApked;
    public void startJump(View view) {
        if(!loadApked){
            Toast.makeText(this,"请先安装插件！",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(this,ProxyActivity.class);
        intent.putExtra(IProxyActivity.CLASSNAME,PluginManger.getInstance().getEntryActivityName());
        startActivity(intent);
    }

    public void loadApk(View view) {
        File apkFile = new File(Environment.getExternalStorageDirectory(),"pluginapp.apk");
        PluginManger.getInstance().loadPath(apkFile.getPath());
        loadApked = true;
        Toast.makeText(this,"插件已安装！",Toast.LENGTH_LONG).show();
    }
}
