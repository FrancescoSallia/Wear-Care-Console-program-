import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Bestellung(
    var warenkorb: MutableList<ModeProdukte>,
    var uhrzeit: LocalDateTime
) {
    fun produktVomWarenkorb(){
        println(uhrzeit.format(DateTimeFormatter.ofPattern("dd.MM.yyyy:HH:mm:ss")))
        for (produkt in warenkorb){
            produkt.alleProduktEigenschaftenAnzeigen()
            println()
        }
    }
}