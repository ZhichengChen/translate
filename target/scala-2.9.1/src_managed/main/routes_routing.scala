// @SOURCE:/home/ze/git/translate/conf/routes
// @HASH:116319315491324cfecea1365dde0c7056c1bc52
// @DATE:Sat Apr 20 11:59:11 CST 2013

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart("/"))))
                    

// @LINE:7
val controllers_Application_change1 = Route("GET", PathPattern(List(StaticPart("/change/"),DynamicPart("content", """[^/]+"""),StaticPart("/"))))
                    

// @LINE:8
val controllers_Application_trans2 = Route("GET", PathPattern(List(StaticPart("/trans/"),DynamicPart("content", """[^/]+"""),StaticPart("/"))))
                    

// @LINE:9
val controllers_Application_insert3 = Route("GET", PathPattern(List(StaticPart("/insert/"),DynamicPart("id", """[^/]+"""),StaticPart("/"),DynamicPart("content", """[^/]+"""),StaticPart("/"),DynamicPart("title", """[^/]+"""),StaticPart("/"))))
                    

// @LINE:10
val controllers_Application_Make4 = Route("GET", PathPattern(List(StaticPart("/Make/"),DynamicPart("content", """[^/]+"""),StaticPart("/"),DynamicPart("title", """[^/]+"""),StaticPart("/"))))
                    

// @LINE:11
val controllers_Application_Change5 = Route("GET", PathPattern(List(StaticPart("/Change/"),DynamicPart("id", """[^/]+"""),StaticPart("/"),DynamicPart("content", """[^/]+"""),StaticPart("/"),DynamicPart("title", """[^/]+"""),StaticPart("/"))))
                    

// @LINE:12
val controllers_Application_del6 = Route("GET", PathPattern(List(StaticPart("/Del/"),DynamicPart("id", """[^/]+"""),StaticPart("/"),DynamicPart("title", """[^/]+"""),StaticPart("/"))))
                    

// @LINE:15
val controllers_Assets_at7 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Application.index"""),("""GET""","""/change/$content<[^/]+>/""","""controllers.Application.change(content:String)"""),("""GET""","""/trans/$content<[^/]+>/""","""controllers.Application.trans(content:String)"""),("""GET""","""/insert/$id<[^/]+>/$content<[^/]+>/$title<[^/]+>/""","""controllers.Application.insert(id:String, content:String, title:String)"""),("""GET""","""/Make/$content<[^/]+>/$title<[^/]+>/""","""controllers.Application.Make(content:String, title:String)"""),("""GET""","""/Change/$id<[^/]+>/$content<[^/]+>/$title<[^/]+>/""","""controllers.Application.Change(id:String, content:String, title:String)"""),("""GET""","""/Del/$id<[^/]+>/$title<[^/]+>/""","""controllers.Application.del(id:String, title:String)"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil))
   }
}
                    

// @LINE:7
case controllers_Application_change1(params) => {
   call(params.fromPath[String]("content", None)) { (content) =>
        invokeHandler(_root_.controllers.Application.change(content), HandlerDef(this, "controllers.Application", "change", Seq(classOf[String])))
   }
}
                    

// @LINE:8
case controllers_Application_trans2(params) => {
   call(params.fromPath[String]("content", None)) { (content) =>
        invokeHandler(_root_.controllers.Application.trans(content), HandlerDef(this, "controllers.Application", "trans", Seq(classOf[String])))
   }
}
                    

// @LINE:9
case controllers_Application_insert3(params) => {
   call(params.fromPath[String]("id", None), params.fromPath[String]("content", None), params.fromPath[String]("title", None)) { (id, content, title) =>
        invokeHandler(_root_.controllers.Application.insert(id, content, title), HandlerDef(this, "controllers.Application", "insert", Seq(classOf[String], classOf[String], classOf[String])))
   }
}
                    

// @LINE:10
case controllers_Application_Make4(params) => {
   call(params.fromPath[String]("content", None), params.fromPath[String]("title", None)) { (content, title) =>
        invokeHandler(_root_.controllers.Application.Make(content, title), HandlerDef(this, "controllers.Application", "Make", Seq(classOf[String], classOf[String])))
   }
}
                    

// @LINE:11
case controllers_Application_Change5(params) => {
   call(params.fromPath[String]("id", None), params.fromPath[String]("content", None), params.fromPath[String]("title", None)) { (id, content, title) =>
        invokeHandler(_root_.controllers.Application.Change(id, content, title), HandlerDef(this, "controllers.Application", "Change", Seq(classOf[String], classOf[String], classOf[String])))
   }
}
                    

// @LINE:12
case controllers_Application_del6(params) => {
   call(params.fromPath[String]("id", None), params.fromPath[String]("title", None)) { (id, title) =>
        invokeHandler(_root_.controllers.Application.del(id, title), HandlerDef(this, "controllers.Application", "del", Seq(classOf[String], classOf[String])))
   }
}
                    

// @LINE:15
case controllers_Assets_at7(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                