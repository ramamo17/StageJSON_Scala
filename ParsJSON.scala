import net.liftweb.json.DefaultFormats
import net.liftweb.json._

object ParseJsonArray extends App {
  implicit val formats = DefaultFormats
// a JSON string that represents a list of EmailAccount instances
  val jsonString = """
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

  val json = parse(jsonString) // on doit itt sur les JField
  val elements = (json).children

  //println(elements)

  abstract class DocumentJSON
  case class ListTy(body: List[Any]) extends DocumentJSON
  case class JObjTy(body: JObject) extends DocumentJSON
  case class JstringTy(body: JString) extends DocumentJSON
  case class JFieldTy(body: JField) extends DocumentJSON
  case class JArray(body: JArray) extends DocumentJSON

  def paternMatch(elementMatch: DocumentJSON): String = {
    elementMatch match {
      case ListTy(body)    => s"List : " + {for { valueList <- body } {
        if(valueList.isInstanceOf[JObject]){println(valueList); paternMatch(new JObjTy(valueList))}
        else if(valueList.isInstanceOf[JString]){paternMatch(new JstringTy(valueList))}
        else if(valueList.isInstanceOf[JField]){paternMatch(new JFieldTy(valueList))}
        else if(valueList.isInstanceOf[JArray]){paternMatch(new JArray(valueList))}
      }}
      case JObjTy(body)    => s"JObj"
      case JstringTy(body) => s"String"
      case JFieldTy(body)  => s"Field"
      case JArray(body)    => s"Array"
    }
  }

  val someDoc = ListTy(elements)

  println(paternMatch(someDoc))


}

