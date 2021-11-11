package com.example.sql_test.data_base

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.util.Log.INFO
import com.example.sql_test.data_base.DataBaseConsts.SIZE
import java.util.*
import java.util.logging.Level.INFO

class DataBaseManager(context: Context) {
    val DbHelper = DataBaseHelper(context)
    var db: SQLiteDatabase? = null
    fun openDb(){
        db = DbHelper.writableDatabase
    }
    fun createDB(){
        val thread_1 = Thread{
        for (i in 1..1000){
            val str = "str$i"
            val real: Double = i.toDouble()
            val rnd_int = (1..10).random()
            db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}1000" +
                    "(${DataBaseConsts.TestTable.COLUMN_NAME_INTEGER}, ${DataBaseConsts.TestTable.COLUMN_NAME_TEXT}, " +
                    "${DataBaseConsts.TestTable.COLUMN_NAME_DOUBLE}, ${DataBaseConsts.TestTable.COLUMN_NAME_RANDOM_INT}) VALUES ($i, '$str', $real, $rnd_int)")
                }
            Log.d("MyLog", "Creation 1000 finished")
            }
        thread_1.start()
        val thread_2 = Thread{
            for (i in 1..10000){
                val str = "str$i"
                val real: Double = i.toDouble()
                val rnd_int = (1..100).random()
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}10000" +
                        "(${DataBaseConsts.TestTable.COLUMN_NAME_INTEGER}, ${DataBaseConsts.TestTable.COLUMN_NAME_TEXT}, " +
                        "${DataBaseConsts.TestTable.COLUMN_NAME_DOUBLE}, ${DataBaseConsts.TestTable.COLUMN_NAME_RANDOM_INT}) VALUES ($i, '$str', $real, $rnd_int)")
            }
            Log.d("MyLog", "Creation 10.000 finished")
        }
        thread_2.start()
        val thread_3 = Thread{
            for (i in 1..100000){
                val str = "str$i"
                val real: Double = i.toDouble()
                val rnd_int = (1..1000).random()
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}100000" +
                        "(${DataBaseConsts.TestTable.COLUMN_NAME_INTEGER}, ${DataBaseConsts.TestTable.COLUMN_NAME_TEXT}, " +
                        "${DataBaseConsts.TestTable.COLUMN_NAME_DOUBLE}, ${DataBaseConsts.TestTable.COLUMN_NAME_RANDOM_INT}) VALUES ($i, '$str', $real, $rnd_int)")
            }
            Log.d("MyLog", "Creation 100.000 finished")
        }
        thread_3.start()
    }
    fun testSearchWithID(size: Int){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..1000) {
                val rnd = (1..size).random()
                val startTime = System.nanoTime()
                /** rawQuery for SELECT queries, execSQL for non SELECT */
                db?.rawQuery("SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}$size " +
                        "WHERE ${DataBaseConsts.TestTable.ID} = $rnd", null)
                val endTime = System.nanoTime()
                sum_time += (endTime - startTime)
            }
            Log.d("MyLog", "testSearchWithID $size: ${(sum_time/1000)}")
        }
        thread.start()
    }

    fun testSearchWithNonKey(size: Int){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..1000) {
                val rnd = (1..size/100).random()
                val startTime = System.nanoTime()
                db?.rawQuery("SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}$size " +
                        "WHERE ${DataBaseConsts.TestTable.COLUMN_NAME_RANDOM_INT} = $rnd", null)
                val endTime = System.nanoTime()
                sum_time += (endTime - startTime)
            }
            Log.d("MyLog", "testSearchWithNonKey ${size}: ${(sum_time/1000)}")
        }
        thread.start()
    }
    fun testSearchWithMask(size: Int){
        val thread = Thread {
            var sum_time: Long = 0

            for (i in 1..100) {
                val startTime = System.nanoTime()
                db?.rawQuery("SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}$size WHERE ${DataBaseConsts.TestTable.COLUMN_NAME_TEXT} LIKE '%2'", null)
                val endTime = System.nanoTime()
                sum_time += (endTime - startTime)
            }
            Log.d("MyLog", "testSearchWithMask $size: ${(sum_time/100)}")
        }
        thread.start()
    }
    fun testAddItem(size: Int){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..100) {
                /**Copy database */
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                val str = "str$i"
                val real: Double = i.toDouble()
                var startTime = System.nanoTime()
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy " +
                        "(${DataBaseConsts.TestTable.COLUMN_NAME_INTEGER}, ${DataBaseConsts.TestTable.COLUMN_NAME_TEXT},  " +
                        "${DataBaseConsts.TestTable.COLUMN_NAME_DOUBLE}, ${DataBaseConsts.TestTable.COLUMN_NAME_RANDOM_INT}) VALUES ($i, '$str', $real, $i)")
                var endTime = System.nanoTime()
                sum_time += (endTime - startTime)
                db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                db?.execSQL("VACUUM")
            }
            Log.d("MyLog", "testAddItem ${size}: ${(sum_time/100)}")
        }
        thread.start()

    }
    fun testAddGroupOfItems(size: Int, group_size: Int = 10){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..100) {
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                val str = "str$i"
                val real: Double = i.toDouble()
                val startTime = System.nanoTime()
                for(k in 1..group_size) {
                    db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy " +
                                "(${DataBaseConsts.TestTable.COLUMN_NAME_INTEGER}, ${DataBaseConsts.TestTable.COLUMN_NAME_TEXT},  " +
                                "${DataBaseConsts.TestTable.COLUMN_NAME_DOUBLE}, ${DataBaseConsts.TestTable.COLUMN_NAME_RANDOM_INT}) VALUES ($i, '$str', $real, $i)")
                }
                val endTime = System.nanoTime()
                sum_time += (endTime - startTime)
                db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                db?.execSQL("VACUUM")
            }
            Log.d("MyLog", "testAddGroupOfItems $size: ${(sum_time/100)}")
        }
        thread.start()

    }

    fun testChangeCellWithID(size: Int){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..100) {
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                val cursor = db?.rawQuery("SELECT ${DataBaseConsts.TestTable.ID} FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy", null)
                cursor?.moveToFirst()
                val start = cursor?.getInt(cursor?.getColumnIndex(DataBaseConsts.TestTable.ID))
                if(start != null) {
                    val rnd = (start until size + start).random()
                    val startTime = System.nanoTime()
                    db?.execSQL("UPDATE ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SET ${DataBaseConsts.TestTable.COLUMN_NAME_TEXT} = '$rnd' " +
                                "WHERE ${DataBaseConsts.TestTable.ID} = $rnd")
                    val endTime = System.nanoTime()
                    sum_time += (endTime - startTime)
                    db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                    db?.execSQL("VACUUM")
                }
            }
            Log.d("MyLog", "testChangeCellWithID $size: ${(sum_time/100)}")
        }
        thread.start()
    }
    fun testChangeCellWithNonKey(size: Int){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..100) {
                val rnd = (1..size/100).random()
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                val startTime = System.nanoTime()
                db?.execSQL("UPDATE ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SET ${DataBaseConsts.TestTable.COLUMN_NAME_TEXT} = '$rnd' " +
                        "WHERE ${DataBaseConsts.TestTable.COLUMN_NAME_RANDOM_INT} = $rnd")
                val endTime = System.nanoTime()
                sum_time += (endTime - startTime)
                db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                db?.execSQL("VACUUM")
            }
            Log.d("MyLog", "testChangeCellWithNonKey: ${(sum_time/100)}")
        }
        thread.start()
    }
    fun testDeleteCellWithID(size: Int){
        val thread = Thread {
            var sum_time: Long = 0

            for (i in 1..100) {
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                val cursor = db?.rawQuery("SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy", null)
                cursor?.moveToFirst()
                /** start - first id of copied table*/
                val start = cursor?.getInt(cursor.getColumnIndex(DataBaseConsts.TestTable.ID))
                cursor?.close()
                if(start != null) {
                    val rnd = (start until size + start).random()
                    val startTime = System.nanoTime()
                    db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy " +
                                "WHERE ${DataBaseConsts.TestTable.ID} = $rnd")
                    val endTime = System.nanoTime()
                    sum_time += (endTime - startTime)
                    db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                    db?.execSQL("VACUUM")
                }
            }
            Log.d("MyLog", "testDeleteCellWithID $size: ${(sum_time/100)}")

        }
        thread.start()
    }

    fun testDeleteCellWithNonKey(size: Int){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..100) {
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                    val rnd = (1..size/100).random()
                    val startTime = System.nanoTime()
                    db?.execSQL(
                        "DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy " +
                                "WHERE ${DataBaseConsts.TestTable.COLUMN_NAME_RANDOM_INT} = $rnd"
                    )
                    val endTime = System.nanoTime()
                    sum_time += (endTime - startTime)
                    db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                    db?.execSQL("VACUUM")
            }
            Log.d("MyLog", "testDeleteCellWithNonKey $size: ${(sum_time/100)}")
        }
        thread.start()
    }
    fun testDeleteGroupOfCells_key(size: Int, group_size: Int = 10){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..100) {
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                val cursor = db?.rawQuery(
                    "SELECT ${DataBaseConsts.TestTable.ID} FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy", null)
                cursor?.moveToFirst()
                val start = cursor?.getInt(cursor?.getColumnIndex(DataBaseConsts.TestTable.ID))
                cursor?.close()
                /**creating group_size random ids */
                if (start != null) {
                    val rnd_list = arrayListOf<Int>()
                    for (i in 1..group_size) {
                        var elem = (start until start + size).random()
                            while (rnd_list.contains(elem)) {
                                elem = (start until start + size).random()
                            }
                        rnd_list.add(elem)
                    }

                    for (k in 0 until group_size) {
                        val startTime = System.nanoTime()
                        db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy " +
                                    "WHERE ${DataBaseConsts.TestTable.ID} = ${rnd_list[k]}")
                        val endTime = System.nanoTime()
                        sum_time += (endTime - startTime)
                    }
                    db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                    db?.execSQL("VACUUM")
                }
            }
            Log.d("MyLog", "testDeleteGroupOfCells_key $size: ${(sum_time/100)}")
        }
        thread.start()
    }

    fun testDeleteGroupOfCells_nonKey(size: Int, group_size: Int = 10){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..100) {
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                    val rnd_list = arrayListOf<Int>()
                    /**creating group_size random integers */
                    for (i in 1..group_size) {
                        var elem = (1..size).random()
                        while (rnd_list.contains(elem)) {
                            elem = (1..size).random()
                        }
                        rnd_list.add(elem)
                    }
                    val startTime = System.nanoTime()
                    for (k in 0 until group_size) {
                        db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy " +
                                    "WHERE ${DataBaseConsts.TestTable.COLUMN_NAME_INTEGER} = ${rnd_list[k]}")
                    }
                    val endTime = System.nanoTime()
                    sum_time += (endTime - startTime)
                    db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                    db?.execSQL("VACUUM")

            }
            Log.d("MyLog", "testDeleteGroupOfCells_2 $size: ${(sum_time/100)}")
        }
        thread.start()
    }
    fun testCompressionOfDB(size: Int){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..100) {
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                val cursor = db?.rawQuery("SELECT ${DataBaseConsts.TestTable.ID} FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy", null)
                cursor?.moveToFirst()
                val start = cursor?.getInt(cursor?.getColumnIndex(DataBaseConsts.TestTable.ID))
                cursor?.close()
                if (start != null) {
                    for (i in 1..200) {
                        db?.execSQL(
                            "DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy " +
                                    "WHERE ${DataBaseConsts.TestTable.ID} = ${start + i}")
                    }
                    val startTime = System.nanoTime()
                    db?.execSQL("VACUUM")
                    val endTime = System.nanoTime()
                    sum_time += (endTime - startTime)

                    db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                    db?.execSQL("VACUUM")
                    }
                }

            Log.d("MyLog", "testCompressionOfDB $size: ${(sum_time/100)}")
        }
        thread.start()
    }
    fun testCompressionOfDB200Left(size: Int){
        val thread = Thread {
            var sum_time: Long = 0
            for (i in 1..100) {
                db?.execSQL("INSERT INTO ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy SELECT * FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}")
                val cursor = db?.rawQuery("SELECT ${DataBaseConsts.TestTable.ID} FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy", null)
                cursor?.moveToFirst()
                val start = cursor?.getInt(cursor?.getColumnIndex(DataBaseConsts.TestTable.ID))
                cursor?.close()
                if (start != null) {
                    for (i in start..size + start - 200) {
                        db?.execSQL(
                            "DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy " +
                                    "WHERE ${DataBaseConsts.TestTable.ID} = ${start + i}")
                    }
                    val startTime = System.nanoTime()
                    db?.execSQL("VACUUM")
                    val endTime = System.nanoTime()
                    sum_time += (endTime - startTime)

                    db?.execSQL("DELETE FROM ${DataBaseConsts.TestTable.TABLE_NAME}${size}_copy")
                    db?.execSQL("VACUUM")
                }
            }
            Log.d("MyLog", "testCompressionOfDB200Left $size: ${(sum_time/100)}")
        }
        thread.start()
    }
    fun closeDb(){
        DbHelper.close()
    }
}