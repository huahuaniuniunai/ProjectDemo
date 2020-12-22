//package com.example.projectdemo.mvp.contract;
//
//import androidx.annotation.NonNull;
//
//import com.ctq.eqc.model.messages.MessageBody;
//import com.ctq.eqc.model.messages.MessagesCharHolder;
//import com.ctq.eqc.mvp.BasePresenterImpl;
//import com.ctq.eqc.mvp.BaseView;
//
//import java.util.List;
//
///**
// * User: wangjian
// * Date: 2020/11/4
// * Time: 5:05 PM
// */
//public interface MessageDetailContract {
//
//    interface View extends BaseView<Presenter> {
//        void setTitleBar(MessagesCharHolder h);
//
//        void setMoreStatus();
//
//        void adapter(List<MessageBody> bodys, boolean editing);
//
//        void notifyAdapter(boolean edit);
//
//        void clearEdit();
//    }
//
//    abstract class Presenter extends BasePresenterImpl<View> {
//        public Presenter(@NonNull View v) {
//            super(v);
//        }
//
//        public abstract void sendMessage(String message);
//
//        public abstract void resendMessage(MessageBody body);
//
//        public abstract void moreStatusChange();
//
//        public abstract void choiceAll();
//
//        public abstract void delMessages();
//    }
//}
