package xyz.cirno.brokensandbox;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.system.StructStat;

import java.util.Locale;

public class IsolatedService extends Service {
    private final IAppZygoteDetectionResult.Stub binder = new IAppZygoteDetectionResult.Stub() {
        @Override
        public boolean isSetUidSucceeded() {
            return AppZygote.setUidSuccess;
        }

        @Override
        public String getStat() {
            StructStat st = AppZygote.statDataAdb;
            return String.format(Locale.ROOT, "st_dev=%d:%d\nst_ino=%d\nst_mode=%07o\n" +
                            "st_nlink=%d\nst_uid=%d\nst_gid=%d\nst_rdev=%d\nst_size=%d\nst_blksize=%d\n"+
                            "st_blocks=%d\nst_atime=%d\nst_mtime=%d\nst_ctime=%d",
                    st.st_dev >> 8, st.st_dev & 0xFF , st.st_ino, st.st_mode, st.st_nlink, st.st_uid, st.st_gid, st.st_rdev,
                    st.st_size, st.st_blksize, st.st_blocks, st.st_atime, st.st_mtime, st.st_ctime);
        }

        @Override
        public String[] getEntries() {

            return AppZygote.dataAdbEntries;
        }
    };
    public IsolatedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}