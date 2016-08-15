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




