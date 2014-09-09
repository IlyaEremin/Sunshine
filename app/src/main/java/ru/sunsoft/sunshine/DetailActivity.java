package ru.sunsoft.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.sunsoft.sunshine.utils.Consts;


public class DetailActivity extends ActionBarActivity {

    String details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent data = getIntent();
        if (data != null) {
            details = data.getStringExtra(Consts.BUNDLE_DAY_INFO);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, PlaceholderFragment.newInstance(details))
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        String details;

        public static PlaceholderFragment newInstance(String details) {
            PlaceholderFragment placeholderFragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(Consts.BUNDLE_DAY_INFO, details);
            placeholderFragment.setArguments(args);
            return placeholderFragment;
        }

        public PlaceholderFragment() {
        }

        @Override public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
            Bundle args = getArguments();
            if (args != null) {
                details = args.getString(Consts.BUNDLE_DAY_INFO);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            ((TextView) rootView.findViewById(R.id.detail_textview)).setText(details);
            return rootView;
        }

        @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.detail, menu);
            MenuItem shareMenu = menu.findItem(R.id.menu_item_share);
            ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenu);
            if (shareActionProvider != null) {
                shareActionProvider.setShareIntent(new Intent(Intent.ACTION_SEND)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT, details + " #sunshineapp"));
            }
        }

        @Override public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_settings:
                    startActivity(new Intent(getActivity(), SettingsActivity.class));
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }
}
