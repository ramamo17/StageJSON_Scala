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
println(jsonString)
val json = parse(jsonString) // on doit itt sur les JField
val elements = (json).children
println(json.isInstanceOf[JObject])
  
//println(json.children.tail)
//println(elements)
//for (acct <- elements) {
//val m = acct.extract[EmailAccount]
//println(s"Account: ${m.url}, ${m.username}, ${m.password}")
//println(" Users: " + m.usersOfInterest.mkString(","))
//}
def test(v:JValue): Unit ={ v match {
  case JValue => println(v)
} 
}       
  
test(json)

/*def affichage(doc:Any): String = doc match{
  case doc:JString => println("Jstring")
  case doc:List[Any] => println("Liste") + affichage(doc.head) + affichage(doc.tail)
  case doc:JField => println("JField : ") + affichage(doc.children)
  case doc:JArray => println ("JArray : ") + affichage(doc.children)
  case doc:JObject =>println("JObject : ") + affichage(doc.children) // une boucle sur la liste de JField qui est dedans
}*/
}
