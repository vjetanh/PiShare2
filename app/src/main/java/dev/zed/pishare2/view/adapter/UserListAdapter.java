package dev.zed.pishare2.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import dev.zed.pishare2.R;
import dev.zed.pishare2.common.App;
import dev.zed.pishare2.entity.BaseItem;
import dev.zed.pishare2.entity.UserItem;
import dev.zed.pishare2.view.widget.FeedImageView;

/**
 * Created by Dr on 4/8/2015.
 */
public class UserListAdapter extends BaseListAdapter {
    ImageLoader imageLoader = App.getInstance().getImageLoader();
    private Activity activity;
    private LayoutInflater inflater;
    private List<BaseItem> userItems;

    public UserListAdapter(Activity activity, List<BaseItem> _userItems) {
        this.activity = activity;
        this.userItems = _userItems;
    }

    @Override
    public int getCount() {
        return userItems.size();
    }

    @Override
    public Object getItem(int position) {
        return userItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.friend_item, null); //user_item

        if (imageLoader == null)
            imageLoader = App.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView userEmail = (TextView) convertView
                .findViewById(R.id.userEmail);
        NetworkImageView profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        UserItem item = (UserItem)userItems.get(position);

        name.setText(item.getName());
        userEmail.setText(item.getEmail());
        // user profile pic
        profilePic.setImageUrl(item.getProfilePic(), imageLoader);

        return convertView;
    }
}
