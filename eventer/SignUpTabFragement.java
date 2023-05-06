package com.example.eventer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eventer.entity.User;
import com.example.eventer.implementation.UserImplementation;

public class SignUpTabFragement extends Fragment {

    private Button signUp;
    private EditText name;
    private EditText email;
    private EditText password;
    UserImplementation userImplementation = new UserImplementation();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        name = root.findViewById(R.id.name);
        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        signUp = root.findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(this::saveUser);
        return root;
    }

    private void saveUser(View view) {

        User user = new User(name.getText().toString(), password.getText().toString(), email.getText().toString(), false);
        userImplementation.saveUser(user, getContext());

    }
}
