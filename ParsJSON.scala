import net.liftweb.json.DefaultFormats
import net.liftweb.json._

object ParseJsonArray extends App {
  implicit val formats = DefaultFormats
// a JSON string that represents a list of EmailAccount instances
  val json = """
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

  var jsonParse = parse(json).asInstanceOf[JObject].values;
  print(jsonParse)
  
  def functionUnList(listPara:List[Any]) : Unit = {
    listPara.foreach {
        case value => {
          if(value.isInstanceOf[Map[String, Any]]){println("Object : " + "   " );functionUnMap(value.asInstanceOf[Map[String, Any]])}
          else if(value.isInstanceOf[List[Any]]){println("    Array : ");functionUnList(value.asInstanceOf[List[Any]])}
          else{println("String : " + value)}
        }
      }
  }
  
  def functionUnMap(mapPara:Map[String, Any]) : Unit = {
      mapPara.foreach {
        case (key, value) => {
          if(value.isInstanceOf[Map[String, Any]]){println("Object : " + key + "   " );functionUnMap(value.asInstanceOf[Map[String, Any]])}
          else if(value.isInstanceOf[List[Any]]){println("Array : " + key);functionUnList(value.asInstanceOf[List[Any]])}
          else{println("String : " + key)}
        }
      }
  }
  functionUnMap(jsonParse)



}
