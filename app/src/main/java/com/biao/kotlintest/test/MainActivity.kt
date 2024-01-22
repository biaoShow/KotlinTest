package com.biao.kotlintest.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import com.biao.kotlintest.R
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_test).setOnClickListener {

//            val exec = Runtime.getRuntime().exec("ifconfig | grep \"RX bytes:\"\n")
//            val exec = Runtime.getRuntime().exec("su -c ifconfig")
//            val p = ProcessBuilder()
//            p.command("su", "-c", "ifconfig | grep \"RX bytes\"")
//            val exec = p.start()
////            exec.waitFor()
//            val mReader = BufferedReader(InputStreamReader(exec.inputStream), 1024)
//            var line: String?
//            while (mReader.readLine().also { line = it } != null) {
//                Log.d(TAG, "$line")
//            }

//            val byteArray2 = ByteArray(256)
//            val len2 = exec.inputStream.read(byteArray2)
//            Log.d(TAG, String(byteArray2, 0, len2))


//            val mReader = BufferedReader(InputStreamReader(exec.inputStream), 1024)
//////            if (len2 != -1)
//            var line: String?
//            while (mReader.readLine().also { line = it } != null) {
//                Log.d(TAG, "$line")
//
//            }

//            Thread {
//                val byteArray = ByteArray(256)
//                var len = -1
//                while (exec.inputStream.read(byteArray).also { len = it } != -1) {
//                    Log.d(TAG, String(byteArray, 0, len))
//                }
//            }.start()
//
//
//            exec.outputStream.write("ifconfig | grep \"RX bytes:\"".toByteArray())
//            Log.d(TAG, "$len")

            fetchRemote { msg ->
                Log.i(TAG, "传入fetchRemote方法的具体实现，入参msg = $msg")
            }
            fetchLocal(2) { msg ->
                Log.i(TAG, "传入fetchLocal方法的具体实现，入参msg = $msg")
            }

            //启动应用测试
            Runtime.getRuntime().exec("am start  com.ktcp.video/.activity.HomeActivity")
        }

        findViewById<Button>(R.id.btn_test2).setOnClickListener {
            fetchRemote { msg ->
                Log.i(TAG, "传入fetchRemote方法的具体实现，入参msg = $msg")
                fetchLocal(msg) { msg ->
                    Log.i(TAG, "传入fetchLocal方法的具体实现，入参msg = $msg")
                }
            }
        }

        findViewById<Button>(R.id.btn_test3).setOnClickListener {
            test { msg, str ->
                Log.i(TAG, "传入fetchLocal方法的具体实现，入参: msg = $msg, str = $str")
                "$msg$str"
            }
        }

        findViewById<Button>(R.id.btn_test4).setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val test4 = test4()
                findViewById<Button>(R.id.btn_test4).text = test4
                Log.i(TAG, test4)
            }
            Log.i(TAG, "btn_test4 click")
        }


    }

    fun fetchRemote(onNext: (Int) -> Unit) {
        Thread.sleep(300)
        val value = 1
        onNext(value)
    }

    fun fetchLocal(id: Int, onNext: (Int) -> Unit) {
        Thread.sleep(300)
        val value = 3
        onNext(id + value)
    }


    fun test(method: (Int, String) -> String) {
        Log.d(TAG, "执行：test")
        val method = method(4, "biao1")
        Log.i(TAG, "method 返回：$method")
    }

    suspend fun test4(): String {
        return withContext(Dispatchers.IO) {
            SystemClock.sleep(10 * 1000L)
            "test4 result num = 5"
        }
    }
}
