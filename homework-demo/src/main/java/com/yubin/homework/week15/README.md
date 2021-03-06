毕业总结

1、JVM

   JVM主要讲了Java内存模型、Java字节码、GC相关知识、GC调优等几个模块，其中Java内存模型、GC两个模块在日常工作中很重要，对于解决线上实际问题，优化性能有很大的帮助；字节码相关的，类加载机制以及应用需要掌握。关于性能调优以及GC日志分析还需要自己多实践，同时也可以借助工具分析。整理以下几个点：
   
   1)jvm内存关系图，搞清各内存参数的关系对于性能调优至关重要
   
   ![jvm内存](https://user-images.githubusercontent.com/5638600/129480612-7234e18b-cd00-4770-bb2c-c3d525563b8c.jpg)

   2)类的生命周期，七个步骤：加载、链接（效验、准备、解析）、初始化、使用、卸载
   
   ![类生命周期](https://user-images.githubusercontent.com/5638600/129481059-c512fa6a-e519-46f0-b9a8-742f785410cf.jpeg)
   
   3)双亲委派机制，了解各个类加载器的关系，父类加载器持有的类是能被子类看见
   
   ![双亲委派机制](https://user-images.githubusercontent.com/5638600/129481120-c0bf0683-0685-437c-b42c-9d3742dee08f.jpeg)
   
   4)自定义加载器 通过自定义加载器，了解对类的动态生成加载
    
   ![自定义加载器](https://user-images.githubusercontent.com/5638600/129481342-2554d018-0f30-4104-b056-92fa5f4fc6df.jpeg) 
   
   
   5)内存模型
     
    JVM 内存共分为虚拟机栈，堆，方法区，程序计数器，本地方法栈五个部分。其中堆区域属于线程共享区域，即同一个Java进程里的所有线程共享堆内存。堆内存中放的是所有new出来的对象，同时，为了方便进行垃圾回收，根据对象的存活时间，将堆内存分为了新生代和老年代；栈区域属于线程私有，每启动一个线程便会开启一块区域作为该线程私有；方法区主要放了静态方法和常理，属于线程共享区域；程序计数器存在于线程的栈中，作为程序执行到第几行的一个计数器；
   
   6)垃圾回收
   
    垃圾回收主要针对堆内存来说,通过可达性算法判断对象是否存活，GCRoot节点一般包括虚拟机栈(栈桢中的本地变量表)中的引用的对象 ；方法区中的类静态属性引用的对象 ；方法区中的常量引用的对象 。垃圾回收算法：根据堆内存不同区域的划分，采用不同的算法，新生代是标记-复制算法，简单粗暴效率高，老年代采用标记-清除-整理算法，比较费时，一般需要避免,各种GC对比，以及发展趋势：因为互联网快速相应的要求，GC也朝着尽可能减少一次GC特变慢的情景发展

   
 2、NIO
    ![NIO脑图](https://user-images.githubusercontent.com/5638600/129480851-6b4e4d6f-92ef-4780-aeff-3f4606d4e5fd.png)

