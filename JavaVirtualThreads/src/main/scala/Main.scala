import com.demo.java.HelloJava
import com.demo.kotlin.HelloKotlin
import com.demo.scala.HelloScala

object Main {
  def main(args: Array[String]): Unit = {
    println(HelloScala.greet())
    println(HelloKotlin.INSTANCE.greet())
    println(HelloJava.greet)
  }
}