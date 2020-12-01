package com.example.projectdemo.util.callback.demo;

/**
 * 学生具体对象
 */
class Tom extends Student {
    @Override
    public void resolveQuestion(Callback callback) {
        super.resolveQuestion(callback);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 回调，告诉老师作业写了多久
        callback.tellAnswer(30);
    }
}
