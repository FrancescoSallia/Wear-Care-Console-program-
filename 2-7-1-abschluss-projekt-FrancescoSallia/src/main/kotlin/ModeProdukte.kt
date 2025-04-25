open class ModeProdukte(
    var name: String,
    var preis: Double,
    open var größe: String,
    var farbe: String
) {
    open var alteBestellungenListe: MutableList<ModeProdukte> = mutableListOf()

    open fun alleProduktEigenschaftenAnzeigen() {

        println()
        println("Podukt: $name,\nPreis: ${preis}\nGröße: $größe,\nFarbe: $farbe")
        println()

    }
}