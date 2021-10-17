//1. 함수
//함수 선언시 fun 사용
//파라미터 뒤에 타입 지정
fun main(args: Array<String>){
	println("Hello, World!")
}

//함수 반환 타입도 뒤에 작성 
fun max(a:Int, b:Int) : Int {
	return if (a > b) a else b
}

//함수 작성시 중괄호 없애고 등호와 식으로도 작성 가능
fun max2(a:Int, b:Int) : Int = if (a > b) a else b

//반환 타입 적지 않아도 컴파일러가 함수 본문 식을 분석해 결과 타입을 함수 반환 타입으로 정해
fun max3(a:Int, b:Int)  = if (a > b) a else b

//2. 변수
//변수 선언시 타입 명시 또는 생략 가능
fun vvv1(){
	//val은 변경 불가능 변수
	val question = "코틀린을 공부합니다."
	val anser = 77
	
	//var은 변경 가능 변수 -> 하지만 타입은 변경 불가능 
	var msg: String = "메세지입니다."
	msg = "변경가능합니다."
}

fun vvv2(args: Array<String>){
	var name = if (args.size > 0) args[0] else "kotlin"
	//문자열 템플릿: 선언한 변수를 문자열 안에서 사용 가능 $를 앞에 붙여야 
    println("Hello, $name!")
}

//3. 클래스
//자바에 비해 중복적 작성 코드가 없어진 느낌?
//기본 설정이 public 임으로 생략 가능 
class Person(val name: String)

//코틀린은 프로퍼티 기본 제공
//게터 세터 작성 안해도됨
class Person2(
  var name: String,
  var isMarried: Boolean
)

fun ccc1(){
	//new 키워드 사용하지 않음
	val person = Person2("kgy", true)
	//코틀린이 자동 게터 호출 
    println(person.name)
}


