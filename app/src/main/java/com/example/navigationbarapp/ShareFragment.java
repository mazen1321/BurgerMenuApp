package com.example.navigationbarapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ShareFragment extends Fragment {

    public ShareFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        // Get the ListView from the layout
        ListView listView = view.findViewById(R.id.shareListView);

        // Create an ArrayAdapter with the social media options
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.social_media_options)
        );

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        // Set a click listener for the items in the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Show a toast message with the selected social media
                String toastMessage = selectedItem + " Shared!";
                Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}


