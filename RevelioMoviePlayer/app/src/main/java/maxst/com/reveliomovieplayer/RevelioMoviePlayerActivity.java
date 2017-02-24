package maxst.com.reveliomovieplayer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.maxst.revelio.NativeManager;
import com.maxst.revelio.ViewMode;

public class RevelioMoviePlayerActivity extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

	private VideoView videoView;
	private int videoViewWidth;
	private int videoViewHeight;
	private int videoWidth;
	private int videoHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_revelio_movie_player);
		videoView = (VideoView)findViewById(R.id.video_view);

		String path = "android.resource://" + getPackageName() + "/" + R.raw.eyeresh_dolphin;
		videoView.setOnPreparedListener(this);
		videoView.setOnCompletionListener(this);
		videoView.setVideoURI(Uri.parse(path));
		videoView.getViewTreeObserver().addOnGlobalLayoutListener(

				new ViewTreeObserver.OnGlobalLayoutListener() {
					@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
					@Override
					public void onGlobalLayout() {
						videoViewWidth = videoView.getWidth();
						videoViewHeight = videoView.getHeight();

						resizeVideoView();

						if (ApplicationUtils.hasJellyBean()) {
							videoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
						} else {
							videoView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
						}
					}
				}
		);
	}

	@Override
	protected void onResume() {
		super.onResume();

		NativeManager.setViewMode(ViewMode.TYPE_SIDE_BY_SIDE);
		videoView.start();
	}

	@Override
	protected void onPause() {
		super.onPause();

		// TODO : Change to 2D mode here
		videoView.stopPlayback();
		NativeManager.setViewMode(ViewMode.TYPE_2D);
	}

	@Override
	public void onPrepared(MediaPlayer mediaPlayer) {
		videoWidth = mediaPlayer.getVideoWidth();
		videoHeight = mediaPlayer.getVideoHeight();
		resizeVideoView();
	}

	private void resizeVideoView() {
		synchronized (this) {
			if (videoWidth != 0 && videoHeight != 0 && videoViewWidth != 0 && videoViewHeight != 0) {
				float videoRatio = (float) videoWidth / videoHeight;
				float viewRatio = (float) videoViewWidth / videoViewHeight;

				if (videoRatio > viewRatio) {
					int newVideoViewWidth = (int) ((float) videoViewHeight * videoRatio);
					FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(newVideoViewWidth, videoViewHeight);
					params.leftMargin = -(newVideoViewWidth - videoViewWidth) / 2;
					videoView.setLayoutParams(params);
				} else {
					int newVideoViewHeight = (int) ((float) videoViewWidth *  videoRatio);
					FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(videoViewWidth, newVideoViewHeight);
					params.topMargin = -(newVideoViewHeight - videoViewHeight) / 2;
					videoView.setLayoutParams(params);
				}
			}
		}
	}

	@Override
	public void onCompletion(MediaPlayer mediaPlayer) {
		mediaPlayer.start();
	}
}
