package com.example.parus.autorising;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.parus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegistrationFragment extends Fragment {
    private EditText login_field;
    private EditText first_name_field;
    private EditText last_name_filed;
    private EditText password_field;
    private ProgressBar progressBar;
    private String login;
    private String password;
    private String First_name;
    private String Last_name;
    final String LOGIN_ID="login_id";
    final String PASSWORD_ID="password_id";

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        login="";
        password="";
        First_name="";
        Last_name="";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registration,container,false);
        login_field=view.findViewById(R.id.login);
        progressBar=view.findViewById(R.id.progressBar);
        login_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                login=login_field.getText().toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login=login_field.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

                login=login_field.getText().toString().trim();
            }
        });
        password_field=view.findViewById(R.id.passworder);
        password_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                password=password_field.getText().toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password=password_field.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {
                password=password_field.getText().toString().trim();
            }
        });
        first_name_field=view.findViewById(R.id.first_name);
        first_name_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                First_name=first_name_field.getText().toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                First_name=first_name_field.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {
                First_name=first_name_field.getText().toString().trim();
            }

        });
        last_name_filed=view.findViewById(R.id.last_name);
        last_name_filed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Last_name=last_name_filed.getText().toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Last_name=last_name_filed.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {
                Last_name=last_name_filed.getText().toString().trim();
            }
        });
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  final SharedPreferences login_us_info=requireContext().getSharedPreferences(LOGIN_ID, Context.MODE_PRIVATE);
     //   final SharedPreferences password_us_info=requireContext().getSharedPreferences(PASSWORD_ID, Context.MODE_PRIVATE);
        view.findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // SharedPreferences.Editor editor=login_us_info.edit();
              //  editor.putString(LOGIN_ID,login).apply();
              //  SharedPreferences.Editor editor1=password_us_info.edit();
              //  editor1.putString(PASSWORD_ID,password).apply();
              //  NavHostFragment.findNavController(RegistrationFragment.this).navigate(R.id.action_Registration_to_Second);
                registerUser();

            }
        });
    }

        private void registerUser(){
            if (First_name.isEmpty()) {
                first_name_field.setError(requireContext().getString(R.string.first_name_error));
                first_name_field.requestFocus();
                return;
            }
            if (Last_name.isEmpty()){
                last_name_filed.setError(requireContext().getString(R.string.last_name_error));
                last_name_filed.requestFocus();
                return;

            }
        if (login.isEmpty()){
            login_field.setError(requireContext().getString(R.string.email_error));
            login_field.requestFocus();
            return;
        }
        if (password.isEmpty()){
            password_field.setError(requireContext().getString(R.string.password_error));
            password_field.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(login).matches()){
            login_field.setError(requireContext().getString(R.string.email_matches_error));
            login_field.requestFocus();
            return;
        }
        if (password.length()< 7){
            password_field.setError(requireContext().getString(R.string.pass_leng_error));
            password_field.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(login,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user=new User(First_name,Last_name,login,"");
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser userer=FirebaseAuth.getInstance().getCurrentUser();
                                        Toast.makeText(requireContext(),
                                                requireContext().getString(R.string.successful_user_reg),
                                                Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        userer.sendEmailVerification();
                                        NavHostFragment.findNavController(RegistrationFragment.this)
                                                .navigate(R.id.action_Registration_to_SignInFragment);
                                        //redirect to login layout
                                    }else{
                                        Toast.makeText(requireContext(),
                                                requireContext().getString(R.string.failed),
                                                Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(requireContext(),
                                    requireContext().getString(R.string.failed),
                                    Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });

        }


    }


