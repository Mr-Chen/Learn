package com.imooc.kotlin.datatype

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 05:11
 * Explain  :  集合
 */

fun main() {
    //List  可变 arrayListof  推荐使用mutableListOf
    val listString = mutableListOf<String>()
    listString.add("hello")
    listString.add("w")
    listString.add("r")
    listString.add(2, "0")


    val listString2 = mutableListOf<String>("h", "e")
    listString2.add("l")
    listString2.add(3, "l")
    listString2.add("o")
    listString2[0] = "xxx"

    println(listString2)

    //不可变list
    val noList = listOf<String>("sd", "")

    //可变map
    val map = mutableMapOf<String, Int>(Pair("we", 4))
    map.put("are", 1)
    map["family"] = 6
    map.forEach { (index, value) -> println("key = $index  value = $value") }

    //不可变map
    val noMap = mapOf<String,String>(Pair("key","value"),Pair("k1","k2"))

    //可变set
    val set = mutableSetOf<Int>(1,2,3,5)
    set.add(3)
    set.add(9)
    println(set)

    //不可变set
    val noSet = setOf<Int>(3,2,5,5)
    println(noSet)

    //常用方法和java差不多
    listString.contains("")
    listString.isEmpty()

    val listExample = mutableListOf<Int>(4,0,2,9,8,13)
    println(listExample)
    val reverList = listExample.reversed() //翻转
    reverList.forEach { it -> print("$it,") }
    println()
    listExample.sort()//排序
    listExample.forEach { it -> print("$it") }
    println()
    listExample.sortDescending()
    listExample.forEach { i -> print("$i") }





}

fun men(i : Int,f :Float):Int{
    return 0
}