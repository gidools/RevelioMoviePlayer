package maxst.com.reveliomovieplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

public class RevelioMoviePlayerActivity extends Activity {

	private VideoView videoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_revelio_movie_player);
		videoView = (VideoView)findViewById(R.id.video_view);

		String videoFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "Vuvana.mp4";
		videoView.setVideoPath(videoFilePath);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// TODO : Change to 3D mode here
		videoView.start();
	}

	@Override
	protected void onPause() {
		super.onPause();

		// TODO : Change to 3D mode here
		videoView.stopPlayback();
	}
}
