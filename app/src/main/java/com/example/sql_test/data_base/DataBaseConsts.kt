package com.example.sql_test.data_base

object DataBaseConsts {
    const val  DATABASE_NAME = "Test.db"
    const val  DATABASE_VERSION = 44

    object TestTable {
        const val ID = "id"
        const val TABLE_NAME = "test_table"
        const val COLUMN_NAME_TEXT = "some_text"
        const val COLUMN_NAME_INTEGER = "some_integer"
        const val COLUMN_NAME_DOUBLE = "some_double"
        const val COLUMN_NAME_RANDOM_INT = "random_int"
    }



    const val SQL_CREATE_FILMS_TABLE_1000 =
        "CREATE TABLE IF NOT EXISTS ${TestTable.TABLE_NAME}1000 (" +
                "${TestTable.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TestTable.COLUMN_NAME_TEXT} TEXT, " +
                "${TestTable.COLUMN_NAME_INTEGER} INTEGER, " +
                "${TestTable.COLUMN_NAME_DOUBLE} REAL, " +
                "${TestTable.COLUMN_NAME_RANDOM_INT} INTEGER)"

    const val SQL_CREATE_FILMS_TABLE_10_000 =
        "CREATE TABLE IF NOT EXISTS ${TestTable.TABLE_NAME}10000 (" +
                "${TestTable.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TestTable.COLUMN_NAME_TEXT} TEXT, " +
                "${TestTable.COLUMN_NAME_INTEGER} INTEGER, " +
                "${TestTable.COLUMN_NAME_DOUBLE} REAL, " +
                "${TestTable.COLUMN_NAME_RANDOM_INT} INTEGER)"
    const val SQL_CREATE_FILMS_TABLE_100_000 =
        "CREATE TABLE IF NOT EXISTS ${TestTable.TABLE_NAME}100000 (" +
                "${TestTable.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TestTable.COLUMN_NAME_TEXT} TEXT, " +
                "${TestTable.COLUMN_NAME_INTEGER} INTEGER, " +
                "${TestTable.COLUMN_NAME_DOUBLE} REAL, " +
                "${TestTable.COLUMN_NAME_RANDOM_INT} INTEGER)"


    const val SQL_CREATE_FILMS_TABLE_1000_COPY =
        "CREATE TABLE IF NOT EXISTS ${TestTable.TABLE_NAME}1000_copy (" +
                "${TestTable.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TestTable.COLUMN_NAME_TEXT} TEXT, " +
                "${TestTable.COLUMN_NAME_INTEGER} INTEGER, " +
                "${TestTable.COLUMN_NAME_DOUBLE} REAL, " +
                "${TestTable.COLUMN_NAME_RANDOM_INT} INTEGER)"

    const val SQL_CREATE_FILMS_TABLE_10_000_COPY =
        "CREATE TABLE IF NOT EXISTS ${TestTable.TABLE_NAME}10000_copy (" +
                "${TestTable.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TestTable.COLUMN_NAME_TEXT} TEXT, " +
                "${TestTable.COLUMN_NAME_INTEGER} INTEGER, " +
                "${TestTable.COLUMN_NAME_DOUBLE} REAL, " +
                "${TestTable.COLUMN_NAME_RANDOM_INT} INTEGER)"
    const val SQL_CREATE_FILMS_TABLE_100_000_COPY =
        "CREATE TABLE IF NOT EXISTS ${TestTable.TABLE_NAME}100000_copy (" +
                "${TestTable.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TestTable.COLUMN_NAME_TEXT} TEXT, " +
                "${TestTable.COLUMN_NAME_INTEGER} INTEGER, " +
                "${TestTable.COLUMN_NAME_DOUBLE} REAL, " +
                "${TestTable.COLUMN_NAME_RANDOM_INT} INTEGER)"

    const val SQL_DELETE_FILMS_TABLE = "DROP TABLE IF EXISTS ${TestTable.TABLE_NAME}"
    const val SQL_DELETE_FILMS_TABLE_1000 = "DROP TABLE IF EXISTS ${TestTable.TABLE_NAME}1000"
    const val SQL_DELETE_FILMS_TABLE_10_000 = "DROP TABLE IF EXISTS ${TestTable.TABLE_NAME}10000"
    const val SQL_DELETE_FILMS_TABLE_100_000 = "DROP TABLE IF EXISTS ${TestTable.TABLE_NAME}100000"

    const val SQL_DELETE_FILMS_TABLE_1000_COPY = "DROP TABLE IF EXISTS ${TestTable.TABLE_NAME}1000_copy"
    const val SQL_DELETE_FILMS_TABLE_10_000_COPY = "DROP TABLE IF EXISTS ${TestTable.TABLE_NAME}10000_copy"
    const val SQL_DELETE_FILMS_TABLE_100_000_COPY = "DROP TABLE IF EXISTS ${TestTable.TABLE_NAME}100000_copy"

}