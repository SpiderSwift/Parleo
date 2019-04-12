package com.leathersoft.parleo.fragment.users;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.ProfileFragment;
import com.leathersoft.parleo.fragment.PushFragmentInterface;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private PushFragmentInterface mPushFragmentInterface;

    public UserAdapter(PushFragmentInterface pushFragmentInterface) {
        mPushFragmentInterface = pushFragmentInterface;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(),R.layout.user_item_view,null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProfileFragment();
                mPushFragmentInterface.push(fragment);
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
