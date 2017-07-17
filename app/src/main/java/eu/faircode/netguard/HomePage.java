package eu.faircode.netguard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Mian Kai on 12/7/2017.
 */

public class HomePage extends AppCompatActivity
{

    private static final int MIN_SDK = Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    private static final String TAG = "NetGuard.Main";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i(TAG, "Create version=" + Util.getSelfVersionName(this) + "/" + Util.getSelfVersionCode(this));
        Util.logExtras(getIntent());

        if (Build.VERSION.SDK_INT < MIN_SDK) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.android);
            return;
        }

        Util.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        // Bottom navigation tabs
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item)
                    {
                        switch (item.getItemId()) {
                            case R.id.action_packet_sniffer:
                                //Toast.makeText(HomePage.this, "Packet Sniffer selected", Toast.LENGTH_SHORT).show();

                                Intent IOGraphIntent = new Intent(HomePage.this, ActivityLog.class);
                                startActivity(IOGraphIntent);

                                return true;


                            case R.id.action_app_filtering:
                                //Toast.makeText(HomePage.this, "Application Filtering selected", Toast.LENGTH_SHORT).show();

                                Intent packetLengthIntent = new Intent(HomePage.this, ActivityMain.class);
                                startActivity(packetLengthIntent);

                                return true;


                            case R.id.action_packet_analysis:
                                //Toast.makeText(HomePage.this, "Packet Analysis selected", Toast.LENGTH_SHORT).show();

                                Intent packetAnalysisIntent = new Intent(HomePage.this, PacketAnalysis.class);
                                startActivity(packetAnalysisIntent);

                                return true;
                        }
                        return true;
                    }
                });
    }
}
