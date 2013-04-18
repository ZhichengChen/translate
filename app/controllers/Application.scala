package controllers

import play.api._
import play.api.mvc._
import play.api.templates._

import scalax.io._
import scala.io._
import java.io._

import org.tautua.markdownpapers._
import org.tautua.markdownpapers.ast._
import org.tautua.markdownpapers.parser._

object Application extends Controller {

  
  
  var title = ""

  def markdown(file:String):String={

    val fis = new FileInputStream(new File(file))
    val in = new InputStreamReader(fis, "UTF8")
    val out = new StringWriter()
    val parser = new Parser(in)
    val emitter=new HtmlEmitter(out)
    val document = parser.parse()

    document.accept(emitter)

    out.toString

  }
  
  def index = Action {

    var start = ""
    var goOn = ""

    var files=new File("public/trans/in/")

    for(filelist<-files.list()){
      start += "<li><a href='trans/"+filelist.replace(".md","")+"/' >"+filelist.replace(".md","")+"</a></li>"
    }

    files = new File("public/trans/out/")

    for(filelist<-files.list()){
      goOn += "<li><a href='change/"+filelist.replace(".md","")+"/' >"+filelist.replace(".md","")+"</a></li>"
    }

    Ok(views.html.index(new Html(start),new Html(goOn)))
  }

  def change(file:String) = Action {
    var contents=new Array[String](1000)
    var i = 0
    var tempLine = ""
    var isList = false
    for (line <- Source.fromFile("public/trans/in/"+file+".md").getLines){
      if(line.replaceAll("\\s*", "")=="" || line.startsWith("*") || isList){
        if(!isList && tempLine!=""){
          contents(i) = tempLine
          tempLine = ""
          i+=1
        }
        if(line.startsWith("*")){
          if(!isList){
            isList = true
            tempLine = line + " "        
          }else{
            contents(i) = tempLine
            tempLine = line + " "
            i+=1
          }
        }
        if(isList && line.replaceAll("\\s*", "")==""){
          contents(i) = tempLine
          tempLine = ""
          i+=1
          isList = false
        }  
        if(isList && !line.startsWith("*") && line.replaceAll("\\s*", "")!="")
          tempLine += line + " "
        
      }else{
        tempLine += line + " "
      }
    }
    var html = "<table class='table'>"
    var size = i
    i = 0
    for (line <- Source.fromFile("public/trans/out/"+file+".md").getLines){
      if(line.replaceAll("\\s*", "")!=""){
        html += "<tr><td width='40%'><p>"
        html += contents(i)
        html += "</p></td><td width='5%' class='right'><span>➺</span></td><td class='trans' ><p>"
        html += line
        html += "</p></td><td width='5%' class='del'><span>✘</span></td></tr>"
        i+=1
      }
    }
    if(size>(i+1)){
      for(j <- i to (size-1)){
        html += "<tr><td width='40%'><p>"
        html += contents(i)
        html += "</p></td><td width='5%' class='right'><span>➺</span></td><td class='trans' >"
        html += "</td><td width='5%' class='del'><span>✘</span></td></tr>"
        i+=1
      }
    }
    html += "</table>"
    Ok(views.html.change(new Html(html),file))
  }  

  def trans(content:String) = Action {
    title = content
    
    val origin = new Html(markdown("public/trans/in/"+content+".md"))

    val trans = if(new File("public/trans/out/"+content+".md").exists())
                  new Html(markdown("public/trans/out/"+content+".md"))
                else
                  new Html("")
    Ok(views.html.trans(origin,trans))
  }

  def Make(content:String) = Action {
    val output:Output = scalax.io.Resource.fromFile("public/trans/out/"+content+".md")
    output.write(content+"\r\n\r\n")
    Ok("Success")
  }

  def insert(id:String,content:String,file:String) = Action {
    var contents=new Array[String](1000)
    var i=0

    for (line <- Source.fromFile("public/trans/out/"+file+".md").getLines){
      contents(i)=line
      i+=1
    }
  
    contents(id.toInt)=content.replaceAll("~~~","#")++"\r\n\r\n"+contents(id.toInt)

    if(new File("public/trans/out/"+file+".md").exists())
      new File("public/trans/out/"+file+".md").delete()

    val output:Output =  scalax.io.Resource.fromFile("public/trans/out/"+file+".md")

    var isList = false

    for(con <- contents){
      if(con!=null && con!="" && con.replaceAll("\\s*", "")!=""){
        if(con.startsWith("*")){
          if(!isList)
            isList = true
          output.write(con+"\r\n")
        }else{
          if(isList){
            output.write("\r\n")
            isList = false
          }
          output.write(con+"\r\n");
        }
      }
    }

    Ok("Success")

  }

  def del(id:String,file:String) = Action {
    var contents=new Array[String](1000)
    var i=0

    for (line <- Source.fromFile("public/trans/out/"+file+".md").getLines){
      contents(i)=line
      i+=1
    }

    if(new File("public/trans/out/"+file+".md").exists())
      new File("public/trans/out/"+file+".md").delete()

    val output:Output =  scalax.io.Resource.fromFile("public/trans/out/"+file+".md")

    i=0
    var isList = false

    for(con <- contents){
      if(con!=null && con!="" && con.replaceAll("\\s*", "")!=""){
        if(id.toInt!=i){
          if(con.startsWith("*")){
            if(!isList)
              isList = true
            output.write(con+"\r\n")
          }else{
            if(isList){
              output.write("\r\n")
              isList = false
            }
            output.write(con+"\r\n\r\n");
          }
        }
        i += 1
      }
    }

    Ok("Success")

  }

  def Change(id:String,content:String,file:String) = Action {
    var contents=new Array[String](1000)
  	var i=0

  	for (line <- Source.fromFile("public/trans/out/"+file+".md").getLines){
      if(line.replaceAll("\\s*", "")!=""){
    		contents(i)=line
    		i+=1
      }
  	}
	
  	contents(id.toInt)=content.replaceAll("~~~","#")

  	if(new File("public/trans/out/"+file+".md").exists())
  		new File("public/trans/out/"+file+".md").delete()

  	val output:Output =  scalax.io.Resource.fromFile("public/trans/out/"+file+".md")

    var isList = false

  	for(con <- contents){
  		if(con!=null){
        if(con.startsWith("*")){
          if(!isList)
            isList = true
          output.write(con+"\r\n")
        }else{
          if(isList){
            output.write("\r\n")
            isList = false
          }
          output.write(con+"\r\n");
        }
      }
        
  	}

  	Ok("Success")
  }
  
}
