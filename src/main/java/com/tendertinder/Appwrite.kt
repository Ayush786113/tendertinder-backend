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
        .setProject(Constants.PROJECT)
        .setKey(Constants.API)
        .setEndpoint(Constants.ENDPOINT)

    val databases: Databases = Databases(client)

    val database: String = Constants.DATABASE
    val user: String = Constants.USER
    val profile: String = Constants.PROFILE

    fun writeRecommendation(callbacks: Callback, person: Person){
        GlobalScope.launch {
            try {
                val result = databases.listDocuments(
                    database,
                    user,
                    listOf(Query.equal("id", person.id))
                )
                println(result.total.toInt())
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
                    listOf(Query.orderAsc("id"))
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

    fun searchByName(serviceCallback: ServiceCallback, name: String){
        GlobalScope.launch {
            try{
                val data: DocumentList<Map<String, Any>> = databases.listDocuments(
                    database,
                    user,
                    listOf(Query.equal("name", name), Query.startsWith("name", name))
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
    fun searchByGender(serviceCallback: ServiceCallback, gender: Int){
        GlobalScope.launch {
            try{
                val data: DocumentList<Map<String, Any>> = databases.listDocuments(
                    database,
                    user,
                    listOf(Query.equal("gender", gender))
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
    fun searchByCity(serviceCallback: ServiceCallback, city: String){
        GlobalScope.launch {
            try{
                val data: DocumentList<Map<String, Any>> = databases.listDocuments(
                    database,
                    user,
                    listOf(Query.equal("city", city))
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