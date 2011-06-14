/*
 * Copyright (c) 2009 Haefelinger IT 
 *
 * Licensed  under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required  by  applicable  law  or  agreed  to in writing, 
 * software distributed under the License is distributed on an "AS 
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied.
  
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

package it.haefelinger.flaka.util;

import it.haefelinger.flaka.el.Binding;

import java.lang.reflect.Method;


public class ELBinding {
  // variables e, pi
 
  @Binding(type=2)
  static public Object e() {
    return new Double(Math.E);
  }
  @Binding(type=2)
  static public Object pi() {
    return new Double(Math.PI);
  }
  
  @Binding(type=2)
  static public Object wh() {
    return new String("wolfgang h�feliger");
  }
  
  @Binding(type=3)
  static public Method rand() throws SecurityException, NoSuchMethodException {
    return Math.class.getMethod("random");
  }
}