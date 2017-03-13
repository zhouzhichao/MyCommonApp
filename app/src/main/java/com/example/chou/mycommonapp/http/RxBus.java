package com.example.chou.mycommonapp.http;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by xiaguangcheng on 16/5/17.
 */
public class RxBus {
    /**
     * 参考网址: http://hanhailong.com/2015/10/09/RxBus%E2%80%94%E9%80%9A%E8%BF%87RxJava%E6%9D%A5%E6%9B%BF%E6%8D%A2EventBus/
     *          http://www.loongwind.com/archives/264.html
     *          https://theseyears.gitbooks.io/android-architecture-journey/content/rxbus.html
     */
    private static volatile RxBus mDefaultInstance;

    private RxBus() {
    }

    public static RxBus getDefault() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return _bus;
    }
    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     * @param eventType 事件类型
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return _bus.ofType(eventType);
    }

    /**
     * 提供了一个新的事件,根据code进行分发
     * @param code 事件code
     * @param o
     */
    public void post(int code, Object o){
        _bus.onNext(new RxBusBaseMessage(code,o));

    }


    /**
     * 根据传递的code和 eventType 类型返回特定类型(eventType)的 被观察者
     * 对于注册了code为0，class为voidMessage的观察者，那么就接收不到code为0之外的voidMessage。
     * @param code 事件code
     * @param eventType 事件类型
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(final int code, final Class<T> eventType) {
        /*
        ofType操作符类似于filter操作符，区别在于ofType操作符是按照类型对结果进行过滤
        操作符 http://blog.csdn.net/Job_Hesc/article/details/46495281
         */
        return _bus.ofType(RxBusBaseMessage.class)
                .filter(new Func1<RxBusBaseMessage,Boolean>() {
                    @Override
                    public Boolean call(RxBusBaseMessage o) {
                        //过滤code和eventType都相同的事件
                        return o.getCode() == code && eventType.isInstance(o.getObject());
                    }
                }).map(new Func1<RxBusBaseMessage,Object>() {
                    //map函数只有一个参数，参数一般是Func1<I,O>I,O模版分别为输入和输出值的类型，实现Func1的call方法对I类型进行处理后返回O类型数据
                    //map()和flatMap  http://blog.csdn.net/new_abc/article/details/48025513
                    @Override
                    public Object call(RxBusBaseMessage o) {
                        return o.getObject();
                    }
                }).cast(eventType);
        /*
        cast操作符类似于map操作符，不同的地方在于map操作符可以通过自定义规则，把一个值A1变成另一个值A2，A1和A2的类型可以一样也可以不一样；
        而cast操作符主要是做类型转换的，传入参数为类型class，如果源Observable产生的结果不能转成指定的class，则会抛出ClassCastException运行时异常。
         */
    }
    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return _bus.hasObservers();
    }


}
