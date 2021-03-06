package anartzmugika.delayexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amugika.chronometer.ChronometerWithMS;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ChronometerWithMS chronometerWithMS;
    private Button delayListButton;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometerWithMS = (ChronometerWithMS) findViewById(R.id.chronometer);

        textView = (TextView) findViewById(R.id.startTextView);

        delayListButton = (Button) findViewById(R.id.delayListButton);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        System.out.println("Testing..." + new Date());
        textView.setText(new Date().toString());

        chronometerWithMS.start();
        //In background execute delay
        new LoadDataBackground().execute(5000);

        delayListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private class LoadDataBackground extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {

                try {
                    Thread.sleep(params[0]);
                    System.out.println("Testing..." + new Date());

                } catch (InterruptedException e) {
                    Thread.interrupted();
                }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Finish!!", Toast.LENGTH_LONG).show();
            System.out.println(result);
            textView.setText("Executed!! (FINISH DATA: " + new Date() + ")");
            new Animations().setAnimationAppear(textView, 10);

            chronometerWithMS.stop();

            progressBar.setVisibility(View.GONE);

        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            textView.setText(textView.getText().toString() + "\n" + "Start to load...(5 seconds wait please)");
            new Animations().setAnimationAppear(textView, 10);

        }

        @Override
        protected void onProgressUpdate(Void... values) {}

    }
}
