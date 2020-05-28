package com.example.easyfood.view.customer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.view.MainActivity;
import com.example.easyfood.viewmodel.CustomerBaseViewModel;


public class CustomerBaseActivity extends BaseActivity {
    private CustomerBaseViewModel viewModel;
    protected String customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customerId = firebaseAuth.getCurrentUser().getUid();

        viewModel = new ViewModelProvider(this).get(CustomerBaseViewModel.class);
        viewModel.init(customerId);

        // Listener method call here
        createNotificationChannel();
        onOrderChangeListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucustomer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ic_exit) {
            firebaseAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
        } else if (item.getItemId() == R.id.ic_cart) {
            startActivity(new Intent(this, BasketActivity.class));
        } else if (item.getItemId() == R.id.ic_account) {
            startActivity(new Intent(this, CustomerSettingsActivity.class));
        } else if (item.getItemId() == R.id.ic_home) {
            startActivity(new Intent(this, EateryActivity.class));
        } if (item.getItemId() == R.id.ic_dining) {
            startActivity(new Intent(this, CustomerOrdersActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void onOrderChangeListener() {
        viewModel.getOrderStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String order) {
                // Temporary
                // send push notification here

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "notifications_orderstatus")
                        .setSmallIcon(R.drawable.ic_cart)
                        .setContentTitle("Order Status Changed")
                        .setContentText(order)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                // Issue the notification.
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(0, builder.build());

                System.out.println(order);
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifications_orderstatus", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
