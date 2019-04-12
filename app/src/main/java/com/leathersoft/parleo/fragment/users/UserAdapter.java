package com.leathersoft.parleo.fragment.users;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.ProfileFragment;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private FragmentManager mFragmentManager;

    public UserAdapter(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(),R.layout.user_item_view,null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment fragment = new ProfileFragment();

                mFragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container,fragment)
                        .addToBackStack(null)
                        .commit();

//                v.getContext().startActivity(
//                        new Intent(v.getContext(), ProfileActivity.class)
//                );


            }
        });
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
