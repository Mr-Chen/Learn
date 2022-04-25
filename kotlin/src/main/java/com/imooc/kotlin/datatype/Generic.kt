package com.imooc.kotlin.datatype

import org.json.JSONObject

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 23:29
 * Explain  :  泛型
 */
fun main() {

    Apple().drink("苹果汁")
    val blue = BlueColor("蓝色")
    blue.printColor()

    //泛型方法
    //String::class.java  将clas作为参数传递
    fromJson("{}", String::class.java)

}

//泛型接口
interface Drink<T> {
    fun drink(t: T)
}

class Apple : Drink<String> {
    override fun drink(t: String) {
        println("drink:$t")
    }
}

//泛型类
abstract class Color<T>(val t: T) {
    abstract fun printColor()
}

class BlueColor(val color: String) : Color<String>(color) {
    override fun printColor() {
        println(color)
    }

}

//泛型方法  ?表示可为null
fun <T> fromJson(json: String, clz: Class<T>): T? {

    val instance: T? = clz.newInstance()
    return instance
}

//泛型约束
//传入的泛型T必须是JsonObject的子类或JsonObject
fun <T : JSONObject> fromJson(json: String, clz: Class<T>): T? {
    val instance: T? = clz.newInstance()
    return instance
}

//泛型约束2
//传入的泛型T必须是JsonObject的子类或JsonObject 并且必须实现Comparable接口
fun <T> fromJson1(json: String, clz: Class<T>): T? where T : JSONObject, T : Comparable<T> {
    val instance: T? = clz.newInstance()
    return instance
}

//out(协变  指定泛型上限)  in(逆变  指定泛型下限)
open class Animal
class DogAnimal : Animal()

fun animalFuns() {
    //在使用出指定  上限
    val list: ArrayList<out Animal> = ArrayList<DogAnimal>()
    //在定义处指定  下限
    val list1: DefList<Animal> = DefList<DogAnimal>()

    //在使用处定义下限
    val list2: ArrayList<in DogAnimal> = ArrayList<Animal>()
}

class DefList<out Animal> {

}

