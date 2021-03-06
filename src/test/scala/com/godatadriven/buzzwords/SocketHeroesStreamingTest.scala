/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.godatadriven.buzzwords

import org.apache.flink.api.common.ExecutionConfig
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, createTypeInformation}
import org.scalatest.FlatSpec

class SocketHeroesStreamingTest extends FlatSpec {

  private val keySerializer = createTypeInformation[String].createSerializer(new ExecutionConfig)

  "The Heroer Streaming Pipeline" should "give a suggestion of a team" in {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val dataStream = env.fromCollection[String](List(
      // Assasin
      """{"id":12,  "character":"Alarak"}""",
      """{"id":123, "character":"Cassia"}""",
      """{"id":234, "character":"Chromie"}""",
      """{"id":345, "character":"Falstad"}""",

      // Specialists
      """{"id":456, "character":"Abathur"}""",
      """{"id":567, "character":"Azmodan"}""",

      // Support
      """{"id":678, "character":"Auriel"}""",
      """{"id":789, "character":"Brightwing"}""",

      // Warrior
      """{"id":890, "character":"Artanis"}""",
      """{"id":901, "character":"Arthas"}"""
    ))

    SocketHeroesStreaming.start(dataStream)

    env.execute("SocketHeroesStreamingTest")
  }
}
