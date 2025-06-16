class PflegeProdukte(
    name: String,
    preis: Double,
    größe: String,
    farbe: String,
    var ölfrei: Boolean
) : ModeProdukte(name, preis, größe, farbe) {

    override fun alleProduktEigenschaftenAnzeigen() {
        println()
        println("Podukt: $name,\nPreis: $preis\n ($größe),\nFarbe: $farbe\n Ölfrei: ${if (ölfrei) "Ja" else "Nein"}")
        println()
    }

    fun pflegeProduktePräsentieren() {
        println("$name, $preis€ , $größe ml, $farbe ")
    }
}