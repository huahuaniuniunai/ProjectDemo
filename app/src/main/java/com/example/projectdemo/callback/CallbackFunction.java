package com.example.projectdemo.callback;

public interface CallbackFunction {
    void onStart() ;

    void onSuccess() ;

    void onFailed(String msg) ;
}
