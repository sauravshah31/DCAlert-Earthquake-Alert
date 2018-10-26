package np.com.sauravshah.practise;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {


    private Button dissableButton;
    private TextView ssidTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer soundMP = MediaPlayer.create(this,R.raw.earth);
        dissableButton = (Button) this.findViewById(R.id.buttonDissable);
        dissableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ssidTextView = (TextView) this.findViewById(R.id.textView);

        final WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

                if(!wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(true);
                }


        dissableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                }
            }
        });

        if(wifiManager.isWifiEnabled()) {
            String sid;

            sid = "";
            int i=0;

            while(i!=1) {
                List<ScanResult> list = wifiManager.getScanResults();
                StringBuilder sb = new StringBuilder();

                for (ScanResult scanResult : list) {

                    sid = scanResult.SSID;
                    if (sid.equals("SNum")) {
                        //earthquake alert goes here
                        soundMP.start();
                        dissableButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });  dissableButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                soundMP.stop();

                            }
                        });
                        i = 1;
                        break;
                    }
                }


            }
        }


    }
}