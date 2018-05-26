package ru.dmitry.selection_committee.gui.screens.admin.presenters;

import ru.dmitry.selection_committee.gui.mvp.MvpView;

/**
 * Интрефейс для управления экранами добавления
 */
public interface AddScreenView extends MvpView {

    void onSuccessAdded();

    void onFailAdded();

    void onFullNameEmpty();

    void onShortNameEmpty();

    void onReferenceNotSelected();

}
