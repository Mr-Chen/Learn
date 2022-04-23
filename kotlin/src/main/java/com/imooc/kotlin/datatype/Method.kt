package com.imooc.kotlin.datatype

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 20:49
 * Explain  :  方法
 */
fun main() {

    //普通类需要先实例化,才能调用方法  直接在后面加()
    CommClass().method()
    //使用companion object在普通类中定义静态方法
    CommClass.staticMethod()

    val result = StaticClass.staticMethod(4, 93)
    println("result:$result")
}

class CommClass{
    fun method(){
        println("普通方法")
    }

    companion object{
        fun staticMethod(){
            println("普通类中定义静态方法")
        }
    }
}

//静态类 使用关键子object
object StaticClass{
    fun staticMethod(a:Int,b :Int):Int{
        return a*b;
    }

}