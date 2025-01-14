// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.warmup.util

import com.intellij.platform.util.ArgsParser
import java.nio.file.Files
import java.nio.file.Path

interface OpenProjectArgs {
  val projectDir: Path

  val convertProject: Boolean
  val configureProject: Boolean

  val disabledConfigurators: Set<String>
}

class OpenProjectArgsImpl(parser: ArgsParser) : OpenProjectArgs {
  override val projectDir by parser.arg("project-dir", "project home directory").file()

  override val convertProject by parser.arg("convert-project", "Call IntelliJ version converters").optional().boolean { true }
  override val configureProject by parser.arg("configure-project", "Call IntelliJ project configurators").optional().boolean { true }

  override val disabledConfigurators: Set<String> by lazy {
    val config = projectDir.resolve(".idea").resolve(".disabled-headless-configurators")
    if (Files.isRegularFile(config)) {
      Files.readAllLines(config).asSequence().map { it.trim() }.filter { it.isNotBlank() }.toSortedSet()
    }
    else {
      setOf()
    }
  }
}
