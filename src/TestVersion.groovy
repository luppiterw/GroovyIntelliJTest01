/**
 * Created by hughie on 2016/10/25.
 */

def myVersionName = "i5cnc"
def getMyVersionName = {
    -> "hhh"
}
def getMyVersionCode = {
    it ->
        def sss = new ByteArrayOutputStream()
        Process process = Runtime.getRuntime().exec("git branch -v")// | grep develop")
        String trimProcessStr = process.getText().trim()
        trimProcessStr = trimProcessStr.substring(trimProcessStr.indexOf("$it") + "$it".length()).trim()
        println("$it")
        trimProcessStr = trimProcessStr.substring(0, trimProcessStr.indexOf(' ')).trim()
        println(trimProcessStr)
        long val = Long.parseUnsignedLong(trimProcessStr, 16)//"0x$trimProcessStr".toLong()
        val
        println(val)
//        Long.toHexString(val)
        Integer.parseInt(trimProcessStr, 16)
//        Integer.MAX_VALUE
}

println(myVersionName)
println(getMyVersionName())
//println(getMyVersionCode("develop"))
println(getMyVersionCode("master"))
//println((char)getMyVersionCode("master"))

