/**
 * Created by hughie on 16/8/15.
 */

//println "svn help".execute().text
//println("svn help".execute().text)

//println("svn help".execute().getClass().name)
println("svn help".execute().getClass())

print("groovy -v".execute().waitFor())
//"groovy -v".execute().waitForOrKill(1000)
//println("svn help".execute().text)

Process exePrg = "svn help".execute()
print(exePrg.text)