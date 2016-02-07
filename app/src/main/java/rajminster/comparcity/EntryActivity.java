package rajminster.comparcity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


public class EntryActivity extends AppCompatActivity {
    EditText currentCity;
    EditText currentState;
    EditText currentSalary;
    EditText futureCity;
    EditText futureState;
    EditText futureSalary;
    Spinner maritalStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentCity = (EditText) findViewById(R.id.currentCity);
        currentState = (EditText) findViewById(R.id.currentState);
        currentSalary = (EditText) findViewById(R.id.currentSalary);
        futureCity = (EditText) findViewById(R.id.futureCity);
        futureState = (EditText) findViewById(R.id.futureState);
        futureSalary = (EditText) findViewById(R.id.futureSalary);
        maritalStatus = (Spinner) findViewById(R.id.maritalStatus);
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setVisibility(View.INVISIBLE);
     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //new RetrieveData().execute();
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if (currentCity.length() != 0 && currentSalary.length() != 0 && futureCity.length() != 0 && futureSalary.length() != 0) {
                    progress.setVisibility(View.VISIBLE);
                    progress.setProgress(10);
                    Intent intent = new Intent(EntryActivity.this, ReportActivity.class);
                    intent.putExtra("Current City", currentCity.getText().toString());
                    intent.putExtra("Current State", currentState.getText().toString());
                    intent.putExtra("Current Salary", Double.parseDouble(currentSalary.getText().toString()));
                    intent.putExtra("Future City", futureCity.getText().toString());
                    intent.putExtra("Future State", futureState.getText().toString());
                    intent.putExtra("Future Salary", Double.parseDouble(futureSalary.getText().toString()));
                    intent.putExtra("Marital Status", maritalStatus.getSelectedItem().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Fill out all values", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
