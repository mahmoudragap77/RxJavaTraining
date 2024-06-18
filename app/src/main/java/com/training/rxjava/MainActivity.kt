package com.training.rxjava

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doOnTextChanged
import com.training.rxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var compositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        compositeDisposable = CompositeDisposable()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        foo()
    }

    @SuppressLint("CheckResult")
    private fun foo() {
        val observable = Observable.interval(1, TimeUnit.SECONDS).take(10)

        val subject = PublishSubject.create<Int>()
//        observable.subscribe(subject)

//        Thread.sleep(3000)
        subject.onNext(5)
        subject.onNext(10)
        subject.subscribe(
            {
                Log.d(TAG, "foo: $it")
            },
            {
                Log.d(
                    TAG, "foo: ${it.message}"
                )
            })

        subject.onNext(100)
        subject.onNext(200)
    }


    companion object {
        const val TAG = "MainActivity"
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}