package com.example.dainim.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dainim.R;
import com.example.dainim.controller.DeleteListener;
import com.example.dainim.controller.SignoutListener;
import com.example.dainim.controller.UpdateListener;
import com.example.dainim.model.User;

import com.example.dainim.controller.UserHelper;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;

import static android.view.View.*;

/**
 * Class implementing the profile page activity
 */
public class ProfileActivity extends BaseActivity {
    /**
     * TextInputEditText:
     */
    protected TextInputEditText textInputEditTextUsername;
    /**
     * TextView
     */
    protected TextView textViewEmail;
    /**
     * ProgressBar
     */
    protected ProgressBar progressBar;
    /**
     * Button
     */
    protected Button update;
    /**
     * Button
     */
    protected Button signout;
    /**
     * Button
     */
    protected Button delete;

    //FOR DATA
    /**
     * Id for Task
     */
    private static final int SIGN_OUT_TASK = 10;
    /**
     * Id for Task
     */
    private static final int DELETE_USER_TASK = 20;
    /**
     * Id for Task
     */
    private static final int UPDATE_USERNAME = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        this.configureAll();
        //Setting
        this.update = (Button)findViewById(R.id.profile_activity_button_update);
        this.update.setOnClickListener(new UpdateListener(this));
        this.signout = (Button)findViewById(R.id.profile_activity_button_sign_out);
        this.signout.setOnClickListener(new SignoutListener(this));
        this.delete = (Button) findViewById(R.id.profile_activity_button_delete);
        this.delete.setOnClickListener(new DeleteListener(this));
        this.textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        this.progressBar = findViewById(R.id.progressBar);
        this.progressBar.setVisibility(INVISIBLE);
        this.textInputEditTextUsername = findViewById(R.id.textUserName);
        this.updateUIWhenCreating();

    }

    // --------------------
    // Event
    // --------------------

    /**
     * signOutUserFromFirebase, Disconnected user
     */
    public void signOutUserFromFirebase(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK));
    }

    /**
     * deleteUserFromFirebase, delete user to application
     */
    public void deleteUserFromFirebase(){
        if (this.getCurrentUser() != null) {

            //4 - We also delete user from firestore storage
            UserHelper.deleteUser(this.getCurrentUser().getUid());

            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));
        }
    }

    /**
     * updateUsernameInFirebase, add modification for user
     */
    public void updateUsernameInFirebase(){

        this.progressBar.setVisibility(VISIBLE);
        String username = this.textInputEditTextUsername.getText().toString();

        if (this.getCurrentUser() != null){
            if (!username.isEmpty() &&  !username.equals(getString(R.string.info_no_username_found))){
                UserHelper.updateUsername(username, this.getCurrentUser().getUid()).addOnSuccessListener(this.updateUIAfterRESTRequestsCompleted(UPDATE_USERNAME));
            }
        }
    }

    // --------------------
    // Windows
    // --------------------

    /**
     * updateUIWhenCreating, add email in textView
     */
    public void updateUIWhenCreating(){

        if (this.getCurrentUser() != null){


            //Get email & username from Firebase
            String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();
            System.out.println("TEST email :" + this.getCurrentUser().getEmail());
            //Update views with data
            this.textViewEmail.setText(email);

            // 5 - Get additional data from Firestore
            UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    User currentUser = documentSnapshot.toObject(User.class);
                    String username = TextUtils.isEmpty(currentUser.getUsername()) ? getString(R.string.info_no_username_found) : currentUser.getUsername();
                    textInputEditTextUsername.setText(username);
                }
            });
        }
    }

    /**
     * updateUIAfterRESTRequestsCompleted, modify windows and realise task
     * @param origin int id Task
     * @return OnSuccessListener<Void></Void>
     */
    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin){
        return new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                switch (origin){
                    case UPDATE_USERNAME:
                        progressBar.setVisibility(INVISIBLE);
                        break;
                    case SIGN_OUT_TASK:
                        finish();
                        break;
                    case DELETE_USER_TASK:
                        finish();
                        break;
                    default:
                        break;
                }
            }
        };
    }
}
