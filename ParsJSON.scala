import net.liftweb.json._

implicit val formats = DefaultFormats // Brings in default date formats etc.

case class LongitudeC(typeOf:String, minimum:Int, maximum:Int)
case class LatitudeC(typeOf:String, minimum:Int, maximum:Int)
case class Properties(latitude:LatitudeC, longitude:LongitudeC)
case class Geography(title:String, desciption:String, required:List[String], typeOf:String, properties:Properties)

val json = parse("""
         {
            "title": "Longitude and Latitude Values",
            "description": "A geographical coordinate.",
            "required": [ "latitude", "longitude" ],
            "typeOf": "object",
            "properties": {
               "latitude": {
                  "typeOf": "number",
                  "minimum": -90,
                  "maximum": 90
               },
               "longitude": {
                  "typeOf": "number",
                  "minimum": -180,
                  "maximum": 180
               }
            }
         }
       """)

json.extract[Geography] 

/*
Based on this JSON schema
{
  "title": "Longitude and Latitude Values",
  "description": "A geographical coordinate.",
  "required": [ "latitude", "longitude" ],
  "type": "object",
  "properties": {
    "latitude": {
      "typeOf": "number",
      "minimum": -90,
      "maximum": 90
    },
    "longitude": {
      "typeOf": "number",
      "minimum": -180,
      "maximum": 180
    }
  }
}

*/