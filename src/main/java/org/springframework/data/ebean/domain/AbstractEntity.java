/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.data.ebean.domain;

import org.springframework.data.domain.Persistable;
import org.springframework.util.ClassUtils;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Abstract base class for entities. Allows parameterization of id type, chooses auto-generation and implements
 * {@link #equals(Object)} and {@link #hashCode()} based on that id.
 *
 * @author Xuegui Yuan
 */
@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Long> {

  private static final long serialVersionUID = -5554308939380869754L;

    @Id
  protected Long id;

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {

    int hashCode = 17;

    hashCode += null == getId() ? 0 : getId().hashCode() * 31;

    return hashCode;
  }

  @Override
  public Long getId() {
    return id;
  }

  /**
   * Sets the id of the entity.
   *
   * @param id the id to set
   */
  public void setId(final Long id) {
    this.id = id;
  }

  @Transient
  @Override
  public boolean isNew() {
    return null == getId();
  }

  @Override
  public boolean equals(Object obj) {

    if (null == obj) {
      return false;
    }

    if (this == obj) {
      return true;
    }

    if (!getClass().equals(ClassUtils.getUserClass(obj))) {
      return false;
    }

    AbstractEntity that = (AbstractEntity) obj;

    return null == this.getId() ? false : this.getId().equals(that.getId());
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
  }


}
