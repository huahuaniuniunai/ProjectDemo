//package com.example.projectdemo.mvp.contract;
//
//import androidx.annotation.NonNull;
//
//import com.ctq.eqc.model.messages.Message;
//import com.ctq.eqc.model.messages.MessagesHolder;
//import com.ctq.eqc.mvp.BasePresenterImpl;
//import com.ctq.eqc.mvp.BaseView;
//
//import java.util.List;
//
///**
// * User: wangjian
// * Date: 2020/11/2
// * Time: 5:46 PM
// */
//public interface MessagesContract {
//    interface View extends BaseView<Presenter> {
//        void setTitleBar(MessagesHolder h);
//
//        void setMoreStatus();
//
//        void adapter(List<Message> messages, boolean edit);
//
//        void notifyAdapter(boolean edit);
//    }
//
//    abstract class Presenter extends BasePresenterImpl<View> {
//        public Presenter(@NonNull View v) {
//            super(v);
//        }
//
//        public abstract void moreStatusChange();
//
//        public abstract void choiceAll();
//
//        public abstract void delMessages();
//    }
//}
