/**
 * Created by hughie on 16/8/15.
 */

def aClosure = {
    String s, int d, def t ->
        println(s + " " + d + " " + t)
//        print(s,d)

}

//
//aClosure.call("ad ", 12, true)
//aClosure("bb ", 32, 12)

greeting = {
    "Hello, $it"
}
greetingIt = {
    it -> "Hello, $it"
}

greeting("ABS")
//assert greeting("ABS") == 'Hello, ABS'

//println(greetingIt("BBS"))

noPar = {
    -> "hello"
}
//println(noPar())

def dList = [12, "test", true, 1.2F, 0xEE]
udList = [121, "test1", false, 1.21F, 0x1A]

//dList.each {
//    println(it)
//}
dList.each ({
    println(it)
})

def testClosure (int v1, String v2, Closure closure) {
    closure()
}

testClosure(4, "asd", {
    println("im in closure")
})


testClosure(4, "asd", ({
    println("im in closure")
}))

def cl
try {
    def al = 'sugar'
    cl = {
        al
    }
} catch (Exception ex) {
    println(ex)
}

//assert cl().equals('sugar')
def c2 = {
    def a2 = {
        'A2String'
    }
    println("a2 => c2")
    a2
}

def c3 = {
    def a3 = 'A3String'
    println("a3 => c3")
    a3
}

//println(c3())
//if(c3().equals("A2String")) {
//    println(12)
//} else {
//    println(34)
//}



//println(c2.call().println())


def toTriple = {
    n ->
        n * 3
}

println(toTriple(12))
println(toTriple("ABC "))


//owner.it 表示脚本范围内的it参数
it = 5
def toTripleIt = {
    owner.it * 3
}

println(toTripleIt(20))

//执行两次闭包调用
def runTwice = {
    var1, closure1 ->
        closure1(closure1(var1))
}
println(runTwice(5, toTriple))

//when closure is last param, can put it after the param list
println(runTwice(5) {
    it * 4
})
// 以上写法等同于
println(runTwice(5, {
    it * 5
}))

// 闭包接受参数的规则，会将参数列表中所有有键值关系的参数，作为一个map组装，传入闭包作为调用闭包的第一个参数
def ruleF = {
    m, v1, v2, v3 ->
        v1 + v2 + v3 + m.x + m.y + m.z
}
println(ruleF(6, x:3, y:4, z:5, 7, 8))

def ruleFC = {
    m, v1, v2, c1 ->
        c1(m.x + m.y, v1 + v2)
}
println(ruleFC(1, 2, x:3, y:4) {
    a, b ->
        a * b
})

println("ruleFC.getMaximumNumberOfParameters()=" + ruleFC.getMaximumNumberOfParameters())

//闭包可以将其最后的参数设置其默认的取值
def e = {
    a, b, c=3, d='A' ->
        "${a+b+c}$d"
}
println("e(7,4)=" + e(7,4))
println("e(9,8,7)=" + e(9,8,7))
println("e(9,8,7,B)=" + e(9,8,7,'B'))

/**
 * 闭包可以通过定义最后一个参数声明为Object[]，来获取任意多个参数。
 * 同时，在闭包的逻辑处理中要使用这些参数则需要使用数组的each方法
 * */
def clList = {
    arg1, Object[] args ->
        def list = []
        list << arg1
        println("LIST=" + list)
        args.each {
            list << it
        }
        list
}
println(clList(1))
try {
    println(clList(1,2,3,4))
} catch (Exception ex) {
    ex.printStackTrace()
} finally {
    println("cList print end")
}


//如果闭包的参数声明中没有list，那么传入参数可以设置为list，里面的参数将分别传入闭包参数
def clListSum = {
    a, b, c ->
        a + b + c
}
def tList = [1,2,3]
println("clListSum([1,2,3]) = " + clListSum(tList))


//闭包有一个curry方法，该方法的作用是锁定闭包的首个参数。类似于java中的方法重载。见代码
def concat = {
    p1, p2, p3 ->
        "$p1 $p2 $p3"
}
def concatAfterFly = concat.curry('fly')
println("concat.curry('fly') concatAfterFly('drive', 'cycle')= " + concatAfterFly('drive', 'cycle'))
def concatAfterFlySwim = concatAfterFly.curry('swim')
println("concatAfterFly.curry('swim') concatAfterFly('drive', 'cycle')= " + concatAfterFlySwim('walk'))

//闭包是可嵌套的 (greatest common divisor 最大公约数)
def gcd
gcd = {
    m, n ->
        m % n == 0 ? n : gcd(n, m % n)
}
println("gcd(28, 35) = " + gcd(28, 35))
println("gcd(280, 350) = " + gcd(280, 350))
println("gcd(35, 28) = " + gcd(35, 28))

//可以在闭包中用call闭包进行迭代Fibonacci numbers
def results = [];
{
    a, b ->
        results << a
        a < 10 && call(b, a + b)
}(1, 1)
println("Fibonacci numbers = " + results)

///<@Hughie 最小公倍数 lowest common multiple(LCM) 最小公倍数=两数的乘积/最大公约（因）数
def lcm
lcm = {
    m, n ->
        m * n / gcd(m, n)
}
println("lcm(35, 28) = " + lcm(35, 28))


///<@Hughie 列出[2,n] 内质数 在一般领域，对正整数n，如果用2到 根号n 之间的所有整数去除，均无法整除，则n为质数。
def isPrime = {
    num ->
        if(num <= 3)
            return num > 1
        for(i in 2..Math.sqrt(num)){
            if(num % i == 0)
                return false
        }
        true
}
def getPrimeNumbers = {
    n ->
        retList = [];
        for(nx in 2..n) {
            if(isPrime(nx))
                retList << nx
        }
        retList
}
prime100 = getPrimeNumbers(100)
println("getPrimeNumbers(100)" + prime100 + " count = " + prime100.size())
