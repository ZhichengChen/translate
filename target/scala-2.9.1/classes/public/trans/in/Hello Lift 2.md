#Hello Lift

This chapter covers

* An introduction to the SBT build tool
* Creating your first Lift-powered application
* Snippet and templating overview

In this chapter, you’ll be creating your first Lift application, but before getting to
that, you need to set up your environment so you can compile and run your appli-
cation code. In order to run on the JVM, Scala code must be compiled before it can
be executed. Although it’s possible to compile Scala source code manually, it’s a
good idea to have an automated build tool that does this for you.

If you’re coming from a dynamic language such as Ruby or PHP, you may never
have needed a build tool. Essentially, build tools automate parts of your devel-
opment and deployment processes. Typical tasks include compiling and packag-
ing code into deployable binaries, generating code documentation, and lots of
other things.

In this chapter, you’ll be setting up the Simple Build Tool (SBT) that you’ll use
throughout this book. You’ll also see how you can get SBT to speed up your devel-
opment by generating starting points for projects, classes, and markup templates.

Once you have your environment configured, you can get to work making
your first Lift application. Section 2.2 walks you through creating this first
project and explains the various component parts, their purposes, and how you can
add to them.

The final section builds upon this introduction and explains how you can put
together your own snippets and templates.
First, though, let’s get you set up and working with SBT.

##Getting started with SBT

SBT is primarily a command-line tool and is shipped as an executable JAR file. This
section will show you how to configure that executable as a command-line system
tool, but it’s also possible to leverage it from within your IDE if that’s how you pre-
fer to work. For more information on setting up an IDE to work with Scala, see
appendix B.

    NOTE Scala is fully interoperable with Java, which means that Scala is also very conversant with the range of Java build tools, such as Maven http://maven.apache.org) and Ant (http://ant.apache.org). These tools have fair support, and you can use them within your IDE of choice if you prefer.

Even though there are a variety of tools available to build your Scala code, SBT is the
most prevalent in the community, and it’s what you’ll find the majority of projects
using (including Lift). Broadly speaking, SBT is relatively fast at compiling code, it has
a simple command interface, and it’s easy to extend with simple Scala plugins, which
is likely why it has proven so popular.

In order to set up SBT, you need to take a moment to make sure you have sev-
eral things in place. As discussed in chapter 1, Scala runs inside the Java runtime, so
you’ll need to have Java Runtime Edition (JRE) 1.5 or greater installed to work with
Lift. At the time of writing, Lift will work equally well with either Java 1.5 or 1.6, but
in future versions Java 1.5 will likely be dropped in order to tighten up the Lift API.
You can verify your Java version by opening a console window and running the fol-
lowing command:

    java -version

If you have Java installed, this will output something like: java version "1.6.0_17". If
you don’t have Java, head over to the main download site (http:/
/www.java.com/en/
download/index.jsp) and follow the instructions to install it.

Provided Java is on your system, the first thing to do is download the SBT launcher
JAR and place it somewhere on the environment path ($PATH on Unix and %PATH% on
Windows). SBT is provided as an executable JAR file, which essentially means that the
JAR file is like a mini application; it’s a compiled archive that has the ability to be run
as a program or process. Invoking it from the command line will load Java and then
load the SBT shell.

To get SBT, head over to the SBT downloads page and grab the latest release (http:/
/code.google.com/p/simple-build-tool/downloads/list). At the time of writing 0.7.7,was the latest stable build of the 0.7.x series of SBT, but the instructions that follow
should make sense with subsequent versions of SBT.

    NOTE As this book was being finished, the SBT project was starting to release early versions of a completely redesigned version of SBT under the 0.10+ branches. Currently this series is so radically different that the configu- ration and setup will differ from what is described here. The 0.7 series will continue to be supported for the foreseeable future, so using it is fine, and, when the time comes, migrating to the official 1.0 version of SBT shouldn’t be too difficult.

SBT is a command-line application and has no out-of-the-box graphical user interface
(GUI) to speak of, so it must be executed from a console window and interacted with
from the SBT shell. In order to make executing SBT easy, it’s best to wrap it in a small
shell script (or .cmd file on Windows) that will let you execute the JAR with the simple
command sbt. This small extra step will pay dividends in your development cycle, so
let’s take a moment to set up the wrapper script, as shown in table 2.1.

Table 2.1 Setting up SBT on your development machine

<table class="table table-bordered">
    <tr>
        <td colspan="3">
            Configuring SBT
        </td>
    </tr>
    <tr>
        <td>
            Step
        </td>
        <td>
            Action
        </td>
        <td>
            Result
        </td>
    </tr>
    <tr>
        <td >
            1
        </td>
        <td>
            Download SBT, place it in a well-known location, and name it sbt-launch-VERION.jar.
                  
            Unix: We recommend putting the file in /usr/local/bin

            Windows: We recommend putting the file in C:\scala\sbt
        </td>
        <td>
            The downloaded SBT launcher should have exe- cutable permissions and be in a well-known file
    location.
        </td>
    </tr>
    <tr>
        <td>
            2
        </td>
        <td>
            Create a file in the same directory called “sbt” and give it executable permissions.
                     
             Note: Windows users will need to call their file “sbt.bat” or “sbt.cmd”.
         </td>
         <td>

         </td>
     </tr>
                 <tr>
                  <td>
                     3
                   </td>
                   <td>
                        Populate that file with the correct execution
                        command for your operating system.
                        
                        Unix 

                        Windows
                      </td>
                      <td>
                        java -XX:+CMSClassUnloadingEnabled
                            -XX:MaxPermSize=1024m -Xmx2048M -
                           Xss4M -jar `dirname $0`/sbt-
                          launch.jar "$@"

                           set SCRIPT_DIR=%~dp0
                               java -XX:+CMSClassUnloadingEnabled
                              -XX:MaxPermSize=1024m -Xmx2048M -
                             Xss4M -jar "%SCRIPT_DIR%sbt-
                            launch.jar" %*
                    </td>
                  </tr>
                </table>

Your first Lift application
23
With your SBT script set up and available on your environment path, it should be pos-
sible to open a console window, type sbt, and see the following:
$ sbt
Project does not exist, create new project? (y/N/s)
If you’re prompted to create a new project, SBT has successfully been installed! For
the moment you can simply enter n as the answer to quit the shell; you’ll be creating
an application in the next section. But if you don’t see a prompt similar to the preced-
ing snippet of terminal output, please refer to the SBT installation documentation
(http:/
/code.google.com/p/simple-build-tool/wiki/Setup).
Providing your install went well, from here on you’ll only work with SBT from its
interactive console to execute tasks and actions. Without further ado, let’s get on with
creating your first Lift application with your fresh install of SBT!
2.2
Your first Lift application
Throughout the course of the next few chapters, you’ll be building an auction-style
application. The next chapter discusses in detail the application’s functionality, so for
the moment we’ll focus on the fundamental building blocks that form the basis of any
Lift project. This will involve creating an empty SBT project and then populating that
with the configuration required to run a Lift application. This will give you a fully
functioning (albeit very basic) Lift application that you can take forward to subse-
quent chapters. You’ll also be able to use it as a guide for building your own applica-
tions, both in terms of the steps used to create the project and in terms of the
interaction within the SBT shell.
The next section will walk you through the commands and options involved in cre-
ating a new SBT project and also introduce a Lift community tool called Lifty (http:/
/
lifty.github.com/Lifty/), which you can use to speed the setup of new projects. With
the project structure in place, the subsequent two sections will discuss the various
components of the default Lift application and then demonstrate how to boot the
built-in web server so you can interact with the application on your local computer.
2.2.1
Creating the project
To get started, open a console window switch, with cd, into a new working directory of
your choosing. Here you should invoke the sbt command. After doing so, SBT will
check to see if a project is already in place, and if not, it will then prompt you to create
a new SBT project. SBT determines whether a project already exists by checking for a
project directory containing a build.properties file.
When creating a new SBT project, you’ll be prompted to answer several questions
about the new project configuration. SBT displays the defaults in square brackets next
to the question. For each line, just enter the value you would like to use, and press the
Enter key. Table 2.2 lists the things SBT will ask for and describes them, providing
some suggested values.
24
CHAPTER 2
Table 2.2
Hello Lift
SBT prompts and suggested values
Prompt
Description
Name This value defines the name of your project. It’s also used as the
      artifact identifier for the published binary. For example, having a
     name of “Sample” will result in a binary named Sample.jar.
Organization This is typically the group identifier of the output application binary.
            For example, Lift’s WebKit module has organization set
             as net.liftweb.
Version [1.0] This is the version number you want to start your project with.
Scala version [2.8.1] At the time of writing, 2.8.1 was the latest release of Scala that
                     Lift officially supported, and this is what all the code samples in this
                    book are compiled against. The 2.7.x series of Scala is now depre-
                   cated.
sbt version [0.7.7] If you want to specify a newer version of SBT, you can enter it here,
                   and SBT will automatically find the correct JAR online from its repos-
                  itory and use that for this project.
After answering the SBT prompts, the terminal should drop into a shell with a single
prompt on the left side—the > prompt that your console window is now displaying.
This is the interactive SBT shell, and this is where you must issue commands to control
your project and execute tasks. Figure 2.1 shows the full interaction with SBT involved
in creating a new project.
Figure 2.1
Output from creating a new SBT project
Your first Lift application
25
You may have noticed that SBT has generated a folder structure consisting of several
elements that manage the SBT build, providing you with a starting point for any Scala-
based application. Right now, this project can only compile standalone Scala code and
lacks the required files and configuration to support a web application.
All SBT projects have a project definition class, or project file (typically called Project
.scala). By default, SBT doesn’t generate this because it isn’t mandatory for the most
basic Scala console applications, but in order to build a Lift web app, you’ll need to
create a project file to define dependencies and generally control the build process.
To avoid creating this manually, you can use an SBT feature called processors, which
allow SBT to pull executables from the internet to augment the default commands
that SBT ships with.
To get started creating the project structure, you’ll use a processor to generate
the structure and default files you’ll need to start working on the application.
From the interactive shell, run this command:
> *lift is org.lifty lifty 1.6.1
This command instructs SBT to define a processor called lift; the asterisk in the com-
mand is important, so make sure you include it.
Now, whenever lift is entered into the shell, it should use the processor actions
defined in the org.lifty.lifty JAR file that’s located on the scala-tools.org repository.
Because both SBT and Lift are hosted on the same online repository, SBT already knows
where to find the Lifty binaries. The first time you define the lift processor, there may
be a slight pause after pressing Enter, because SBT has to fetch the JAR from the online
repository. Don’t worry, though; the downloading should only take a few moments.
It’s Lifty, not Lift
Lifty is a community project developed by Mads Hartmann Jensen during the 2010
Google Summer of Code, and although it isn’t an official part of the Lift project, it has
strong links with the Lift community and team. More information about Lifty is avail-
able from the project home page (http:/
/lifty.github.com/Lifty/).
Note that you could define the SBT processor as being called “lifty” if you wanted. It
won’t have any practical impact, so call it whatever you like. For the purposes of this
book, though, it will be called lift.
Now that the lift processor is defined in your SBT setup, you can invoke any of the
actions that are defined in it. For example, create is an action that takes a set of argu-
ments and can create files in your SBT project, whether it’s a single snippet or a whole
project. If you want more information about the actions available, just type lift help.
You can also get more specific information on the arguments that a specific template
requires by typing lift <name_of_template>. For example, lift create will tell you
specifically what arguments it needs and what the options are.
26
CHAPTER 2
Hello Lift
In order to start creating your application, you need to add the components
required for a Lift web project. Fortunately, the lift processor already knows how to
add these things, so you only need to execute the following command to populate a
blank SBT project with Lift web goodness!
> lift create project-blank
Invoking this command will prompt you for a mainpack. This is the main package
that you would like the code to be generated in. An example value could be
com.mycompany, but by default it will be the value you supplied as the organization
when you created the project. Next, it will prompt you for a Lift version; use 2.3
because all of the code samples in this book are compiled and tested against that
version of Lift. When you press Enter, Lifty will generate a set of files for your Lift
application, including the SBT project definition and all the associated elements
(which we’ll discuss shortly).
Now you need to tell SBT about the new defi-
nition. To do this, type the following commands:
> reload
> update
These are important commands that you’ll use
pretty frequently with SBT. The reload command
tells SBT to recompile the project definition and
refresh the classpath, whereas the update com-
mand gets SBT to download the necessary depen-
dencies from the repositories defined in the
project definition.
All Lift applications depend on a set of libraries
that contain the Lift code, and SBT automatically
downloads these for you and enables them within
the project. The required JAR files will be down-
loaded into the lib_managed directory that SBT cre-
ates. For more information on SBT and how it
handles dependencies and Scala versions, head
over to the online wiki: http:/
/code.google.com/p/
simple-build-tool/wiki/LibraryManagement.
2.2.2
Inspecting the project
Now that you have a fresh project created, let’s take
a moment to inspect the files that were created.
The generated source tree should be quite similar
to figure 2.2.
For readers familiar with Java web development,
you’ll notice several familiar elements in the project
Figure 2.2 A new project tree
detailing the files you’ll see after
creating a new project with SBT
27
Your first Lift application
structure. In particular, the web.xml file and WEB-INF directory are standard Java web
application items. One of the great benefits of Lift is that it utilizes standard Java
deployment packages such as Web Application Archives (WAR) and Enterprise
Archives (EAR), so it can be easily deployed in your standard Java servlet containers with
no changes to code or reinvestment in business infrastructure. It just works.
If you’re not familiar with Java web application structure, don’t worry too much;
these files are essentially default configurations that indicate to the Java web server
how it should handle the application when it’s deployed.
Let’s take a look at generated project structure and the functions of the main com-
ponents of the source tree.
PROJECT DIRECTORY
Working from the root folder through the project tree, you should see the project
directory. This is where SBT holds the information about the application, dependencies,
and repositories. You configure the build environment with Scala code, which is fully
type-safe, and SBT won’t let you proceed if your Project.scala won’t compile. The basic
project file for the example application should look something like the next listing.
Listing 2.1
The project definition
import sbt._
class LiftProject(info: ProjectInfo)
 ̄ extends DefaultWebProject(info) {
val liftVersion = "2.3"
val webkit = "net.liftweb" %% "lift-webkit" %
liftVersion % "compile->default"
val logback = "ch.qos.logback" % "logback-classic" %
"0.9.26" % "compile->default"
val servlet = "javax.servlet" % "servlet-api" %
"2.5" % "provided->default"
val jetty6 = "org.mortbay.jetty" % "jetty" %
"6.1.22" % "test->default"
val junit = "junit" % "junit" %
"4.5" % "test->default"
val specs = "org.scala-tools.testing" %% "specs" %
"1.6.6" % "test->default"
B
Extends
DefaultWebProject
C
Defines
dependencies
lazy val scalatoolsSnapshots = ScalaToolsSnapshots
}
All SBT project definitions must extend one of the bundled project types, and for web
applications that’s DefaultWebProject B. This trait supplies actions for executing a
local Jetty server for development and some other web-specific infrastructure that
you’ll need when developing a web application.
Dependencies within your project are defined by using the percent character as a
delimiter between group identifier, artifact identifier, version, and finally the scope
that you require from that dependency C. You’ll also sometimes see artifacts that are
delimited by a double percent symbol (%%), and in this case SBT will automatically
28
CHAPTER 2
Hello Lift
append the Scala version to the artifact identifier when attempting to resolve the
dependency. For example, the main WebKit JAR that this project depends upon uses
the %% notation; SBT will automatically append the Scala version, so the dependency is
resolved as lift-webkit_2.8.1.jar. This feature was added to SBT because there are dis-
tinct binary incompatibilities between different versions of Scala; this neat solution
hides having to detail the Scala version for every Scala-based dependency.
If you’re new to dependency management, all you need to know is that you have
the scopes defined in table 2.3.
Table 2.3
Available SBT dependency scopes
Scope
Description
Compile Dependencies directly required to compile parts of your code
Provided Dependencies required to compile your code but that will be provided
        at deployment time, usually by the servlet container or deployment runtime
Runtime Dependencies that are required only at runtime, and not for compilation
Test Dependencies that aren’t required for normal operation of the
      application, but only for testing purposes
There are some additional scopes available, but these are the most common ones
you’ll use in conjunction with SBT.
SRC DIRECTORY
As the name implies, the src directory is where all the source code for your applica-
tion lives. In its main directory there are three important directories that you’ll
need to utilize during your Lift development; table 2.4 describes the purpose of
each section. The src directory contains everything that directly contributes to the
application’s functionality.
Table 2.4
Directories created in the SBT project
Directory Description
src/main/resources The resources directory is where you can place configuration and
                  related files that you want to be packaged up with your application
                 deployment WAR. The things you place here will be on the root class-
                path of the output package, so it’s usually a good place to hold things
                 like logging configuration or other resources that you need access to at
                runtime. Lift’s configuration files (ending with .props) typically live in the
                 resources/props folder.
src/main/scala This is where all the Scala and Lift code you write will be placed
              and managed from. By default, Lift will look for a Boot class in the
             bootstrap.liftweb package within your application. In the package
            name that you supplied earlier on the command line, several subpack-
           ages have been created with a sample HelloWorld.scala file for illus-
          trative purposes.
Your first Lift application
Table 2.4
29
Directories created in the SBT project (continued)
Directory
src/main/webapp
Description
This directory holds all your XHTML markup files and any associated
static resources you might want to use as part of your Lift application.
The main difference between this setup and usual Java web applications
is the templates-hidden directory. Lift has a very sophisticated templat-
ing system, and any markup files that are present in templates-hidden
can only be used for templating and not for complete pages. More on
this in chapter 6.
There is also the test folder, but as the name implies, it only contains testing materials.
Testing is discussed in more detail in chapter 14.
The next section will show you how to take your stub application and run it and
start playing with Lift itself.
2.2.3
Booting the application
Now that you’re fully oriented with your first Lift application, let’s get on with get-
ting it running! Fortunately, SBT comes with a built-in Jetty web server (http:/
/
jetty.codehaus.org/jetty/) that you can use to run your application while you’re
developing it, so there’s no need to go through the process of building a WAR and
deploying to a standalone servlet container as you would do when your application
goes to production.
Boot up your application with the following command at the SBT interactive shell:
> jetty
This will compile all your code before starting up a local Jetty server in the root of
your project; you can then visit http:/
/localhost:8080 in your browser to see the appli-
cation running. Your application is fully functional and will operate exactly the same
in this embedded Jetty server as it will in production, which is an invaluable develop-
ment aid.
By default, SBT will attempt to start Jetty on port 8080, but in the event
that you already have something running on that port, you can easily swap to
an alternative port by overriding the jettyPort method in your SBT configu-
ration: override def jettyPort = 9090.
NOTE
With Jetty now running, you should be able to open a browser window and see some-
thing similar to figure 2.3.
In order to stop the Jetty server, press the Enter key to return to the SBT shell and
stop Jetty outputting to the console. Then type this command:
> jetty-stop
Jetty will then stop what it’s doing and shut down.
30
CHAPTER 2
Hello Lift
Figure 2.3 A screenshot of the browser window after you start the local development server running
the basic Lift application
There are often times when you’ll want to work on your project with the compiler run-
ning and giving you feedback on the code you’re writing at that moment. There’s a
convenient command for this:
> ~compile
This will force the compiler into a mode called continuous compilation. With continu-
ous compilation running, any files you save will automatically be recompiled. The ~
symbol can be prefixed to any SBT action, and the action will then attempt to do that
task continuously.
Now let’s look at the basics of snippets and get an overview of Lift’s sophisticated
templating mechanism.
Avoid restarting with JRebel
During your development cycle, it can be annoying to need to restart the Jetty
server when you want to check the effect of some new changes, but, by default,
this is the only way to test out the impact of your changes. That’s where tools
such as JRebel (http:/
/www.zeroturnaround.com/jrebel/current/) can be extremely
useful when used in conjunction with continuous compilation. JRebel lets you
dynamically reload your altered classes, removing the need to restart Jetty your-
self after each change.
JRebel is a commercial tool, but they do kindly offer a free license for pure Scala
development—all you need to do is apply online (http:/
/sales.zeroturnaround.com/).
After you do so, they’ll send you a license that you can use when you’re developing
your Lift (or any other Scala-based) applications. Awesome!
31
Snippets and templating overview
2.3
Snippets and templating overview
Now you’ve seen the overall structure of a Lift application based upon the generated
project, but you haven’t yet looked at any Lift code, so you may be wondering exactly
how this all hangs together and what the code looks like. Well, several different file
groups were generated, but the two we’ll look at here form the crux of any Lift appli-
cation: snippets and templates.
2.3.1
Snippets
In chapter 1, we touched on the concept of snippets and mentioned how one of
the key principals of the view-first pattern is having small, reusable pieces of render-
ing logic. Snippets are just functions that take template markup as their input and
then transform it by executing the logic defined in that function to produce the
desired markup.
Because snippets are just functions, they typically have an encapsulating class to
contain them. The default project you generated doesn’t have any snippet classes yet,
but you can create a new one by giving SBT the following command:
>lift create snippet
This command will then prompt you to enter a name for the snippet class and ask you
which package you would like it to be placed in. Answer these two prompts, and then
Lifty will generate a new snippet class.
If you called the snippet class HelloWorld, the newly created file would have the
definition displayed in the following listing.
Listing 2.2
Default HelloWorld snippet
package example.travel.snippet
import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
class HelloWorld {
def render = "*" #> <strong>hello world!</strong>
}
Import implicit
helpers
B
Begin snippet
definition
This is a simple Scala class featuring a single snippet method that defines what is
known as a CSS transformer B. These CSS transformers are essentially functions of
NodeSeq => NodeSeq and are supplied by importing the Helpers._ object, which con-
tains the right implicit conversions.
Scala referrers to XML as a NodeSeq; that is, a sequence of XML nodes. You can
think of snippet methods as things that take in XML, transform it, and then yield an
XML output. In listing 2.2, the render method will replace the snippet call site with
the words hello world in bold. CSS transformers are discussed in depth in chapter 6, but
just be aware that it’s possible to use them to replace or transform the nodes you select
with the computed dynamic values that feature in your snippet.
32
CHAPTER 2
Hello Lift
Implicit conversions
The Helpers object from Lift Utilities contains a whole set of functions that are
somewhat special to the Scala compiler. The functions are known as implicit con-
versions, and what that essentially means is that given a function that knows how
to turn type A into type B, the compiler will automatically apply that function at the
right time. This allows you to build APIs that call seemingly nonexistent functions
on particular types.
In listing 2.2, String doesn’t have a definition of #> but the compiler knows how
to take a String and wrap it in such a way so that it’s the right type to satisfy the
call to #>.
Let’s take this example a little further and illustrate exactly what the snippet method is
doing. Consider the following markup:
<p lift="HelloWorld.render">Replace me</p>
This markup calls the render snippet on the HelloWorld class, so assuming this
XML is passed into the render method from listing 2.2, the resulting markup would
be as follows:
<strong>hello world!</strong>
The entire <p /> node has been replaced with the <strong /> node. Although this is a
simple example, it’s a very powerful concept, and it means that absolutely zero code
makes it into your markup templates—they always remain fully valid XHTML files.
You may already be wondering how it is that these templates trigger the right
snippet transformations. Well, Lift has several methods for resolving snippets to
actual Scala code, but the one that we’ll be focusing on for the moment is reflection-
based lookup.
Lift can be very clever about the snippet markup so that it remains idiomatic no
matter how you like to work or what your conventions are. Given the snippet in list-
ing 2.2, any one of the following would be a valid snippet call in your template.
<div lift="HelloWorld.render">...</div>
<div class="l:HelloWorld.render">...</div>
<div class="lift:HelloWorld.render">...</div>
<lift:hello_world.render><p>Replace me</p></lift:hello_world.render>
<lift:HelloWorld.render><p>Replace me</p></lift:HelloWorld.render>
<lift:helloWorld.render><p>Replace me</p></lift:helloWorld.render>
<lift:snippet type="HelloWorld:render"><p>Replace me</p></lift:snippet>
Lift uses reflection and some basic name translation rules to look for the correct class,
and then uses that to transform the input markup to the desired output markup,
which is then piped back into the rendered output to the browser.
Although this fundamental concept of transforming XML is a simple one, it can be
very powerful when you’re building web applications, and Lift uses the same snippet
mechanism for implementing many parts of its default infrastructure. A primary
33
Snippets and templating overview
example of that would be Lift’s templating support, which is built upon the very same
snippet mechanism.
2.3.2
Templating overview
Templates in Lift are always fully valid XHTML or HTML5 markup. Lift doesn’t let you
write invalid markup. Even though templates are just XML without any executable
code, templates have a lot more functionality than just being a place to invoke your
own application snippets.
In the same way that Lift helps keep your server code cleanly separated, Lift
offers some convenient helpers for your templates via some built-in snippets. These
snippets let you modularize your template code and promote reuse of both markup
and Scala code.
More often than not, your application will use either a single or small collection of
top-level templates that contain the majority of the markup. Each page has a much
smaller template that contains the static content and calls to whichever snippets are
needed to provide the various dynamic items for the page. These smaller page frag-
ments are wrapped with what is referred to as a surround, in order for them to inherit
the full-page template. Surrounds can wrap other pieces of template markup to con-
struct a hierarchical structure within the template so each page has only the minimum
markup required to render the page.
The following listing is an example of a template that could have page content
inserted by individual pages at the bind point called “content.”
Listing 2.3
Example of a template surround
<html xmlns=http://www.w3.org/1999/xhtml
xmlns:lift="http://liftweb.net/">
<head>
<title>demo:demo:1.0-SNAPSHOT</title>
</head>
<body>
<lift:bind name="content" />
</body>
</html>
B
Binding point referenced
by “content”
Listing 2.3 defines a binding point B for specific page content to be injected into,
and the handle with which you can reference it with later is content. That is to say,
pages can declare surrounds that bind to content, and their markup will be
replaced at that location. It’s important to note that you can have as many binding
points as you like in any given template, and not all the points have to be used in a
given page rendering.
From the page-level perspective, each template (for example, index.html) can
specify the surrounding template that it will be wrapped with. Importantly though,
each child template can only have a single root element, because otherwise it would
be an invalid XML document.
34
CHAPTER 2
Hello Lift
An example of using a surround in a page can be seen in the following markup:
<lift:surround with="default" at="content">
<h2>Your content goes here</h2>
</lift:surround>
The purpose here is to wrap the <h2>...</h2> code (the particular page content)
with the broader page template defined in templates-hidden/default.html. Together
they make a full page, inclusive of content.
The surround snippet takes two parameters. The first is with, which defines the
template to wrap the content with. In this case, "default" refers to the template
located at src/main/webapp/templates-hidden/default.html. By default, your sur-
round, or parent, templates need to be located in templates-hidden in order for Lift
to actually find them. The second parameter is at, which defines the reference name
of the binding point in the parent template. Essentially, you’re telling Lift to take this
content and insert it into the parent at a given location based on the <lift:bind />
element discussed in listing 2.3.
In addition to the functionality provided by surrounds, you might find you need to
insert markup from another template while building your application, to avoid dupli-
cation of markup. For example, a form for adding a product to a system would be
much like a form for editing that product in a different section of the system. Lift has
this covered; here’s an example of using template embedding:
<lift:embed what="/foo/_bar"/>
This call to <lift:embed> allows you to arbitrarily embed templates into one another
so you don’t have to worry about duplicating your presentation code. The what
attribute parameter takes a path from the root of the webapp directory; in this case, it
would include the content from the template in the src/main/webapp/foo/
_bar.html file. This can be an extremely effective technique and can really assist you in
not repeating yourself in the application markup.
Whether you’re embedding or surrounding content, another common idiom that
most applications require is to have page-specific items such as JavaScript and CSS ele-
ments in the <head> of a page. Lift has some nifty tooling for this. All you need to do
is define the <head> element inside of a surround element, and Lift will automatically
merge that content with the top <head> element. Consider this example:
<lift:surround with="default" at="content">
<head>
<script type="text/javascript"
src="thing.js"></script>
</head>
<h2>Whatever Page</h2>
...
</lift:surround>
B
Demo JS
file
In this code block, notice how the sample JavaScript file detailed at B is enclosed in the
<head /> element. When Lift runs this template, it will realize that the head element is
Summary
35
present and merge its child nodes with the top-level head element so that all your page-
specific resources sit where they should.
Alternatively, if you prefer to speed the page loading and place a file before the clos-
ing <body> tag, as is the current fashion, Lift also supports this via the <lift:tail />
snippet. The functionality is the same as the head merge, but it instead places content
just before the closing </body> tag.
These are a few of the out-of-the-box tools Lift supplies for working with dynamic
content and page markup. There are a whole set of additional tools that are covered
in chapter 6.
2.4
Summary
In this chapter, you built a basic, but fully functioning, Lift application with SBT. In
addition, you’ve had a high-level overview of some of Lift’s templating and snippet
functionality. You should now have a good idea of how a Lift application is assembled
and be aware of some of the high-level components that can be used to get it off the
ground. These include the powerful concept of snippets that transform input XML to
dynamically rendered output, and the way Lift assists you in keeping your template
code concise and empowers you to not repeat yourself.
Importantly, though, you put in place the building blocks for the main application
that you’ll be building during the course of the book. In the next chapter, you’ll
enhance the application and start to design and implement some of the core func-
tionality. This will take us deeper into Lift’s snippet system, and you’ll learn about
database connectivity with Mapper.
