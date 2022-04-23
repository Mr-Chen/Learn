package com.imooc.kotlin.datatype

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 21:29
 * Explain  :  方法参数
 */
fun main() {

    //如果默认参数不是最后一个，则需要使用具名参数 指定需要传入的参数
    println(defaultMethod(end = 3))


    //如果方法的参数为方法（方法名:(参数)->返回值）
    paramMethod(end = 5,action = {

        //最后一行为返回值 return可以省略
        "括号内使用具名参数传递action参数"
    })

    //如果最后一个参数为方法 也可以在括号外调用
    paramMethod(end = 8){
        "括号外调用 传递action参数"
    }

    //可变参数  只有一个参数可以指定  如果最后一个不是可变参数则需要具名
    println(append('h', 'e', 'l', 'l', 'o',end = 7))
    //将数组传入可变参数 *array
    val array = charArrayOf('w','o','r','l','d')
    println(append('h', 'e', 'l', 'l', 'o', *array, end = 7))

}

//默认参数
fun defaultMethod(start:Int=2,end:Int):Int{

    return start*end
}

//
fun paramMethod(start:Int = 4,end:Int,action:()->String){

    val result = action()
    println("result = $result")
}

fun append(vararg str:Char,end:Int):String{
    val buffer = StringBuffer()
    for (i in str) {
        buffer.append(i)
    }

    return buffer.toString()
}

