package ru.dmitry.selection_committee.gui.mvp;

/**
 * Абстрактный класс базового представителя (презентера)
 * @param <V> интрерфейс управления экраном
 */
public abstract class BasePresenter<V extends MvpView> {

    private V mvpView;

    public BasePresenter(V mvpView){
        this.mvpView = mvpView;
    }

    protected V getViewState(){
        return mvpView;
    }

}
