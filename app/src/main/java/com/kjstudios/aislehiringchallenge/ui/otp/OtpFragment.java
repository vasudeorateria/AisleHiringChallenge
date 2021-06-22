package com.kjstudios.aislehiringchallenge.ui.otp;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.gson.JsonObject;
import com.kjstudios.aislehiringchallenge.R;
import com.kjstudios.aislehiringchallenge.data.UserPreferences;
import com.kjstudios.aislehiringchallenge.utils.Resource;

public class OtpFragment extends Fragment {

    private OtpViewModel mViewModel;

    public static OtpFragment newInstance() {
        return new OtpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(OtpViewModel.class);
        return inflater.inflate(R.layout.otp_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        NavController navController = Navigation.findNavController(view);

        TextView phone_number_otp = view.findViewById(R.id.phone_number_otp);
        EditText otp_et = view.findViewById(R.id.otp_et);
        Button continue_otp = view.findViewById(R.id.continue_otp);
        Chronometer otp_timer = view.findViewById(R.id.otp_timer);

        String countryCode = OtpFragmentArgs.fromBundle(getArguments()).getCountryCode();
        String phoneNumber = OtpFragmentArgs.fromBundle(getArguments()).getPhoneNumber();
        phone_number_otp.setText(countryCode + " " + phoneNumber);
        phone_number_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });

        otp_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 4) {
                    continue_otp.setEnabled(true);
                    continue_otp.setAlpha(1.0f);
                } else {
                    continue_otp.setEnabled(false);
                    continue_otp.setAlpha(0.5f);
                }

            }
        });

        continue_otp.setOnClickListener(v -> {
            continue_otp.setEnabled(false);
            mViewModel.verifyOtp(countryCode, phoneNumber, otp_et.getText().toString());
        });

        mViewModel.otp_verify_status.observe(getViewLifecycleOwner(), result -> {
            if (result instanceof Resource.Success) {
                JsonObject data = ((Resource.Success<JsonObject>) result).getData();
                String token = data.get("token").getAsString();
                new UserPreferences(getContext()).addToken(token);

                Toast.makeText(getContext(), "otp verified", Toast.LENGTH_SHORT).show();
                NavDirections action = OtpFragmentDirections.actionOtpFragmentToNotes();
                navController.navigate(action);
            } else if (result instanceof Resource.Error) {
                continue_otp.setEnabled(true);
                Toast.makeText(getContext(), "Unable to verify otp at the momment", Toast.LENGTH_SHORT).show();
            } else {
                // loading
            }
        });

        otp_timer.setBase(SystemClock.elapsedRealtime() + 60000);
        otp_timer.start();

    }

}