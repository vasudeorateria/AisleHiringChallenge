package com.kjstudios.aislehiringchallenge.ui.notes.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.kjstudios.aislehiringchallenge.R;
import com.kjstudios.aislehiringchallenge.data.UserPreferences;
import com.kjstudios.aislehiringchallenge.data.model.java.InviteProfile;
import com.kjstudios.aislehiringchallenge.data.model.java.Invites;
import com.kjstudios.aislehiringchallenge.data.model.java.Likes;
import com.kjstudios.aislehiringchallenge.data.model.java.Note;
import com.kjstudios.aislehiringchallenge.data.model.java.Photo;
import com.kjstudios.aislehiringchallenge.data.model.java.Profile;
import com.kjstudios.aislehiringchallenge.utils.Resource;

import java.util.List;

public class DiscoverFragment extends Fragment {

    private DiscoverViewModel mViewModel;

    private ConstraintLayout invites_layout;
    private RelativeLayout upgrade_layout;
    private RecyclerView likes_profile_rv;
    ShapeableImageView profile_image;
    TextView profileDetails, profileAction;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(DiscoverViewModel.class);
        return inflater.inflate(R.layout.discover_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        invites_layout = view.findViewById(R.id.invites_layout);
        profile_image = view.findViewById(R.id.profileImage);
        profileDetails = view.findViewById(R.id.profileDetails);
        profileAction = view.findViewById(R.id.profileAction);
        upgrade_layout = view.findViewById(R.id.upgrade_layout);
        likes_profile_rv = view.findViewById(R.id.profileLikes_rv);

        String token = new UserPreferences(getContext()).getToken();
        if (token != null) {
            mViewModel.getNotes(token);
        }

        mViewModel.notes.observe(getViewLifecycleOwner(), result -> {
            if (result instanceof Resource.Success) {
                Note data = ((Resource.Success<Note>) result).getData();
                Likes likes = data.getLikes();
                Invites invites = data.getInvites();
                setProfileLikeRv(likes.getProfiles(), likes.isCan_see_profile());
                setProfileInvites(invites);
            } else if (result instanceof Resource.Error) {
                Toast.makeText(getContext(), "Unable to get details at the momment", Toast.LENGTH_SHORT).show();
            } else {
                // loading
            }
        });

    }

    public void setProfileInvites(Invites invites) {
        invites_layout.setVisibility(View.VISIBLE);
        InviteProfile invite = invites.getProfiles().get(0);
        List<Photo> photos = invite.getPhoto();
        for (Photo photo : photos) {
            if (photo.getSelected()) {
                Glide.with(getContext())
                        .load(photo.getPhoto())
                        .into(profile_image);
                break;
            }
        }
        String firstName = invite.getGeneralInformation().getFirst_name();
        int age = invite.getGeneralInformation().getAge();
        String information = firstName + " , " + age;
        profileDetails.setText(information);
    }

    private void setProfileLikeRv(List<Profile> profile_likes_list, boolean canViewProfile) {
        if (canViewProfile) {
            upgrade_layout.setVisibility(View.GONE);
        } else {
            upgrade_layout.setVisibility(View.VISIBLE);
        }

        LikesAdapter adapter = new LikesAdapter(profile_likes_list, canViewProfile);
        likes_profile_rv.setHasFixedSize(true);
        likes_profile_rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        likes_profile_rv.setAdapter(adapter);
    }
}