package com.example.offersnear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {



    Button b1,b2;
    FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
            if(drawerToggle.onOptionsItemSelected(item))
            {
                return true;
            }
            return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Fragment fragment=new TrendyOffers();
        FragmentTransaction fragmenttransaction=getSupportFragmentManager().beginTransaction();
        fragmenttransaction.add(R.id.mainMenuContainer,fragment).addToBackStack("Trendy").commit();

//        final SharedPreferences sharedPreferences=getSharedPreferences("data", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        int x=intent.getIntExtra("x",0);



        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        if(x==1)
        {
            String s=intent.getStringExtra("gmailid");
            String name1=intent.getStringExtra("name");

//            SharedPreferences.Editor editor=sharedPreferences.edit();
//            String email = sharedPreferences.getString("email", "");
//            String name = sharedPreferences.getString("name", "");
//            editor.putString("name",name1);
//            editor.putString("email",s);
//            editor.commit();
//            if(!email.equalsIgnoreCase(""))
//            {
//                s = email;  /* Edit the value here*/
//            }
//            if(!name.equalsIgnoreCase(""))
//            {
//                name1 = name;  /* Edit the value here*/
//            }
            TextView navUsername = (TextView) headerView.findViewById(R.id.text1);
            navUsername.setText(""+s);
            TextView navUsername1 = (TextView) headerView.findViewById(R.id.text2);
            navUsername1.setText(""+name1);

        }

        else
        {
//            String email = sharedPreferences.getString("email", "");
//            String name1=intent.getStringExtra("name");
//            String name = sharedPreferences.getString("name", "");
//            TextView navUsername = (TextView) headerView.findViewById(R.id.text1);
//            navUsername.setText(""+email);
//            if(!name.equalsIgnoreCase(""))
//            {
//                name1 = name;  /* Edit the value here*/
//            }
//
//            TextView navUsername1 = (TextView) headerView.findViewById(R.id.text2);
//            navUsername1.setText(""+name1);


        }


        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.profile:
                    {
                        if(x==1)
                        {
//                            SharedPreferences.Editor editor=sharedPreferences.edit();
//                            editor.putString("email","");
//                            editor.apply();
                            FirebaseAuth.getInstance().signOut();
                            Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if(x==0)
                        {
//                            SharedPreferences.Editor editor=sharedPreferences.edit();
//                            editor.putString("email","");
//                            editor.apply();
                            FirebaseAuth.getInstance().signOut();
                            Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                    break;

                    case R.id.map:
                    {
                        Intent intent=new Intent(HomeActivity.this,MapsActivity.class);
                        startActivity(intent);
                        break;
                    }

                }
                return false;
            }
        });
    }
}