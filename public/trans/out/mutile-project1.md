#构建 Muti-Project

本文介绍了怎样在一个 build里面构建多个项目。

在阅读本文之前，首先请读一下前面的开始指南，尤其是需要了解一下 build.sbt 和 .scala build definition。

##Mutiple projects

它可以在一个 build 里面构建多个相关的项目，如果它们互相依赖并且你想在一起编辑它们，那就在好不过了。

build 里的每一个 sub-project 都有它自己的 src/main/scala，当你运行 package的时候会生成自己的 jar 文件，就像其它项目一样。 

##在 .scala 文件里定义项目

为了使用 multiple project ，你必须在一个.scala 文件里声明每一个项目并且描述它们之间的联系。这在 .sbt文件里是无法做到的。不管怎样，你可以在 .sbt文件里为每一个项目定义配置，下面是一个 定义了一个根工程 hello 的.scala文件的例子，这个根项目一共有两个 sub-projects，hello-foo 和 hello-bar：

    import sbt._
    import Keys._
    object HelloBuild extends Build {
        lazy val root = Project(id = "hello",
                                base = file(".")) aggregate(foo, bar)
        lazy val foo = Project(id = "hello-foo",
                               base = file("foo"))
        lazy val bar = Project(id = "hello-bar",
                               base = file("bar"))
    }

sbt使用反射技术来找到 Project 对象的列表的，进而在 Build 对象里寻找 Project 字段。

因为 hello-foo 对象通过 base = file("foo") 来定义，所以它包含一个 foo 的子路径。它的源文件可以在 foo 下面的路径里找到，比如 foo/Foo.scala，或者 /foo/src/main/scala。通过 build 定义文件可以看出，在 foo 下面有 sbt 默认目录结构。

foo 里面的所有的 .sbt 文件，也就是 foo/build.sbt ，将会并入到整个构建的构建定义里，但是不会在 hello-foo 工程里。

如果你的整个工程都在 hello 里，尝试着在 hello/build.sbt ，hello/foo/build.sbt 以及 hello/bar/build.sbt 里面定一个不同的版本（version :="0.6"）。然后在sbt 控制台键入 show version。你会得到像这样的结果（取决与你定义的版本）：

    > show version
    [info] hello-foo/*:version
    [info]  0.7
    [info] hello-bar/*:version
    [info]  0.9
    [info] hello/*:version
    [info]  0.5

hello-foo/* 的版本定义在 hello/foo/build.sbt 里面，hello-bar/* 的版本定义在 hello/bar/build/sbt 里面，hello/* 的版本定义在 hello/build.sbt 里面。记住每个语法的范围的 keys*(Scopes.html)。每个 version key 对应着项目的一个基于 build.sbt 的范围。但是所有的 build.sbt 都是 build 定义的一部分。

每一个工程的设置都可以在 工程的根目录里面的 sbt 文件里进行配置，就像上面的例子那样，使用 .scala 文件同样也会很简洁的列出了项目以及基本的目录，但是没有必要把配置都放到 .scala 文件里面。

你可能会觉的把一切东西包括配置都放在 .scala 文件里很干净，因为保持了所有的构建定义仅用了一个 project 路径就完成了。好吧，一切随你。

不能在 sub-projects 里面在加一个 project 子路径，那样的话 foo/project/Build.scala 会被忽略的。

##集成

如果你愿意，项目的构建和另一个完全相互独立。

在上面的例子里，你可能注意到了 aggregate(foo,bar) 方法。它把 hello-foo 和 hello-bar 都放在了根路径下。

集成意味着在一个集成的工程里面运行任务的同时运行这个集成的工程。这个例子里我们用 sbt 启动两个子项目，并尝试编译。你将会看到三个项目都被编译了。

在根项目 hello 里在集成项目，你可以控制每一个任务。 

    aggregate in update := false

aggregate in update 是描述更新任务的范围的全局量，见 scopes。 

注意：在没有定义顺序的情况下，项目会按照字母排列顺序运行。

##Classpath 依赖

一个项目可能会依赖其它的项目。调用 dependsOn 方法来实现。例如，如果 hello-foo 需要使用 classpath里面有 hello-bar 的类，你可以在你的 Build.scala 里面这样写。

    lazy val foo = Project(id = "hello-foo",
                           base = file("foo")) dependsOn(bar)

现在 hello-foo 可以使用 hello-bar 里面的类了。它还会在编译它们的时候创建一个顺序：hello-bar 必须在hello-foo 编译之前更新编译。

为了更方便的使用于mutiple projects,在dependsOn 里面可以使用多个参数，如 dependsOn(bar,baz)。

###依赖里面classpath的每一个配置

foo dependsOn(bar) 意味着 foo 里面的
Compile 配置依赖于bar 里面的
Compile 配置。 你也可以这样写
dependsOn(bar % "compile->compile")。

compile->compile 里面的 -> 意味着依赖于，所以 test->compile 意味着 Test 里面的 foo 配依赖于 bar 里面的编译配置。

省略的 ->config 部分意味着
->compile, 因此 dependsOn(bar % "test")
意味着foo 里面的 Test  配置bar 里面的
Compile 配置。

一个有用的写法是 test->test，意味着 Test 依赖于 Test。比如，它允许你在 bar/src/test/scala 里面测试代码，接着又在 foo/src/test/scala 里面测试。

在sbt 控制台提示符下输入 projects 来列出有的工程，输入 project <projectname> 来选择一个做为当前的工程。当你运行像 compile

你可以在一个依赖里面使用多个配置，使用分号分隔。例如，
`dependsOn(bar % "test->test;compile->compile")`.

##Navaigating 项目的集成

在 sbt 控制台输入 project 来列出你所有的项目，project <projectname> 来选择一个当前使用的项目。当你使用类似 compile 的命令时，是在当前项目里执行的。所以你不必编译根项目，你只需要在一个子项目里 compile。

##共享设置

当在不同的项目里面使用一个 .scala 配置文件时，在不同的项目中间重用配置是很方便的。但是即使是使用不同的 buid.sbt 配置文件，在 main build 里面使用共享的配置依然很方便，只需要对相应的配置使用 ThisBuild 来把它应用的全局范围。例如，当 main 项目依赖于一个子项目时，为了这两个项目使用同一个版本的Scala 来编译。可以在 build.sbt 文件里面这样写一次： 

    scalaVersion in ThisBuild := "2.10.0"

把 2.20.0 替换成相应的 Scala 版本。这个设置会在所有的子项目里面生效。也可以在 “page on scope ”Thisbuild 处获得更多信息。

继续学习自定义配置。



