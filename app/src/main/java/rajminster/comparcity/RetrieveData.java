/*package rajminster.comparcity;

import android.os.AsyncTask;
import android.util.Log;

import com.wolfram.alpha.alpha.WAEngine;
import com.wolfram.alpha.alpha.WAException;
import com.wolfram.alpha.alpha.WAPlainText;
import com.wolfram.alpha.alpha.WAPod;
import com.wolfram.alpha.alpha.WAQuery;
import com.wolfram.alpha.alpha.WAQueryResult;
import com.wolfram.alpha.alpha.WASubpod;

 class RetrieveData extends AsyncTask<String, Void, Void> {

    private Exception exception;

    protected String doInBackground(String[] s) {
        String input = "moving san francisco to new york city salary $42000";

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
        System.out.println("");

        // This sends the URL to the Wolfram|Alpha server, gets the XML result
        // and parses it into an object hierarchy held by the WAQueryResult object.
        WAQueryResult queryResult = null;
        try {
            queryResult = engine.performQuery(query);
        } catch (WAException e) {
            e.printStackTrace();
        }

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
                if (pod.getTitle().equals("Result")) {
                    for (WASubpod subpod : pod.getSubpods()) {
                        for (Object element : subpod.getContents()) {
                            if (element instanceof WAPlainText) {
                                System.out.println(((WAPlainText) element).getText());
                                Log.d("Test", ((WAPlainText) element).getText());
                                System.out.println("");
                            }
                        }
                    }
                }
                if (!pod.isError()) {
                    System.out.println(pod.getTitle());
                    //System.out.println("------------");
                    for (WASubpod subpod : pod.getSubpods()) {
                        for (Object element : subpod.getContents()) {
                            if (element instanceof WAPlainText) {
                                // System.out.println(((WAPlainText) element).getText());
                                // System.out.println("");
                            }
                        }
                    }
                    System.out.println("");
                }
            }
            // We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
            // These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
        }
    }

    protected void onPostExecute() {
        // TODO: check this.exception 
        // TODO: do something with the feed
    }

}*/