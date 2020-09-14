## 集成介绍（请star支持）

> 本项目是项目androidx项目。精选了X系列最实用的几个库，可大大提高开发的效率。

* [XUI 一个简洁而优雅的Android原生UI框架，解放你的双手！](https://github.com/xuexiangjys/XUI)

* [XUtil 一个方便实用的Android工具类库！](https://github.com/xuexiangjys/XUtil)

* [XAOP 一个轻量级的AOP(Android)应用框架。囊括了最实用的AOP应用。](https://github.com/xuexiangjys/XAOP)

* [XPage 一个非常方便的fragment页面框架。](https://github.com/xuexiangjys/XPage)

* [XUpdate 一个轻量级、高可用性的Android版本更新框架。](https://github.com/xuexiangjys/XUpdate)

* [XHttp2 一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。](https://github.com/xuexiangjys/XHttp2)

除此之外，还集成了其他优秀的第三方库:

* [AndroidAutoSize 优秀的屏幕适配方案](https://github.com/JessYanCoding/AndroidAutoSize)

* [butterknife 控件依赖注入框架](https://github.com/JakeWharton/butterknife)

* [vlayout 阿里巴巴开源淘宝多布局组件](https://github.com/alibaba/vlayout)

* [MMKV 腾讯开源键值对存储](https://github.com/Tencent/MMKV)

* [leakcanary 内存泄漏检测](https://github.com/square/leakcanary)

## 更多资源

* [简化版Android空壳模板工程](https://github.com/xuexiangjys/TemplateSimpleProject)

* [X-Library系列Android应用框架详解](https://github.com/xuexiangjys/AndroidAdvancedLearning/blob/master/framework/X-Library系列Android应用框架详解.md)

* [Flutter空壳模板工程](https://github.com/xuexiangjys/flutter_template)

1.克隆项目

```
git clone git@github.com:wangchang0408/chat_online.git
```

2.修改项目名（文件夹名），并删除目录下的.git文件夹（隐藏文件）

3.使用AS打开项目，然后修改`包名`、`applicationId`和`app_name`

* 修改包名

* 修改applicationId

* 修改app_name


## 项目打包

1.修改工程根目录的`gradle.properties`中的`isNeedPackage=true`。

2.添加并配置keystore，在`versions.gradle`中修改`app_release`相关参数。

3.如果考虑使用友盟统计的话，在`local.properties`中设置应用的友盟ID:`APP_ID_UMENG`。

4.使用`./gradlew clean assembleReleaseChannels`进行多渠道打包。


## 联系方式

QQ：505162243
TEL:15939122079
