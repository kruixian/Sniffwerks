package eu.faircode.netguard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mian Kai on 12/7/2017.
 */

public class Conversation extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        Util.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);

    }
}
