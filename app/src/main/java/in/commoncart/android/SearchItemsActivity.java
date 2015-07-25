package in.commoncart.android;

import android.app.Dialog;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class SearchItemsActivity extends AppCompatActivity {

    private ExpandableListView filterListView;

    private boolean isList;

    private ListView mItemsListView;
    private GridView mItemsGridView;
    private DrawerLayout mDrawerLayout;

    static boolean[] isExpanded;

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
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.product_list_item, null);
                }
                return convertView;
            }
        });
    }

    private void createGrid() {
        setContentView(R.layout.activity_search_items_gridview);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.activity_search_items_drawer);
        mItemsGridView = (GridView)findViewById(R.id.activity_search_items_gridview);
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
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.product_grid_item, null);
                }
                return convertView;
            }
        });
    }

    private void init(boolean isList) {
        this.isList = isList;
        if(isList) createList();
        else createGrid();

        filterListView = (ExpandableListView)findViewById(R.id.activity_search_items_filter_list);
        final String[] headers = {"Subcategories", "Brands", "Stores"};
        final String[] dummySubs = {"Logitech", "Cyborg", "Razer", "Steelseries"};
        isExpanded = new boolean[headers.length];
        filterListView.setAdapter(new BaseExpandableListAdapter() {
            @Override
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(filterListView);
            }

            @Override
            public void onGroupExpanded(int groupPosition) {
                super.onGroupExpanded(groupPosition);
                isExpanded[groupPosition] = true;
                setListViewHeightBasedOnChildren(filterListView);
            }

            @Override
            public void onGroupCollapsed(int groupPosition) {
                super.onGroupCollapsed(groupPosition);
                isExpanded[groupPosition] = false;
                setListViewHeightBasedOnChildren(filterListView);
            }

            @Override
            public int getGroupCount() {
                return 3;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return 4;
            }

            @Override
            public Integer getGroup(int groupPosition) {
                return groupPosition;
            }

            @Override
            public Integer getChild(int groupPosition, int childPosition) {
                return groupPosition * childPosition;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return (long)groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return (long)groupPosition * childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.filter_listview_header, null);
                }
                SearchItemsActivity.isExpanded[groupPosition] = isExpanded;
                TextView text1 = (TextView)convertView.findViewById(R.id.filter_listview_header_name);
                text1.setText(headers[groupPosition]);
                return convertView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_checkbox_item, null);
                }
                CheckBox check1 = (CheckBox)convertView.findViewById(R.id.list_checkbox_item_checkbox);
                check1.setText(dummySubs[childPosition]);
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }
        });

        setListViewHeightBasedOnChildren(filterListView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public static void setListViewHeightBasedOnChildren(ExpandableListView listView) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        View groupView = null;
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            groupView = listAdapter.getGroupView(i, isExpanded[i], groupView, null);
            if (i == 0)
                groupView.setLayoutParams(new ListView.LayoutParams(desiredWidth, ListView.LayoutParams.WRAP_CONTENT));

            groupView.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += groupView.getMeasuredHeight();
            if(isExpanded[i]) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    view = listAdapter.getChildView(i, j, j == listAdapter.getChildrenCount(i) - 1, view, null);
                    if (i == 0)
                        view.setLayoutParams(new ListView.LayoutParams(desiredWidth, ListView.LayoutParams.WRAP_CONTENT));

                    view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
                    totalHeight += view.getMeasuredHeight();
                }
            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /**Regular list test adapter*/
    private static class TestBaseAdapter extends BaseAdapter{

            private LayoutInflater mInfalter;

            public TestBaseAdapter(LayoutInflater i) {
                mInfalter = i;
            }

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
                    convertView = mInfalter.inflate(R.layout.list_checkbox_item, null);
                }
                return convertView;
            }
        }

}
