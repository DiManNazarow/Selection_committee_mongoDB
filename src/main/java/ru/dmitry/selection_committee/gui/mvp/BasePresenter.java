package ru.dmitry.selection_committee.gui.mvp;

public abstract class BasePresenter<V extends MvpView> {

    private V mvpView;

    public BasePresenter(V mvpView){
        this.mvpView = mvpView;
    }

    protected V getViewState(){
        return mvpView;
    }

}
