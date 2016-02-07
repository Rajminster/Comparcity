package rajminster.comparcity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wolfram.alpha.alpha.WAEngine;
import com.wolfram.alpha.alpha.WAException;
import com.wolfram.alpha.alpha.WAPlainText;
import com.wolfram.alpha.alpha.WAPod;
import com.wolfram.alpha.alpha.WAQuery;
import com.wolfram.alpha.alpha.WAQueryResult;
import com.wolfram.alpha.alpha.WASubpod;

public class ReportActivity extends AppCompatActivity {
    String currentCity;
    String currentState;
    double currentSalary;
    String futureCity;
    String futureState;
    double futureSalary;
    String maritalStatus;

    TextView current;
    TextView future;
    TextView need;

    ProgressBar currentSeek;
    ProgressBar futureSeek;
    ProgressBar needSeek;
    ProgressBar currentTaxSeek;
    ProgressBar futureTaxSeek;
    ProgressBar currentHouseSeek;
    ProgressBar futureHouseSeek;

    TextView currTax;
    TextView currHouse;
    TextView currLeft;
    TextView futTax;
    TextView futHouse;
    TextView futLeft;
    TextView currCity;
    TextView futCity;
    TextView taxDiff;
    TextView houseDiff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentCity = extras.getString("Current City");
            currentState = extras.getString("Current State");
            currentSalary = extras.getDouble("Current Salary");
            futureCity = extras.getString("Future City");
            futureState = extras.getString("Future State");
            futureSalary = extras.getDouble("Future Salary");
            maritalStatus = extras.getString("Marital Status");
        }
        double stateTax = 0;
        current = (TextView) findViewById(R.id.currenttext);
        future = (TextView) findViewById(R.id.futuretext);
        need = (TextView) findViewById(R.id.salarytext);

        currentSeek = (ProgressBar) findViewById(R.id.currentSeek);
        //currentSeek.setEnabled(false);
        futureSeek = (ProgressBar) findViewById(R.id.futureSeek);
        // futureSeek.setEnabled(false);
        needSeek = (ProgressBar) findViewById(R.id.needSeek);
        // needSeek.setEnabled(false);
        currentTaxSeek = (ProgressBar) findViewById(R.id.currTaxSeek);
        //currentTaxSeek.setEnabled(false);
        futureTaxSeek = (ProgressBar) findViewById(R.id.futureTaxSeek);
        //futureTaxSeek.setEnabled(false);
        currentHouseSeek = (ProgressBar) findViewById(R.id.currHouseSeek);
        //currentHouseSeek.setEnabled(false);
        futureHouseSeek = (ProgressBar) findViewById(R.id.futureHouseSeek);
        //futureHouseSeek.setEnabled(false);

        currTax = (TextView) findViewById(R.id.currTaxBudget);
        currHouse = (TextView) findViewById(R.id.currHouseBudget);
        currLeft = (TextView) findViewById(R.id.currLeftBudget);
        futTax = (TextView) findViewById(R.id.futTaxBudget);
        futHouse = (TextView) findViewById(R.id.futHouseBudget);
        futLeft = (TextView) findViewById(R.id.futLeftBudget);
        currCity = (TextView) findViewById(R.id.currentCityText);
        futCity = (TextView) findViewById(R.id.futureCityText);
        taxDiff = (TextView) findViewById(R.id.taxDiff);
        houseDiff = (TextView) findViewById(R.id.houseDiff);

        String input = "median home sales price" + currentCity + ", " + currentState + " vs " +
                futureCity + ", " + futureState;
        double currentPrice = 0;
        double futurePrice = 0;

        WAEngine engine = new com.wolfram.alpha.alpha.WAEngine();

        // These properties will be set in all the WAQuery objects created from this WAEngine.
        engine.setAppID("JXEGAK-T8Y74GTGRR");
        engine.addFormat("plaintext");

        // Create the query.
        WAQuery query = engine.createQuery();

        // Set properties of the query.
        query.setInput(input);


        // For educational purposes, print out the URL we are about to send:
        System.out.println("Query URL:");
        System.out.println(engine.toURL(query));


        // This sends the URL to the Wolfram|Alpha server, gets the XML result
        // and parses it into an object hierarchy held by the WAQueryResult object.
        WAQueryResult queryResult = null;

        try {
            queryResult = engine.performQuery(query);


            if (queryResult.isError()) {
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
            } else if (!queryResult.isSuccess()) {
                System.out.println("Query was not understood; no results available.");
            } else {
                // Got a result.
                System.out.println("Successful query. Pods follow:\n");
                for (WAPod pod : queryResult.getPods()) {
                    if (pod.getTitle().equals("Results")) {
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText) {
                                    String result = ((WAPlainText) element).getText();
                                    for (int i = 10; i < result.length(); i++) {
                                        if (result.charAt(i) == '$') {
                                            System.out.println("Real Price: " + result.substring(i + 1));
                                            if (currentPrice == 0) {
                                                currentPrice = Double.parseDouble(result.substring(i + 1, i + 7));
                                            } else {
                                                futurePrice = Double.parseDouble(result.substring(i + 1, i + 7));
                                            }
                                            //System.out.println("Price: " + result.substring(i));
                                        }
                                    }
                                    //System.out.println();
                                    //Log.d("Test", ((WAPlainText) element).getText());
                                    //Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_LONG);
                                    // System.out.println("");
                                }
                            }
                        }
                    }
                    if (!pod.isError()) {
                        System.out.println(pod.getTitle());
                        //System.out.println("------------");
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText && subpod.getTitle().equals("Results")) {
                                    String result = ((WAPlainText) element).getText();
                                    for (int i = 10; i < result.length(); i++) {
                                        if (result.charAt(i) == '$') {
                                            System.out.println("Price: " + result.substring(i + 2));
                                            if (currentPrice == 0) {
                                                currentPrice = Double.parseDouble(result.substring(i + 2, i + 8));
                                            } else {
                                                futurePrice = Double.parseDouble(result.substring(i + 2, i + 8));
                                            }
                                        }
                                    }
                                    // System.out.println(((WAPlainText) element).getText());
                                    // System.out.println("");
                                }
                            }
                        }
                    }

                }


            }
        } catch (WAException e) {
            e.printStackTrace();
        }
        current.setText("Current Salary: $" + currentSalary);
        future.setText("Future Salary: $" + futureSalary);
        int needed = (int) (Math.round((futurePrice * 0.75 / currentPrice) * currentSalary));
        need.setText("Salary needed to maintain current lifestyle: $" + needed);
        currentSeek.setProgress((int) (50 + (currentSalary - futureSalary) / 2000.0));
        futureSeek.setProgress((int) (50 + (futureSalary - currentSalary) / 2000.0));
        needSeek.setProgress((int) (50 + (needed - currentSalary) / 2000.0));
        double currentTax = (1 + stateTax) * getTax(new String[]{currentSalary + "", maritalStatus});
        double futureTax = (1 + stateTax) * getTax(new String[]{futureSalary + "", maritalStatus});
        currentTaxSeek.setProgress((int) (50 + (currentTax - futureTax) / 2000.0));
        futureTaxSeek.setProgress((int) (50 + (futureTax - currentTax) / 2000.0));
        int diff = (int) Math.round(Math.abs((futureTax - currentTax) / currentTax) * 100);
        taxDiff.setText(diff + "%");
        currentHouseSeek.setProgress((int) (50 + (currentPrice - futurePrice) / 2000.0));
        futureHouseSeek.setProgress((int) (50 + (futurePrice - currentPrice) / 2000.0));
        diff = (int) (Math.round(Math.abs((futurePrice - currentPrice) / currentPrice) * 100));
        houseDiff.setText(diff + "%");
        currTax.setText("$" + Math.round(currentTax / 12.0));
        futTax.setText("$" + Math.round(futureTax / 12.0));
        double house = Math.round(currentSalary * 0.33 / 12.0);
        currHouse.setText("$" + house);
        house = Math.round(needed * 1.25 * 0.33 / 12.0);
        futHouse.setText("$" + house);
        double leftover = Math.round(currentSalary / 12.0 - (currentTax / 12.0 + currentSalary * 0.33 / 12.0));
        currLeft.setText("$" + leftover);
        leftover = Math.round(futureSalary / 12.0 - (futureTax / 12.0 + needed * 1.25 * 0.33 / 12.0));
        futLeft.setText("$" + leftover);
        currCity.setText(currentCity);
        futCity.setText(futureCity);
    }


    double getTax(String[] args) {
        double salary = Double.parseDouble(args[0]);
        String married = args[1];
        String MARRIED = married.toUpperCase();
        //String married = args[3];
        //double[] money = new double[8];
        //for (int i = 0; i<args.length(); i++) {
        //money = money[i] + args[i];
        //}
// capitilize married
//medicare and social security
//state taxes (pull from database)
        int[] MaxPaymentArray = new int[23];
        MaxPaymentArray[0] = 0;
        MaxPaymentArray[1] = 0;
        MaxPaymentArray[2] = 8303;
        MaxPaymentArray[3] = 23991;
        MaxPaymentArray[4] = 39974;
        MaxPaymentArray[5] = 70955;
        MaxPaymentArray[6] = 222000;
        MaxPaymentArray[7] = 1700;
        MaxPaymentArray[8] = 0;
        MaxPaymentArray[9] = 16605;
        MaxPaymentArray[10] = 47982;
        MaxPaymentArray[11] = 57225;
        MaxPaymentArray[12] = 57060;
        MaxPaymentArray[13] = 121304;
        MaxPaymentArray[14] = 34678;
        MaxPaymentArray[15] = 0;
        MaxPaymentArray[16] = 11835;
        MaxPaymentArray[17] = 31492;
        MaxPaymentArray[18] = 59550;
        MaxPaymentArray[19] = 57780;
        MaxPaymentArray[20] = 135106;
        MaxPaymentArray[21] = 17875;
        MaxPaymentArray[22] = 0;

        double NetIncome = 0;

        double[] TaxPercentArray = new double[7];
        TaxPercentArray[0] = .396;
        TaxPercentArray[1] = .1;
        TaxPercentArray[2] = .15;
        TaxPercentArray[3] = .25;
        TaxPercentArray[4] = .28;
        TaxPercentArray[5] = .33;
        TaxPercentArray[6] = .35;

        int incomeBracket = 0;
        if (MARRIED.equals("SINGLE")) {

            if (salary <= 9275) {
                incomeBracket = 1;
            } else if (salary > 9275 && salary <= 37650) {
                incomeBracket = 2;
            } else if (salary > 37650 && salary <= 91150) {
                incomeBracket = 3;
            } else if (salary > 91150 && salary <= 190150) {
                incomeBracket = 4;
            } else if (salary > 190150 && salary <= 413350) {
                incomeBracket = 5;
            } else if (salary > 413350 && salary <= 415050) {
                incomeBracket = 6;
            } else {
                incomeBracket = 7;
            }
            for (int i = incomeBracket; i > 0; i--) {
                NetIncome = MaxPaymentArray[i];
            }
            NetIncome += (salary - MaxPaymentArray[incomeBracket]) * (1 - TaxPercentArray[incomeBracket % 7]);
        } else if (MARRIED.equals("JOINT"))

        {
            if (salary <= 18550) {
                incomeBracket = 8;
            } else if (salary > 18550 && salary <= 75300) {
                incomeBracket = 9;
            } else if (salary > 75300 && salary <= 151900) {
                incomeBracket = 10;
            } else if (salary > 151900 && salary <= 231450) {
                incomeBracket = 11;
            } else if (salary > 231450 && salary <= 413350) {
                incomeBracket = 12;
            } else if (salary > 413350 && salary <= 466950) {
                incomeBracket = 13;
            } else {
                incomeBracket = 14;
            }
            for (int i = incomeBracket; i > 7; i--) {
                NetIncome = MaxPaymentArray[i];
            }

            NetIncome += (salary - MaxPaymentArray[incomeBracket]) * (1 - TaxPercentArray[incomeBracket % 7]);
        } else

        {//Head of Household
            if (salary <= 13250) {
                incomeBracket = 15;
            } else if (salary > 13250 && salary <= 50400) {
                incomeBracket = 16;
            } else if (salary > 50400 && salary <= 130150) {
                incomeBracket = 17;
            } else if (salary > 130150 && salary <= 210800) {
                incomeBracket = 18;
            } else if (salary > 210800 && salary <= 413350) {
                incomeBracket = 19;
            } else if (salary > 413350 && salary <= 441000) {
                incomeBracket = 20;
            } else {
                incomeBracket = 21;
            }
            for (int i = incomeBracket; i > 14; i--) {
                NetIncome = MaxPaymentArray[i];
            }
            NetIncome += (salary - MaxPaymentArray[incomeBracket]) * (1 - TaxPercentArray[incomeBracket % 7]);
        }
        return Math.round(NetIncome * (1 - .0765));

    }
}
