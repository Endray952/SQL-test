package com.example.sql_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import com.example.sql_test.data_base.DataBaseConsts
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
        //data_base_manager.testDeleteGroupOfCells_1()
            //data_base_manager.testDeleteGroupOfCells_2()
        //data_base_manager.testCompressionOfDB()
        //data_base_manager.testCompressionOfDB200Left()
        //data_base_manager.test()
        /*for (i in 1..20) {
            val rnd = (1..DataBaseConsts.SIZE).random()
            Log.d("MyLog", rnd.toString())
        }*/
        //data_base_manager.createDB()
        //data_base_manager.db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${1000}_copy (${DataBaseConsts.TestTable.COLUMN_NAME_INTEGER}) VALUES (228)")
        //data_base_manager.db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${1000}_copy")
        //data_base_manager.db?.execSQL("VACUUM")
       /* data_base_manager.db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${1000}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${1000}")
        data_base_manager.db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${1000}_copy " +
                "WHERE ${DataBaseConsts.TestTable.ID} = 2")*/
        data_base_manager.db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${1000}_copy")
        data_base_manager.db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${10000}_copy")
        data_base_manager.db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${100000}_copy")
        data_base_manager.db?.execSQL("VACUUM")
    }
    fun bt_1000(view: View){
        //data_base_manager.testSearchWithID(1000)
        data_base_manager.testSearchWithNonKey(1000)
       // data_base_manager.testSearchWithMask(1000)
        //data_base_manager.testAddItem(1000)
        //data_base_manager.testAddGroupOfItems_1(1000)
        //data_base_manager.testChangeCellWithID(1000)
        //data_base_manager.testChangeCellWithNonKey(1000)
        //data_base_manager.testDeleteCellWithID(1000)
        //data_base_manager.testDeleteCellWithNonKey(1000)
        //data_base_manager.testDeleteGroupOfCells_1(1000)
        //data_base_manager.testDeleteGroupOfCells_2(1000)
       // data_base_manager.testCompressionOfDB(1000)
        //data_base_manager.testCompressionOfDB200Left(1000)
    }
    fun bt_10000(view: View){
        //data_base_manager.testSearchWithID(10000)
        data_base_manager.testSearchWithNonKey(10000)
       // data_base_manager.testSearchWithMask(10000)
        //data_base_manager.testAddItem(10000)
        //data_base_manager.testAddGroupOfItems_1(10000)
        //data_base_manager.testChangeCellWithID(10000)
        //data_base_manager.testChangeCellWithNonKey(10000)
        //data_base_manager.testDeleteCellWithID(10000)
        //data_base_manager.testDeleteCellWithNonKey(10000)
        //data_base_manager.testDeleteGroupOfCells_1(10000)
        //data_base_manager.testDeleteGroupOfCells_2(10000)
        //data_base_manager.testCompressionOfDB(10000)
       // data_base_manager.testCompressionOfDB200Left(10000)
    }
    fun bt_100000(view: View){
        //data_base_manager.testSearchWithID(100000)
        data_base_manager.testSearchWithNonKey(100000)
        //data_base_manager.testSearchWithMask(100000)
        //data_base_manager.testAddItem(100000)
        //data_base_manager.testAddGroupOfItems_1(100000)
        //data_base_manager.testChangeCellWithID(100000)
        //data_base_manager.testChangeCellWithNonKey(100000)
        //data_base_manager.testDeleteCellWithID(100000)
        //data_base_manager.testDeleteCellWithNonKey(100000)
        //data_base_manager.testDeleteGroupOfCells_1(100000)
        //data_base_manager.testDeleteGroupOfCells_2(100000)
        //data_base_manager.testCompressionOfDB(100000)
        //data_base_manager.testCompressionOfDB200Left(100000)
    }
    override fun onStop() {
        super.onStop()
        data_base_manager.closeDb()
    }
}