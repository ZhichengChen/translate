
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
object trans extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Html,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(content: Html,trans:Html):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.28*/(""" 
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
            
            /*
            fun:翻译元素内的html
            in:元素
            out:无
            在$("myDiv")内显示翻译内容
            */
            function trans(obj)"""),format.raw("""{"""),format.raw/*52.33*/("""
                var addr = "http://fanyi.youdao.com/openapi.do?keyfrom=playframewrok&key=1708078032&type=data&doctype=jsonp&version=1.1&q=";
                context = obj.html().replace("\n            ","").replace(new RegExp("\n            ","gm")," ");
                $("#myDiv").html("");
                strs=context.split(".");
                for (var i=0;i<strs.length;i++)"""),format.raw("""{"""),format.raw/*57.49*/("""
                    if(strs[i]!=null && strs[i]!="")"""),format.raw("""{"""),format.raw/*58.54*/("""
                        if(strs[i].length<50)"""),format.raw("""{"""),format.raw/*59.47*/("""
                            i++;
                            strs[i]=strs[i-1]+strs[i];
                        """),format.raw("""}"""),format.raw/*62.26*/("""
                        $.post(addr+strs[i],function(ret)"""),format.raw("""{"""),format.raw/*63.59*/("""
                            $("#myDiv").append(ret.translation)
                        """),format.raw("""}"""),format.raw/*65.26*/(""", "jsonp");
                    """),format.raw("""}"""),format.raw/*66.22*/("""
                """),format.raw("""}"""),format.raw/*67.18*/("""
            """),format.raw("""}"""),format.raw/*68.14*/("""
            $(document).ready(function() """),format.raw("""{"""),format.raw/*69.43*/("""
                var marginTop=$("#en p:first").offset().top;


                $("#cn p").live("click",function() """),format.raw("""{"""),format.raw/*73.53*/("""
                    if(!$(this).children().is("textarea"))"""),format.raw("""{"""),format.raw/*74.60*/("""
                        context = $(this).html().replace("\n            ","").replace(new RegExp("\n            ","gm")," ");
                        height = $(this).height();
                        width = $(this).width();
                        $(this).html("<textarea class=\"edit\" title="+($(this).index("p"))+" >" + context + "</textarea>");
                        $(this).children("textarea").height(height);
                        $(this).children("textarea").focus();

                        $("#en p:eq("+($(this).index("p"))+")").addClass("alert");
                        $(document).scrollTop($("#en p:eq("+($(this).index("p"))+")").offset().top-marginTop);
                        trans($("#en p:eq("+($(this).index("p"))+")"));
                    """),format.raw("""}"""),format.raw/*85.22*/("""
                """),format.raw("""}"""),format.raw/*86.18*/(""");//单击译文显示可编辑文本框，相应原文高亮，并滚动到相应位置，显示译文
                $("#en p").click(function() """),format.raw("""{"""),format.raw/*87.46*/("""
                    trans($(this));
                """),format.raw("""}"""),format.raw/*89.18*/(""");//单击原文显示译文
                $("#cn textarea.add").live("blur",function() """),format.raw("""{"""),format.raw/*90.63*/("""
                    if(!$.trim($(this).val())=="")"""),format.raw("""{"""),format.raw/*91.52*/("""
                        $("#en p.alert").removeClass("alert");
                        content="<p>"+$(this).val()+"</p>\r\n";
                        $(this).before(content);
                        $.get("/Make/"+content);
                        $(this).val("");
                    """),format.raw("""}"""),format.raw/*97.22*/("""
                """),format.raw("""}"""),format.raw/*98.18*/(""");//增加文本框失去焦点则保存,原文高亮消失
                $("#cn textarea.edit").live("blur",function() """),format.raw("""{"""),format.raw/*99.64*/("""
                    if(!$.trim($(this).val())=="")"""),format.raw("""{"""),format.raw/*100.52*/("""
                        $("#en p.alert").removeClass("alert");
                        $(this).parent("p").html($(this).val());
                        if($(this).val()!=context)"""),format.raw("""{"""),format.raw/*103.52*/("""
                            content=$(this).val()
                            $.get("/Change/"+$(this).attr("title")+"/"+content+"/"); 
                        """),format.raw("""}"""),format.raw/*106.26*/("""
                        $(this).val("");
                    """),format.raw("""}"""),format.raw/*108.22*/("""
                """),format.raw("""}"""),format.raw/*109.18*/(""");//编辑文本框失去焦点则保存,原文高亮消失
                $("#cn textarea.add").live("focus",function() """),format.raw("""{"""),format.raw/*110.64*/("""
                    $("#en p:eq("+($("#cn p").size()-1)+")").addClass("alert");
                    $(document).scrollTop($("#en p:eq("+($("#cn p").size()-1)+")").offset().top-marginTop);
                    trans($("#en p:eq("+($("#cn p").size()-1)+")"));
                """),format.raw("""}"""),format.raw/*114.18*/(""");//单击增加的文本域相应原文高亮，滚动到相应位置，显示译文
                $("#cn textarea").live("keypress",function() """),format.raw("""{"""),format.raw/*115.63*/("""
                    if(event.which==13)"""),format.raw("""{"""),format.raw/*116.41*/("""
                        content="<p>"+$(this).val()+"</p>\r\n";
                        $(this).before(content);
                        //$.ajax(content);
                        $(this).val("");
                        $(document).scrollTop($("#en p")[$("#cn p").size()-1].offsetTop-marginTop);
                        $("#en p.alert").removeClass("alert");
                        $("#en p:eq("+($("#cn p").size()-1)+")").addClass("alert");
                        $("#en p:eq("+($("#cn p").size()-1)+")").attr("title","up");
                        trans($("#en p:eq("+($("#cn p").size()-1)+")"));
                    """),format.raw("""}"""),format.raw/*126.22*/("""
                """),format.raw("""}"""),format.raw/*127.18*/(""");
                $(window).scroll(function()"""),format.raw("""{"""),format.raw/*128.45*/("""
                    $("#en p").each(function ()"""),format.raw("""{"""),format.raw/*129.49*/("""
                        if($(this).offset().top-$(document).scrollTop()<0)
                            $(this).attr("title","up");
                        else
                            $(this).removeAttr("title");
                    """),format.raw("""}"""),format.raw/*134.22*/(""");//如果外文的p滚动出上界，增加title=up属性
                    if($("#cn p").size()>($("[title='up']").size()) && $("[title='up']").size()>0)"""),format.raw("""{"""),format.raw/*135.100*/("""
                        $("#cn").scrollTop($("#en p")[$("[title='up']").size()-1].offsetTop-$("#en p:first").offset().top);
                    """),format.raw("""}"""),format.raw/*137.22*/("""else if($("#cn p").size()<=($("[title='up']").size()) && $("#cn textarea.add").size()<1)"""),format.raw("""{"""),format.raw/*137.111*/("""
                        $("#cn div").append("<textarea class='add'></textarea><p style='height:30px'></p>");
                        $("#cn").scrollTop($("#en p")[$("[title='up']").size()-1].offsetTop-$("#en p:first").offset().top);
                    """),format.raw("""}"""),format.raw/*140.22*/("""//译文和外文一起滚动,如果译文没有了，译文下面增加文本域
                        
                """),format.raw("""}"""),format.raw/*142.18*/(""");
            """),format.raw("""}"""),format.raw/*143.14*/(""");
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
                        """),_display_(Seq[Any](/*165.26*/trans)),format.raw/*165.31*/("""
                    </div>
                </div>
            </div>
            <div id="en" style="margin-bottom:255px;background:#fff;padding:0 20px;">
                <br><br>
                """),_display_(Seq[Any](/*171.18*/content)),format.raw/*171.25*/("""
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
    
    def render(content:Html,trans:Html) = apply(content,trans)
    
    def f:((Html,Html) => play.api.templates.Html) = (content,trans) => apply(content,trans)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Apr 19 08:17:02 CST 2013
                    SOURCE: /home/ze/git/translate/app/views/trans.scala.html
                    HASH: c36345d4a5d218a7c03842e768939fa9f1646d43
                    MATRIX: 508->1|611->27|981->350|1361->683|1445->720|1613->841|1706->887|2275->1409|2769->1856|3199->2239|3300->2293|3394->2340|3555->2454|3661->2513|3798->2603|3878->2636|3943->2654|4004->2668|4094->2711|4257->2827|4364->2887|5182->3658|5247->3676|5377->3759|5478->3813|5600->3888|5699->3940|6034->4228|6099->4246|6233->4333|6333->4385|6561->4565|6771->4727|6882->4790|6948->4808|7083->4895|7406->5170|7548->5264|7637->5305|8309->5929|8375->5947|8470->5994|8567->6043|8854->6282|9031->6410|9225->6556|9363->6645|9666->6900|9786->6972|9850->6988|10650->7751|10678->7756|10913->7954|10943->7961
                    LINES: 19->1|22->1|31->10|39->18|40->19|45->24|46->25|56->35|73->52|78->57|79->58|80->59|83->62|84->63|86->65|87->66|88->67|89->68|90->69|94->73|95->74|106->85|107->86|108->87|110->89|111->90|112->91|118->97|119->98|120->99|121->100|124->103|127->106|129->108|130->109|131->110|135->114|136->115|137->116|147->126|148->127|149->128|150->129|155->134|156->135|158->137|158->137|161->140|163->142|164->143|186->165|186->165|192->171|192->171
                    -- GENERATED --
                */
            