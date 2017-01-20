package anartzmugika.delayexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.amugika.chronometer.ChronometerWithMS;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageImageView;
    private ChronometerWithMS chronometerWithMS;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometerWithMS = (ChronometerWithMS) findViewById(R.id.chronometer);

        textView = (TextView) findViewById(R.id.startTextView);
        imageImageView = (ImageView) findViewById(R.id.imageImageView);

        System.out.println("Testing..." + new Date());
        textView.setText(new Date().toString());

        imageImageView.setVisibility(View.GONE);

        chronometerWithMS.start();
        //In background execute delay
        new LoadDataBackground().execute(5000);

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
            imageImageView.setVisibility(View.GONE);
            System.out.println(result);
            textView.setText("Executed!! (FINISH DATA: " + new Date() + ")");

            chronometerWithMS.stop();

        }

        @Override
        protected void onPreExecute() {
            imageImageView.setVisibility(View.VISIBLE);
            textView.setText(textView.getText().toString() + "\n" + "Start to load...(5 seconds wait please)");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
