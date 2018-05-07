package ru.dmitry.selection_committee.gui.screens.list.mvp;

import ru.dmitry.selection_committee.gui.mvp.MvpView;

import java.util.List;

public interface ListScreenView<T> extends MvpView {

    void onListReady(List<T> list);

}
