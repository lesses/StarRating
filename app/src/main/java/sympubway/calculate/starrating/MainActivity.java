package sympubway.calculate.starrating;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import sympubway.calculate.starrating.dao.DayRecordDAO;
import sympubway.calculate.starrating.entity.DayRecord;

public class MainActivity extends AppCompatActivity {

    public static final int MAXS_SCORE = 5;
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppContext.getInstance().initialiseContext(this);
        setMainActivityView();
    }

    private void setMainActivityView() {
        // Set rating average score;
        scoreText = (TextView) findViewById(R.id.main_txt_scoreValue);
        scoreText.setText(String.valueOf(getAvgScore()));

        // Setup the rating bar, especially when you have rated
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setMax(this.MAXS_SCORE);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(final RatingBar ratingBar, final float rating, boolean fromUser) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());//TODO check if context is right
                builder.setMessage(R.string.alert_confirm_rating)
                        .setPositiveButton(R.string.alert_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ratingBar.setEnabled(false);
                                writeStarNumberToDB(ratingBar.getNumStars());
                                scoreText.setText(String.valueOf(getAvgScore()));
                            }
                        })
                        .setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ratingBar.setEnabled(true);
                                ratingBar.setNumStars(0);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private float getAvgScore() {
        DayRecordDAO dao = new DayRecordDAO();
        List<DayRecord> records = dao.getWholeList();
        long sum = 0;
        for (DayRecord item : records) {
            sum += item.getValue();
        }
        float result = 0;
        if (records.size() > 0) {
            result = (float) Math.round(sum * 100 / records.size()) / 100;
        }
        return result;
    }

    private void writeStarNumberToDB(int numStars) {
        DayRecordDAO dao = new DayRecordDAO();
        DayRecord record = new DayRecord();
        record.setId(UUID.randomUUID().toString());
        record.setDate(AppHelper.getInstance().get2400(new Date()));
        record.setValue(numStars);
        dao.createNew(record);
    }
}
