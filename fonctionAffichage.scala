import net.liftweb.json.DefaultFormats
import net.liftweb.json._
// a case class to match the JSON data
case class EmailAccount(
accountName: String,
url: String,
username: String,
password: String,
minutesBetweenChecks: Int,
usersOfInterest: List[String]
)
object ParseJsonArray extends App {
implicit val formats = DefaultFormats
// a JSON string that represents a list of EmailAccount instances
val jsonString ="""
{
  "$id": "https://example.com/geographical-location.schema.json",
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "title": "Longitude and Latitude Values",
  "description": "A geographical coordinate.",
  "required": [ "latitude", "longitude" ],
  "type": "object",
  "properties": {
    "latitude": {
      "type": "number",
      "minimum": -90,
      "maximum": 90
    },
    "longitude": {
      "type": "number",
      "minimum": -180,
      "maximum": 180
    }
  }
}
"""
// json is a JValue instance

val json = parse(jsonString)
val elements = (json).children
println(json)
println(json.children)
println(elements.head)
//for (acct <- elements) {
//val m = acct.extract[EmailAccount]
//println(s"Account: ${m.url}, ${m.username}, ${m.password}")
//println(" Users: " + m.usersOfInterest.mkString(","))
//}
def affichage(doc:List[Any]): String = doc match{
  case _:JString => println(doc)
  case_:List[Any] => println("Liste :     ") + affichage(doc.head) + affichage(doc.tail)
  case_:JField => println("JField : ") + affichage(doc.children)
  case_:JArray => println ("JArray : ") + affichage(doc.children)
  case_:JObject =>println("JObject : ") + affichage(doc.children)
}
}
