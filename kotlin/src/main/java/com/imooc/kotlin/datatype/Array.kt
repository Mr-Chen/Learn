package com.imooc.kotlin.datatype

import org.json.JSONObject

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 04:29
 * Explain  :  数组Array
 */
fun main() {
    //必须指定元素
    val array = arrayOf(1, 2, 3)
    //更新元素
    array.set(1, 100)
    array.forEach { i -> println(i) }
    //可以为任意类型
    val arrayAny = arrayOf(1, "2", true, 4.3f)

    //创建一个空数组，必须指定泛型和大小
    val arrayOfNulls = arrayOfNulls<String>(3)
    arrayOfNulls[0] = "element1"
    arrayOfNulls[1] = "element1"
    arrayOfNulls[2] = null

    //原生类型数组 IntArray FLoatArray BooleanArray...
    val byteArray = ByteArray(2)
    byteArray[0] = 3
    byteArray[1] = 4

    //创建一个含有5个元素的整型数组 ，每个元素都是100
    val intArray = IntArray(5) { 100 }
    intArray.forEach { i -> print(i) }

    println()
    //每个元素乘3  it是lambda表达式的专有变量  代表元素的下标
    val intArray1 = IntArray(3) { it * 3 }
    //作用同上 i(可以为任意字母)代表的是每个元素
    val intArray2 = IntArray(3) { o -> o * 3 }

    val forArray = arrayOf(3, 5, 7, 2, "23")
    //数组遍历
    for (item in forArray) {
        println("$item-->")
    }
    //根据下标遍历
    for (index in forArray.indices) {
        println(index.toString() + "->" + forArray[index])
    }

    //带索引
    for ((index, item) in forArray.withIndex()) {
        println("$index-->$item")
    }

    //foreach
    forArray.forEach { item -> println(item) }
    //forEach 带有索引
    forArray.forEachIndexed { inde, item -> println("$inde:$item") }


}