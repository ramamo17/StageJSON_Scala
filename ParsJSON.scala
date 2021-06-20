import net.liftweb.json.DefaultFormats
import net.liftweb.json._

object ParseJsonArray extends App {
    implicit val formats = DefaultFormats

    // a JSON string that represents a list of EmailAccount instances
    val jsonString ="""
    {
      "accounts": [
    { "emailAccount": {
      "accountName": "YMail",
      "username": "USERNAME",
      "password": "PASSWORD",
      "url": "imap.yahoo.com",
      "minutesBetweenChecks": 1,
      "usersOfInterest": ["barney", "betty", "wilma"],
      "type" : "String"
    }},
    { "emailAccount": {
      "accountName": "Gmail",
      "username": "USER",
      "password": "PASS",
      "url": "imap.gmail.com",
      "minutesBetweenChecks": 1,
      "usersOfInterest": ["pebbles", "bam-bam"],
      "type" : "String"
    }}]}
    """

    def parsingJSON(json:String) : List[Any] = {
      // json is a JValue instance
      val json = parse(jsonString)
      val elements = (json).children
      return(elements)
    }
  
    println(parsingJSON(jsonString))
  
    def browseList(listData : Any) : Any = {

      if(listData.isInstanceOf[List[Any]] == true){
        return(listData)
      }else if (listData.isInstanceOf[JArray] == true){
        return(listData)
      }else if (listData.isInstanceOf[JObject] == true){
        return(browseList(listData(0)))
      }else if (listData.isInstanceOf[JField] == true){
        return(browseList(listData(0)))
      }else if (listData.isInstanceOf[JString] == true){
        return(browseList(listData(0)))
      }
      
    }
  
    println(browseList(parsingJSON(jsonString)))
  
    
  
  
}
