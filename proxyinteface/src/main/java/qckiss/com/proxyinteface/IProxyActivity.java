package qckiss.com.proxyinteface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by qckiss on 2018/2/2.
 */

public interface IProxyActivity {

    String CLASSNAME = "className";
    /**
     * 注入上下文
     * @param activity
     */
    void onAttach(Activity activity);

    void onCreate(@Nullable Bundle savedInstanceState);


    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int requestCode);

//    void onRestart();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

//    void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState);
//
//    boolean onTouchEvent(MotionEvent event);
//
//    void onBackPressed();

//    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
//
//    boolean onKeyDown(int keyCode, KeyEvent event);

}
