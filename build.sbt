import sbt.ExclusionRule

/* =========================================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * =========================================================================================
 */

val kamonCore = "io.kamon"     %%  "kamon-core" % "1.0.0-RC7"
val opentsdb  = "net.opentsdb" % "opentsdb"     % "2.4.0RC1" excludeAll(
   ExclusionRule(organization = "ch.qos.logback"),
   ExclusionRule(organization = "com.google.gwt"),
   ExclusionRule(organization = "net.opentsdb", artifact = "opentsdb_gwt_theme"),
   ExclusionRule(organization = "org.jgrapht"),
   ExclusionRule(organization = "ch.qos.logback")
   )

val hbase = "org.hbase" % "asynchbase" % "1.7.2"
name := "kamon-opentsdb"
parallelExecution in Test in Global := false
crossScalaVersions := Seq("2.12.4")

libraryDependencies ++=
    compileScope(kamonCore, slf4jApi, opentsdb, hbase) ++
    testScope(scalatest, akkaDependency("testkit").value, slf4jApi, slf4jnop,
       "org.mockito" % "mockito-all" % "2.0.2-beta"
    )

resolvers += Resolver.bintrayRepo("kamon-io", "releases")
