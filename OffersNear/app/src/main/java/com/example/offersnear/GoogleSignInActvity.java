package com.example.offersnear;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleSignInActvity extends MainActivity {

    private static final int RC_SIGN_IN = 101;
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;

    String personName="";
    BeginSignInRequest signInRequest;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Google Sign In...");
        progressDialog.show();
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);
        Intent i=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(i,1234);
    }


//    @Override
//    public void OnActivityResult(int requestCode,int resultCode, Intent data)
//    {
//
//        super.onActivityResult(requestCode,resultCode,data);
//        if(requestCode==RC_SIGN_IN)
//        {
//            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
//            try
//            {
//                GoogleSignInAccount account=task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account.getIdToken());
//
//            }
//            catch (ApiException e)
//            {
//
//                Toast.makeText(this, "Google sign Fail", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//                finish();
//            }
//        }
//
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1234)
        {
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try
            {
                GoogleSignInAccount account=task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount acct = result.getSignInAccount();
                personName= acct.getDisplayName();
            }
            catch (ApiException e)
            {
                Toast.makeText(this, "Google sign Fail", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                finish();
            }

        }

    }

    private void firebaseAuthWithGoogle(String idToken)
    {
        AuthCredential credential= GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    progressDialog.dismiss();
                    FirebaseUser user=mAuth.getCurrentUser();
                    updateUI(user);
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(GoogleSignInActvity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }

    private void updateUI(FirebaseUser user)
    {
//        final SharedPreferences sharedPreferences=getSharedPreferences("data", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        if(mUser!=null)
//        {
////            editor.putString("email",mUser.getEmail());
////            Toast.makeText(this, ""+mUser.getEmail(), Toast.LENGTH_SHORT).show();
////            editor.commit();
//            signOut();
//        }
//        else
//        {
//            Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
//        }

        signOut();

    }


    private void signOut() {
        mAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(GoogleSignInActvity.this,HomeActivity.class);
                        intent.putExtra("x",1);
//                        intent.putExtra("gmailid",mUser.getEmail());
//                        intent.putExtra("name",personName);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
    }



}



