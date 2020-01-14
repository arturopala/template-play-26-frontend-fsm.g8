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

package $package$.journey
import uk.gov.hmrc.http.HeaderCarrier
import $package$.journeys.$servicenameCamel$FrontendJourneyModel.State.{End, Start}
import $package$.journeys.$servicenameCamel$FrontendJourneyModel.Transitions._
import $package$.journeys.$servicenameCamel$FrontendJourneyModel.{State, Transition}
import $package$.journeys.$servicenameCamel$FrontendJourneyService
import $package$.models.$servicenameCamel$FrontendModel
import uk.gov.hmrc.play.test.UnitSpec

import scala.concurrent.ExecutionContext.Implicits.global

class $servicenameCamel$FrontendModelSpec extends UnitSpec with StateMatchers[State] {

  implicit val hc: HeaderCarrier = HeaderCarrier()

  case class given(initialState: State)
      extends $servicenameCamel$FrontendJourneyService with TestStorage[(State, List[State])] {
    await(save((initialState, Nil)))

    def when(transition: Transition): (State, List[State]) =
      await(super.apply(transition))
  }

  "$servicenameCamel$FrontendModel" when {
    "at state Start" should {
      "transition to End when Start submitted a form" in {
        given(Start) when submitStart("001.H")(
          $servicenameCamel$FrontendModel("Henry", None, None, None)) should thenGo(
          End("Henry", None, None, None))
      }
    }
  }
}
