package com.example.projectdemo.callback.demo;

/**
 * 回调测试
 *
 * 1、老师调用学生抽象类的方法resolveQuestion()向学生提问；
 * 2、学生解决完毕问题之后调用老师的回调方法tellAnswer()。
 */
public class CallbackTest {
    public void testCallback() {
        Student student = new Tom();
        Teacher teacher = new Teacher(student);

        teacher.askQuestion();
    }
}
