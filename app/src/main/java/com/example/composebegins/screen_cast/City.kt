package com.example.composebegins.screen_cast

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(val name: String, val country: String) : Parcelable
/*
 if for any reason @Parcelize does not work (build.Gradle must be checked first), use 'mapSaver'
    to define conversion parameters of object into a set of values to save in 'Bundle'

 Example1:

 val CitySaver = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = { mapOf(nameKey tp it.name, countryKey tp it.country) },
        restore = { City(it[nameKey] as String, it[countryKey] as String) }
    )
 }

 Example2: in order to avoid the need to define keys in map, it's possible to use 'listSaver'

 val CitySaver = listSaver<City, Any>(
    save = { listOF(it.name, it.country) },
    restore = { City(it[nameKey] as String, it[countryKey] as String) }
 )
 */

@Composable
fun CityScreen() {
    var selectedCity = rememberSaveable {
        mutableStateOf(City("Madrid", "Spain"))
    }
}











