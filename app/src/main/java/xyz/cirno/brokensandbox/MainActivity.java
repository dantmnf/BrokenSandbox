package xyz.cirno.brokensandbox;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Defines callbacks for service binding, passed to bindService() */
    private final ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            IAppZygoteDetectionResult result = IAppZygoteDetectionResult.Stub.asInterface(service);
            try {
                String textresult = "setuid(0) " +
                        (result.isSetUidSucceeded() ? "succeeded" : "failed") +
                        "\n\nstat /data/adb:\n" +
                        result.getStat() +
                        "\n\nEntries in /data/adb:\n" +
                        String.join("\n", result.getEntries());
                ((TextView)findViewById(R.id.resultText)).setText(textresult);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            unbindService(connection);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(this, IsolatedService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}