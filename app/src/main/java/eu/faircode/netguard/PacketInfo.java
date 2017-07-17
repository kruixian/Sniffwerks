package eu.faircode.netguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static android.R.attr.version;

/**
 * Created by Mian Kai on 3/7/2017.
 */

public class PacketInfo extends AppCompatActivity
{
    private static final String TAG = "Sniffwerk.packetinfo";

    private boolean running = false;
    private AdapterLog adapter;

    private boolean resolve;
    private boolean organization;
    private InetAddress vpn4 = null;
    private InetAddress vpn6 = null;

    TextView tvView;
    private ListView lvPacketInfo;


    protected void onCreate(Bundle savedInstanceState) {
        Util.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packet_info);
        running = true;

        // Action bar
        View actionView = getLayoutInflater().inflate(R.layout.access, null, false);
        //SwitchCompat swEnabled = (SwitchCompat) actionView.findViewById(R.id.swEnabled);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(actionView);

        getSupportActionBar().setTitle(R.string.packet_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get settings
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        resolve = prefs.getBoolean("resolve", false);
        organization = prefs.getBoolean("organization", false);
        boolean log = prefs.getBoolean("log", false);

        // Listen for preference changes
        //prefs.registerOnSharedPreferenceChangeListener(this);

        /*
        lvPacketInfo = (ListView) findViewById(R.id.lvPacketInfo);

        boolean udp = prefs.getBoolean("proto_udp", true);
        boolean tcp = prefs.getBoolean("proto_tcp", true);
        boolean other = prefs.getBoolean("proto_other", true);
        boolean allowed = prefs.getBoolean("traffic_allowed", true);
        boolean blocked = prefs.getBoolean("traffic_blocked", true);

        adapter = new AdapterLog(this, DatabaseHelper.getInstance(this).getLog(udp, tcp, other, allowed, blocked), resolve, organization);
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return DatabaseHelper.getInstance(PacketInfo.this).searchLog(constraint.toString());
            }
        });

        lvPacketInfo.setAdapter(adapter);

        try {
            vpn4 = InetAddress.getByName(prefs.getString("vpn4", "10.1.10.1"));
            vpn6 = InetAddress.getByName(prefs.getString("vpn6", "fd00:1:fd00:1:fd00:1:fd00:1"));
        } catch (UnknownHostException ex) {
            Log.e(TAG, ex.toString() + "\n" + Log.getStackTraceString(ex));
        }


        PackageManager pm = getPackageManager();
        */

        tvView = (TextView) findViewById(R.id.textView);
        //lvPacketInfo = (ListView) findViewById(R.id.lvPacketInfo);


        Intent intent = getIntent();
        /*long time = cursor.getLong(cursor.getColumnIndex("time"));
        int version = cursor.getInt(cursor.getColumnIndex("version"));
        int protocol = cursor.getInt(cursor.getColumnIndex("protocol"));
        final String flags = cursor.getString(cursor.getColumnIndex("flags"));
        final String saddr = cursor.getString(cursor.getColumnIndex("saddr"));
        final int sport = (cursor.isNull(cursor.getColumnIndex("sport")) ? -1 : cursor.getInt(cursor.getColumnIndex("sport")));
        final String daddr = cursor.getString(cursor.getColumnIndex("daddr"));
        final int dport = (cursor.isNull(cursor.getColumnIndex("dport")) ? -1 : cursor.getInt(cursor.getColumnIndex("dport")));
        final String dname = cursor.getString(cursor.getColumnIndex("dname"));
        final int uid = (cursor.isNull(cursor.getColumnIndex("uid")) ? -1 : cursor.getInt(cursor.getColumnIndex("uid")));*/


        int version = intent.getIntExtra("version", 0);
        int protocol = intent.getIntExtra("protocol", 0);
        final String flags = intent.getStringExtra("flags");
        final String saddr = intent.getStringExtra("saddr");
        final int sport = intent.getIntExtra("sport", 0);
        final String daddr = intent.getStringExtra("daddr");
        final int dport = intent.getIntExtra("dport", 0);
        final String dname = intent.getStringExtra("dname");
        final int uid = intent.getIntExtra("uid", 0);
        String appName = intent.getStringExtra("appName");
        String dateTime = intent.getStringExtra("dateTime");

        /*Log.d("test" , Util.getApplicationNames(uid, ActivityLog.this) + " " + SimpleDateFormat.getDateTimeInstance().format(time) + " " + Integer.toString(version) + " " + Util.getProtocolName(protocol, version, false)
                + " " + flags + " " + saddr + " " + Integer.toString(sport) + " " + daddr + " " + Integer.toString(dport) + " " + dname + "\n");*/

        tvView.setText("\nTime : " + dateTime +
                "\n\nApplication Name : " + appName + "\n\nUID : " + uid +
                "\n\nIP Version : " + Integer.toString(version) +
                "\n\nProtocol : " + Util.getProtocolName(protocol, version, false) +
                "\n\nFlags : " + flags +
                "\n\nSource IP Address : " + saddr +
                "\n\nSource Port : " + Integer.toString(sport) +
                "\n\nDestination Address : " + daddr +
                "\n\nDestination Port : " + Integer.toString(dport) +
                "\n\nResolved Destination Name : " + dname);

        /*
        String[] mobileArray = {dateTime, "Application Name = appName","WindowsMobile","Blackberry",
                "WebOS","Ubuntu","Windows7","Max OS X"};


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.lvPacketInfo);
        listView.setAdapter(adapter);
        */
    }

};
