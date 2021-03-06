/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package $package$.journeys
import play.api.libs.json._
import $package$.journeys.$servicenameCamel$FrontendJourneyModel.State
import $package$.journeys.$servicenameCamel$FrontendJourneyModel.State._
import uk.gov.hmrc.play.fsm.JsonStateFormats

object $servicenameCamel$FrontendJourneyStateFormats extends JsonStateFormats[State] {

  val EndFormat = Json.format[End]

  override val serializeStateProperties: PartialFunction[State, JsValue] = {
    case s: End => EndFormat.writes(s)
  }

  override def deserializeState(stateName: String, properties: JsValue): JsResult[State] =
    stateName match {
      case "Start"     => JsSuccess(Start)
      case "End"       => EndFormat.reads(properties)
      case "SomeError" => JsSuccess(SomeError)
      case _           => JsError(s"Unknown state name \$stateName")
    }
}
