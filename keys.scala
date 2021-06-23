import io.circe._
import io.circe.parser._

val raw = """{
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
}"""

//stack-unsafe, simple
def keys(json: Json): Vector[String] =
    for {
      jobject <- json.asObject.toVector
      (k, v) <- jobject.toVector
      key <- keys(v) :+ k
    } yield key

println(parse(raw).map(keys))

//less simple, stacksave
def reckeys(json: Json): Vector[String] = {
  def go(todo: Vector[Json], found: Vector[String]): Vector[String] = {
    //my kingdom for Vector#uncons
    todo.headOption match {
      case Some(head) => {
        head.asObject match {
          case None => go(todo.tail, found)
          case Some(jObject) => {
            val (keys, values) = jObject.toVector.unzip
            go(values ++ todo.tail, keys ++ found)
          }
        }
      }
      case None => found
    }
    
  }
  go(Vector(json), Vector.empty)
}

val res = parse(raw).map(keys)
println(parse(raw).map(reckeys))

