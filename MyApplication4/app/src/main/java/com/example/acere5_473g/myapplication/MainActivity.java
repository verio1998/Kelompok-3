package com.example.acere5_473g.myapplication;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.PopupMenu;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // create variable
    Button btnclickme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // load control
        btnclickme = (Button) findViewById(R.id.button);

        btnclickme.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Create the instance of Menu
                PopupMenu popup = new PopupMenu(MainActivity.this, btnclickme);
                // Inflating menu using xml file
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                // registering OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,
                                "Anda telah memilih : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });
    }
}