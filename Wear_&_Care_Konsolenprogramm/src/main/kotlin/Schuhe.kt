class Schuhe(
    name: String,
    preis: Double,
    größe: String,
    farbe: String,
) : ModeProdukte(name, preis, größe, farbe) {

    fun schuhePräsentieren() {
        println("$name, $preis€ , $größe, $farbe")
    }
}