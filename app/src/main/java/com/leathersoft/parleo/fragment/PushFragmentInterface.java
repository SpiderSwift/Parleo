package com.leathersoft.parleo.fragment;

import androidx.fragment.app.Fragment;

public interface PushFragmentInterface {
    void push(Fragment fragment);
    void replace(Fragment fragment);
}
