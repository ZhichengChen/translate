
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
object trans extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Html,Html,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(content: Html,trans:Html,file:String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.40*/(""" 
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
            textarea,textarea:focus"""),format.raw("""{"""),format.raw/*19.37*/(""" 
            	margin:0; 
            	padding:0;
            	width: 100%; 
            	overflow: hidden;
            """),format.raw("""}"""),format.raw/*24.14*/("""
            textarea.add,textarea.add:focus """),format.raw("""{"""),format.raw/*25.46*/("""
              border:1px solid;
              border-color: rgba(82, 168, 236, 0.8);
              -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
              -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
              box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
              outline: 0;
              outline: thin dotted \9;
              height:150px;
              /* IE6-9 */
            """),format.raw("""}"""),format.raw/*35.14*/("""
        </style>
        <script src="/assets/js/j.js" type="text/JavaScript">
        </script>
		<script type="text/javascript">
            var context = "";
            var height = "";
            var widht = "";
            var strs = new Array();
            var i = 0;
            var file = """"),_display_(Seq[Any](/*45.26*/file)),format.raw/*45.30*/("""" ;
            
            /*
            fun:翻译元素内的html
            in:元素
            out:无
            在$("myDiv")内显示翻译内容
            */
            function trans(obj)"""),format.raw("""{"""),format.raw/*53.33*/("""
                var addr = "http://fanyi.youdao.com/openapi.do?keyfrom=playframewrok&key=1708078032&type=data&doctype=jsonp&version=1.1&q=";
                context = obj.html().replace("\n            ","").replace(new RegExp("\n            ","gm")," ");
                $("#myDiv").html("");
                strs=context.replace(/<[^>]+>/g," ").split(".");
                for (var i=0;i<strs.length;i++)"""),format.raw("""{"""),format.raw/*58.49*/("""
                    if(strs[i]!=null && strs[i]!="")"""),format.raw("""{"""),format.raw/*59.54*/("""
                        if(strs[i].length<50)"""),format.raw("""{"""),format.raw/*60.47*/("""
                            i++;
                            strs[i]=strs[i-1]+strs[i];
                        """),format.raw("""}"""),format.raw/*63.26*/("""
                        $.post(addr+strs[i],function(ret)"""),format.raw("""{"""),format.raw/*64.59*/("""
                            $("#myDiv").append(ret.translation+"。")
                        """),format.raw("""}"""),format.raw/*66.26*/(""", "jsonp");
                    """),format.raw("""}"""),format.raw/*67.22*/("""
                """),format.raw("""}"""),format.raw/*68.18*/("""
            """),format.raw("""}"""),format.raw/*69.14*/("""
            $(document).ready(function() """),format.raw("""{"""),format.raw/*70.43*/("""
                var marginTop=$("#en p:first").offset().top;


                $("#cn p").live("click",function() """),format.raw("""{"""),format.raw/*74.53*/("""
                    if(!$(this).children().is("textarea"))"""),format.raw("""{"""),format.raw/*75.60*/("""
                        context = $(this).html().replace("\n            ","").replace(new RegExp("\n            ","gm")," ");
                        height = $(this).height();
                        width = $(this).width();
                        $(this).html("<textarea class=\"edit\" title="+($(this).index("p"))+" >" + context + "</textarea>");
                        $(this).children("textarea").height(height);
                        $(this).children("textarea").focus();

                        $("#en p:eq("+($(this).index("p"))+")").addClass("alert");
                        $(document).scrollTop($("#en p:eq("+($(this).index("p"))+")").offset().top-marginTop);
                        trans($("#en p:eq("+($(this).index("p"))+")"));
                    """),format.raw("""}"""),format.raw/*86.22*/("""
                """),format.raw("""}"""),format.raw/*87.18*/(""");//单击译文显示可编辑文本框，相应原文高亮，并滚动到相应位置，显示译文
                $("#en p").click(function() """),format.raw("""{"""),format.raw/*88.46*/("""
                    trans($(this));
                """),format.raw("""}"""),format.raw/*90.18*/(""");//单击原文显示译文
                $("#cn textarea.add").live("blur",function() """),format.raw("""{"""),format.raw/*91.63*/("""
                    if(!$.trim($(this).val())=="")"""),format.raw("""{"""),format.raw/*92.52*/("""
                        $("#en p.alert").removeClass("alert");
                        content="<p>"+$(this).val()+"</p>\r\n";
                        $(this).before(content);
                        $.get("/Make/"+$(this).val().replace(new RegExp("#","gm"),"~~~").replace(new RegExp("<","gm"),"~lt~").replace(new RegExp(">","gm"),"~gt~").replace(new RegExp("/","gm"),"~line~")+"/"+file+"/");
                        $(this).val("");
                    """),format.raw("""}"""),format.raw/*98.22*/("""
                """),format.raw("""}"""),format.raw/*99.18*/(""");//增加文本框失去焦点则保存,原文高亮消失
                $("#cn textarea.edit").live("blur",function() """),format.raw("""{"""),format.raw/*100.64*/("""
                    if(!$.trim($(this).val())=="")"""),format.raw("""{"""),format.raw/*101.52*/("""
                        $("#en p.alert").removeClass("alert");
                        $(this).parent("p").html($(this).val());
                        if($(this).val()!=context)"""),format.raw("""{"""),format.raw/*104.52*/("""
                            content=$(this).val()
                            $.get("/Change/"+$(this).attr("title").replace(new RegExp("#","gm"),"~~~").replace(new RegExp("<","gm"),"~lt~").replace(new RegExp(">","gm"),"~gt~").replace(new RegExp("/","gm"),"~line~")+"/"+content+"/"+file+"/"); 
                        """),format.raw("""}"""),format.raw/*107.26*/("""
                        $(this).val("");
                    """),format.raw("""}"""),format.raw/*109.22*/("""
                """),format.raw("""}"""),format.raw/*110.18*/(""");//编辑文本框失去焦点则保存,原文高亮消失
                $("#cn textarea.add").live("focus",function() """),format.raw("""{"""),format.raw/*111.64*/("""
                    $("#en p:eq("+($("#cn p").size()-1)+")").addClass("alert");
                    $(document).scrollTop($("#en p:eq("+($("#cn p").size()-1)+")").offset().top-marginTop);
                    trans($("#en p:eq("+($("#cn p").size()-1)+")"));
                """),format.raw("""}"""),format.raw/*115.18*/(""");//单击增加的文本域相应原文高亮，滚动到相应位置，显示译文
                $("#cn textarea").live("keypress",function() """),format.raw("""{"""),format.raw/*116.63*/("""
                    if(event.which==13)"""),format.raw("""{"""),format.raw/*117.41*/("""
                        content="<p>"+$(this).val()+"</p>\r\n";
                        $(this).before(content);
                        $.get("/Make/"+$(this).val().replace(new RegExp("#","gm"),"~~~").replace(new RegExp("<","gm"),"~lt~").replace(new RegExp(">","gm"),"~gt~").replace(new RegExp("/","gm"),"~line~")+"/"+file+"/");
                        $(this).val("");
                        $(document).scrollTop($("#en p")[$("#cn p").size()-1].offsetTop-marginTop);
                        $("#en p.alert").removeClass("alert");
                        $("#en p:eq("+($("#cn p").size()-1)+")").addClass("alert");
                        $("#en p:eq("+($("#cn p").size()-1)+")").attr("title","up");
                        trans($("#en p:eq("+($("#cn p").size()-1)+")"));
                    """),format.raw("""}"""),format.raw/*127.22*/("""
                """),format.raw("""}"""),format.raw/*128.18*/(""");
                $(window).scroll(function()"""),format.raw("""{"""),format.raw/*129.45*/("""
                    $("#en p").each(function ()"""),format.raw("""{"""),format.raw/*130.49*/("""
                        if($(this).offset().top-$(document).scrollTop()<0)
                            $(this).attr("title","up");
                        else
                            $(this).removeAttr("title");
                    """),format.raw("""}"""),format.raw/*135.22*/(""");//如果外文的p滚动出上界，增加title=up属性
                    if($("#cn p").size()>($("[title='up']").size()) && $("[title='up']").size()>0)"""),format.raw("""{"""),format.raw/*136.100*/("""
                        $("#cn").scrollTop($("#en p")[$("[title='up']").size()-1].offsetTop-$("#en p:first").offset().top);
                    """),format.raw("""}"""),format.raw/*138.22*/("""else if($("#cn p").size()<=($("[title='up']").size()) && $("#cn textarea.add").size()<1)"""),format.raw("""{"""),format.raw/*138.111*/("""
                        $("#cn div").append("<textarea class='add'></textarea><p style='height:30px'></p>");
                        if($("[title='up']").size()>0)
                            $("#cn").scrollTop($("#en p")[$("[title='up']").size()-1].offsetTop-$("#en p:first").offset().top);
                    """),format.raw("""}"""),format.raw/*142.22*/("""//译文和外文一起滚动,如果译文没有了，译文下面增加文本域
                        
                """),format.raw("""}"""),format.raw/*144.18*/(""");
            """),format.raw("""}"""),format.raw/*145.14*/(""");
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
            <div style="position:fixed;width:980px;bottom:0;background:#f9f9f9;">
            	<div class="alert alert-info"  style="min-height:20px;" id="myDiv">
            		You dao~
            	</div>
                <div style="overflow: hidden;height:200px;padding:0 20px;" id="cn">
                    <div>
                        """),_display_(Seq[Any](/*167.26*/trans)),format.raw/*167.31*/("""
                    </div>
                </div>
            </div>
            <div id="en" style="margin-bottom:255px;background:#fff;padding:0 20px;">
                <br><br>
                """),_display_(Seq[Any](/*173.18*/content)),format.raw/*173.25*/("""
                <p>
                    <br/><br/><br/><br/><br/>
                </p>
                <p>
                    <br/><br/><br/><br/><br/>
                </p>
                <p>
                    <br/><br/><br/><br/><br/>
                </p>
                <p>
                    <br/><br/><br/><br/><br/>
                </p>
                <p>
                    <br/><br/><br/><br/><br/>
                </p>
            </div>
        </div>
    </body>

</html>"""))}
    }
    
    def render(content:Html,trans:Html,file:String) = apply(content,trans,file)
    
    def f:((Html,Html,String) => play.api.templates.Html) = (content,trans,file) => apply(content,trans,file)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Apr 20 17:06:26 CST 2013
                    SOURCE: /home/ze/git/translate/app/views/trans.scala.html
                    HASH: 02d8d3c4f1dcbfbda4148ecf08380c3d70779262
                    MATRIX: 515->1|630->39|1000->362|1380->695|1464->732|1632->853|1725->899|2294->1421|2633->1724|2659->1728|2879->1901|3333->2308|3434->2362|3528->2409|3689->2523|3795->2582|3936->2676|4016->2709|4081->2727|4142->2741|4232->2784|4395->2900|4502->2960|5320->3731|5385->3749|5515->3832|5616->3886|5738->3961|5837->4013|6340->4469|6405->4487|6540->4574|6640->4626|6868->4806|7236->5126|7347->5189|7413->5207|7548->5294|7871->5569|8013->5663|8102->5704|8948->6502|9014->6520|9109->6567|9206->6616|9493->6855|9670->6983|9864->7129|10002->7218|10364->7532|10484->7604|10548->7620|11348->8383|11376->8388|11611->8586|11641->8593
                    LINES: 19->1|22->1|31->10|39->18|40->19|45->24|46->25|56->35|66->45|66->45|74->53|79->58|80->59|81->60|84->63|85->64|87->66|88->67|89->68|90->69|91->70|95->74|96->75|107->86|108->87|109->88|111->90|112->91|113->92|119->98|120->99|121->100|122->101|125->104|128->107|130->109|131->110|132->111|136->115|137->116|138->117|148->127|149->128|150->129|151->130|156->135|157->136|159->138|159->138|163->142|165->144|166->145|188->167|188->167|194->173|194->173
                    -- GENERATED --
                */
            