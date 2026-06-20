package com.fongmi.android.tv.ui.dialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.fongmi.android.tv.bean.Class;
import com.fongmi.android.tv.databinding.DialogTypeBinding;
import com.fongmi.android.tv.ui.adapter.TypeAdapter;
import com.fongmi.android.tv.ui.adapter.TypeDialogAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

public class TypeDialog extends BaseBottomSheetDialog implements TypeAdapter.OnClickListener {

    private DialogTypeBinding binding;
    private TypeAdapter.OnClickListener listener;
    private List<Class> items;

    public static TypeDialog create() {
        return new TypeDialog();
    }

    public TypeDialog items(List<Class> items) {
        this.items = items;
        return this;
    }

    public void show(Fragment fragment) {
        for (Fragment f : fragment.getChildFragmentManager().getFragments()) if (f instanceof TypeDialog) return;
        this.listener = (TypeAdapter.OnClickListener) fragment;
        show(fragment.getChildFragmentManager(), null);
    }

    @Override
    protected ViewBinding getBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return binding = DialogTypeBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        binding.recycler.setHasFixedSize(false);
        binding.recycler.setItemAnimator(null);
        FlexboxLayoutManager manager = new FlexboxLayoutManager(requireContext(), FlexDirection.ROW);
        manager.setFlexWrap(FlexWrap.WRAP);
        binding.recycler.setLayoutManager(manager);
        binding.recycler.setAdapter(new TypeDialogAdapter(this, items));
    }

    @Override
    public void onItemClick(int position, Class item) {
        dismiss();
        listener.onItemClick(position, item);
    }
}
