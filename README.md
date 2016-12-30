#Know#
Know采用了比较流行的`Rxjava + Retrofit + OkHttp + MVP`的移动开发框架,架构简单,深度解耦,方便迭代,该框架具有很高的普适性,对于我们快速开发移动应用具有很大的借鉴意义。

###RxJava##
Rxjava是一个在JVM上使用**可观测序列来组成的异步的,基于事件的库**.简单地说,就是一个**实现异步的**库。

###Retrofit##
Retrofit就是**对Okhttp做了一层封装**。把网络请求都交给给了Okhttp，我们只需要通过简单的配置就能使用retrofit来进行网络请求了,Retrofit除了提供了传统的Callback形式的API，还有RxJava版本的Observable形式API。

###OkHttp##
OkHttp是一个很强大的网络请求数据库,可以支持一般的get、post请求，支持基于http的文件上传、文件下载、图片加载，支持请求回调，直接返回对象或者对象集合，支持session。

###MVP##
MVP的重点在于Presenter,它将Model和View分开了，由Presenter将Model层的数据拿来处理后，交给View让它自行去显示。其最核心的任务就是：**将Model业务逻辑处理和View页面处理分开**！

###应用截图###

#####splash界面#####
![Image text](https://github.com/WadeDewnye/Know-master/blob/master/Screenshot_splash.png)

#####Home界面#####
![Image text](https://github.com/WadeDewnye/Know-master/blob/master/Screenshot_home.png)

#####Detail界面#####
![Image text](https://github.com/WadeDewnye/Know-master/blob/master/Screenshot_detail.png)
