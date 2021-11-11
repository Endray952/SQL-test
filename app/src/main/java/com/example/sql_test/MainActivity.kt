package com.example.sql_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import com.example.sql_test.data_base.DataBaseConsts
import com.example.sql_test.data_base.DataBaseManager
enum class RadioOptions{
    KeySearch, NonKeySearch, Mask, Insert, GroupInsert, UpdateKey, UpdateNonKey, DeleteKey,DeleteNonKey,DeleteGroupKey,DeleteGroupNonKey,Compress,Compress200Left
}
class MainActivity : AppCompatActivity() {
    val data_base_manager = DataBaseManager(this)
    var selected_test = RadioOptions.KeySearch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val radio = findViewById<RadioGroup>(R.id.RadioGroup)
        radio.setOnCheckedChangeListener{group, checkedID ->
            when(checkedID){
                R.id.KeySearch -> selected_test = RadioOptions.KeySearch
                R.id.NonKeySearch -> selected_test = RadioOptions.NonKeySearch
                R.id.Mask -> selected_test = RadioOptions.Mask
                R.id.Insert -> selected_test = RadioOptions.Insert
                R.id.GroupInsert -> selected_test = RadioOptions.GroupInsert
                R.id.UpdateKey -> selected_test = RadioOptions.UpdateKey
                R.id.UpdateNonKey -> selected_test = RadioOptions.UpdateNonKey
                R.id.DeleteKey -> selected_test = RadioOptions.DeleteKey
                R.id.DeleteNonKey -> selected_test = RadioOptions.DeleteNonKey
                R.id.DeleteGroupKey -> selected_test = RadioOptions.DeleteGroupKey
                R.id.DeleteGroupNonKey -> selected_test = RadioOptions.DeleteGroupNonKey
                R.id.Compress -> selected_test = RadioOptions.Compress
                R.id.Compress200Left -> selected_test = RadioOptions.Compress200Left
            }
        }
    }

    override fun onResume() {
        super.onResume()
        data_base_manager.openDb()
        /**Init table */
        //data_base_manager.createDB()
        data_base_manager.db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${1000}_copy")
        data_base_manager.db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${10000}_copy")
        data_base_manager.db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${100000}_copy")
        data_base_manager.db?.execSQL("VACUUM")
    }
    fun bt_1000(view: View){
        when(selected_test){
            RadioOptions.KeySearch -> data_base_manager.testSearchWithID(1000)
            RadioOptions.NonKeySearch -> data_base_manager.testSearchWithNonKey(1000)
            RadioOptions.Mask -> data_base_manager.testSearchWithMask(1000)
            RadioOptions.Insert ->  data_base_manager.testAddItem(1000)
            RadioOptions.GroupInsert -> data_base_manager.testAddGroupOfItems(1000)
            RadioOptions.UpdateKey -> data_base_manager.testChangeCellWithID(1000)
            RadioOptions.UpdateNonKey -> data_base_manager.testChangeCellWithNonKey(1000)
            RadioOptions.DeleteKey -> data_base_manager.testDeleteCellWithID(1000)
            RadioOptions.DeleteNonKey -> data_base_manager.testDeleteCellWithNonKey(1000)
            RadioOptions.DeleteGroupKey -> data_base_manager.testDeleteGroupOfCells_key(1000)
            RadioOptions.DeleteGroupNonKey -> data_base_manager.testDeleteGroupOfCells_nonKey(1000)
            RadioOptions.Compress -> data_base_manager.testCompressionOfDB(1000)
            RadioOptions.Compress200Left -> data_base_manager.testCompressionOfDB200Left(1000)

        }
    }
    fun bt_10000(view: View){
        when(selected_test){
            RadioOptions.KeySearch -> data_base_manager.testSearchWithID(10000)
            RadioOptions.NonKeySearch -> data_base_manager.testSearchWithNonKey(10000)
            RadioOptions.Mask -> data_base_manager.testSearchWithMask(10000)
            RadioOptions.Insert ->  data_base_manager.testAddItem(10000)
            RadioOptions.GroupInsert -> data_base_manager.testAddGroupOfItems(10000)
            RadioOptions.UpdateKey -> data_base_manager.testChangeCellWithID(10000)
            RadioOptions.UpdateNonKey -> data_base_manager.testChangeCellWithNonKey(10000)
            RadioOptions.DeleteKey -> data_base_manager.testDeleteCellWithID(10000)
            RadioOptions.DeleteNonKey -> data_base_manager.testDeleteCellWithNonKey(10000)
            RadioOptions.DeleteGroupKey -> data_base_manager.testDeleteGroupOfCells_key(10000)
            RadioOptions.DeleteGroupNonKey -> data_base_manager.testDeleteGroupOfCells_nonKey(10000)
            RadioOptions.Compress -> data_base_manager.testCompressionOfDB(10000)
            RadioOptions.Compress200Left -> data_base_manager.testCompressionOfDB200Left(10000)

        }
    }
    fun bt_100000(view: View){
        when(selected_test){
            RadioOptions.KeySearch -> data_base_manager.testSearchWithID(100000)
            RadioOptions.NonKeySearch -> data_base_manager.testSearchWithNonKey(100000)
            RadioOptions.Mask -> data_base_manager.testSearchWithMask(100000)
            RadioOptions.Insert ->  data_base_manager.testAddItem(100000)
            RadioOptions.GroupInsert -> data_base_manager.testAddGroupOfItems(100000)
            RadioOptions.UpdateKey -> data_base_manager.testChangeCellWithID(100000)
            RadioOptions.UpdateNonKey -> data_base_manager.testChangeCellWithNonKey(100000)
            RadioOptions.DeleteKey -> data_base_manager.testDeleteCellWithID(100000)
            RadioOptions.DeleteNonKey -> data_base_manager.testDeleteCellWithNonKey(100000)
            RadioOptions.DeleteGroupKey -> data_base_manager.testDeleteGroupOfCells_key(100000)
            RadioOptions.DeleteGroupNonKey -> data_base_manager.testDeleteGroupOfCells_nonKey(100000)
            RadioOptions.Compress -> data_base_manager.testCompressionOfDB(100000)
            RadioOptions.Compress200Left -> data_base_manager.testCompressionOfDB200Left(100000)

        }
    }
    override fun onStop() {
        super.onStop()
        data_base_manager.closeDb()
    }
}