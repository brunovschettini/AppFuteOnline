package br.com.futeonline.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Loader;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import br.com.futeonline.R;
import br.com.futeonline.main.MainActivity;


public class Progress extends AppCompatActivity {

    private View view;
    private View progress;
    private Resources resources;
    private ProgressDialog progressDialog;

    public Progress() {
        this.resources = null;
        this.view = null;
        this.progress = null;
        this.progressDialog = null;
    }

    public Progress(Resources resources, View view, View progress) {
        this.resources = resources;
        this.view = view;
        this.progress = progress;
        this.progressDialog = progressDialog;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        try {
            // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
            // for very easy animations. If available, use these APIs to fade-in
            // the progress spinner.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                int shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime);

                view.setVisibility(show ? View.GONE : View.VISIBLE);
                view.animate().setDuration(shortAnimTime).alpha(
                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });

                progress.setVisibility(show ? View.VISIBLE : View.GONE);
                progress.animate().setDuration(shortAnimTime).alpha(
                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progress.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
            } else {
                // The ViewPropertyAnimator APIs are not available, so simply show
                // and hide the relevant UI components.
                progress.setVisibility(show ? View.VISIBLE : View.GONE);
                view.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void define(Context c, String message) {
        progressDialog = new ProgressDialog(c);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // progressDialog.setProgress(0);
        progressDialog.setIndeterminate(true);
        progressDialog.setIcon(R.drawable.preloader_1);
        try {
            new Thread().sleep(2000);
        } catch (Exception e) {

        }
    }

    public void start() {
        progressDialog.show();
    }

    public void stop() {
        progressDialog.dismiss();
    }
}
