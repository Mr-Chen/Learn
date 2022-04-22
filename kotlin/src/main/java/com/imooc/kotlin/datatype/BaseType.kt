package com.imooc.kotlin.datatype

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 03:39
 * Explain  :  基本数据类型
 */
//psvm 快速生成main
fun main() {
    //默认整数类型为Int，如果超出Int最大值则为Long
    val number = 1
    val bigNumber = 8000000000

    //也可以加l以long类型运算
    val longNumber = 3L

    //显示设置类型
    val byte: Byte = 3

    //默认小数位double（默认精度小数点后16位）
    val doubleNum = 3.211212234243
    //末尾加f设置为float,如果赋值超过精度（小数点后6位数）会四舍五入
    val floatNum = 3.2323f
    val floatNumber:Float = 3.232324323F

    println("double:"+doubleNum)
    println("float:"+floatNumber)

    //字符 使用单引号
    val c = 's'
    val c1:Char = 's'

    //布尔类型
    val boolean:Boolean = true //false
    val bo = false

    //字符串
    val str = "He Is"
    //根据下标索引  char类型
    val str3 = str[3]
    println("str3:"+str3)

    //字符串模板/拼接
    println("The result is$str")
    println("The length is${str.length}")
    println("the word"+"is hello")

    //转义字符
    val hello = "hello\nworld"
    val world = "{\"name\":\"value\"}"
    println(hello)
    println(world)

    //保留格式三个双引号
    val stay = """
        |hello world
        |what is your name
        |how are you
        
    """
    println(stay)

//强制类型转换
    val swNumber = 100
    swNumber.toString()
    swNumber.toFloat()
    swNumber.toByte()
    println(swNumber)

    //数据运算
    val numberInt = 3/2
    println("int/int = $numberInt")
    val numberDouble = 3/2.toDouble()
    println("int/double=${numberDouble}")
    println("取余 3%2= ${3%2}")
    println("取余含小数 3%2= ${3%2.toDouble()}")
    println("取余含小数 2%3= ${2%3}")
    println("取余含小数 2%3= ${true.and(false)}")




}