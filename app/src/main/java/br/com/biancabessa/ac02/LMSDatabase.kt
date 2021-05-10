package br.com.biancabessa.ac02

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(AddCadastro::class), version = 1)
abstract class LMSDatabase: RoomDatabase() {
    abstract fun cadastroDAO(): CadastroDAO
}