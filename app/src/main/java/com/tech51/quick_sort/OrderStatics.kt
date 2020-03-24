package com.tech51.quick_sort

import kotlin.math.min

typealias intArray = Array<Int>

class OrderStatics {

    var minimumValue = -1
    /**
     * @param lassMinimumValue for less minimum index to search
     * */
    fun divideAndSort(array: Array<Int>, lessMinimumValue: Int, success: (Int) -> Unit) {
        minimumValue = lessMinimumValue
        //Divide array as 5 column

        var i = 0
        var last = 0
        val twoD = arrayListOf<intArray>()
        while (last < array.size) {
            last = last + 5
            twoD.add(array.sliceArray(i..last - 1).sortedArray())
            i = last
        }

        for (e in 0..twoD.size - 1) {
            for (a in 0..4) {
                print("${twoD.get(e).get(a)} ")
            }
            println()
        }
        val res = middleSorter(twoD)
        if (res.size < 10) {
            array.sort()
            success.invoke(array.get(lessMinimumValue))
            return
        }
        val gp = makeAndGetGroup(res, minimumValue)
        divideAndSort(gp.toTypedArray(), minimumValue, success)
        println("Gp")


    }

    private fun middleSorter(item: ArrayList<intArray>): ArrayList<Array<Int>> {
        val itemArray = item
        val a = arrayListOf<Int>()
        println()
        item.forEach {
            a.add(it.get(2))
        }
        a.sort()
        //start replace with sorted array in middle
        val replaceIndex = 2
        var count = 0
        //scan all middle
        itemArray.forEach {
            it[replaceIndex] = a.get(count)
            count++

        }
        println()
        println("After sort")
        itemArray.forEach {
            it.forEach {
                print("$it ")
            }
            println()
        }
        return item
    }

    /**
     *
     * */
    private fun makeAndGetGroup(item: ArrayList<intArray>, indexOfItem: Int): ArrayList<Int> {
        print("index $indexOfItem  \n")
        //col for |
        //row for _
        val p = item.size / 5
        val k = item.get(0).size / 2
        //change to ternary operator..
        val rowMid = if (item[0].size % 2 == 0) {
            //even
            k
        } else {
            //odd
            k
        }
        val colMid = if (item.size % 5 == 0) {
            p
        } else {
            p + 1
        }
        val middleItem = item.get(colMid).get(rowMid)
        println("middle is $middleItem")
        // s1 for  storing less than middle value
        val s1 = arrayListOf<Int>()
        // ms for storing middle value
        val ms = arrayListOf<Int>()
        //s2 for storing greater than middle value
        val s2 = arrayListOf<Int>()
        item.forEach {
            it.forEach {
                if (it == middleItem) {
                    ms.add(it)
                } else if (it < middleItem) {
                    s1.add(it)
                } else {
                    s2.add(it)
                }
            }
        }
        val sizeNeverUseInGp = s1.size + ms.size
        minimumValue = Math.abs(indexOfItem - sizeNeverUseInGp)
        return if (s1.size >= indexOfItem) s1 else s2
    }

}


