package alan.example.com.persontestdemo.statubar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import alan.example.com.persontestdemo.R;

public class StatuBarMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_statu_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

        //StatusBarCompat.compat(this, 0xFFFF0000);
        StatusBarCompat.compat(this);

    }

}
