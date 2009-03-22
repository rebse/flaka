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

package net.haefelingerit.flaka;

import org.apache.tools.ant.BuildException;

/**
 * A task to throw a special build exception: to terminate a for-loop.
 * 
 * See {@link http://w/Flaka/API/Task#for} for details on task for.
 */

public class Continue extends Break
{
  final static public String TOKEN = "%%cOnT1NuE%%";
  final static protected BuildException EXCEPTION = new BuildException(TOKEN);

  protected void fail() {
    throw EXCEPTION;
  }
 }
