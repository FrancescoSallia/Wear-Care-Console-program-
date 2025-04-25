class Tshirt(
    name: String,
    preis: Double,
    größe: String,
    farbe: String,
    var ärmel: String,

    ) : ModeProdukte(name, preis, größe, farbe) {

    fun tshirtPräsentieren() {
        println("$name, $preis€ , $größe, $farbe, Ärmel:$ärmel")
    }
}






