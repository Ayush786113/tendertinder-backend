package com.tendertinder

import com.fasterxml.jackson.databind.ObjectMapper
import io.appwrite.Client
import io.appwrite.ID
import io.appwrite.Query
import io.appwrite.models.Document
import io.appwrite.models.DocumentList
import io.appwrite.services.Databases
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Component
import java.util.ArrayList
import java.util.LinkedList
import java.util.Objects
import kotlin.system.exitProcess

@Component
class Appwrite {
    val client: Client = Client()
        .setProject("658c87ce373ca5b6f199")
        .setKey("a942436a74cec7f40a8018b8d147923584c588d1de7fb4a83e6d3cad38aa0316115e5eba996badb72b7a30f46ab01c6b6686106087913d76f57cac811adf36ef7931ce0a522740b6c79e20b2799bc9151d779c653029dd955999dc6e9fa88908e1eaac8091c56bd6ed6e52e6cbc1093bf2e348d703db7987ae05ddeb28cc594c")
        .setEndpoint("https://cloud.appwrite.io/v1")

    val databases: Databases = Databases(client)

    val database: String = "658c88723a3ad5085092"
    val user: String = "658c8879b81581684dae"
    val profile: String = "658c88800de9d48dc39b"

    fun writeRecommendation(callbacks: Callback, person: Person){
        GlobalScope.launch {
            try {
                val result = databases.listDocuments(
                    database,
                    user,
                    listOf(Query.equal("id", person.id))
                )
                if(result.total.toInt() == 0){
                    databases.createDocument(
                        database,
                        user,
                        ID.unique(),
                        person
                    )
                }
            } catch (exception: Exception){
                println(exception.message)
            }
            finally {
                callbacks.done()
            }
        }
    }
    fun getProfiles(serviceCallback: ServiceCallback){
        GlobalScope.launch {
            try{
                val data: DocumentList<Map<String, Any>> = databases.listDocuments(
                    database,
                    user,
//                    listOf(Query.limit(1000))
                )
                var list: LinkedList<Person> = LinkedList()
                for(user in data.documents){
                    val person: Person = Person()
                    person.id = user.data.get("id").toString()
                    person.name = user.data.get("name").toString()
                    person.birth_date = user.data.get("birth_date").toString()
                    person.bio = user.data.get("bio").toString()
                    person.city = user.data.get("city").toString()
                    person.gender = if (user.data.get("gender") != null) (user.data.get("gender") as Double).toInt() else 0
                    person.height = if (user.data.get("height") != null) (user.data.get("height") as Double).toInt() else 0
                    person.interests = user.data.get("interests") as ArrayList<String>
                    person.photos = user.data.get("photos") as ArrayList<String>
                    person.relationship_intent = user.data.get("relationship_intent").toString()
                    person.unit_of_measure = user.data.get("unit_of_measure").toString()
                    list.add(person)
                }
                serviceCallback.result(list)
            } catch (exception: Exception){
                println(exception.message)
                serviceCallback.result("Something went wrong when reading data from the database!")
            }
            finally {
                serviceCallback.done()
            }
        }
    }
}