class SimpleProperties {
  def minLength(word:String, minNumber:Int) : Boolean = {
    if(word.length <= minNumber){
      return(false)
    }else{
      return(true)
    }
  }
  
  def maxLength(word:String, maxNumber:Int) : Boolean = {
    if(word.length >= maxNumber){
      return(false)
    }else{
      return(true)
    }
  }
  
  def mutipleOf(numb:Int, multiple:Int) : Boolean = {
    if(numb%multiple==0){
      return(true)
    }else{
      return(false)
    }
  }

  def minimum(numb:String, listeNumb:Array[String]) : Boolean = {
   var a = 0
   var isMin:Boolean = false;
   for(a <- 0 to (listeNumb.size - 1)){
      if(listeNumb(a) < numb){
         isMin = false;
      }else{
         isMin = true;
      }
   }
   return(isMin)
  }
}

def minimumExclusive(item:Array[Int]) : Int = {
  var r = item(0)
  for (x <- item){
    if (x<r){
      r = x
    }
  }
  return r
}

def maximumExclusive(item:Array[Int]) : Int = {
  var r = item(0)
  for (x <- item){
    if (x>r){
      r = x
    }
  }
  return r
}


def maximum(numb:String, listeNumb:Array[String]) : Boolean = {
   var a = 0
   var isMax:Boolean = false;
   for(a <- 0 to (listeNumb.size - 1)){
      if(listeNumb(a) >numb){
         isMax = false;
      }else{
         isMax = true;
      }
   }
   return(isMax)
 }

def contains ( item:Array[String]) : Boolean = {
  for ( x <- item){
    if (x == true) return true
  }
  return false
}

val inst: SimpleProperties = new SimpleProperties();
printf("" + inst.minLength("Hello", 8));
