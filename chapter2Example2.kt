//4. 패키지 
package kotlin.chapter2.example2
//패키지 선언: 자바와 다르게 다른 패키지의 파일을 마음대로 위치할 수 있다.

import java.util.Random
import java.io.BufferedReader
import java.io.StringReader
import java.util.TreeMap
//자바 라이브러리 클래스 임포트 가능

class Rectangle(val height: Int, val width: Int) {
	val isSquare: Boolean
	get() = height == width
}

fun createRandomRectangel() : Rectangle {
	var random = Random()
	return Rectangle(random.nextInt(), random.nextInt())
}

//5. enum
//enum은 단순히 값만 열거하는 존재가 아니다. enum 클래스 안에도 프로퍼티나 메소드를 정의할 수 있다.
enum class Color (val r:Int, val g: Int, val b: Int) { //상수 프로퍼티 정의
  //상수 생성시 프로퍼티 값 지정해야 한다.
  RED(255,0,0), ORANGE(255,165,0), YELLOW(255,255,0), GREEN(0,255,0), BLUE(0,0,255), INDIGO(75,0,130), VIOLET(238,130,238); //세미콜론 사용 필수
 
  fun rgb() = (r * 256 + g) * 256 + b
		
}

fun eee1(){
  println(Color.BLUE.rgb())
}

//6. when
//자바의 switch에 해당한다.
//break 쓰지 않아도 된다.

//함수의 반환 값으로 when을 이용해 적합한 문자열을 돌려준다.
fun getMnemonic(color: Color) =
	when (color) {
		Color.RED -> "rrrrrrr"
	    Color.ORANGE -> "ooooooo"
		Color.YELLOW -> "yyyyyy"
		Color.GREEN -> "gggggg"
		Color.BLUE -> "bbbbbb"
		Color.INDIGO -> "iiiiii"
		Color.VIOLET -> "vvvvvvv"
	}

fun eee2(){
  println(getMnemonic(Color.BLUE))
}

//코틀린은 when 분기 조건에 상수만 사용할 수있는 자바와 다르게 임의의 객체 허용?
fun mix(c1: Color, c2: Color) =
   when (setOf(c1, c2)){//인자로 아무 객체나 사용 가능
	   setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
	   setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
	   setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
	   else -> throw Exception("Dirth color")
	
   }

//when에 인자 없이 사용 가능
//위와 같이 set 인스턴스를 여러개 생성하는 것은 비효율적
//when에 아무 인자가 없으려면 각 분기의 조건이 불리언 결과를 계산하는 식이어야 한다.
fun mixOptimized(c1: Color, c2: Color) =
	when {
		(c1 == Color.RED && c2 == Color.YELLOW) ||
		(c1 == Color.RED && c2 == Color.YELLOW) ->
			Color.ORANGE
		
		(c1 == Color.YELLOW && c2 == Color.BLUE) ||
		(c1 == Color.BLUE && c2 == Color.YELLOW) ->
			Color.GREEN
		
		else -> throw Exception("Dirth color")	
	}

//7. 스마트 캐스트 ?? 이부분 이해 안됨

//8. if
fun ififif(){
	//다른 언어들과 같이 if를 일반적으로 사용 가능
    var x = 10
	if(x == 10){
		println("값이 같습니다.")
	}else{
		println("값이 다릅니다.")
	}
	
	//if를 문이 아닌 표현식으로 사용 가능
    val msg = if(x==10) "값이 같습니다." else "값이 다릅니다."
	println(msg)
	
}

//9. while, for 루프
//while은 자바와 동일
//for-each 루프 형태만 존재
//대신 range,downTo 이용 비슷하게 사용 가능
fun main(args:Array<String>) {
    for(i in 1..100) {      //1..100범위의 정수에 대해 이터레이션 한다.
        print(fizzBuzz(i))
    }
    for (i in 100 downTo 1 step 2){
        print(fizzBuzz(i))
    }
}

fun fizzBuzz(i : Int) = when{
    i%15==0 -> "FizzBuzz"
    i%3==0 -> "Fizz"
    i%5==0 -> "Buzz"
    else -> "$i"
}

//10. List, Set, Map
//값을 변경할 수 있는 Immutable형식과 값을 추가, 삭제, 변경 할 수 있는 mutable 형식
fun LSM(){
	//List
	//값 변경할 수 없는 리스트 
	val nList = listOf<Int>(1,2,3,4,5,6,7)
	println(nList.get(0))
	println(nList[1])
	
	//값 변경할 수 있는 리스트
	val mList = mutableListOf<Int>(1, 2, 3)
	mList.add(3, 4)    // 3번 인덱스에 4 추가
	mList.add(5)       // 인덱스를 표시하지 않으면 맨 뒤에 추가됨
	mList.add(0, 100)  // 0번 인덱스에 값을 넣으면 이미 있던 데이터들은 뒤로 밀림
	mList.set(0, 200)  // 0번 인덱스의 값을 200으로 바꿈
	mList.removeAt(1)  // 1번 인덱스의 값 삭제
	
	//Set
	//중복 허용 X, 순서 X
	//값을 변경할 수 없는 Set
	val nSet = setOf<Int>(1, 2, 3, 3, 3)
    println(nSet)    // 중복을 허용하지 않기 때문에 3은 하나만 들어간다 -> [1, 2, 3]

	//값을 변경할 수 있는 Set
	val mSet = mutableSetOf<Int>(1, 2, 3, 4)
	mSet.add(2)      // 2 추가, 중복을 허용하지 않기 때문에 값은 변함없음
	mSet.remove(2)   // 2 삭제

	//Map
	//키와 값 형식
	//값을 변경할 수 없는 map
	val nMap = mapOf<String, Int>("one" to 1, "two" to 2, "three" to 3)
	println(nMap.get("one"))
	
	//값을 변경할 수 있는 map
	val mMap = mutableMapOf<String, Int>("one" to 1, "two" to 2)
	mMap.put("Three", 3)       // 값 추가
	mMap.replace("Three", 5)   // "Three"에 해당되는 value를 5로 변경
	println(mMap.keys)         // mNumMap의 key값만 전부 출력
	println(mMap.values)       // mNumMap의 value값만 전부 출력
	mMap.clear()               // mNumMap의 데이터 모두 삭제
}

//11. in 연산자
//어떤 값이 범위에 속하는지 검사 가능, 배열 리스트 범
//반대로 !in을 사용하면 어떤값이 범위에 속하지 않는지 검사 가능
fun ininin(){
   var arr = arrayOf(1,2,3,4)
	println(3 in arr)
	println(5 !in arr)
	
	var arr1 = listOf('가','나','다','라','마')
    println('가' in arr1)
	println('하' !in arr1)
}


//12. 예외처리
//코틀린의 예외(exception)처리는 자바나 다른 언어의 예외 처리와 비슷
//예외 인스턴스를 만들 때도 new를 붙일 필요가 없음
//자바와 달리 throw가 식이므로 다른 식에 포함가능
//try, catch, finally
fun main2(args:Array<String>) {
    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))    //239
}
fun readNumber(reader : BufferedReader) : Int?{
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    }
    catch (e: NumberFormatException){   //예외 타입을 :의 오른쪽에 쓴다.
        return null
    } finally {
        reader.close()
    }
}

//코틀린의 try 키워드는 if나 when과 마찬가지로 식이다.
//따라서 try의 값을 변수에 대입할 수 있다.
//본문 반드시 중괄호() 감싸야함
fun main3(args:Array<String>) {
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)   //null
}
fun readNumber2(reader : BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException){
        null
    }
    println(number)
}


/*
==========================================================================================================================================================
요약

 - 함수를 정의할 때 fun 키워드를 사용한다. val과 var는 각각 읽기 전용 변수와 변경 가능한 변수를 선언할 때 쓰인다.

 - 문자열 템플릿을 사용하면 문자열을 연결하지 않아도 되므로 코드가 간결해진다. 변수 이름 앞에 $를 붙이거나, 식을 $(식)처럼 $()로 둘러싸면 변수나 식의 값을 문자열 안에 넣을 수 있다.

 - 코틀린에서는 값 객체 클래스를 아주 간결하게 표현할 수 있다.

 - 다른 언어에도 있는 if는 코틀린에서 식이며, 값을 만들어낸다.

 - 코틀린 when은 자바의 switch와 비슷하지만 더 강력하다

 - 어떤 변수의 타입을 검사하고 나면 굳이 그 변수를 캐스팅하지 않아도 검사한 타입의 변수처럼 사용할 수 있다. 그런 경우 컴파일러가 스마트캐스트를 해 자동으로 타입을 바꿔준다.

 - for, while, do-while 루프는 자바가 제공하는 같은 키워드의 기능과 비슷하다. 하지만 코틀린의 for는 자바의 for보다 더 편리하다. 특히 맵을 이터레이션하거나 이터레이션하면서 컬렉션의 원소와 인덱스를 함께 사용해야 하는 경우 코틀린의 for가 더 편리하다.

 - 1..5와 같은 식은 범위를 만들어낸다. 범위와 수열은 코틀린에서 같은 문법을 사용하며, for 루프에 대해 같은 추상화를 제공한다. 어떤 값이 범위 안에 들어있거나 들어있지 않은지 검사하기 위해서 in이나 !in을 사용한다.

 - 코틀린 예외 처리는 자바와 비슷하다. 다만 코틀린에서는 함수가 던질 수 있는 예외를 선언하지 않아도 된다

*/