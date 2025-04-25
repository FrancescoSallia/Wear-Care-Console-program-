import java.time.LocalDateTime


class Kunde(
    name: String,
    passwort: Int,
    var alter: Int,

    ) : Account(name, passwort) {

    var größeTshirt: String? = null
    var größeSchuhe: String? = null
    var guthaben: Double? = 0.0
    var hautempfindlich: Boolean? = null

    constructor(
        name: String,
        passwort: Int,
        größeT: String?,
        größeS: String?,
        guthaben: Double?,
        hautempfindlich: Boolean?,
        alter: Int
    ) : this(name, passwort,alter) {
        this.größeTshirt = größeT
        this.größeSchuhe = größeS
        this.guthaben = guthaben
        this.hautempfindlich = hautempfindlich

    }

    fun kundenMenu(store: Store) {
        var j = 0
        println("WAS MÖCHTEN SIE JETZT TUN ?")
        println()
        println("Warenkorb: 0")
        println("Alle Produkte sehen: Geben Sie 1 ein")
        println("Alle Tshirts:  Geben Sie 2 ein")
        println("Tshirts in deiner Größe:  Geben Sie 3 ein")
        println("Alle Schuhe: Geben Sie 4 ein")
        println("Schuhe in deiner Größe: Geben Sie 5 ein")
        println("Alle Pflege-Produkte: Geben Sie 6 ein")
        println("Pflege-Produkte für dich geeignet: Geben Sie 7 ein")
        println("Guthaben einsehen: Geben Sie 8 ein")
        println("Guthaben aufladen: Geben Sie 9 ein")
        println("Deine persönlichen Daten: 10")
        println("Alle Artikeln nach Preisen Sortiert: Geben Sie 11 ein")
        println("Ausloggen: 12")
        try {


        when (readln().toInt()) {
            0 -> warenkorbKategorie(store)
            1 -> alleProdukte(store)
            2 -> store.tshirtsAlleAnzeigen(store, this)
            3 -> store.tshirtGrößeFilternVomKunden(store, this)
            4 -> store.schuheAlleAnzeigen(store, this)
            5 -> store.schuhGrößeFilternVomKunden(store, this)
            6 -> store.pflegeProdukteAlleAnzeigen(store, this)
            7 -> store.pflegeProdukteFilternVomKunden(store, this)
            8 -> guthabenEinsehen(store, this)
            9 -> guthabenEinzahlen(store)
            10 -> kundenProfilDatenEinsehen(store, this)
            11 -> store.preisSortiertVonAllenArtikeln(
                store,
                this) // oben muss noch ein println zum auspucken eingegeben werden
            12 -> {
                println("Sie haben sich erfolgreich Ausgeloggt!")
                store.startMenu()
            }
            else -> kundenMenu(Store())
        }
    }catch (e: Exception) {
        println()
        println("Du hast eine falsche Eingabe gemacht! Du wirst zurück zur Übersicht weitergeleitet!")
            println()
            kundenMenu(store)
        }
}
    fun alleProdukte(store: Store) {
        var j = 0
        var m = 0
        var n = 0
        println("-------------------TSHIRTS----------------")

        for (i in store.tshirtsImStore) {
            println("${j + 1} Tshirt Marke: ${store.tshirtsImStore[j].name} \n Preis: ${store.tshirtsImStore[j].preis}€ \n Größe: ${store.tshirtsImStore[j].größe} \n Farbe: ${store.tshirtsImStore[j].farbe} \n Ärmeln: ${store.tshirtsImStore[j].ärmel} \n")
            j++
        }
        println("------------------SCHUHE------------------")
        for (i in store.schuheImStore) {
            println("${j + 1} Schuh-Marke: ${i.name} \n Preis: ${i.preis}€ \n Größe: ${i.größe}\n Farbe: ${i.farbe} \n")
            m++
            j++
        }
        println("-----------------PFLEGE-PRODUKTE-------------------")
        for (i in store.pflegeProdukteImStore) {
            println("${j + 1} Pflege-Produkt Name: ${store.pflegeProdukteImStore[n].name} \n Preis: ${store.pflegeProdukteImStore[n].preis}€ \n (${store.pflegeProdukteImStore[n].größe}) ml \n Farbe: ${store.pflegeProdukteImStore[n].farbe} Ölfrei: ${if (store.pflegeProdukteImStore[n].ölfrei) "Ja" else "Nein"} \n")
            n++
            j++
        }


        kundenMenu(store)

    }

    fun profilBearbeiten() {

        println("Gib deine Tshirt größe ein (S/M/L oder XL?):")
        var eingabeGrößeTshirt = readln().uppercase()
        größeTshirt = eingabeGrößeTshirt

        println("Gib deine Schuhgröße ein (39,40..46?):")
        var eingabeGrößeSchuhe = readln()
        größeSchuhe = eingabeGrößeSchuhe

        println("Hast du eine empfindliche Haut ?")
        var eingabeHautempfindlich = when (readln().lowercase()) {
            "ja", "j" -> true
            "nein", "n" -> false
            else -> false
        }
        hautempfindlich = eingabeHautempfindlich

    }

    fun guthabenEinzahlen(store: Store) {
        println()
        println("Wie viel möchtest du Einzahlen?:")
        var eingabeGuthaben = readln().toDouble()
        var aktuellerKontostand = guthaben?.plus(eingabeGuthaben)
        guthaben = aktuellerKontostand
        var formatieren = String.format("%.2f", aktuellerKontostand)
        println(formatieren)
        println()
        println("Sie haben erfolgreich $eingabeGuthaben€ eingezahlt!")
        println("Dein aktueller Kontostand beträgt jetzt: $aktuellerKontostand€")
        println()
        kundenMenu(store)
    }

    // diese funktion gilt nur, falls der kunde nicht genug guthaben hat im bezahlprozess
    fun guthabenEinzahlenWarenkorb(store: Store): Double? {
        println()
        println("Wie viel möchtest du Einzahlen?:")
        var eingabeGuthaben = readln().toDouble()
        var aktuellerKontostand = guthaben?.plus(eingabeGuthaben)
        guthaben = aktuellerKontostand
        var formatieren = String.format("%.2f", guthaben)

        println()
        println("Sie haben erfolgreich $eingabeGuthaben eingezahlt!")
        println("Dein aktueller Kontostand beträgt jetzt: $formatieren")
        println()

        return aktuellerKontostand
    }

    fun guthabenEinsehen(store: Store, kunde: Kunde) {

        var preisOhneKommaStellen = String.format("%.2f", guthaben)

        println("Dein Guthaben beträgt: ${preisOhneKommaStellen}€ ")
        println()//test
        kundenMenu(store)
    }

    fun warenkorbKategorie(store: Store){
       println()
        println("Warenkorb ansehen: 1")
        println("Produkt vom Warenkorb entfernen: 2")
        println("Zurück : 3")
        var input = readln().toInt()
        when (input) {
            1 -> warenkorb(store,this)
            2 -> warenkorbProdukteEntfernen(store,this)
            3 -> kundenMenu(store)
            else -> kundenMenu(store)
        }
    }
    fun warenkorbProdukteEntfernen(store: Store, kunde: Kunde){

        var anzahl = 0
        println("Dein Warenkorb: ")
        for (i in store.warenkorbListe) {
            println("${anzahl+1} ${i.name} Preis: ${i.preis}€ Größe:${i.größe}  ")
            anzahl++
        }

        println("""Was möchtest du von deinem Warenkorb entfernen?
        (Gib die Zahl vom Artikel ein!)
                      """.trimIndent())
        println()
        var input = readln().toInt()-1



        if (input < store.warenkorbListe.size && input >= 0) {

            var entfernt = store.warenkorbListe.removeAt(input)
            println("${entfernt.name} wurde erfolgreich vom Warenkorb entfernt!")
            println()

            println("Möchtest du weitere Artikeln von deinem Warenkorb entfernt?")
            var input1 = readln().lowercase()
            when(input1){
                "ja","j" -> warenkorbProdukteEntfernen(store,this)
                else -> warenkorbKategorie(store)
            }

        }else if(store.warenkorbListe.isEmpty()){
            println()
            println("Dein Warenkorb ist leer")
            Thread.sleep(1000)
            warenkorbKategorie(store)

        }

        else{
            println("Kein Artikel gefunden!")
            warenkorbProdukteEntfernen(store,this)
        }





    }

    fun warenkorb(store: Store, kunde: Kunde): Double {

        var preis = 0.0
        println("Dein Warenkorb: ")
        for (i in store.warenkorbListe) {
            println("${i.name} Preis: ${i.preis}€ Größe:${i.größe}  ")
            preis += i.preis

        }
        var preisOhneKommaStellen = String.format("%.2f", preis)
        println()
        println("Gesamtpreis: $preisOhneKommaStellen€")
        println()
        bezahlvorgang(store, kunde, preis, preis)
        return preis
    }

    fun bezahlvorgang(store: Store, kunde: Kunde, warenkorb: Double, eingezahlt: Double?) {

        println(" Bezahlen ? (ja/nein)")
        var input = readln()
        if (input.lowercase() == "ja" || input.lowercase() == "j") {
            if (warenkorb < guthaben!! && store.warenkorbListe.isNotEmpty()) {
                guthaben = guthaben!! - warenkorb
                print("Bitte warten Sie einen Moment")
                repeat(3) {
                    print(".")

                    Thread.sleep(1700)
                }
                println("\nVielen Dank, dass Sie bei uns eingekauft haben")
                Thread.sleep(1500)
                println(
                    """
                    Ihr aktueller Guthaben beträgt jetzt: ${String.format("%.2f", guthaben)}€
                    Sie werden zurück zur Übersicht weitergeleitet!
                    """.trimIndent()
                )
                store.alteBestellungenListe.add(Bestellung(store.warenkorbListe.toMutableList(),LocalDateTime.now()))
                store.warenkorbListe.clear()
                println()
                Thread.sleep(1600)
                kundenMenu(store)
            } else if (warenkorb > guthaben!!) {
                println(
                    """
                    |Sie haben nicht ausreichend Guthaben für den Bezahlprozess,
                    |möchten Sie Einzahlen?(ja/nein)
                """.trimMargin()
                )
                var input = readln()
                if (input.lowercase() == "ja" || input.lowercase() == "j") {
                    this.guthaben = guthabenEinzahlenWarenkorb(store)
                    bezahlvorgang(store, kunde, warenkorb, guthaben)
                }else if (input.lowercase() == "nein" || input.lowercase() == "n"){
                    println()
                    println("Möchten Sie ein Artikel entfernen oder zurück zur Übersicht?")
                    println()
                    println("Artikel entfernen: 1")
                    println("Zurück zur Übersicht: 2")
                    var eingabe = readln().toInt()
                    println()
                    when(eingabe){
                        1 -> warenkorbProdukteEntfernen(store,kunde)
                        2 -> kundenMenu(store)
                        else -> kundenMenu(store)
                    }

                } else {
                    kundenMenu(store)
                }
            } else {
                println("Warenkorb ist Leer, Sie werden zurück zur Übersicht weitergeleitet!  ")
                Thread.sleep(1700)
                kundenMenu(store)
            }
        } else {
            println("Sie werden zurück zur Übersicht weitergeleitet!  ")

            Thread.sleep(1700)
            kundenMenu(store)
        }
    }

    fun alteBestellungenAusprinten(store: Store, kunde: Kunde ) {

        if (store.alteBestellungenListe.isNotEmpty()) {
            println("Deine letzten Bestellungen:")
            println()

            for (bestellung in store.alteBestellungenListe) {
                bestellung.produktVomWarenkorb()
            }
        }
    }

    fun kundenProfilDatenEinsehen(store: Store, kunde: Kunde) {

        println()
        var hauttyp = when (kunde.hautempfindlich!!) {
            true -> "Ja"
            else -> "Nein"
        }

        println(
            """
        |Name: ${kunde.name}
        |Alter: ${kunde.alter}
        |Deine Tshirt-Größe: ${kunde.größeTshirt}
        |Deine Schuhe-Größe: ${kunde.größeSchuhe}
        |Empfindliche haut: $hauttyp
        |
    """.trimMargin()
        )
        alteBestellungenAusprinten(store, this)
        println()
        kundenMenu(store)
    }


}




