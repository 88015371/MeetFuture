package com.zjman.meetfuture.dagger

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

/**
 * Created by zjman on 2018/5/16.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class ContextLife(val value: String = "Application")
