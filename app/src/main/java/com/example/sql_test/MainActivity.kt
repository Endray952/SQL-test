package com.example.sql_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import com.example.sql_test.data_base.DataBaseManager

class MainActivity : AppCompatActivity() {
    val data_base_manager = DataBaseManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //data_base_manager.openDb()
    }

    override fun onResume() {
        super.onResume()
        data_base_manager.openDb()
        /**Init table */
        //data_base_manager.createDB()
       /* val thread = Thread{
            var startTime = System.currentTimeMillis()
            data_base_manager.createDB(1000)
            var endTime = System.currentTimeMillis()
            Log.d("MyLog",(endTime - startTime).toString() )
        }
        thread.start()*/
        //data_base_manager.testSearchWithID()
        //data_base_manager.testSearchWithNonKey()
        //data_base_manager.testSearchWithMask()
       // data_base_manager.testAddItem()
        //data_base_manager.testAddGroupOfItems_1()
        //data_base_manager.testAddGroupOfItems_2()
        //data_base_manager.testChangeCellWithID()
        //data_base_manager.testChangeCellWithNonKey()
        //data_base_manager.testDeleteCellWithID()
        //data_base_manager.testDeleteCellWithNonKey()
        data_base_manager.testDeleteGroupOfCells_1()
            //data_base_manager.testDeleteGroupOfCells_2()
        //data_base_manager.testCompressionOfDB()
        //data_base_manager.testCompressionOfDB200Left()
        //data_base_manager.test()

    }
    override fun onStop() {
        super.onStop()
        data_base_manager.closeDb()
    }
}