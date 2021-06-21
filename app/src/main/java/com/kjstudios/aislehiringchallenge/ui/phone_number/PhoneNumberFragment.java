package com.kjstudios.aislehiringchallenge.ui.phone_number;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.kjstudios.aislehiringchallenge.R;

public class PhoneNumberFragment extends Fragment {

    private PhoneNumberViewModel mViewModel;

    public static PhoneNumberFragment newInstance() {
        return new PhoneNumberFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.phone_number_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PhoneNumberViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        EditText phone_number = view.findViewById(R.id.phone_number_et);
        Button continue_phone_number = view.findViewById(R.id.continue_phone_number);

        phone_number.addTextChangedListener(new TextWatcher() {
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

        continue_phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make network call to send otp
                NavDirections action =
                        PhoneNumberFragmentDirections.actionPhoneNumberFragmentToOtpFragment(phone_number.getText().toString());
                Navigation.findNavController(view).navigate(action);
            }
        });
    }
}