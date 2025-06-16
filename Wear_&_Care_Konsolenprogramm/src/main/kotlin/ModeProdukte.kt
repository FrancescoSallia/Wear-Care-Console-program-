open class ModeProdukte(
    var name: String,
    var preis: Double,
    var größe: String,
    var farbe: String
) {

    open fun alleProduktEigenschaftenAnzeigen() {
        println()
        println("Podukt: $name,\nPreis: ${preis}\nGröße: $größe,\nFarbe: $farbe")
        println()
    }
}