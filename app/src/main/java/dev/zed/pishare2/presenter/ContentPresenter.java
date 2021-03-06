package dev.zed.pishare2.presenter;

import android.support.v7.widget.SearchView;

import java.util.ArrayList;

import dev.zed.pishare2.common.App;
import dev.zed.pishare2.entity.BaseItem;
import dev.zed.pishare2.listener.OnContentListerner;
import dev.zed.pishare2.model.NewsFeedModel;
import dev.zed.pishare2.model.UsersModel;
import dev.zed.pishare2.model.interfaces.INewsFeedModel;
import dev.zed.pishare2.model.interfaces.IUsersModel;
import dev.zed.pishare2.presenter.interfaces.IContentPresenter;
import dev.zed.pishare2.view.interfaces.IMainFragView;

/**
 * Created by Dr on 4/7/2015.
 */
public class ContentPresenter implements IContentPresenter, OnContentListerner, SearchView.OnQueryTextListener {
    private IMainFragView mainView;
    private INewsFeedModel newsFeedModel;
    private IUsersModel usersModel;

    public ContentPresenter(IMainFragView _mainView) {
        mainView = _mainView;
        if (mainView.getType() == 0) {
            newsFeedModel = new NewsFeedModel();
//            ArrayList<BaseItem> items =
            newsFeedModel.getItems(App.CurUser, this);
            mainView.setItems(new ArrayList<BaseItem>());
        } else if (mainView.getType() == 1) {
            usersModel = new UsersModel();
            usersModel.getItems(App.CurUser, this);
            mainView.setItems(new ArrayList<BaseItem>());
        }
    }

    @Override
    public void OnDatanotifyDataSetChanged(ArrayList items) {
        mainView.setItems(items);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        usersModel = new UsersModel();
        if(mainView.getType() != 1 && newText.equals("")){
            usersModel = new UsersModel();
            usersModel.getItems(App.CurUser, this);
            mainView.setItems(new ArrayList<BaseItem>(),1);
        }else{
            usersModel.search(App.CurUser, newText, this);
        }
        return false;
    }
}
