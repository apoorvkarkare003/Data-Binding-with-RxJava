package com.test.tc;

import android.util.Log;

import com.test.tc.model.Counter;
import com.test.tc.retrofit.RetrofitProvider;
import com.test.utils.Utils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Presenter {

    private final String[] tenthChar = new String[1];
    private final String[] everyTenthChar = new String[1];
    private final String[] unique = new String[1];

    public void getData(RetrofitProvider provider, Counter counter) {

        final Observable<RetrofitProvider.Page> tenthCharObservable = provider.getApis().getTenthChar()
                .subscribeOn(Schedulers.io())
                .doOnNext(page ->
                        tenthChar[0] = Utils.parseTenthChar(page.content) + "");

        final Observable<RetrofitProvider.Page> everyTenthCharObservable = provider.getApis().getEveryTenthChar()
                .subscribeOn(Schedulers.io())
                .doOnNext(page ->
                        everyTenthChar[0] = Utils.parseEveryTenthChar(page.content));

        final Observable<RetrofitProvider.Page> uniqueWordObservable = provider.getApis().getUniqueChar()
                .subscribeOn(Schedulers.io())
                .doOnNext(page ->
                        unique[0] = Utils.parseEveryUniqueChar(page.content));

        getDataFromApi(tenthCharObservable, everyTenthCharObservable, uniqueWordObservable, counter);
    }

    /**
     * Calls 3 Apis in parallel
     * @param tenthCharObservable observes the tenth character of content
     * @param everyTenthCharObservable observes every tenth character of content
     * @param uniqueWordObservable observes unique words response
     * @param counter model class
     */
    private void getDataFromApi(Observable<RetrofitProvider.Page> tenthCharObservable,
                                Observable<RetrofitProvider.Page> everyTenthCharObservable,
                                Observable<RetrofitProvider.Page> uniqueWordObservable, Counter counter) {

        Observable.merge(tenthCharObservable
                        .observeOn(AndroidSchedulers.mainThread())
                        .doAfterNext(page ->
                                counter.setTenthChar(tenthChar[0] + "")),
                everyTenthCharObservable
                        .observeOn(AndroidSchedulers.mainThread())
                        .doAfterNext(page ->
                                counter.setEveryTenthChar(everyTenthChar[0])),
                uniqueWordObservable
                        .observeOn(AndroidSchedulers.mainThread())
                        .doAfterNext(page ->
                                counter.setUniqueChar(unique[0])))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetrofitProvider.Page>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Not implemented
                    }

                    @Override
                    public void onNext(RetrofitProvider.Page page) {
                        //Not implemented
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ERROR", e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Task", "completed");
                    }
                });
    }

}
