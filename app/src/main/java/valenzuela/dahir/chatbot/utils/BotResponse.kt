package valenzuela.dahir.chatbot.utils

import android.os.Message
import valenzuela.dahir.chatbot.utils.Constans.OPEN_GOOGLE
import valenzuela.dahir.chatbot.utils.Constans.OPEN_SEARCH
import java.lang.Exception
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {
    fun basicResponse(_message:String):String{
        val random = (0..2).random()
        val message = _message.toLowerCase()

        return when{
            message.contains("flip") && message.contains("coins") -> {
                val r = (0..1).random()
                val result = if (r==0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }
            message.contains("solve") ->{
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"
                }catch (e: Exception){
                    "Sorry, i cant solve that"
                }
            }
            message.contains("hello")->{
                when(random){
                    0 -> "Hello there"
                    1 -> "sup"
                    2 -> "bungiorno"
                    else -> "error"
                }
            }
            message.contains("how are you") -> {
                when(random){
                    0 -> "I´m doing fine, thanks"
                    1 -> "im hungry"
                    2 -> "pretty good, how about you?"
                    else -> "error"
                }
            }
            message.contains("time") && message.contains("?") ->{
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }
            message.contains("search") -> {
                OPEN_SEARCH
            }
            else -> {
                when (random){
                    0 -> "i dont understand"
                    1 -> " try asking me somthing different"
                    2 -> "idk"
                    else -> "error"
                }
            }
        }
    }
}