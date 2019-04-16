package $package$.wiring

import com.google.inject.ImplementedBy
import javax.inject.Inject
import uk.gov.hmrc.play.bootstrap.config.ServicesConfig

@ImplementedBy(classOf[AppConfigImpl])
trait AppConfig {

  val appName: String
  val someInt: Int
  val someString: String
  val someBoolean: Boolean
  val authBaseUrl: String
  val serviceBaseUrl: String
  val mongoSessionExpiryTime: Int
}

class AppConfigImpl @Inject()(config: ServicesConfig) extends AppConfig {
  val appName = config.getString("appName")

  val someInt = config.getInt("new-shiny-service-26.someInt")
  val someString = config.getString("new-shiny-service-26.someString")
  val someBoolean = config.getBoolean("new-shiny-service-26.someBoolean")
  val authBaseUrl = config.baseUrl("auth")
  val serviceBaseUrl = config.baseUrl("new-shiny-service-26")
  val mongoSessionExpiryTime: Int = config.getInt("mongodb.session.expireAfterSeconds")
}
