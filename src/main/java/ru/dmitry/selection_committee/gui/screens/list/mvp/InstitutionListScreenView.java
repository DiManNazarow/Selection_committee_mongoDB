package ru.dmitry.selection_committee.gui.screens.list.mvp;

import ru.dmitry.selection_committee.gui.mvp.MvpView;
import ru.dmitry.selection_committee.server.models.Institution;

import java.util.List;

public interface InstitutionListScreenView extends MvpView {

    void onInstitutionListReady(List<Institution> institutions);

}
