package com.imooc.kotlin.datatype

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 22:43
 * Explain  :  条件表达式
 */
fun main() {

    println("maxOf:${maxOf(3, 4)}")

    //如果使用多级if/else嵌套可以使用when
    println(isNumber(23))

}

fun isNumber(number:Number) : String = when(number){
    20f->"the number is 200"
    is Int ->"the number is Int"
    is Float -> "the number isString"
    else ->"没有匹配条件"


}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun maxOf2(a: Int, b: Int): Int {
    //等同于三目运算符
    return if (a > b) a else b
}

fun maxOf3(a:Int,b:Int):Int = if (a>b) a else b
