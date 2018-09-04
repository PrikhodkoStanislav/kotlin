/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers

import org.jetbrains.kotlin.config.LanguageVersionSettings
import java.util.ArrayList


class TestModule(val name: String) : Comparable<TestModule> {
    lateinit var languageVersionSettings: LanguageVersionSettings

    private val dependencies = ArrayList<TestModule>()

    fun getDependencies(): MutableList<TestModule> = dependencies

    override fun compareTo(other: TestModule): Int = name.compareTo(other.name)

    override fun toString(): String = name
}