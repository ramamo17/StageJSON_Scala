class SimpleProperties {
  def minLength(word:String, minNumber:Int) : Boolean = {
    if(word.length < minNumber){
      return(false)
    }else{
      return(true)
    }
  }
  
  def maxLength(word:String, maxNumber:Int) : Boolean = {
    if(word.length > maxNumber){
      return(false)
    }else{
      return(true)
    }
  }
  
  def mutipleOf(numb:String, mutiple:Int) : Boolean = {
    if(numb%multiple==0){
      return(true)
    }else{
      return(false)
    }
  }

  def minimum(numb:String, listeNumb:Array) : Boolean = {
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

val inst: SimpleProperties = new SimpleProperties();
printf("" + inst.minLength("Hello", 8));