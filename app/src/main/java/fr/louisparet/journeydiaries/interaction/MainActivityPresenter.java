package fr.louisparet.journeydiaries.interaction;

import java.io.Serializable;

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter, Serializable{
    private MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
    }


    @Override
    public void onSaveData(String name, String from, String to) {
        view.saveData(name, from, to);
    }
}