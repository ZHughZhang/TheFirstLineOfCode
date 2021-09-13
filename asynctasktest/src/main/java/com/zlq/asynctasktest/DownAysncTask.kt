package com.zlq.asynctasktest

import android.os.AsyncTask

/**
 *   @Auther:  asad
 *   @Date:  2021/08/03 15:56
 *   @Description:  TODO
 *   @ClassName:  com.zlq.asynctasktest
 *   @PackageName:  DownAysncTask
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
class DownAysncTask : AsyncTask<Unit, Int, Boolean>() {




    /**
     * 这个方法中的所有代码都会在子线程中运行，
     * 我们应该在这里去处理所有的耗时任务。任务一旦完成，
     * 就可以通过return语句将任务的执行结果返回，
     * 如果AsyncTask的第三个泛型参数指定的是Unit，
     * 就可以不返回任务执行结果。注意，在这个方法中是不可以进行UI操作的，
     * 如果需要更新UI元素，比如说反馈当前任务的执行进度，
     * 可以调用publishProgress (Progress...)方法来完成。
     * */
    override fun doInBackground(vararg params: Unit?): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * 这个方法会在后台任务开始执行之前调用，
     * 用于进行一些界面上的初始化操作，
     * 比如显示一个进度条对话框等。
     * */
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onCancelled() {
        super.onCancelled()
    }

    /**
     * 当后台任务执行完毕并通过return语句进行返回时，
     * 这个方法就很快会被调用。返回的数据会作为参数传递到此方法中，
     * 可以利用返回的数据进行一些UI操作，比如说提醒任务执行的结果，
     * 以及关闭进度条对话框等
     * */
    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
    }

    override fun onCancelled(result: Boolean?) {
        super.onCancelled(result)
    }

    /**
     * 当在后台任务中调用了publishProgress(Progress...)方法后，
     * onProgressUpdate (Progress...)方法就会很快被调用，该方法中携带的参数就是在后台任务中传递过来的。
     * 在这个方法中可以对UI进行操作，利用参数中的数值就可以对界面元素进行相应的更新。
     * */
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

}