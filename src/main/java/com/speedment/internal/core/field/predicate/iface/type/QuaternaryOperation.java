/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.internal.core.field.predicate.iface.type;

/**
 *
 * @author pemi
 * @param <T0> type 0
 * @param <T1> type 1
 * @param <T2> type 2
 */
public interface QuaternaryOperation<T0, T1, T2> extends HasFirstOperand<T0>, HasSecondOperand<T1>, HasThirdOperand<T2> {

}