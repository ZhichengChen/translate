
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Html,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(start: Html,goOn:Html):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.25*/(""" 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <meta name="generator" content="ReText 3.1.4">
        <link rel="stylesheet" media="all" href="/assets/css/b.css" >
        <style>
            body"""),format.raw("""{"""),format.raw/*10.18*/("""
                background: url(/assets/img/bg.png) no-repeat;
                background-position: left 40px;
                background-attachment: fixed;
                background-repeat: repeat;
                background-repeat: no-repeat;
                background-color: #C0DEED;
                padding:10px;
            """),format.raw("""}"""),format.raw/*18.14*/("""
            li"""),format.raw("""{"""),format.raw/*19.16*/("""
                line-height: 30px;
            """),format.raw("""}"""),format.raw/*21.14*/("""
        </style>
        <script src="/assets/js/j.js" type="text/JavaScript">
        </script>
        <title>
            Translate
        </title>
    </head>
    
    <body class="container" style="width:1000px;">
        <div class="navbar navbar-fixed-top">
          <div class="navbar-inner">
            <div class="container">
              Translate
            </div>
          </div>
        </div>
        <div style="background:url(/assets/img/wash-white-30.png);padding:0 10px;">
            <div class="container-fluid" style="background:#fff;padding:200px 20px;">
              <div class="row-fluid" style="padding:70px;">
                <div class="span5">
                    <div class="well" style="padding:8px 0px;">
                        <ul class="nav nav-list">
                            <li class="nav-header">
                                开始翻译
                            </li>
                            """),_display_(Seq[Any](/*47.30*/start)),format.raw/*47.35*/("""
                            <li class="divider">
                                <a href="#">&nbsp;</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="span5">
                    <div class="well" style="padding:8px 0;">
                        <ul class="nav nav-list">
                            <li class="nav-header">
                                完善翻译
                            </li>
                            """),_display_(Seq[Any](/*60.30*/goOn)),format.raw/*60.34*/("""
                            <li class="divider">
                                <a href="#">&nbsp;</a>
                            </li>
                        </ul>
                    </div>
                </div>
              </div>
            </div>
        </div>
    </body>

</html>"""))}
    }
    
    def render(start:Html,goOn:Html) = apply(start,goOn)
    
    def f:((Html,Html) => play.api.templates.Html) = (start,goOn) => apply(start,goOn)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Apr 19 08:17:02 CST 2013
                    SOURCE: /home/ze/git/translate/app/views/index.scala.html
                    HASH: 17319c75b5bd065f88e0262bb3d59e202248869f
                    MATRIX: 508->1|608->24|978->347|1358->680|1421->696|1517->745|2500->1692|2527->1697|3082->2216|3108->2220
                    LINES: 19->1|22->1|31->10|39->18|40->19|42->21|68->47|68->47|81->60|81->60
                    -- GENERATED --
                */
            