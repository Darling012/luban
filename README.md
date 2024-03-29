#  关于“云梯”脚手架

## 想做什么？

尽可能的让开发的主要精力放在需求分析，业务逻辑和用户体验上。

## 为什么这么做？

让循此往复**写bug，找bug，用bug改bug**的bug三连更为顺畅。

## “云梯”做了什么？

1 统一返回值处理

> controller可以直接返回POJO,不用再手动包装。

2 统一异常处理

> 尽可能的减少业务代码中try catch，并封装友好的异常统一返回值。

3 文档及前端API提供

> 基于swagger及Easy Mock 实现多格式接口文档及前端调用api接口文件。

4 统一参数校验

> 基于hibernate-validator和Guava中Preconditions实现对入参、返回值、构造器的参数处理以及MVC不同层次间的校验。

5 单表CRUD无SQL

> 基于Mybatis-Plus实现，封装了其提供的统一mapper，处理了空值，批量操作等。

6 copy、组装查询条件等工具类的提供

> 各层之间pojo的高效转换，查询参数自动转换为QueryWrapper。

7 等等

> 大家的共识越高，不必要的沟通越少。脚手架作为一个Module模块在项目中提供了修改的灵活性，又带来了不同项目组魔改后的差异，这样别人在接受你项目后，又要熟悉差异的部分。提供jar又完全封闭了，前期规划不好，一改都改更麻烦。所以脚手架在每一个项目代码的code review后，适应新需求的部分应尽可能的能同别的组统一。

# 关于NUll

> null会导致空指针，什么情况下会出现？
>
> > 1 Integer 等包装类的自动拆箱
> >
> > 2 字符串比较
> >
> > 3 不支持null的容器
> >
> > 4 嵌套对象的级联调用
> >
> > 5 返回值
>
> 怎么解决？
>
> > Optional处理。

> 关于null的定位
>
> > 1 入参中的null代表什么？没传这个属性还是这个属性值为空？
> >
> > 2 为null这么纠结，是否需要有默认值？
> >
> > 3 数据库允不允许为null，允许为null的字段返给前台应该返什么？
> >
> 怎么解决？
> 
> > 前后端达成共识，数据库定义规范

> mysql 关于null的点
>
> > sum count 两个函数对于Null的处理。

# 关于代码规范

客观来看，程序员富贵工作内容的技术含量并不高，做的业务也并非很复杂，但总是很忙，忙完后回头看，又不知道富贵忙了些什么。富贵究竟在干什么呢？

在业务理解正确的前提下，大多数的bug出现在哪里？未能正确处理数据库返回值、RPC调用返回值导致的空指针，入参错误导致业务逻辑错误更可怕的是产生脏数据，未能正确处理异常导致业务逻辑进行不下去等，这些异常错误都有一个共性，就是定位到后可以很容易解决。而由于分层不明确，controller里写逻辑引mapper，异常处理不规范随意抛出或吞掉又导致错误难以定位。富贵的大部分时间都是在 定位问题 + 改代码，真正开发的时间并不多。定位问题包括开发转测试的时候发现问题和上线后发现问题，改代码的包括改bug和因为需求变动修改代码。归根到底是因为编码习惯太糟糕，代码质量差，代码冗余重复多，很多无关的代码和业务代码搅在一起，疲于奔命于找问题改问题。

**对于个人来说，技术很重要，但是对于工作来说，编码的习惯比技术更加主要**。



