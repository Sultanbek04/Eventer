package com.example.eventer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eventer.implementation.UserImplementation;

import org.w3c.dom.Text;

public class LoginTabFragment extends Fragment {

    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private UserImplementation userImplementation = new UserImplementation();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);
        btnLogin = root.findViewById(R.id.btnLogin);
        etEmail = root.findViewById(R.id.email);
        etPassword = root.findViewById(R.id.password);

        btnLogin.setOnClickListener(view -> {
            userImplementation.findUserByEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString(), root.getContext());

        });

        return root;
    }
}
