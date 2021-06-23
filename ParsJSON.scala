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
  case class JStringTy(body: JString) extends DocumentJSON
  case class JFieldTy(body: JField) extends DocumentJSON
  case class JArrayTy(body: JArray) extends DocumentJSON
  case class JIntTy(body: JInt) extends DocumentJSON

  def paternMatch(elementMatch: DocumentJSON): String = {
    elementMatch match {
      case ListTy(body)    => s"List : " + {for { valueList <- body } {
        if(valueList.isInstanceOf[JObject]){print(paternMatch(new JObjTy(valueList.asInstanceOf[JObject])))}
        else if(valueList.isInstanceOf[JString]){print(paternMatch(new JStringTy(valueList.asInstanceOf[JString])))}
        else if(valueList.isInstanceOf[JField]){print(paternMatch(new JFieldTy(valueList.asInstanceOf[JField])))}
        else if(valueList.isInstanceOf[JArray]){print(paternMatch(new JArrayTy(valueList.asInstanceOf[JArray])))}
        else if(valueList.isInstanceOf[JInt]){print(paternMatch(new JIntTy(valueList.asInstanceOf[JInt])))}
      }}
      case JObjTy(body)    => s"Object : " + println("    " + paternMatch(new ListTy(body.children)))
      case JStringTy(body) => s"String : " + println("    " + body.extract[String])
      case JFieldTy(body)  => s"Field : " + println(body.name + " " + body.value)
      case JArrayTy(body)  => s"Array : " + println("   " + paternMatch(new ListTy(body.children)))
      case JIntTy(body)    => s"Int : " + println(body.values)
    }
  }

  val someDoc = ListTy(elements)

  print(paternMatch(someDoc))


}

