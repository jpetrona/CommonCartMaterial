package in.commoncart.android;

import android.app.Dialog;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;


public class SearchItemsActivity extends AppCompatActivity {

    private boolean isList;

    private ListView mItemsListView;
    private GridView mItemsGridView;
    private DrawerLayout mDrawerLayout;

    private ListView filterSubcategories, filterBrands, filterStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_items, menu);
        return true;
    }

    private void createList() {
        setContentView(R.layout.activity_search_items);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.activity_search_items_drawer);
        mItemsListView = (ListView)findViewById(R.id.activity_search_items_listview);
        //Dummy adapter
        mItemsListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Integer getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.product_list_item, null);
                }
                return convertView;
            }
        });

        filterSubcategories = (ListView)findViewById(R.id.activity_search_items_filter_subcategories_listview);
        String[] dummySubs = {"Logitech", "Cyborg", "Razer", "Steelseries"};
        filterSubcategories.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dummySubs));
        setListViewHeightBasedOnChildren(filterSubcategories);

        filterBrands = (ListView)findViewById(R.id.activity_search_items_filter_brand_listview);
        //Dummy adapter
        filterBrands.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Integer getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_checkbox_item, null);
                }
                return convertView;
            }
        });
        setListViewHeightBasedOnChildren(filterBrands);

        filterStores = (ListView)findViewById(R.id.activity_search_items_filter_store_listview);
        //Dummy adapter
        filterStores.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Integer getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_checkbox_item, null);
                }
                return convertView;
            }
        });
        setListViewHeightBasedOnChildren(filterStores);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createGrid() {
        setContentView(R.layout.activity_search_items_gridview);
        mItemsGridView = (GridView)findViewById(R.id.activity_search_items_gridview);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.activity_search_items_drawer);
        //Dummy adapter
        mItemsGridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Integer getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.product_grid_item, null);
                }
                return convertView;
            }
        });

        filterSubcategories = (ListView)findViewById(R.id.activity_search_items_filter_subcategories_listview);
        String[] dummySubs = {"Logitech", "Cyborg", "Razer", "Steelseries"};
        filterSubcategories.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dummySubs));
        setListViewHeightBasedOnChildren(filterSubcategories);

        filterBrands = (ListView)findViewById(R.id.activity_search_items_filter_brand_listview);
        //Dummy adapter
        filterBrands.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Integer getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_checkbox_item, null);
                }
                return convertView;
            }
        });
        setListViewHeightBasedOnChildren(filterBrands);

        filterStores = (ListView)findViewById(R.id.activity_search_items_filter_store_listview);
        //Dummy adapter
        filterStores.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Integer getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_checkbox_item, null);
                }
                return convertView;
            }
        });
        setListViewHeightBasedOnChildren(filterStores);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init(boolean isList) {
        this.isList = isList;
        if(isList) createList();
        else createGrid();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.sort_dialog);
            dialog.show();
            return true;
        } else if(id == R.id.action_filter) {
            mDrawerLayout.openDrawer(Gravity.RIGHT);
            return true;
        } else if(id == android.R.id.home) {
            finish();
        }  else if(id == R.id.action_switch_view) {
            init(!isList);
        }

        return super.onOptionsItemSelected(item);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ListView.LayoutParams(desiredWidth, ListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
