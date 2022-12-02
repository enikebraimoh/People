package com.enike.routes

import com.enike.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

data class LoginInput(val email: String, val password: String)
data class userModel(val email: String, val password: String)

fun Route.peopleRoutes() {
    route("/people") {
        get {
            if (peopleStorage.isNotEmpty()) {
                call.respond(peopleStorage)
            } else {
                call.respondText("Nobody found 🥲", status = HttpStatusCode.OK)
            }
        }

        suspend fun getPerson(body: ApplicationCall) {
            body.respondText("jhihidhi")
        }

        var users = listOf(userModel("john@email.com", "123456"))

        suspend fun loginnHanndler(body: ApplicationCall) {
            val data = body.receive<LoginInput>()

            val accessToke = body.request.headers.get("Authorization")

        }

        get("dss") {
            getPerson(call)

        }

        post("/login") {
            loginnHanndler(call)
        }




        get("{name?}") {
            val name = call.parameters["name"] ?: return@get call.respondText(
                "please pass name query parameter",
                status = HttpStatusCode.BadRequest
            )

            val person = peopleStorage.find { it.name.equals(name, ignoreCase = true) } ?: return@get call.respondText(
                "Nobody found",
                status = HttpStatusCode.NotFound
            )

            call.respond(person)
        }
        post {
            val customer = call.receive<Person>()
            peopleStorage.add(customer)
            call.respondText("person stored 🥳", status = HttpStatusCode.Created)
        }

        delete("{name?}") {
            val name = call.parameters["name"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

            if (peopleStorage.removeIf { it.name == name }) {
                call.respondText("person removed ❌", status = HttpStatusCode.OK)
            } else {
                call.respondText("person not found", status = HttpStatusCode.NotFound)
            }

        }

    }

}