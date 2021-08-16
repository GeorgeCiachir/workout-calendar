# Workout Calendar Application

## Known issues

* Did not manage to run the integration tests with a Security context. In order to run the integration tests, it is
  needed to comment the SecurityConfig class and the security dependencies (spring-boot-starter-security and
  spring-cloud-starter-oauth2) in the infrastructure module's pom
* Did not have time to create any unit tests, as I focused on the integration ones, so obviously, no TDD. If given
  enough time, I would have also created the unit tests (quite easy after all). The plan was to mock only the services
  that are two layers down from the current tested service (create services integration as much as possible)

## Not yet implemented

* Recurrence for workouts -> This implies creating new Recurrence entities that also contains the WeekDay and linking
  them to workouts
* The plan was to also extract the username from the SecurityContext and add it to the Workout object, so that the user
  can CRUD only its own workouts. Security is implemented and ot was easy to do, but because the integration tests were
  not running with security, I decided not to do it, in order to be able to run the tests.
* The weather service provides the weather for 16 days only
* A better cache should be implemented, based on the location and requested weather INTERVAL (start + 16 days). It
  currently supports only the location and start date (not the interval), so for the same location, for the dates 01 and
  05 August we'll make 2 calls, while we should have made only one (because 05 August is in the interval 01 August + 16)
* Not implemented to run in a Docker container (because it's a multi-module maven project, it is a bit trickier)

## Additional info

* The application has security -> see the SecurityConfig class in the infrastructure module, and it integrates with
  Keycloack. I have configured a Keycloack server to run locally, but did not export the realm and user configurations
  so, unless the SecurityConfig and the pom deps are commented out, it cannot be used.
* I chose to implement separate domain and DB entities. Could/should have used the model as DB entities, but then I
  would have polluted the domain with specific persistence framework details. I also chose to use Mapstruct and Lomkbok
  directly in the domain (but I considered it acceptable and easy to remove from there if required)

## Testing

* There is a docker-compose file to start a MySql Docker container for testing

## Swagger

* http://localhost:8080/swagger-ui/

