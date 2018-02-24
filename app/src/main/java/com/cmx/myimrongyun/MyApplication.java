package com.cmx.myimrongyun;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.cmx.myimrongyun.bean.UnReadMessage;
import com.cmx.myimrongyun.util.MyUnReadMessageUtil;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.push.RongPushClient;
import io.rong.push.common.RongException;

/**
 * Created by Administrator on 2017/12/22/0022.
 */

public class MyApplication extends Application {
    public static List<String>  myList = new ArrayList();
//    public static List<UnReadMessage> unReadMessageCountList=new ArrayList<>();
    public static MyUnReadMessageUtil myUnReadMessageUtil;
    @Override
    public void onCreate() {
        super.onCreate();
        myUnReadMessageUtil=new MyUnReadMessageUtil(this);

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);
            //RongPushClient.init(this,"x18ywvqfxnmwc");
            try {
                RongPushClient.checkManifest(this);
            } catch (RongException e) {
                e.printStackTrace();
            }
        }

        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new SampleExtensionModule());
            }
        }

       // RongIM.getInstance().registerConversationTemplate(new MyDiscussionConversationProvider());
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
