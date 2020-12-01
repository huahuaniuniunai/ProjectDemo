package com.example.projectdemo.util.callback.demo;

import android.util.Log;

import com.example.projectdemo.util.log.LogUtil;

/**
 * 老师对象
 */
class Teacher implements Callback {

    private Student mStudent;
    public Teacher(Student student) {
        this.mStudent = student;
    }

    // 向学生提问
    public void askQuestion() {
        mStudent.resolveQuestion(this);
    }

    @Override
    public void tellAnswer(int answer) {
        LogUtil.d("answer", "答案是：" + answer + "分钟。");
    }
}
