/**
 * Created by hughie on 16/8/15.
 */


def foo(str) {
//    if(str != null) {
//        str.reverse()
//    }
    str?.reverse()          ///< 简化判断语句
}


println(foo('evil'))
println(foo(null))


///< openFile没有处理FileNotFoundException异常,而是被传给调用代码
def openFile(fileName) {
    new FileInputStream(fileName)
}

try {
    openFile("nnnnonon")
} catch (FileNotFoundException e) {
    println("FileNotFoundException => " + e)
}

try {
    openFile("nnnnonon")
} catch (Exception e) {
    println("Exception => " + e)
}


