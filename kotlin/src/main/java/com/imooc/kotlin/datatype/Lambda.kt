package com.imooc.kotlin.datatype

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 22:30
 * Explain  :  lambda表达式
 */
fun main() {
    val array = arrayOf(0,1,2,3,4)
    transform(array,action = {index,element->
        index * element
    })

    //it 不是关键字或者保留字  当只有一个参数的时候  可以自定义
    array.forEach { it -> println(it) }

    transform(array){
        index,element->
        index*element
    }

    array.forEachIndexed{
        index,ele ->
        println("index:$index --> element:$ele")
    }
}

fun transform(array:Array<Int>,action:(a:Int,b:Int)->Int){
    for (index in array.indices) {
        val newParam = action(index,array[index])
        array[index] = newParam
    }
}