#Lift in Action#

The simply functional web framework for Scala

Timothy Perrett

MANNING

SHELTER ISLAND

#To my Dad#

for teaching me that hard work and dedication
can triumph over any problem

For online information and ordering of this and other Manning books, please visit
www.manning.com. The publisher offers discounts on this book when ordered in quantity.

For more information, please contact

    Special Sales Department
    Manning Publications Co.
    20 Baldwin Road
    PO Box 261
    Shelter Island, NY 11964
    Email: orders@manning.com

©2012 by Manning Publications Co. All rights reserved.

No part of this publication may be reproduced, stored in a retrieval system, or transmitted, in
any form or by means electronic, mechanical, photocopying, or otherwise, without prior written
permission of the publisher.

Many of the designations used by manufacturers and sellers to distinguish their products are
claimed as trademarks. Where those designations appear in the book, and Manning
Publications was aware of a trademark claim, the designations have been printed in initial caps
or all caps.

Recognizing the importance of preserving what has been written, it is Manning’s policy to have
the books we publish printed on acid-free paper, and we exert our best efforts to that end.
Recognizing also our responsibility to conserve the resources of our planet, Manning books
are printed on paper that is at least 15 percent recycled and processed without the use of
elemental chlorine.

    Manning Publications Co.
    20 Baldwin Road
    PO Box 261
    Shelter Island, NY 11964
    
    Development editors:Katharine Osborne
    Copyeditor:Andy Carroll
    Typesetter:Dennis Dalinnik
    Cover designer:Marija Tudor

ISBN: 9781935182801
Printed in the United States of America
1 2 3 4 5 6 7 8 9 10 – MAL – 17 16 15 14 13 12 11




#brief contents#

PART 1 GETTING STARTED ........................................................1

1. Introducing Lift 3
2. Hello Lift 20

PART 2 APPLICATION TUTORIAL ..............................................37

3. The auction application 39
4. Customers, auctions, and bidding 60
5. Shopping basket and checkout 83

PART 3 LIFT IN DETAIL ..........................................................105

6. Common tasks with Lift WebKit 107
7. SiteMap and access control 140
8. HTTP in Lift 160
9. AJAX, wiring, and Comet 187
10. Persistence with Mapper 223
11. Persistence with Record 259
12. Localization 282
13. Distributed messaging and Java enterprise integration 293
14. Application testing 314
15. Deployment and scaling 347

#contents#

* preface xiii
* acknowledgments xv
* about this book xvii
* about the author xxi
* about the cover illustration xxii


#preface#

The web has completely revolutionized the way we live our lives—the average person
in the UK now does an average of six Google searches a day. Within the lifetime of one
generation, our entire society has changed, and it continues to be catalyzed by tech-
nology in a very fundamental way. For me, this is the most fascinating thing to observe
and an even more interesting thing to be a part of.

The web development industry has seen sweeping change over the past five or six
years as it has attempted to cope with these new social habitats and behaviors. Proba-
bly one of the most notable changes was the way in which Ruby on Rails altered devel-
opers’ outlook toward building applications and the manner in which they
approached problems. Massive enterprise architecture was out the window and small,
iterative, agile processes became all the rage. At the beginning of 2006, I had been
coding Ruby on Rails for quite some time and had built several large systems with the
Ruby stack. Although I was blown away by the productivity gains that Rails supplied,
taking code to production was a comparative nightmare. I specifically recall Zed
Shaw’s “Rails is a Ghetto” rant and how that was very similar to my own views at the
time. It was then that I started to look for something else, something new.

Before long, I came across Lift, which felt “right” from the very beginning. Scala and
Lift’s elegant fusion of the functional and object-oriented paradigms was a breath of
fresh air when compared to other languages and frameworks. It was great to have all
the security features baked right into a framework, and not have to worry about many
things that typically cause a lot of headaches for developers. These kinds of choices
make a great developer-oriented framework: focusing on removing work from the
developer in a pragmatic and logical way while using as little runtime magic as possible.

Having been involved with Lift from an early stage, seeing it grow and evolve in an
organic fashion has been very rewarding. Even with an intimate understanding of Lift,
writing this book has been far more difficult than I could have ever anticipated. As a
framework, Lift is growing at an exponential rate, and I’ve tried to cover as much of it
as possible and keep it up-to-date with the latest advancements, all while providing you
with a base from which to understand the Lift way of solving problems.

#acknowledgments#

Many people contributed to this book, both in the tangible sense of giving reviews
and feedback, and also in a more intangible regard by giving me the encourage-
ment and positive words to continue with the project, even when there was seemingly
no end in sight.

Throughout the course of writing, I was fortunate enough to receive feedback
from a wide range of sources, but there are several people that I specifically need to
single out and thank. First, I would like to thank Jon-Anders Teigen and Ross
Mellgren for being such amazing sounding boards for ideas, and for often providing
a much-needed sanity check late at night. In addition, I would like to thank the fol-
lowing people from the Scala community who have had an influence on me during
the writing of this book; your blogs, screencasts, and personal discussions have
been a source of inspiration and always remind me there is so much more to learn:
Martin Odersky, Debasish Ghosh, Tony Morris, Rúnar Bjarnason, Mark Harrah,
Jeppe Nejsum Madsen, Jeppe Cramon, Vassil Dichev, Marius Danicu, Derek Chen-
Becker, Jorge Ortiz, and Josh Suereth.

I would also like to thank the companies that use Scala commercially and who have
constructively given their feedback; particular thanks go to Harry Heymann and all
the Foursquare engineers, Daniel Spiewak and David LaPalomento at Novel, Steve
Jenson and Robey Pointer at Twitter, and Jonas Bonér and Viktor Klang at Typesafe.

Writing Lift in Action has without doubt been one of the most difficult things I’ve
ever done, and it’s been a huge personal challenge. During the writing of this book,
I’ve circumnavigated the globe nearly twice, severely broken my hand, learned Italian,
and still found time for a day job. None of those things would have been achievable
without the support of my family and three best friends: Robert Dodge, Paul Dredge,
and Michael Edwards. I simply couldn’t wish for closer friends or a more supportive
family. You guys are awesome.

I’d also like to say thank you to all the amazing people who have contributed to
Lift over the years, and also to David Pollak for founding the project in the first place.
Working on Lift and being a part of the community has truly been one of the high-
lights of my career to date.

The team at Manning has also been a huge, huge help. Working with such a pro-
fessional group of people has been a joy end-to-end. I would specifically like to thank
Michael Stephens for bringing me on board to write this book: his words from our
first call together, “...writing a book is completely survivable,” are something I have
thought about often. Additionally, Katharine Osbourne has been a legendary devel-
opment editor; without her support and consultation, this book would likely have
never made it to completion. Thanks also to the production team of Andy Carroll,
Melody Dolab, Dennis Dalinnik, and Mary Piergies; and to Jon Anders Teigen, Graham
Tackley, and Phil Wells for their careful technical proofread of the manuscript,
shortly before it went to press.

Finally, my thanks to the reviewers who read the manuscript numerous times dur-
ing development and who provided invaluable feedback: Andy Dingley, Paul Stusiak,
Guillaume Belrose, John Tyler, Ted Neward, Andrew Rhine, Jonas Bandi, Tom Jensen,
Ross Mellgren, Richard Williams, Viktor Klang, and Dick Wall.

#about this book#

Lift is an advanced, next-generation framework for building highly interactive and
intuitive web applications. Lift aims to give you a toolkit that scales with both your
needs as a developer and the needs of your applications. Lift includes a range of fea-
tures right out of the box that set it apart from other frameworks in the marketplace:
namely security, statefulness, and performance.

Lift also includes a range of high-level abstractions that make day-to-day development
easy and powerful. In fact, one of the main driving forces during Lift’s evolution has
been to include only features that have an actual production use. You, as the developer,
can be sure that the features you find in Lift are distilled from real production code.

Lift in Action is a step-by-step exploration of the Lift web framework, and it’s split
into two main parts: chapters 1 through 5 introduce Lift and walk you through build-
ing a small, sample application, and then chapters 6 through 15 take a deep dive into
the various parts of Lift, providing you with a deep technical reference to help you get
the best out of Lift.

##Roadmap##

Chapter 1 introduces Lift and sets the scene with regard to how it came into existence.
It also covers the various modules of the framework to give you an appreciation for
the bigger picture.

Chapter 2 shows you how to get up and running with the Scala build tool SBT and
start making your first web application with Lift. This chapter focuses on small, incremen-
tal steps covering the concepts of development that you’ll need in the rest of the book.

Chapter 3, 4, and 5 walk you through the construction of a real-time auction appli-
cation to cover as many different parts of Lift as possible. This includes creating tem-
plates, connecting to a database, and implementing basic AJAX and Comet.

Chapter 6 takes a dive into the practical aspects of Lift WebKit, showing you how to
work with the sophisticated templating system, snippets, and form building through
LiftScreen and Wizard. Additionally, this chapter introduces Lift’s own abstraction
for handling application state in the form of RequestVar and SessionVar. This chap-
ter concludes with an overview of some useful extension modules, known as widgets,
that ship with the Lift distribution.

Chapters 7 focuses on Lift’s SiteMap feature, which allows you to control access
and security for particular resources.

Chapter 8 covers the internal working of Lift’s HTTP pipeline, detailing the various
hooks that are available and demonstrating several techniques for implementing
HTTP services.

Chapter 9 explores Lift’s sophisticated AJAX and Comet support, demonstrating
these technologies in practice by assembling a rock-paper-scissors game. This chapter
also covers Lift’s AJAX abstraction called wiring, which allows you to build chains of
AJAX interaction with ease.

Chapters 10 and 11 cover Lift’s persistence systems, Mapper and Record. Mapper
is an active-record style object-relational mapper (ORM) for interacting with SQL data
stores, whereas Record is store-agnostic and can be used with any backend system
from MySQL to modern NoSQL stores such as MongoDB.

Chapter 12 demonstrates Lift’s localization toolkit for building applications that
can work seamlessly in any language. This includes the various ways in which you can
hook in your ResourceBundles to store localized content.

Chapter 13 is all about the enterprise aspects often associated with web application
development. Technologies such as JPA are prevalent within the enterprise space, and
companies often want to reuse them, so this chapter shows you how to implement JPA
with Lift. Additionally, this chapter covers messaging using the Akka framework.

Chapter 14 covers testing with Lift and shows you some different strategies for test-
ing snippets. More broadly, it demonstrates how to design code that has a higher
degree of decoupling, so your general coding lends itself to testing.

Finally, chapter 15 consolidates all that you’ve read in the book and shows you how
to take your application into production. This includes an overview of various servlet
containers, a demonstration of implementing distributed state handling, and a guide
to monitoring with Twitter Ostrich.

##Who should read this book?

Primarily, this book is intended to demonstrate how to get things done using Lift.
With this in mind, the book is largely slanted toward users who are new to Lift, but
who have experience with other web development frameworks. Lift has its own unique
way of doing things, so some of the concepts may seem foreign, but I make conceptual comparisons to things you may be familiar with from other popular frameworks or libraries to smooth the transition.

If you’re coming to Lift with little or no knowledge of Scala, you should know that
Lift makes use of many Scala language features. This book includes a Scala rough
guide to get you up and running within the context of Lift as quickly as possible.

The book largely assumes that you have familiarity with XML and HTML. Lift’s
templating mechanism is 100 percent based on XML, and although it’s straightfor-
ward to use, it’s useful to have an understanding of structured XML that makes use
of namespaces.

Finally, because Lift is primarily a web framework designed for browser-based
experiences, JavaScript is inevitably part of the application toolchain. Lift includes a
high-level Scala abstraction for building JavaScript expressions, but having an under-
standing of JavaScript and client-side scripting can greatly improve your understand-
ing of the client-server interactions supplied by Lift.

##Code conventions and examples

This book includes a wide range of examples and code illustrations from Scala code
and HTML templates, to plain text configurations for third-party products. Source
code in the listings and in the text is presented in a fixed width font to separate it
from ordinary text. Additionally, Scala types, methods, keywords, and XML-based
markup elements in text are also presented using fixed width font. Where applica-
ble, the code examples explicitly include import statements to clarify which types and
members originate from which packages. In addition, functions and methods have
explicitly annotated types where the result type is not clear.

Although Scala code is typically quite concise, there are some listings that needed
to be reformatted to fit in the available page space in the book. You are encouraged to
download the source code from the online repository, in order to see the sample code
in its original form (https:/
/github.com/timperrett/lift-in-action). In addition to
some reformatting, all the comments have been removed for brevity. You can also
download the code for the examples in the book from the publisher’s website at
www.manning.com/LiftinAction.

Code annotations accompany many of the source code listings, highlighting
important concepts. In some cases, numbered bullets link to explanations in the sub-
sequent text.

Lift itself is released under the Apache Software License, version 2.0, and all the
source code is available online at the official Github repository (https:/
/github.com/
lift/framework/). Reading Lift’s source code can greatly speed your efforts at becom-
ing productive in using Lift for your own applications.

##Author Online

Purchase of Lift in Action includes free access to a private web forum run by Man-
ning Publications where you can make comments about the book, ask technical questions, and receive help from the author and from other users. To access the
forum and subscribe to it, point your web browser to www.manning.com/Liftin
Action or www.manning.com/perrett. This page provides information on how to get
on the forum once you’re registered, what kind of help is available, and the rules of
conduct on the forum.

Manning’s commitment to our readers is to provide a venue where a meaningful
dialog between individual readers and between readers and the author can take place.
It’s not a commitment to any specific amount of participation on the part of the
author, whose contribution to the AO remains voluntary (and unpaid). We suggest
you try asking the author some challenging questions lest his interest stray!

The Author Online forum and the archives of previous discussions will be accessi-
ble from the publisher’s website as long as the 

#about the author#

Timothy Perrett is a technical specialist at a business unit of Xerox Corporation and has been a member of the Lift core team since early 2008. He has a wealth of experience programming in different languages and platforms but has now settled on Scala as his language (and community) of choice for nearly all production activities. Timothy is a
specialist in enterprise integration and automation systems for manufacturing and marketing workflows.

When not speaking at conferences or blogging about
Scala and Lift, Timothy lives by the river in the beautiful
city of Bath, England, where he enjoys socializing with
friends and drinking the local ale.

#about the cover illustration

The figure on the cover of Lift in Action is captioned “A Water Carrier.” The illustra-
tion is taken from a 19th-century edition of Sylvain Maréchal’s four-volume compen-
dium of regional dress customs published in France. Each illustration is finely drawn
and colored by hand. The rich variety of Maréchal’s collection reminds us vividly of
how culturally apart the world’s towns and regions were just 200 years ago. Isolated
from each other, people spoke different dialects and languages. In the streets or in
the countryside, it was easy to identify where they lived and what their trade or station
in life was just by their dress.

Dress codes have changed since then and the diversity by region, so rich at the
time, has faded away. It is now hard to tell apart the inhabitants of different conti-
nents, let alone different towns or regions. Perhaps we have traded cultural diversity
for a more varied personal life—certainly for a more varied and fast-paced technolog-
ical life.

At a time when it is hard to tell one computer book from another, Manning cele-
brates the inventiveness and initiative of the computer business with book covers
based on the rich diversity of regional life of two centuries ago, brought back to life by
Maréchal’s pictures.

#Part 1 Getting started
The first two chapters of this book introduce the Lift framework and dem-
onstrate how you can get everything set up and ready for your first development.

Chapter 1 starts by introducing both Scala and Lift concepts, complete with
high-level explanations and samples. The aim is to give you grounding in what is
a fundamentally different way of thinking. In chapter 2, you’ll be building upon
the basis laid down in chapter 1 by constructing your very first Hello World
application, which will involve the most basic Lift steps. In these chapters, you’ll
see first-hand how Lift leverages a view-first architecture and how easy it is to get up
and running with the Lift web framework.

##Introducing Lift

This chapter covers
* An overview of Scala and Lift
* Lift’s history and design rationale
* An overview of Lift’s structure

Lift is an exciting new way of building web applications that feature rich, highly
interactive user experiences. Lift is built atop the powerful functional program-
ming language Scala, which lends itself to writing exceedingly concise but powerful
code. By leveraging Scala, Lift aims to be expressive and elegant while stressing the
importance of maintainability, scalability, and performance.

The first section of this chapter will introduce Scala and functional program-
ming, including some examples of how the language compares and contrasts to
the more familiar style of imperative programming. The second section intro-
duces Lift and discusses how it differs from other web programming tools avail-
able today. Lift is largely a conceptual departure from many of the traditional
approaches to building web frameworks; specifically, Lift doesn’t have a controller-
dispatched view system and opts for an idea called view first. This chapter dis-
cusses these core design goals and introduces these new ideas at a high level.
Throughout the course of the book, the concepts outlined here will be expanded on in much greater detail, and you’ll see concrete examples to assist you in getting
up to speed.

If you’re reading this book but are new to Scala programming, you can find a rough
guide in appendix A that will show you the ropes and give you a foundation for making
use of Lift. If you want to get serious with Scala, I highly recommend looking at the
other Scala titles published by Manning: Scala in Action by NilanjanRaychaudhuri, and
then the more advanced Scala in Depth by Joshua Suereth.

###1.1What is Scala?

Scala (http:/
/www.scala-lang.org/) is a powerful, hybrid programming language that
incorporates many different concepts into an elegant fusion of language features and
core libraries. Before delving any deeper, let’s just consider how functional program-
ming differs from imperative programming with languages such as Java and Ruby, and
what a functional programming language actually is.

As the name implies, functional programming languages have a single basic idea at
their root: functions. Small units of code are self-contained functions that take type A as
an argument and return type B as a result; this is expressed more directly in Scala
notation: A => B. How this result is achieved is an implementation detail for the most
part; as long as the function yields a value of type B, all is well.

    NOTE Functional programming languages often derive from a mathematical concept called lambda calculus. You can read more about it on Wikipedia:http://en.wikipedia.org/wiki/Lambda_calculus.

With this single concept in mind, it’s possible to boil down complex problems into
these much smaller functions, which can then be composed to tackle the larger prob-
lem at hand; the result of function one is fed into function two and so on, ad infini-
tum. The upshot of such a language design is that once you wrap your head around
this base level of abstraction, many of the language features can be thought of as
higher levels built upon this foundation of basic functions.

Immutability is another trait that marks out functional languages against their
imperative cousins. Specifically, within functional languages the majority of data struc-
tures are immutable. That is to say, once they’re created there is no changing that
instance; rather, you make a copy of that instance and alter your copy, leaving the orig-
inal unaltered.

Martin Odersky, however, wanted to fuse object orientation and functional pro-
gramming together in one unified language that could compile and run on the Java
Virtual Machine (JVM). From here, Scala was born, and consequently Scala compiles
down to Java bytecode, which means that it can run seamlessly on the JVM and inter-
operate with all your existing Java code, completely toll free. In practical terms, this
means that your existing investment in Java isn’t lost; simply call that code directly
from your Scala functions and vice versa.

With this fusion of programming styles, Scala gives you the ability to write code
that’s typically two or three times more concise than the comparative Java code. At the
same time, the Scala code is generally less error-prone due to the heavy use of immuta-
ble data constructs, and it’s also more type-safe than Java, thanks to Scala’s very sophis-
ticated type system.

These are the general concepts that make up functional programming, and upon
which Scala is built. To further exemplify these differences, table 1.1 presents some
examples that illustrate the differences in Scala’s approach compared to imperative
code. If you don’t know Java, don’t worry: the examples here are pretty easy to fol-
low, and the syntax should be fairly readable for anyone familiar with Ruby, PHP, or
similar languages.

Table 1.1
Comparing Java and Scala styles of coding
<table class="table table-bordered">
    <tr>
        <th>Java</th>
        <th>Scala</th>
    </tr>
    <tr>
        <td colspan="2">
			When building class definitions, it’s common to have to build so-called getter and setter methods in
			order to set the values of that instance. This typically creates a lot of noise in the implementation (as
			seen in the Java example that follows). Scala combats this by using the case modifier to automatically
			provision standard functionality into the class definition. Given an instance of the Person case class,
			calling person.name would return the name value.
	    </td>
	</tr>
	<tr>
	     <td>
                    <code>
			public class Person {<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;private int _age;<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;private String _name;<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;public Person(String n, int a){<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_age = a;<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_name = n;<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;String name(){ return _name; }<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;int age(){ return _age; }<br/>
			}<br/>
                    </code>
	    </td>
	    <td>
                   <code>
			case class Person(
			name: String, age: Int)
	        </code> 
              </td>
	</tr>
	<tr>
	    <td colspan="2">
			Most applications at some point have to deal with collections. The examples that follow create an ini-
			tial list and then produce a new list instance that has the same animal names, but in lowercase. The
			Java example on the left creates a list of strings, then creates a second empty list, which then has its
			contents mutated by looping through the first list and calling toLowerCase() on each element. The
			Scala version achieves the exact same result by defining a function that should be executed on each
			element of the list. The Scala version is a lot more concise and does the exact same thing without the
			code noise.
               </td>
	</tr>
	<tr>
	    <td>
                   <code>
			List<String> in = Arrays.asList("Dog", "Cat", "Fish");<br/>
			List<String> out = new ArrayList<String>();<br/>
			for(String i : in){<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;out.add(i.toLowerCase());<br/>
			}
                   </code>
	    </td>
	    <td>
                   <code>
			List("Dog","Cat","Fish").map(_.toLowerCase)
                   </code>
              </td>
         </tr>
</table>

These are just some of the ways in which Scala is a powerful and concise language.
With this broad introduction to Scala and functional programming out of the way,
let’s learn about Lift.

###1.2 What is Lift?

First and foremost, Lift (http:/
/liftweb.net/) is a sophisticated web framework for
building rich, vibrant applications. Secondly, Lift is a set of well-maintained Scala
libraries that are used by many other projects within the broader Scala ecosystem. For
example, the Dispatch HTTP project (http:/
/dispatch.databinder.net/Dispatch.html)
uses Lift’s JSON-handling library extensively for parsing JSON within the context of
standalone HTTP clients. This book, however, really focuses on Lift as a web frame-
work, and it’s here that our story begins.

User behavior online in recent years has changed; people now spend more time
than ever online, and this means they want the way they interact with online services
to be more intuitive and natural. But building such rich applications has proven to be
tough for many developers, and this often results in interfaces and infrastructures that
aren’t really up to the job or user expectations. Lift aims to make building real-time,
highly interactive, and massively scalable applications easier than it has ever been by
supporting advanced features like Comet, which allow you to push data to the browser
when it’s needed, without the user having to make any kind of request for it.

In fact, Lift has been designed from the ground up to support these kinds of sys-
tems better than anything else. Building interactive applications should be fun, acces-
sible, and simple for developers. Lift removes a lot of the burdens that other
frameworks place on developers by mixing together the best ideas in the marketplace
today and adding some unique features to give it a component set and resume that
are unlike any other framework you have likely come across before. Lift brings a lot of
new ideas to the web framework space, and to quote one of the Lift community mem-
bers, “it is not merely an incremental improvement over the status quo; it redefines
the state of the art” (Michael Galpin, Developer, eBay). This departure from tradi-
tional thinking shouldn’t worry you too much, though, because Lift does adopt tried
and tested, well-known concepts, such as convention over configuration, to provide
sensible defaults for all aspects of your application while still giving you a very granu-
lar mechanism for altering that behavior as your project dictates.

One of the areas in which Lift is radically different is in how it dispatches content
for a given request. Unlike other frameworks, such as Rails, Django, Struts, and oth-
ers, Lift doesn’t use the traditional implementation of Model-View-Controller (MVC),
where view dispatching is decided by the controller. Rather, Lift uses an approach
called view first. This is one of the key working concepts within Lift, and it affects
nearly everything most developers are used to when working with a framework. Specif-
ically, it forces you to separate the concerns of content generation from content ren-
dering markup.

In the early days of web development, it was commonplace to intermingle the code
that did business computations with the code that generated the HTML markup for
the user interface. This can be an exceedingly painful long-term strategy, as it makes
maintaining the code problematic and tends to lead to a lot of duplication within any
given project. Conceptually, this is where the MVC pattern should shine, but most
implementations still give the developer the ability to write real code within the pre-
sentation layer to generate dynamic markup; this can add accidental complexity to a
project when the developer unwittingly adds an element of business or process logic
to the presentation layer. It takes programmers who are very disciplined to ensure that
none of the business or application logic seeps into the view. Lift takes the standpoint
that being able to write interpreted code within markup files can lead to all manner of
issues, so it’s outlawed completely; this ensures that your templates contain nothing
but markup.

The view-first idea in Lift really inherits from the broader design goals upon which
Lift was conceived. The following sections will cover these design goals, provide some
details about Lift’s architecture, and give you an overview of the Lift project structure
and community.

###1.2.1 Lift design goals

The design goals upon which Lift was based have remained fairly constant features of
the project. For example, the belief that complex problems, such as security, should
be the responsibility of a framework, and not of the developer, have remained central
ideals. In short, Lift’s design goals are security, conciseness, and performance. Let’s
just take a look at these in closer detail and consider how they impact you when using
Lift as a general-purpose web development framework.

####SECURITY

The web can be a dangerous place for developers who don’t fully appreciate the
potential attacks their applications could come under. There are whole rafts of mali-
cious techniques, including cross-site request forgery (CSRF), cross-site scripting
(XSS), SQL injection, and lots, lots more. Many developers can’t keep up with the con-
stantly changing world of security threats, let alone fully understand how to effectively
and securely protect their applications.

To this end, Lift provides protection against common malicious attacks without
the need for the developer to do any additional work or configuration; Lift just does the
right thing. Whenever you make an AJAX call, use Comet, or even build a simple
form, Lift is right there in the background securing the relevant processing from
attack. Lift typically does this by replacing input names and URLs with opaque GUIDs
that reference specific functions on the server; this essentially completely eliminates
tampering, because there is no way for an attacker to know what the right GUID might
be. This comprehensive security is covered in more detail in chapters 6 and 9.

A nice illustration of Lift’s security credentials is the popular social media site
Foursquare.com, which runs on Lift. Even RasmusLerdorf, the inventor of PHP
and infamous security pundit, was impressed by not being able to find a single
security flaw![1]

####CONCISENESS

If you have spent any time coding in a moderately verbose imperative programming
language like Java, you’ll be more than familiar with the value of conciseness. More-
over, studies have shown that fewer lines of code mean statistically fewer errors, and
overall it’s easier for the brain to comprehend the intended meaning of the code.[2]

Fortunately, Scala assists Lift in many aspects with the goal of conciseness; Scala has
properties, multiple inheritance via traits, and as was touched on earlier, it has a com-
plex type system that can infer types without explicit type annotations, which gives an
overall saving in character tokens per line of code that you write. These are just some
of the ways in which Scala provides a concise API for Lift, and these savings are cou-
pled with the design of the Lift infrastructure, which aims to be short and snappy
where possible, meaning less typing and more doing.

####PERFORMANCE

No matter what type of application you’re building for use on the web, no devel-
oper wants his or her work to be slow. Performance is something that Lift takes very
seriously, and as such, Lift can be very, very quick. As an example, when using the
basic Lift project, you can expect upward of 300 requests per second on a machine
with only 1 GB of RAM and a middle-of-the-road processor. In comparison, you
should see upwards of 2,000 requests per second on a powerful 64-bit machine with
lots of RAM. Whatever your hardware, Lift will give you really great throughput and
blistering performance.

###1.2.2 View-first design

Lift takes a different approach to dispatching views; rather than going via a control-
ler and action, which then select the view template to use based upon the action
itself, Lift’s view-first approach essentially does the complete opposite. It first chooses
the view and then determines what dynamic content needs to be included on that
page. For most people new to Lift, trying not to think in terms of controllers and
actions can be one of the most difficult parts of the transition. During the early
phases of Lift development, there was a conscious choice taken to not implement
MVC-style controller-dispatched views.

In a system where views are dispatched via a controller, you’re essentially tied to
having one primary call to action on that page, but with modern applications, this is
generally not the case. One page may have many items of page furniture that are
equally important.

1. Tweet on Rasmus Lerdorf’s Twitter stream: http://twitter.com/rasmus/status/5929904263
2. For more information, see Gilles Dubochet’s paper, “Computer Code as a Medium for Human Communica-
tion: Are Programming Languages Improving?” (ÉcolePolytechniqueFédérale de Lausanne, 2009). http:/
/
infoscience.epfl.ch/record/138586/files/dubochet2009coco.pdf?version=2

Consider a typical shopping-cart application: the cart itself might feature on multi-
ple pages in a side panel, and a given page could contain a catalog listing with the
mini shopping cart on the left. Both are important, and both need to be rendered
within the same request. It’s at this very point that the MVC model becomes somewhat
muddy, because you’re essentially forced to decide which is the primary bit of page
content. Although there are solutions for such a situation, the concept of having a pri-
mary controller action for that request immediately becomes less pure.

In an effort to counter this problem, Lift opts for the view-first approach.
Although it’s not a pattern you may have heard about before, the three compo-
nent parts are view, snippet, and model—VSM for short. This configuration is illus-
trated in figure 1.1.

Figure 1.1 shows that the view is the initial calling component within this architec-
ture, and this is where the view-first name comes from. Let’s now take a moment to
review each element within the view-first setup.

####VIEW

Within the context of view-first, the view refers primarily to the HTML content served
for a page request. Within any given Lift application, you can have two types of view:
* Template views that bind dynamic content into a predefined markup template
* Generated views in which dynamic content is created, typically with Scala
XML literals

Template views are the most commonly used method of generating view content, and
they require that you have a well-formed XHTML or HTML5 template. It’s important
to note that Lift doesn’t allow you to use view code that’s invalid; this means that
when you’re working with a design team, if their templates are W3C-validate, you
know they’ll work with Lift because the snippet invocations are also part of the
markup. This ensures that designers don’t inadvertently introduce problems by

![figure1.1](figure1.1.png)

** Figure 1.1 A representation of the view-first design. The view invokes the snippets, which in
turn call any other component of the application business logic.**

altering framework-specific code within the template, which is a common problem
with other frameworks.

Generated views are far less common, but sometimes they’re used for quick proto-
typing by using Scala XML literals.

Whichever route you choose to take, the view is the calling component in the
architecture, and as such you can invoke as many different (and completely separate)
snippets as you like from within any given view. This is a core idea within Lift: views
can have more than a single concrete purpose. This helps to minimize the amount of
code duplication within an application and lends itself nicely to a pure model of com-
ponent encapsulation.

####SNIPPET

Snippets are rendering functions that take XML input from within a given page tem-
plate and then transform that input based upon the logic within the snippet function.
For example, when rendering a list of items, the template could contain the markup
for a single item, and then the snippet function would generate the markup for an
entire list of items, perhaps by querying the database and then iterating over the result
set to produce the desired list of items.

There’s a very tight and deliberate coupling between the snippet and the XML out-
put. The snippet isn’t intended to be a controller, such as those found in the MVC
design pattern, nor is it meant to take on any control-flow responsibilities. The snip-
pet’s sole purpose within Lift is to generate dynamic content and mediate changes in
the model back to the view.

####MODEL

In this context, the model is an abstract notion that could represent a number of dif-
ferent things. But for most applications, it will represent a model of persistence or
data (irrespective of the actual process it undertakes to get that data). You ask the
model for value x, and it returns it.

In terms of Lift’s view-first architecture, the snippet will usually call the model for
some values. For example, the snippet might request a list of all the current prod-
ucts in an ecommerce application or ask the model to add an item to the user’s
shopping cart. Whatever the operation, when the model is asked to do something, it
applies whatever business logic it needs to and then responds appropriately to the
snippet. The response could include validation errors that the snippet then renders
to the view.

The actual mechanism for updating the view isn’t important for this discussion
(full page load, AJAX, or some other method). Rather, the model responds and the
response is passed to the view via the snippet.

###1.2.3Community and team

Since the very beginning, the Lift team has always been very diverse; right from the
early days, the team grew in a very organic fashion and has continued to do so over
recent years. Today the Lift core team consists of professional and highly talented individuals not only from all over the world but in a bewildering array of different
market sectors. This gives Lift its vibrancy and overall well-rounded approach.

If you’re new to the Lift community, welcome. It’s a very stimulating place, and
you’ll find that the majority of our team members on the mailing list or hanging out
in IRC will assist you if you get stuck with something. Although I hope that this book
will cover most of the things you might want to know about Lift, there will inevitably
be things you wonder about as you continue to use Lift in your own projects. To that
end, take a look at the resources listed in table 1.2.

Table 1.2 Helpful Lift resources that can be found online

<table class="table table-bordered">
    <tr>
        <th>
            Resource
        </th>
        <th>
            Description
        </th>
    </tr>
    <tr>
        <td>
            Main Lift site
        </td>
        <td>
			http://liftweb.net

			First and foremost is the main Lift homepage. Here you’ll find the latest
			news about Lift, regularly updated as time goes by. This page also has
			links to the source code, the issue tracker, and the wiki.
	    </td>
	</tr>
	<tr>
	    <td>
            Assembla
        </td>
        <td>
			https:/
			/www.assembla.com/wiki/show/liftweb
			Lift moved to the Assembla platform for its wiki and bug-tracking require-
			ments some time ago, and since then it has accumulated a fair amount
			of community-created articles.
		</td>
	</tr>
	<tr>
	    <td>
             Mailing list
        </td>
        <td>
			http://groups.google.com/group/liftweb
			The Google group is the official support channel for Lift. If you have a
			question, you can come to the mailing list and find a friendly, responsive
			community that will be more than happy to answer your questions.
		</td>
	</tr>
	<tr>
	    <td>
			IRC channel
        </td>
        <td>
			#lift on freenode.net
			IRC isn’t as popular as it once was, but you’ll still find some of the Lift
			team hanging out in IRC from time to time.
		</td>
	</tr>
</table>

Now that you’ve had a brief overview of the Lift framework and its evolution, let’s get
into some technical details as to what it can actually do and how it can help you be
more productive and produce higher quality applications.

##1.3 Lift features

During the past three years, the Lift codebase has exploded in size and now features
all manner of functionality, from powerful HTTP request-response control, right
through to enterprise extensions like a monadic transaction API and Actor-based
wrappers around AMQP and XMPP.

Lift is broken down into three top-level subprojects: Lift Core and Lift Web, Lift
Persistence, and Lift Modules. We’ll now take a closer look at each module to give you
an overview of its structure and functionality.

###1.3.1 Lift Core and Lift Web

There are two modules that make up the central framework: Core and Web. The Core
consists of four projects that build to separate libraries that you can use both with and
without Lift’s Web module. The Web module itself builds upon the Core and supplies
Lift’s sophisticated components for building secure and scalable web applications.
The Web module itself is made up of three projects: the base web systems and two
additional projects that provide specialized helpers. Figures 1.2 and 1.3 depict the var-
ious modules and their layering.

Let’s spend some time going through each module in figure 1.2, working from the
bottom up, and discuss their key features and functionality.

####LIFT COMMON

The Lift Common module contains a few base classes that are common to everything
else within Lift. Probably most important of all, Lift Common can be used in projects
that aren’t even web applications. Utilities like Box, Full, and Empty (discussed more in
appendix C) can be exceedingly useful paradigms for application development, even if
the application isn’t using any other part of Lift. Lift Common also includes some base
abstractions that make working with the logging facade SLF4J (http:/
/www.slf4j.org/)
much simpler.

#### LIFT ACTOR

Actors are a model for concurrent programming whereby asynchronous messaging is
used in place of directly working with threads and locks. There are several actor
implementations within the Scala ecosystem, and Lift has its own for the specific
domain of web development. To that end, Lift Actor provides concrete implementa-
tions of the base actor traits that are found within Lift Common (more information
on traits can be found in appendix A).

![figure1.2](figure1.2.png)

Figure 1.2 An illustration of the
module dependencies within the
Lift Core and Web subprojects

####LIFT UTILITIES

During the development of web applications, there are invariably things that can be
reused because there are common idioms in both your and other peoples’ work. Lift
Utilities is a collection of classes, traits, and objects that are designed to save you time
or provide convenience mechanisms for dealing with common paradigms.

A good example is the handling of a time span. Consider the following code,
which defines a pair of TimeSpan instances by way of implicitly converting a regular inte-
ger value into a TimeSpanBuilder:

    10 seconds
    1 hour

Simplistic helpers provide an easy-to-use dialect for handling even complex subjects
like time and duration. This example shows both hour and second helpers, where
both lines result in net.liftweb.util.Helpers.TimeSpan instances.

Here’s another example from the SecurityHelpers trait. It hashes the string
“hello world” with an MD5 algorithm:

    md5("hello world")

Once again, Lift Utilities provides simple-to-use helper methods for common use
cases found within web development—everything from handling time to hashing and
encrypting values and much more.

####LIFT JSON

Probably one of the most popular additions to the Lift Core grouping, Lift JSON pro-
vides an almost standalone package for handling JSON in a highly performant way.
Needless to say, JSON is becoming one of the standards within the emerging online
space, so having great support for it is quite critical for any web framework. The parser
included within Lift JSON is approximately 350 times faster than the JSON parser that’s
included in the Scala standard library—this gives Lift blisteringly fast performance
when serializing back and forth to JSON.

You might be wondering if Lift can only parse JSON quickly, or if it also provides a
means to construct JSON structures. Well, Lift JSON provides a slick domain-specific
language (DSL) for constructing JSON objects.

Let’s take a quick look at a basic example:

    val example = ("name" -> "joe") ~ ("age" -> 35)
    compact(JsonAST.render(example))

This example defines a value in the first line, which represents a JSON structure with
Scala tuples. This structure is then rendered to JSON by using the compact and render
methods from the JsonAST object in the second line. Here’s the output:

    {"name":"joe","age":35}

As you can see, this is a straightforward String and Int construction from the DSL,
but we’ll cover more in-depth details of Lift-JSON in chapter 9. All you need to know
for now is that Lift’s JSON provisioning is fast and very versatile.

####LIFT WEBKIT

Finally we get to the central part of Lift’s web toolkit. The WebKit module is where
Lift holds its entire pipeline, from request processing right down to localization and
template rendering. For all intents and purposes, it’s the main and most important
part of Lift.

Rather than covering the various parts of WebKit in detail here, table 1.3 gives an
extremely brief overview of each of the core components and notes the chapter that
addresses it in more detail.

Table 1.3 Features of Lift WebKit
<table class="table table-bordered">
    <tr>
        <th>Feature</th>
        <th>Description</th>
        <th>Chapter</th>
    </tr>
    <tr>
        <td>Snippet processing</td>
        <td> Snippets are the core of Lift’s rendering and page-display 6
mechanism. </td>
        <td>6</td>
    </tr>
    <tr>
        <td>SiteMap</td>
        <td> SiteMap provides a declarative model for defining security 7
and access control to page resources. </td>
        <td>7</td>
    </tr>
    <tr>
        <td>HTTP abstraction</td>
        <td> Although Lift typically operates within a Java servlet con- 8
tainer, it’s totally decoupled from the underlying imple- 
mentation and can run anywhere. </td>
        <td>8</td>
    </tr>
    <tr>
        <td>Request-response pipeline processing</td>
        <td> The whole request and response pipeline is contained 8
 within WebKit, as are the associated configuration hooks. </td>
        <td>8</td>
    </tr>
    <tr>
        <td>REST components</td>
        <td> REST features allow you to hook into the request-response 8
pipeline early on and deliver stateless or stateful web 
services. </td>
        <td>8</td>
    </tr>
    <tr>
        <td>Secure AJAX</td>
        <td> All the AJAX processing and function mapping infrastruc- 9
ture lives in WebKit. </td>
        <td>9</td>
    </tr>
    <tr>
        <td>Rich Comet support </td>
        <td>The Comet support Lift provides is one of the main fea- 9
tures WebKit offers. </td>
        <td>9</td>
    </tr>
</table>

Although you aren’t familiar with Lift syntax or classes just yet, the following listing
shows an example of a real-time Comet clock to give you a flavor of the kinds of things
contained within the WebKit project.

** Listing 1.1 CometActor clock **

    import scala.xml.Text
    import net.liftweb._,
        util.Schedule, util.Helpers._,
        http.CometActor, http.js.JsCmds.SetHtml
    class Clock extends CometActor {
        Schedule.schedule(this, Tick, 5 seconds)//Schedule redraw
        def render = "#clock_time *" replaceWithtimeNow.toString
        override def lowPriority = {
            case Tick =>
                partialUpdate(SetHtml("clock_time", Text(timeNow.toString)))
                Schedule.schedule(this, Tick, 5 seconds)
        }
    }
With only a few lines of code, you get a clock that pushes the updated time to the
browser, so it will appear as if there’s a live clock in the user’s browser. All the complex-
ities associated with Comet, like connection handling, long polling, and general
plumbing are handled by Lift right out of the box!

###1.3.2 Lift Persistence

The vast majority of applications will at some point want to save their data for later
use. This typically requires some kind of backend storage, and this is where Lift Persis-
tence comes into play. Lift provides you with a number of options for saving your data,
whether it’s a relational database management system (RDBMS) or one of the new
NoSQL solutions.

There are three foundations for persistence, as depicted in figure 1.3; the follow-
ing subsections take a look at these base components.

####LIFT DB AND MAPPER

The vast majority of applications you’ll write will no doubt want to communicate with
an RDBMS of some description, be it MySQL, SQL Server, or one of the other popular
storage systems. When you’re working with Lift, Mapper provides you with a unified
route for persistence.

At a high level, Mapper takes a design direction that’s similar, but not completely
faithful to the Active Record pattern. Mapper provides you with an object-relational
mapping (ORM) implementation that handles all the usual relationship tasks, such as
one-to-one, one-to-many, and many-to-many, so that you don’t have to write SQL join
queries manually. But when you want to write that raw SQL, perhaps for performance
reasons or by preference, you can easily pull back the covers and write SQL directly.

Mapper is unified into many parts of Lift and thus has several advantages out of
the box over other solutions that are available within the Scala ecosystem. Consider
this very basic example of the Mapper API and how it can be used:

    User.find(By(User.email, "foo@bar.com"))
    User.find(By(User.birthday, new Date("Jan 4, 1975")))

![figure1.2](figure1.3.png)
Figure 1.3 Dependency structure of persistence within Lift

Notice that this code is quite readable, even without a prior familiarity with the Map-
per API. For example, in the first line, you want to find a user by their email address.
In the second line, you’re finding a user by their birthday.

####LIFT JPA####

The Java Persistence API is well known in the wider Java space, and, being Java, it can
work right out of the box from the Scala runtime, which shares the common JVM plat-
form. Unfortunately, because JPA is Java, its idiomatic implementation gives it a lot of
mutable data structures and other things that are typically not found within Scala
code—so much so that you might well choose to avoid writing Java-like code when
you’re working with Scala.

To that end, a module was added to Lift’s persistence options to wrap the JPA API
and give it a more idiomatic Scala feel. This module significantly reduces the Java-style
code that you need to write when working with JPA and the associated infrastructure.
This is covered in more detail in chapter 13.

####LIFT RECORD####

This is one of the most interesting aspects of Lift Persistence. Record was designed
with the idea that persistence has common idioms no matter what the actual backend
implementation was doing to interact with the data. Record is a layer that gives users
create, read, update, and delete (CRUD) semantics and a set of helpers for displaying
form fields, operating validation, and so forth. All this without actually providing the
connection to a concrete persistence implementation.

Currently, Record has three backend implementation modules as part of the
framework: one for working with the NoSQL document-orientated storage system
CouchDB (http:/
/couchdb.apache.org/), a second for the NoSQL data store Mon-
goDB (http:/
/www.mongodb.org/), and finally a layer on top of Squeryl (http:/
/
squeryl.org/), the highly sophisticated functional persistence library. These imple-
mentations could not be more different in their underlying mechanics, but they
share this common grounding through Record because of the abstract semantics the
Record infrastructure provides.

At the time of writing, Record is still fairly new. As time goes by, more and more
backend implementations will come online, and perhaps eventually the Mapper
RDBMS code will also be merged with Record.

Here is a sample from the CouchDB implementation that queries a CouchDB
people_by_age JavaScript view:

    Person.queryView("test", "people_by_age", _.key(JInt(30)))

It’s important to note that third-party backend implementations for Record are start-
ing to appear in the Scala ecosystem, and although they aren’t a bona fide part of Lift,
they’re available on github.com and similar services.

#####UNDERSTANDING YOUR USE CASE

As you’ve probably grasped from the framework overview, Lift has many different com-
ponents, some of which overlap in their intended usage. This isn’t a legacy growing
pain, quite the opposite: it’s deliberate. With Lift there’s often more than one way to
reach an acceptable solution, and the factors that dictate which route you take are
largely application-specific and depend on the particular problem domain you’re
working with.

Throughout the course of this book, you’ll see a range of different approaches to
solving problems with Lift. Often the different methods are equally as good, and
which you choose is a matter of preference or style. For example, in chapter 14 you’ll
learn about three different approaches to dependency injection with Scala. These
approaches ultimately achieve very similar results, but depending upon your team,
environment, or application, one may be a better fit than the others. That’s some-
thing you must experiment with for yourself to get a feel for which is going to work
best for you.

The next section discusses some plugins, or modules of ancillary code that are also
available as part of the Lift project. They may help you in building your applications
and getting up to speed with less plumbing.

1.3.3 Lift Modules

Lift Modules is where the project houses all the extensions to the core framework.
Unlike the other groups of subprojects within Lift, the modules are more organic and
have little or no relation to one another. Each module is generally self-contained
regarding the functionality it provides.

Rather than go through each module in detail here, table 1.4 lists the modules
available at the time of writing.

Table 1.4 Available add-on modules supplied as part of Lift
<table class="table table-bordered">
    <tr>
        <th>Module</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>Advanced Message Queue Protocol (AMQP) </td>
        <td>Actor-based wrapper system on AMQP messaging</td>
    </tr>
     <tr>
        <td>Facebook integration</td>
        <td> API integration module for the popular social networking site</td>
    </tr>
     <tr>
        <td>Imaging </td>
        <td>Selection of helper methods for manipulating images</td>
    </tr>
     <tr>
        <td>Java Transaction API (JTA) integration</td>
        <td>Functional style wrapper around the Java Transaction API</td>
    </tr>
     <tr>
        <td>Lift state machine </td>
        <td>State machine tightly integrated with WebKit and Mapper</td>
    </tr>
     <tr>
        <td>OAuth </td>
        <td>Building blocks for creating the server component of OAuth</td>
    </tr>
     <tr>
        <td>OAuth Mapper</td>
        <td> Extension to the OAuth module to use Mapper as
            a backend</td>
    </tr>
     <tr>
        <td>Open ID </td>
        <td>Integration module for using OpenID federated providers</td>
    </tr>
     <tr>
        <td>OSGi </td>
        <td>For those who want to run their Lift app within an OSGI
    container</td>
    </tr>
     <tr>
        <td>PayPal</td>
        <td> Integration module for PayPal PDT and IPN services</td>
    </tr>
     <tr>
        <td>Test kit </td>
        <td>Helpers for writing tests concerning the HTTP operations
        in Lift</td>
    </tr>
     <tr>
        <td>Textile</td>
        <td> Scala implementation of a Textile markup parser</td>
    </tr>
     <tr>
        <td>Widgets </td>
        <td>Selection of helpful widgets (such as calendaring,
       Gravatar, and JavaScript autocomplete)</td>
    </tr>
     <tr>
        <td>XMPP</td>
        <td> Actor-based wrappers around XMPP message
    exchange</td>
    </tr>
</table>
At the time of writing, the available modules are located within a separate Git reposi-
tory (https:/
/github.com/lift/modules), and the community is discussing making the
addition of new modules available to non–core team committers.

If you want to create your own modules, it’s just a case of depending upon the
parts of Lift that you wish to extend. Typically this means creating a small library of
your own that depends upon WebKit and extends or implements the relevant types.
To use this custom module within another application, you only have to provide some
kind of initialization point that will wire the relevant materials into that Lift applica-
tion during startup. That’s all there is to it.

##1.4 Summary
In this chapter, we’ve taken a look at both Scala and Lift and outlined their major con-
ceptual differences from more traditional web frameworks. Lift provides developers
with a very capable toolkit for building interactive, scalable, and highly performant
real-time web applications. These themes really underpin the core design goals of Lift:
security, conciseness, and performance.

As the author of a Lift application, you don’t need to worry about the bulk of
security issues prevalent in other systems: Lift does that for you. The framework is
always there securing element names and URIs without you having to intervene. In
addition to security, idiomatic Lift application code tends to be brief and make use
of powerful Scala language features to create an API that’s readable, maintainable,
and performant.

Lift also differs quite wildly from other frameworks available today in that it
doesn’t implement controller-dispatched views as many MVC frameworks do. Instead,
Lift implements its own view-first architecture that gives you a far purer model for cre-
ating components and modularity within your code. Your rendering logic takes the
form of neat, maintainable functions rather than monolithic stacks of special classes.

Finally, the majority of the code contained within the Lift framework is either run-
ning in production, or is a distillation from live production code. To that end, you can
have absolute confidence in Lift when building your enterprise applications.

Without further ado, let’s move on to setting up your environment and getting
your very first Lift-powered application up and running.
