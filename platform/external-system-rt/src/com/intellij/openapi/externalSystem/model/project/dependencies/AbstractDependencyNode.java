// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.externalSystem.model.project.dependencies;

import com.intellij.openapi.externalSystem.util.IteratorUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractDependencyNode implements DependencyNode, Serializable {
  private final long id;
  @NotNull
  private final List<DependencyNode> dependencies;
  private String resolutionState;
  private String selectionReason;

  protected AbstractDependencyNode(long id) {
    this.id = id;
    dependencies = new ArrayList<DependencyNode>(0);
  }

  @Override
  public long getId() {
    return id;
  }

  @NotNull
  @Override
  public List<DependencyNode> getDependencies() {
    return dependencies;
  }

  @Nullable
  @Override
  public String getResolutionState() {
    return resolutionState;
  }

  public void setResolutionState(@Nullable String resolutionState) {
    this.resolutionState = resolutionState;
  }

  @Nullable
  @Override
  public String getSelectionReason() {
    return selectionReason;
  }

  public void setSelectionReason(@Nullable String selectionReason) {
    this.selectionReason = selectionReason;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AbstractDependencyNode node = (AbstractDependencyNode)o;
    if (id != node.id) return false;
    return true;
  }

  @Override
  public final int hashCode() {
    return (int)(id ^ (id >>> 32));
  }

  private static final class DependenciesIterator extends IteratorUtils.AbstractObjectGraphIterator<DependencyNode> {
    private DependenciesIterator(Collection<DependencyNode> dependencies) {
      super(dependencies);
    }

    @Override
    public Collection<? extends DependencyNode> getChildren(DependencyNode node) {
      return node.getDependencies();
    }
  }
}
