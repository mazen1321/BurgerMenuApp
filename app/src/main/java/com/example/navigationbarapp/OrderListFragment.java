package com.example.navigationbarapp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;
public class OrderListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        // Retrieve data from arguments
        Bundle args = getArguments();
        if (args != null) {
            List<OrderItem> selectedItems = args.getParcelableArrayList("selectedItems");

            // Display the selected items in a ListView
            ListView listView = view.findViewById(R.id.listView);
            ArrayAdapter<OrderItem> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, selectedItems);
            listView.setAdapter(adapter);
        }

        return view;
    }
}


