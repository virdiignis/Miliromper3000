package com.example.alkoapp.data

import com.example.alkoapp.data.models.Drink

class DrinksDataset{
    companion object{
        fun createDataSet(): ArrayList<Drink> {
            val list = ArrayList<Drink>()
            list.add(
                Drink(
                    "10",
                    "Burrito shot",
                    "7.6"
                )
            )

            list.add(
                Drink("9", "blue shot", "7.0")
            )

            list.add(
                Drink("7", "random shot", "9.6")
            )
            list.add(
                Drink("5", "Kamikadze", "7.6")
            )
            list.add(
                Drink(
                    "11",
                    "Burrito shokamitkat",
                    "7.6"
                )
            )
            list.add(
                Drink(
                    "10",
                    "Burrito shot",
                    "7.6"
                )
            )

            list.add(
                Drink("9", "blue shot", "7.0")
            )

            list.add(
                Drink("7", "random shot", "9.6")
            )
            list.add(
                Drink("5", "Kamikadze", "7.6")
            )
            list.add(
                Drink(
                    "11",
                    "Burrito shokamitkat",
                    "7.6"
                )
            )
            list.add(
                Drink(
                    "10",
                    "Burrito shot",
                    "7.6"
                )
            )

            list.add(
                Drink("9", "blue shot", "7.0")
            )

            list.add(
                Drink("7", "random shot", "9.6")
            )
            list.add(
                Drink("5", "Kamikadze", "7.6")
            )
            list.add(
                Drink(
                    "11",
                    "Burrito shokamitkat",
                    "7.6"
                )
            )
            return list
        }
    }
}