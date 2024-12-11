package com.example.navigationbarapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<OrderItem> selectedItems;

    private ListView listView;


    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = view.findViewById(R.id.listView);

        // Initialize spinners
        Spinner burgersSpinner = view.findViewById(R.id.burgers_spinner);
        Spinner drinksSpinner = view.findViewById(R.id.drinks_spinner);

        // Populate spinners with dummy data (replace this with your actual data)
        ArrayAdapter<CharSequence> burgersAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.choose_burger_options,
                android.R.layout.simple_spinner_item
        );
        burgersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        burgersSpinner.setAdapter(burgersAdapter);

        ArrayAdapter<CharSequence> drinksAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.choose_drink_options,
                android.R.layout.simple_spinner_item
        );
        drinksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drinksSpinner.setAdapter(drinksAdapter);

        // Initialize radio group
        RadioGroup friesRadioGroup = view.findViewById(R.id.fries_radio_group);

        // Set onClickListener for the view_button
        Button viewButton = view.findViewById(R.id.view_button);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewOrderButtonClick();
            }
        });

        // Set onClickListener for the submit_button
        Button submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitOrderButtonClick();
            }
        });

        return view;
    }

    public void onViewOrderButtonClick() {
        // Get selected items from spinners

        if (selectedItems != null) {
            selectedItems.clear();
        }

        Spinner burgersSpinner = requireView().findViewById(R.id.burgers_spinner);
        Spinner drinksSpinner = requireView().findViewById(R.id.drinks_spinner);

        String selectedBurger = burgersSpinner.getSelectedItem().toString();
        String selectedDrink = drinksSpinner.getSelectedItem().toString();

        // Get selected fries from RadioGroup
        RadioGroup friesRadioGroup = requireView().findViewById(R.id.fries_radio_group);
        int selectedFriesId = friesRadioGroup.getCheckedRadioButtonId();
        String selectedFries = "";

        if (selectedFriesId != -1) {
            RadioButton selectedRadioButton = friesRadioGroup.findViewById(selectedFriesId);
            selectedFries = selectedRadioButton.getText().toString();
        }

        // Get comment from EditText
        EditText commentEditText = requireView().findViewById(R.id.comment);
        String comment = commentEditText.getText().toString();

        // Create an OrderItem object with the selected data
        OrderItem orderItem = new OrderItem(selectedBurger, selectedDrink, selectedFries, comment);

        // Add the OrderItem to the list of selected items
        if (selectedItems == null) {
            selectedItems = new ArrayList<>();
        }
        selectedItems.add(orderItem);

        // You can add additional logic here if needed

        // Navigate to OrderListFragment
        OrderListFragment orderListFragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("selectedItems", new ArrayList<>(selectedItems));
        orderListFragment.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, orderListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onSubmitOrderButtonClick() {
        // Show a toast message
        Toast.makeText(getContext(), "Order received!", Toast.LENGTH_SHORT).show();

        // Clear selected items (assuming selectedItems is the list containing selected items)
        if (selectedItems != null) {
            selectedItems.clear();

            // Notify the adapter to update the ListView
            if (listView != null && listView.getAdapter() != null) {
                ((ArrayAdapter<?>) listView.getAdapter()).notifyDataSetChanged();
            }
        }

        // Uncheck the radio buttons
        RadioGroup friesRadioGroup = requireView().findViewById(R.id.fries_radio_group);
        friesRadioGroup.clearCheck();

        // Clear the comment field
        EditText commentEditText = requireView().findViewById(R.id.comment);
        commentEditText.setText("");
    }

}



