package com.example.dagger;

import dagger.Component;
import dagger.Module;

/**
 * Created by ouyang on 17/7/12.
 *
 * 一个component只能对应一个inject对象吗，比如此处就是MainActivity，看上去是的
 */

@Component(modules=PModule.class)
interface MainComponent {
    void inject(MainActivity activity);
}


