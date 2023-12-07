# JavaMastersCaseStudy
Case Study for Java Workshop


Implementation:

ROLES 

USER AND ADMIN


MODEL

USER
BUS
TRIP

STATIC ROUTES MODEL (PRELOADED DATA)

ROUTE

Working:

Endpoint Details:

/allUsers/signUp  -  allows every user to signUp with loginId and password
/allUsers/login -  allows every user to login to the application and auth_token confirming the login would be provided.

To access API:

Pass "auth_token" in the request header

ADMIN Controlled endpoints :   /admin/**   - accessible only to ADMIN
USER Controlled endpoints : /user/**  - accessible to both USER AND ADMIN
 

Functionality API's

Access Control 

/admin/UpdateRole - update Admin/Non Admin for any user
/admin/getAllUsers - get All the Users loginId and their roles


Routes API

/user/getAllRoutes
/admin/addRoute
/admin/deleteRoute/{routeId}

Bus API

/admin/addBus
/admin/modifyBus/{busId}
/admin/deleteBus/{busId}
/user/getAllBuses

Trip API

/admin/addTrip - Associates bus to a trip with start time and end time checks for overlap of the provided bus in any other mapped routes.
/admin/deleteTrip/{tripId}

/user/getBusesForRoute/{routeId} - Uses Stored Procedure get_buses_for_route(Integer tripId) for retrieving bus information


PORT - http://localhost:4004/
SWAGGER URI - http://127.0.0.1:4004/swagger-ui/index.html#/
DB POSTGRES 

                        ------------------------- Happpy Testing -------------------------------



