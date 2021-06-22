package com.kjstudios.aislehiringchallenge.ui.phone_number;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.gson.JsonObject;
import com.kjstudios.aislehiringchallenge.R;
import com.kjstudios.aislehiringchallenge.data.UserPreferences;
import com.kjstudios.aislehiringchallenge.data.remote.RetrofitClient;
import com.kjstudios.aislehiringchallenge.ui.otp.OtpFragmentDirections;
import com.kjstudios.aislehiringchallenge.utils.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneNumberFragment extends Fragment {

    private PhoneNumberViewModel mViewModel;

    private String countryCode , phoneNumber;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PhoneNumberViewModel.class);
        return inflater.inflate(R.layout.phone_number_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView countryCode_tv = view.findViewById(R.id.country_code_tv);
        EditText phone_number_et = view.findViewById(R.id.phone_number_et);
        Button continue_phone_number = view.findViewById(R.id.continue_phone_number);

        phone_number_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 10) {
                    continue_phone_number.setEnabled(true);
                    continue_phone_number.setAlpha(1.0f);
                } else {
                    continue_phone_number.setEnabled(false);
                    continue_phone_number.setAlpha(0.5f);
                }
            }
        });

        continue_phone_number.setOnClickListener(v -> {
            continue_phone_number.setEnabled(false);
            countryCode = countryCode_tv.getText().toString();
            phoneNumber = phone_number_et.getText().toString();
            mViewModel.sendOtp(countryCode , phoneNumber);
        });

        mViewModel.otp_send_status.observe(getViewLifecycleOwner() , result->{
            if (result instanceof Resource.Success) {
                JsonObject data = ((Resource.Success<JsonObject>) result).getData();
                boolean status = data.get("status").getAsBoolean();

                if (status) {
                    Toast.makeText(getContext(), "otp sent", Toast.LENGTH_SHORT).show();
                    NavDirections action = PhoneNumberFragmentDirections
                            .actionPhoneNumberFragmentToOtpFragment(countryCode, phoneNumber);
                    Navigation.findNavController(view).navigate(action);
                } else {
                    continue_phone_number.setEnabled(true);
                    Toast.makeText(getContext(), "unable to send otp", Toast.LENGTH_SHORT).show();
                }

            } else if (result instanceof Resource.Error) {
                continue_phone_number.setEnabled(true);
                Toast.makeText(getContext(), "Unable to send otp at the momment", Toast.LENGTH_SHORT).show();
            } else {
                // loading
            }
        });
    }
}