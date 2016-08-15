/**
 * Created by hughie on 16/8/15.
 */

def aClosure = {
    String s, int d, def t ->
        println(s + " " + d + " " + t)
//        print(s,d)

}


aClosure.call("ad ", 12, true)
aClosure("bb ", 32, 12)

greeting = {
    "Hello, $it"
}
greetingIt = {
    it -> "Hello, $it"
}

greeting("ABS")
assert greeting("ABS") == 'Hello, ABS'

println(greetingIt("BBS"))

noPar = {
    -> "hello"
}
println(noPar())