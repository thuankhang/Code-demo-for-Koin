package org.koin.sample

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class CheckModulesTest : KoinTest {

    @Test
    fun checkAllModules() {
        koinApplication { modules(appModule) }.checkModules()
    }

}