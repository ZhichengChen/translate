
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
object change extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Html,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(content: Html,title:String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.30*/(""" 
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
            td span"""),format.raw("""{"""),format.raw/*36.21*/("""
                display: none;
            """),format.raw("""}"""),format.raw/*38.14*/("""
            td:hover span"""),format.raw("""{"""),format.raw/*39.27*/("""
                display: block;
                cursor: pointer;
                vertical-align: middle;
                float:left;
                width:20px;
            """),format.raw("""}"""),format.raw/*45.14*/("""
        </style>
        <script src="/assets/js/j.js" type="text/JavaScript">
        </script>
        <script>
            var title =""""),_display_(Seq[Any](/*50.26*/title)),format.raw/*50.31*/("""";
            /*
            fun:翻译元素内的html
            in:元素
            out:无
            在$("myDiv")内显示翻译内容
            */
            function trans(obj)"""),format.raw("""{"""),format.raw/*57.33*/("""
                var content = "";
                var addr = "http://fanyi.youdao.com/openapi.do?keyfrom=playframewrok&key=1708078032&type=data&doctype=jsonp&version=1.1&q=";
                context = obj.html().replace("\n            ","").replace(new RegExp("\n            ","gm")," ");
                $("#myDiv").html("");
                strs=context.split(".");
                for (var i=0;i<strs.length;i++)"""),format.raw("""{"""),format.raw/*63.49*/("""
                    if(strs[i]!=null && strs[i]!="")"""),format.raw("""{"""),format.raw/*64.54*/("""
                        if(strs[i].length<50)"""),format.raw("""{"""),format.raw/*65.47*/("""
                            i++;
                            strs[i]=strs[i-1]+strs[i];
                        """),format.raw("""}"""),format.raw/*68.26*/("""
                        $.post(addr+strs[i],function(ret)"""),format.raw("""{"""),format.raw/*69.59*/("""
                            $("#myDiv").append(ret.translation)
                        """),format.raw("""}"""),format.raw/*71.26*/(""", "jsonp");
                    """),format.raw("""}"""),format.raw/*72.22*/("""
                """),format.raw("""}"""),format.raw/*73.18*/("""
            """),format.raw("""}"""),format.raw/*74.14*/("""
            $(document).ready(function() """),format.raw("""{"""),format.raw/*75.43*/("""
                var content = "";
                var contenttemp = "";
                var height = "";
                var width = "";
                var context = "";
                var addr = "";

                $(".right").live("click",function()"""),format.raw("""{"""),format.raw/*83.53*/("""
                    var times=$(this).parents("tr").index("tr")-1;
                    if(times==-1)"""),format.raw("""{"""),format.raw/*85.35*/("""
                        times=0;
                        content=$(this).parent("td").next("td.trans").html();
                    """),format.raw("""}"""),format.raw/*88.22*/("""
                    $("tr:gt("+times+")").each(function()"""),format.raw("""{"""),format.raw/*89.59*/("""
                        contenttemp = $(this).children("td.trans").html();
                        $(this).children("td.trans").html(content);
                        content=contenttemp;
                    """),format.raw("""}"""),format.raw/*93.22*/(""");
                    $(this).parent("td").next("td").html("<textarea class='add'></textarea>")
                    trans($(this).parent("td").prev("td"));
                """),format.raw("""}"""),format.raw/*96.18*/(""");//单击中间的合并按钮，相应右边译文新增一个编辑框

                $(".copy").live("click",function()"""),format.raw("""{"""),format.raw/*98.52*/("""
                    content=$(this).parent("td").prev("td").html();
                    $(this).parent("td").next("td.trans").html(content);
                """),format.raw("""}"""),format.raw/*101.18*/(""");//单击中间的复制按钮，相应左边的所有内容复制到右边

                $(".del").live("click",function()"""),format.raw("""{"""),format.raw/*103.51*/("""
                    addr = "/Del/"+$(this).parents("tr").index("tr")+"/"+title+"/";
                    var times=$(this).parent("tr").index("tr");
                    $("tr:gt("+times+")").each(function()"""),format.raw("""{"""),format.raw/*106.59*/("""
                        $(this).prev("tr").children("td.trans").html($(this).children("td.trans").html());
                        if($(this).index("tr")==($("tr").size()-1))
                            $(this).children("td.trans").html("")
                    """),format.raw("""}"""),format.raw/*110.22*/(""");
                    $.get(addr,function(result)"""),format.raw("""{"""),format.raw/*111.49*/("""
                        if(result!="Success")
                            $("#state").html("不能删除，出错了，不信你刷新一下～<a href='"+addr+"'>"+addr+"</a>"); 
                    """),format.raw("""}"""),format.raw/*114.22*/(""");
                """),format.raw("""}"""),format.raw/*115.18*/(""");//单击右边的删除按钮，删除相应的条目

                $("textarea.add").live("blur",function() """),format.raw("""{"""),format.raw/*117.59*/("""
                    if(!$.trim($(this).val())=="")"""),format.raw("""{"""),format.raw/*118.52*/("""
                        content="<p>"+$(this).val()+"</p>\r\n";
                        addr = "/insert/"+$(this).parents("tr").index("tr")+"/"+$(this).val().replace(new RegExp("#","gm"),"~~~").replace(new RegExp("<","gm"),"~lt~").replace(new RegExp(">","gm"),"~gt~").replace(new RegExp("/","gm"),"~line~")+"/"+title+"/"
                        $.get(addr,function(result)"""),format.raw("""{"""),format.raw/*121.53*/("""
                            if(result!="Success")
                                $("#state").html("不能保存，出错了，不信你刷新一下～<a href='"+addr+"'>"+addr+"</a>"); 
                        """),format.raw("""}"""),format.raw/*124.26*/(""");
                        $(this).parent("td").html(content);
                    """),format.raw("""}"""),format.raw/*126.22*/("""
                """),format.raw("""}"""),format.raw/*127.18*/(""");//增加文本框失去焦点则保存

                $("td.trans p").live("click",function() """),format.raw("""{"""),format.raw/*129.58*/("""
                    if(!$(this).children().is("textarea"))"""),format.raw("""{"""),format.raw/*130.60*/("""
                        context = $(this).html();
                        context = context.replace("\n            ","");
                        context = context.replace(new RegExp("\n            ","gm")," ");
                        height = $(this).height();
                        width = $(this).width();
                        $(this).html("<textarea class=\"edit\" title="+($(this).index("p"))+" >" + context + "</textarea>");
                        $(this).children("textarea").height(height);
                        $(this).children("textarea").focus();
                        trans($(this).parents("tr").children("td:first"));
                    """),format.raw("""}"""),format.raw/*140.22*/("""
                """),format.raw("""}"""),format.raw/*141.18*/(""");//单击译文显示可编辑文本框，显示译文

                $("textarea.edit").live("blur",function() """),format.raw("""{"""),format.raw/*143.60*/("""
                    if(!$.trim($(this).val())=="")"""),format.raw("""{"""),format.raw/*144.52*/("""
                        if($(this).val()!=context)"""),format.raw("""{"""),format.raw/*145.52*/("""
                            content=$(this).val().replace(new RegExp("#","gm"),"~~~").replace(new RegExp("<","gm"),"~lt~").replace(new RegExp(">","gm"),"~gt~").replace(new RegExp("/","gm"),"~line~");
                            addr="/Change/"+$(this).parents("tr").index("tr")+"/"+content+"/"+title+"/";

                            $.get(addr,function(result)"""),format.raw("""{"""),format.raw/*149.57*/("""
                                if(result!="Success")
                                    $("#state").html("不能保存，出错了，不信你刷新一下～<a href='"+addr+"'>"+addr+"</a>"); 
                            """),format.raw("""}"""),format.raw/*152.30*/(""");
                                
                        """),format.raw("""}"""),format.raw/*154.26*/("""
                        $(this).parent("p").html($(this).val());
                        $(this).val("");
                    """),format.raw("""}"""),format.raw/*157.22*/("""
                """),format.raw("""}"""),format.raw/*158.18*/(""");//编辑文本框失去焦点则保存

                $("textarea.edit").live("keypress",function() """),format.raw("""{"""),format.raw/*160.64*/("""
                    if(event.which==13)"""),format.raw("""{"""),format.raw/*161.41*/("""
                        if(!$.trim($(this).val())=="")"""),format.raw("""{"""),format.raw/*162.56*/("""
                            if($(this).val()!=context)"""),format.raw("""{"""),format.raw/*163.56*/("""
                                content=$(this).val().replace(new RegExp("#","gm"),"~~~").replace(new RegExp("<","gm"),"~lt~").replace(new RegExp(">","gm"),"~gt~").replace(new RegExp("/","gm"),"~line~");
                                addr = "/Change/"+$(this).parent("p").parent("td").parent("tr").index("tr")+"/"+content+"/"+title+"/";
                                $.get(addr,function(result)"""),format.raw("""{"""),format.raw/*166.61*/("""
                                    if(result!="Success")
                                        $("#state").html("不能保存，出错了，不信你刷新一下～<a href='"+addr+"'>"+addr+"</a>"); 
                                """),format.raw("""}"""),format.raw/*169.34*/(""");
                            """),format.raw("""}"""),format.raw/*170.30*/("""
                            $(this).parent("p").html($(this).val());
                            $(this).val("");
                        """),format.raw("""}"""),format.raw/*173.26*/("""
                    """),format.raw("""}"""),format.raw/*174.22*/("""
                """),format.raw("""}"""),format.raw/*175.18*/(""");//键入回车键，编辑保存

                $("textarea.add").live("keypress",function() """),format.raw("""{"""),format.raw/*177.63*/("""
                    if(event.which==13)"""),format.raw("""{"""),format.raw/*178.41*/("""
                        if(!$.trim($(this).val())=="")"""),format.raw("""{"""),format.raw/*179.56*/("""
                            content="<p>"+$(this).val()+"</p>\r\n";
                            addr = "/insert/"+$(this).parents("tr").index("tr")+"/"+$(this).val().replace(new RegExp("#","gm"),"~~~").replace(new RegExp("<","gm"),"~lt~").replace(new RegExp(">","gm"),"~gt~").replace(new RegExp("/","gm"),"~line~")+"/"+title+"/"
                            $.get(addr,function(result)"""),format.raw("""{"""),format.raw/*182.57*/("""
                                if(result!="Success")
                                    $("#state").html("不能保存，出错了，不信你刷新一下～<a href='"+addr+"'>"+addr+"</a>"); 
                            """),format.raw("""}"""),format.raw/*185.30*/(""");
                            $(this).parent("td").html(content);
                        """),format.raw("""}"""),format.raw/*187.26*/("""
                    """),format.raw("""}"""),format.raw/*188.22*/("""
                """),format.raw("""}"""),format.raw/*189.18*/(""");//键入回车键，新增保存
            """),format.raw("""}"""),format.raw/*190.14*/(""");
        </script>
        <title>
            Translate
        </title>
    </head>
    
    <body class="container" style="width:95%;">
        <div class="navbar navbar-fixed-top">
          <div class="navbar-inner">
            <div style="line-height:35px;margin-left:60px;" id="state">
              Translate
            </div>
          </div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid" style="background:url(/assets/img/wash-white-30.png);padding:0 10px;">
                <div style="background:#fff;padding:0 20px;margin-top:30px;">
                    """),_display_(Seq[Any](/*208.22*/content)),format.raw/*208.29*/("""
                </div>
            </div>
            <div style="position:fixed;width:93%;bottom:0;background:#f9f9f9;">
                <div class="alert alert-info"  style="min-height:20px;" id="myDiv">
                    You dao~
                </div>
            </div>
        </div>     
    </body>
</html>"""))}
    }
    
    def render(content:Html,title:String) = apply(content,title)
    
    def f:((Html,String) => play.api.templates.Html) = (content,title) => apply(content,title)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Apr 19 10:24:04 CST 2013
                    SOURCE: /home/ze/git/translate/app/views/change.scala.html
                    HASH: e77432636718032688cbb089de8d93a284293f75
                    MATRIX: 511->1|616->29|986->352|1366->685|1450->722|1618->843|1711->889|2280->1411|2348->1432|2440->1477|2514->1504|2736->1679|2912->1819|2939->1824|3145->1983|3609->2400|3710->2454|3804->2501|3965->2615|4071->2674|4208->2764|4288->2797|4353->2815|4414->2829|4504->2872|4807->3128|4956->3230|5136->3363|5242->3422|5499->3632|5720->3806|5847->3886|6054->4045|6182->4125|6437->4332|6748->4595|6847->4646|7062->4813|7130->4833|7259->4914|7359->4966|7781->5340|8008->5519|8140->5603|8206->5621|8329->5696|8437->5756|9150->6421|9216->6439|9346->6521|9446->6573|9546->6625|9957->6988|10196->7179|10305->7240|10481->7368|10547->7386|10676->7467|10765->7508|10869->7564|10973->7620|11421->8020|11672->8223|11752->8255|11940->8395|12010->8417|12076->8435|12202->8513|12291->8554|12395->8610|12829->8996|13068->9187|13208->9279|13278->9301|13344->9319|13420->9347|14071->9961|14101->9968
                    LINES: 19->1|22->1|31->10|39->18|40->19|45->24|46->25|56->35|57->36|59->38|60->39|66->45|71->50|71->50|78->57|84->63|85->64|86->65|89->68|90->69|92->71|93->72|94->73|95->74|96->75|104->83|106->85|109->88|110->89|114->93|117->96|119->98|122->101|124->103|127->106|131->110|132->111|135->114|136->115|138->117|139->118|142->121|145->124|147->126|148->127|150->129|151->130|161->140|162->141|164->143|165->144|166->145|170->149|173->152|175->154|178->157|179->158|181->160|182->161|183->162|184->163|187->166|190->169|191->170|194->173|195->174|196->175|198->177|199->178|200->179|203->182|206->185|208->187|209->188|210->189|211->190|229->208|229->208
                    -- GENERATED --
                */
            