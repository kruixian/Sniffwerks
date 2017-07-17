package eu.faircode.netguard;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


/**
 * Created by Mian Kai on 12/7/2017.
 */

public class PacketAnalysis extends AppCompatActivity {

    private static final String TAG = "Sniffwerk.packetinfo";

    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packet_analysis);
        running = true;

        // Action bar
        View actionView = getLayoutInflater().inflate(R.layout.access, null, false);
        //SwitchCompat swEnabled = (SwitchCompat) actionView.findViewById(R.id.swEnabled);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(actionView);

        getSupportActionBar().setTitle(R.string.packet_analysis);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get settings
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item)
                    {
                        switch (item.getItemId()) {
                            case R.id.action_io_graph:
                                Toast.makeText(PacketAnalysis.this, "IO Graph selected", Toast.LENGTH_SHORT).show();

                                Intent IOGraphIntent = new Intent(PacketAnalysis.this, IOGraph.class);
                                startActivity(IOGraphIntent);

                                return true;

                            case R.id.action_packet_length:
                                Toast.makeText(PacketAnalysis.this, "Packet length selected", Toast.LENGTH_SHORT).show();

                                Intent packetLengthIntent = new Intent(PacketAnalysis.this, PacketLength.class);
                                startActivity(packetLengthIntent);

                                return true;

                            case R.id.action_conversation:
                                Toast.makeText(PacketAnalysis.this, "Conversation selected", Toast.LENGTH_SHORT).show();

                                Intent conversationIntent = new Intent(PacketAnalysis.this, Conversation.class);
                                startActivity(conversationIntent);

                                return true;
                        }
                        return true;
                    }
                });

    }
}