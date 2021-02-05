package xyz.cirno.brokensandbox;

import android.app.ZygotePreload;
import android.content.pm.ApplicationInfo;
import android.system.ErrnoException;
import android.system.Os;
import android.system.StructStat;
import android.util.Log;

import java.io.File;

public class AppZygote implements ZygotePreload {
    public static boolean setUidSuccess = false;
    public static StructStat statDataAdb;
    public static String[] dataAdbEntries;

    @Override
    public void doPreload(ApplicationInfo appInfo) {
        try {
            Os.setuid(0);
            setUidSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            setUidSuccess = false;
        }
        try {
            statDataAdb = Os.stat("/data/adb");
        } catch (ErrnoException e) {
            e.printStackTrace();
        }
        dataAdbEntries = new File("/data/adb").list();
    }
}
