package com.example.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ouyang on 17/7/13.
 */

@Module
public class PModule {

    int count;
    String name;


    /**
     * 最好不要传参数，不然这里的参数修改，意味着被注入的地方也要修改
     * 常见的MVP模式里，往往会把view传给presenter，这里一般会有个参数 Iview，其实就 此处的MainActivity
     */
    public PModule(int count,String name){
        this.count = count;
        this.name = name;
    }

    /**
     * dagger2是通过 Provides注解，和方法名的返回参数来找到对应的参数的，所以如果这个module里有provide两个相同的参数，那就会出问题
     * 方法名必须以provide前缀，不然找不到，后面部分不重要，按习惯写字段名吧
     */
    @Provides
    public int provideCount(){
        return count;

//        return 2;
    }

    @Provides
    public String provideName(){
        return name;
    }

}
