package br.com.futeonline.main;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.futeonline.R;
import br.com.futeonline.objects.Notify;
import br.com.futeonline.utils.Consulta;
import br.com.futeonline.utils.QueryResult;
import br.com.futeonline.utils.Sessions;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Sessions sessions;
    private String userName = "";
    private String userEmail = "";
    Thread threadPush;
    private TextView user;
    private TextView mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        try {
            if (sessions.getString("access_token").isEmpty()) {
                Intent it = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(it);
                return;
            }

        } catch (Exception e) {
            e.getMessage();
        }


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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        TextView v = (TextView) toolbar.findViewById(R.id.id_nav_header_user);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);
        threadPush = new Thread(n);
        threadPush.start();

        sessions = new Sessions(this);
        setUserName(sessions.getString("user_name"));
        setUserEmail(sessions.getString("user_mail"));

        try {
            TextView textView = (TextView) headerLayout.findViewById(R.id.id_nav_header_user);
            textView.setText("AAAA");
            textView.requestFocus();
        } catch (Exception e) {

        }



        try {
            user = (TextView) navigationView.findViewById(R.id.id_nav_header_user);
            user.setText(userName, TextView.BufferType.NORMAL);
            user.requestFocus();
        } catch (Exception e) {
            e.getMessage();
        }

        try {
            ImageView imageView = (ImageView) navigationView.findViewById(R.id.imageView);
            imageView.requestFocus();
        } catch (Exception e) {
            e.getMessage();
        }
        try {
            setMail((TextView) findViewById(R.id.id_nav_header_email));
            getMail().setText(userEmail);
            getMail().requestFocus();

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_friends) {
        } else if (id == R.id.nav_soccer_schedule) {
        } else if (id == R.id.nav_invite_game) {
        } else if (id == R.id.nav_players) {
        } else if (id == R.id.nav_goalkeeper_find) {
        } else if (id == R.id.nav_notifications) {
            //} else if (id == R.id.nav_groups) {
            //} else if (id == R.id.nav_group_moderator) {
        } else if (id == R.id.nav_logout) {
            sessions.destroy();
            Intent it = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(it);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    Runnable n = new Runnable() {
        @Override
        public void run() {
            notifies();
        }
    };

    public void notifies() {
        Boolean c = true;
        while (true == c) {
            try {

                Map map2 = new HashMap();
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("access_token", sessions.getString("access_token"));
                    String result = Consulta.result(Consulta.makeRequest(Defaults.getKeyToken(), Defaults.getSite() + "/ws/notify/my", jsonObject));
                    Gson gson = new Gson();
                    QueryResult notifyResponse = (QueryResult) gson.fromJson(result, QueryResult.class);
                    if (notifyResponse.getStatus() == 1) {
                        result = "" + notifyResponse.getResult();
                        if (notifyResponse.getResult() > 0) {
                            result = Consulta.result(Consulta.makeRequest(Defaults.getKeyToken(), Defaults.getSite() + "/ws/notify/show/", jsonObject));
                            c = true;
                            List<Notify> listNotify = gson.fromJson(result, new TypeToken<List<Notify>>() {
                            }.getType());
                            View v = this.getCurrentFocus();
                            notifies(v, listNotify);
                        }
                    }
                } catch (Exception e) {

                }
                Thread.sleep(
                        5 *   // minutes to sleep
                                60 *   // seconds to a minute
                                1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


    }

    public void notifies(View view, Object list) {
        notifies(view, list);
    }

    public void notifies(View view, List<Notify> list) {

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, NotificationView.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("Ticker Texto");
        builder.setContentTitle("Título");
        //builder.setContentText("Descrição");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(p);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        for (int i = 0; i < list.size(); i++) {
            style.addLine(list.get(i).getDescription());
            if (i == 5) {
                break;
            }
        }
        builder.setStyle(style);

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.ic_launcher, n);
        try {
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(this, som);
            toque.play();
        } catch (Exception e) {
        }
    }

    public TextView getUser() {
        return user;
    }

    public void setUser(TextView user) {
        this.user = user;
    }

    public TextView getMail() {
        return mail;
    }

    public void setMail(TextView mail) {
        this.mail = mail;
    }
}
