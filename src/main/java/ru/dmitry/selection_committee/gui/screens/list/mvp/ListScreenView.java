package ru.dmitry.selection_committee.gui.screens.list.mvp;

import ru.dmitry.selection_committee.gui.mvp.MvpView;

import java.util.List;

/**
 * Интерфейс для управления экраном списка
 * @param <T> модель данных, которая хранит данные элемента списка
 */
public interface ListScreenView<T> extends MvpView {

    void onListReady(List<T> list);

    void onFilterClear();

}
