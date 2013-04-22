// @SOURCE:/home/ze/git/translate/conf/routes
// @HASH:116319315491324cfecea1365dde0c7056c1bc52
// @DATE:Sat Apr 20 11:59:11 CST 2013

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    


 
// @LINE:11
def Change(id:String, content:String, title:String) = {
   Call("GET", "/Change/" + implicitly[PathBindable[String]].unbind("id", id) + "/" + implicitly[PathBindable[String]].unbind("content", content) + "/" + implicitly[PathBindable[String]].unbind("title", title) + "/")
}
                                                        
 
// @LINE:10
def Make(content:String, title:String) = {
   Call("GET", "/Make/" + implicitly[PathBindable[String]].unbind("content", content) + "/" + implicitly[PathBindable[String]].unbind("title", title) + "/")
}
                                                        
 
// @LINE:9
def insert(id:String, content:String, title:String) = {
   Call("GET", "/insert/" + implicitly[PathBindable[String]].unbind("id", id) + "/" + implicitly[PathBindable[String]].unbind("content", content) + "/" + implicitly[PathBindable[String]].unbind("title", title) + "/")
}
                                                        
 
// @LINE:12
def del(id:String, title:String) = {
   Call("GET", "/Del/" + implicitly[PathBindable[String]].unbind("id", id) + "/" + implicitly[PathBindable[String]].unbind("title", title) + "/")
}
                                                        
 
// @LINE:6
def index() = {
   Call("GET", "/")
}
                                                        
 
// @LINE:8
def trans(content:String) = {
   Call("GET", "/trans/" + implicitly[PathBindable[String]].unbind("content", content) + "/")
}
                                                        
 
// @LINE:7
def change(content:String) = {
   Call("GET", "/change/" + implicitly[PathBindable[String]].unbind("content", content) + "/")
}
                                                        

                      
    
}
                            

// @LINE:15
class ReverseAssets {
    


 
// @LINE:15
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            
}
                    


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    


 
// @LINE:11
def Change = JavascriptReverseRoute(
   "controllers.Application.Change",
   """
      function(id,content,title) {
      return _wA({method:"GET", url:"/Change/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("content", content) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("title", title) + "/"})
      }
   """
)
                                                        
 
// @LINE:10
def Make = JavascriptReverseRoute(
   "controllers.Application.Make",
   """
      function(content,title) {
      return _wA({method:"GET", url:"/Make/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("content", content) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("title", title) + "/"})
      }
   """
)
                                                        
 
// @LINE:9
def insert = JavascriptReverseRoute(
   "controllers.Application.insert",
   """
      function(id,content,title) {
      return _wA({method:"GET", url:"/insert/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("content", content) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("title", title) + "/"})
      }
   """
)
                                                        
 
// @LINE:12
def del = JavascriptReverseRoute(
   "controllers.Application.del",
   """
      function(id,title) {
      return _wA({method:"GET", url:"/Del/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("title", title) + "/"})
      }
   """
)
                                                        
 
// @LINE:6
def index = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"/"})
      }
   """
)
                                                        
 
// @LINE:8
def trans = JavascriptReverseRoute(
   "controllers.Application.trans",
   """
      function(content) {
      return _wA({method:"GET", url:"/trans/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("content", content) + "/"})
      }
   """
)
                                                        
 
// @LINE:7
def change = JavascriptReverseRoute(
   "controllers.Application.change",
   """
      function(content) {
      return _wA({method:"GET", url:"/change/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("content", content) + "/"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:15
class ReverseAssets {
    


 
// @LINE:15
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    


 
// @LINE:11
def Change(id:String, content:String, title:String) = new play.api.mvc.HandlerRef(
   controllers.Application.Change(id, content, title), HandlerDef(this, "controllers.Application", "Change", Seq(classOf[String], classOf[String], classOf[String]))
)
                              
 
// @LINE:10
def Make(content:String, title:String) = new play.api.mvc.HandlerRef(
   controllers.Application.Make(content, title), HandlerDef(this, "controllers.Application", "Make", Seq(classOf[String], classOf[String]))
)
                              
 
// @LINE:9
def insert(id:String, content:String, title:String) = new play.api.mvc.HandlerRef(
   controllers.Application.insert(id, content, title), HandlerDef(this, "controllers.Application", "insert", Seq(classOf[String], classOf[String], classOf[String]))
)
                              
 
// @LINE:12
def del(id:String, title:String) = new play.api.mvc.HandlerRef(
   controllers.Application.del(id, title), HandlerDef(this, "controllers.Application", "del", Seq(classOf[String], classOf[String]))
)
                              
 
// @LINE:6
def index() = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq())
)
                              
 
// @LINE:8
def trans(content:String) = new play.api.mvc.HandlerRef(
   controllers.Application.trans(content), HandlerDef(this, "controllers.Application", "trans", Seq(classOf[String]))
)
                              
 
// @LINE:7
def change(content:String) = new play.api.mvc.HandlerRef(
   controllers.Application.change(content), HandlerDef(this, "controllers.Application", "change", Seq(classOf[String]))
)
                              

                      
    
}
                            

// @LINE:15
class ReverseAssets {
    


 
// @LINE:15
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            
}
                    
                