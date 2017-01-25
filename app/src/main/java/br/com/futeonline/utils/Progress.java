package br.com.futeonline.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.LoaderManager;
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


public class Progress extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private View view;
    private View progress;
    private Resources resources;

    public Progress() {
        this.resources = null;
        this.view = null;
        this.progress = null;
    }

    public Progress( Resources resources, View view, View progress) {
        this.resources = resources;
        this.view = view;
        this.progress = progress;
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
