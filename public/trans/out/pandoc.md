Pandoc 是一个把 markdown 文档转换成其它格式的 Haskell 类库，它是一个命令行工具。它可以读取 markdown 和（subsets of）Textile，reStructuredText，HTML，LaTeX，MediaWiki markup 以及 DocBook XML，它也可以写成简单的文本文件，markdown，reStructuredText，XHTML，HTML5,LaTeX（包括）

Pandoc 的 markdown 增强版本包括页脚，表格，灵活的有序lists，定义list，固定代码块，上标，下标，删除线，标题块，内容的自动表格，嵌入LaTex公式，citations以及markdwon 内的HTML 元素。（这些增强个欧冠你能，在下面的Panddoc‘s markdown里面有描述，可以使用 markdown_strict 输入输出格式开启。）

Pandoc 的 markdown 增强版本包括页脚，表格，灵活的有序lists，定义list，固定代码块，上标，下标，删除线，标题块，内容的自动表格，嵌入LaTex公式，citations以及markdwon 内的HTML 元素。（这些增强个欧冠你能，在下面的Panddoc‘s markdown里面有描述，可以使用 markdown_strict 输入输出格式开启。）

和大多数现有的使用正则表达式来替换工具相比，Pandoc 是模块化设计的：它包含一个读者设置，用给定的格式解析文本生成一个 native 表示的文档，也包含了一个作者设置，转换这个native 表示的到目标文档。因此添加输入或输出格式需要添加一个读者和作者。

如果没有指定输入文件，那么输入就从stdin读取。否则，输入文件使用输入作为连接（每个之间有一个空行）。输出默认使用 stdout（sdout 输入一般是odt，docx，epub和epub3 格式）。对于输入文件使用 -o 选项。

除了使用文件，可以指定一个URI。在下面这个例子里 pandoc 将要使用HTTP匹配内容。

如果给定了多个输入文件

输入和输出的格式可以通过命令行选项明确指定。输入格式使用 -r/--read 或者 -f/--from 选项，输出格式使用 -w/--write 或者 -t/--to选项。因此，要将 hello.txt 从markdown转换成LaTex，你可以这样键入：

要将hello.html 从html 转换成markdown：

支持的输出格式在下面通过 -t/--to 选项给出。支持的输入格式通过下面的 -f/--from选项给出。注意 rst、textile、latex和html 阅读器是不完成的，有一些结构它们不能解析。

如果输入或输出格式没有明确指定，pandoc 会尝试着从给出的输入或输出的文件扩展名来猜测它的格式。例如：

将会把hello.txt 从markdown 转换为LaTex。 如果没有指定输出文件（也包括 stdout输入），或者输出文件的扩展名未知，将默认输出HTML 格式。如果输入文件没有指定（包括没有 stdin），或者输入文件的扩展名未知，输入格式将默认为markdown。

Pandoc 输入和输出的默认编码都是UTF-8。如果你的本地字符编码不是UTF-8，你可能需要通过 iconv 选项来改变输入和输出编码。

早期的pandoc 版本附带了一个markdown2pdf 程序，使用pandoc 和pdflatex 来生成PDF。现在不用了，现在pandoc 可以自己生成pdf 文件。要生成pdf文件，简单的通过 .pdf 作为输出文件的扩展。Pandoc 将会创建一个latex 文件然后使用pdfflatex （或者其它引擎，见 --latex-engine）来转换成PDF： 

生成PDF 需要安装LaTeX 引擎（见下面的 --latex-engine），并且下面的包必须可用：assymb，amsmath，ifxetex，ifluatex，listings（如果使用了--listings 选项的话），fancyvrb，longtable，url，graphicx，hyperref，ulem，babel（如果设置了 lang 变量），fongspec（如果使用了xelatex 或者lualatex 引擎），xltxtra 和xunicode（如果使用了xelatex）。

用户想要替代Markdown.pl 可以给pandoc 创建一个叫做 hsmarkdown 的可执行模型链接。当调用 hsmarkdown的时候，pandoc 会产生和调用 -f markdown_strict --email-obfuscation=erferences 一样的效果，所有的命令行选项将会和标准的参数一样对待。然而，这在 Cygwin 下是没用的，因为它的符号的链接的simulation 问题。

-f FORMAT，-r FORMAT，--from=FORMAT，--read=FORMAT。～～指定输入的格式。FORMAT 可以是 native（native Haskell），json（native AST版本的json  ），markdown（pandoc 的扩展骂人看到欧文你）

