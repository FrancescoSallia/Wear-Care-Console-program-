class Admin(
    name: String,
    pin: Int,
    var adminPassword: String
) : Account(name, pin) {

    fun adminLogin(store: Store) {

        var nameAdmin: String = ""
        var pinAdmin: Int = 0
        var passwortAdmin: String = ""
        // Die try catch funktion fängt den PIN fehler, falls buchstaben statt zahlen eingegeben werden!
        try {
            println("Hallo Admin, bitte Log dich ein.")
            println("Name: ")
            nameAdmin = readln()
            println("PIN: ")
            pinAdmin = readln().toInt()
            println("Passwort: ")
            passwortAdmin = readln()
        } catch (e: NumberFormatException) {
            println("Du sollst ganze Zahlen eingeben!, versuchs nochmal")
            adminLogin(store)
        }

        if (nameAdmin == name && pinAdmin == pin && passwortAdmin == adminPassword) {
            println("Willkommen zurück $name")
            adminMenu(store)

        } else if (nameAdmin != name && pinAdmin == pin && passwortAdmin == adminPassword || nameAdmin == name && pinAdmin != pin && passwortAdmin == adminPassword || nameAdmin == name && pinAdmin == pin && passwortAdmin != adminPassword) {
            println("Ihre eingegebenen Daten sind falsch, versuch es erneut")
            Thread.sleep(2000)

            while (nameAdmin != name && pinAdmin == pin && passwortAdmin == adminPassword || nameAdmin == name && pinAdmin != pin && passwortAdmin == adminPassword || nameAdmin == name && pinAdmin == pin && passwortAdmin != adminPassword) {
                adminLogin(store)
                break
            }
        } else {
            println("Es konnte kein Admin gefunden werden!")
            Thread.sleep(2000)
            adminLogin(store)
        }

    }
}

fun adminMenu(store: Store) {
    println()
    println("Was möchtest du tun ?")
    println()
    println("1 Tshirt im Sortiment hinzufügen")
    println("2 Schuhe im Sortiment hinzufügen")
    println("3 Pflege-Produkt im Sortiment hinzufügen")
    println()
    println("4 Tshirt im Sortiment entfernen")
    println("5 Schuhe im Sortiment entfernen")
    println("6 Pflege-Produkte im Sortiment entfernen")
    println()
    println("7 Ausloggen")
    var input = readln().toInt()


    when (input) {
        1 -> produktHinzufügenTshirt(store)
        2 -> produktHinzufügenSchuhe(store)
        3 -> produktHinzufügenPflege(store)
        4 -> produktEntfernenTshirt(store)
        5 -> produktEntfernenSchuhe(store)
        6 -> produktEntfernenPflege(store)
        7 -> store.startMenu()
    }

}

//Produkte hinzufügen
fun produktHinzufügenTshirt(store: Store) {
    var nameVomProdukt: String?
    var preisVomProdukt: Double?
    var größeVomProdukt: String?
    var farbeVomProdukt: String?
    var ärmelnVomProdukt: String?

    try {

        println("----------Tshirt im Sortiment hinzufügen----------")
        println()

        println("Name vom Produkt: ")
        nameVomProdukt = readln()

        println("Preis vom Produkt: ")
        preisVomProdukt = readln().toDouble()

        println("Größe vom Produkt: ")
        größeVomProdukt = readln().uppercase()

        println("Farbe vom Produkt: ")
        farbeVomProdukt = readln()

        println("Ärmel vom Produkt: ")
        ärmelnVomProdukt = readln()

    } catch (e: NumberFormatException) {

        println("Du sollst ganze Zahlen eingeben!, versuchs nochmal")
        println()
        return produktHinzufügenTshirt(store)
    }
    store.tshirtsImStore.add(
        Tshirt(
            nameVomProdukt,
            preisVomProdukt,
            größeVomProdukt,
            farbeVomProdukt,
            ärmelnVomProdukt
        )
    )
    println()
    println("Tshirt erfolgreich im Sortiment hinzugefügt!")
    adminMenu(store)


}

fun produktHinzufügenSchuhe(store: Store) {

    var nameVomProdukt: String?
    var preisVomProdukt: Double?
    var größeVomProdukt: String?
    var farbeVomProdukt: String?

    try {

        println("----------Schuhe im Sortiment hinzufügen----------")
        println()

        println("Name vom Produkt: ")
        nameVomProdukt = readln()

        println("Preis vom Produkt: ")
        preisVomProdukt = readln().toDouble()

        println("Größe vom Produkt: ")
        größeVomProdukt = readln()

        println("Farbe vom Produkt: ")
        farbeVomProdukt = readln()
    } catch (e: NumberFormatException) {

        println("Du sollst ganze Zahlen eingeben!, versuchs nochmal")
        println()
        return produktHinzufügenSchuhe(store)

    }
    store.schuheImStore.add(Schuhe(nameVomProdukt, preisVomProdukt, größeVomProdukt, farbeVomProdukt))
    println()
    println("Schuhe erfolgreich im Sortiment hinzugefügt!")
    adminMenu(store)
}

fun produktHinzufügenPflege(store: Store) {

    var nameVomProdukt: String?
    var preisVomProdukt: Double?
    var größeVomProdukt: String?
    var farbeVomProdukt: String?
    var enthältÖle: String?
    var enthältÖleBoolean: Boolean?

try {
    println("----------Pflege-Produkt im Sortiment hinzufügen----------")
    println()


    println("Name vom Produkt: ")
    nameVomProdukt = readln()

    println("Preis vom Produkt: ")
    preisVomProdukt = readln().toDouble()

    println("Menge vom Produkt: ")
    größeVomProdukt = readln()

    println("Farbe vom Produkt: ")
    farbeVomProdukt = readln()

//        ich möchte ja eingeben oder nein und nicht true oder false
    println("Enthält Öle?: ")
    enthältÖle = readln().lowercase()
    enthältÖleBoolean =
        when (enthältÖle) {
            "ja", "j" -> true
            else -> false
        }
} catch (e: NumberFormatException) {

    println("Du sollst ganze Zahlen eingeben!, versuchs nochmal")
    println()
    return produktHinzufügenPflege(store)

}
    store.pflegeProdukteImStore.add(
        PflegeProdukte(
            nameVomProdukt,
            preisVomProdukt,
            größeVomProdukt,
            farbeVomProdukt,
            enthältÖleBoolean
        )
    )
    println()
    println("Pflege-Produkt erfolgreich im Sortiment hinzugefügt!")
    adminMenu(store)
}

//Produkte entfernen
fun produktEntfernenTshirt(store: Store) {
    var j = 0
    for (i in store.tshirtsImStore) {
        println("${j + 1} Tshirt Marke: ${store.tshirtsImStore[j].name} \n Preis: ${store.tshirtsImStore[j].preis}€ \n Größe: ${store.tshirtsImStore[j].größe} \n Farbe: ${store.tshirtsImStore[j].farbe} \n Ärmel: ${store.tshirtsImStore[j].ärmel} \n")
        j++
    }
    try {
        println()
        println("Welchen Artikel möchtest du entfernen?")
        var input = readln().toInt()
        println()
        store.tshirtsImStore.removeAt(input - 1)
        println("Artikel erfolgreich entfernt")
        println()
    } catch (e: Exception) {
        adminMenu(store)
    }

    println("Möchtest du noch ein Artikel Entfernen?")
    var input2 = readln()

    if (input2 == "ja" || input2 == "j") {

        produktEntfernenTshirt(store)

    } else {
        adminMenu(store)
    }
}

fun produktEntfernenSchuhe(store: Store) {
    var j = 0
    for (i in store.schuheImStore) {
        println("${j + 1} Schuh-Marke: ${store.schuheImStore[j].name} \n Preis: ${store.schuheImStore[j].preis}€ \n Größe: ${store.schuheImStore[j].größe}\n Farbe: ${store.schuheImStore[j].farbe} \n")
        j++
    }
    try {
        println()
        println("Welchen Artikel möchtest du entfernen?")
        var input = readln().toInt()

        println()
        store.schuheImStore.removeAt(input - 1)
        println("Artikel erfolgreich entfernt")
        println()
    } catch (e: Exception) {
        adminMenu(store)
    }
    println("Möchtest du noch ein Artikel Entfernen?")
    var input2 = readln()

    if (input2 == "ja" || input2 == "j") {

        produktEntfernenSchuhe(store)

    } else {
        adminMenu(store)
    }
}

fun produktEntfernenPflege(store: Store) {
    var j = 0

    for (i in store.pflegeProdukteImStore) {
        println()
        println("${j + 1} Pflege-Produkt Name: ${store.pflegeProdukteImStore[j].name} \n Preis: ${store.pflegeProdukteImStore[j].preis}€ \n ${store.pflegeProdukteImStore[j].größe} ml \n Farbe: ${store.pflegeProdukteImStore[j].farbe} \n Öle: ${if (store.pflegeProdukteImStore[j].ölfrei) "Ja" else "Nein"}")
        j++
    }
    println()

    try {
        println("Welchen Artikel möchtest du entfernen?")
        var input = readln().toInt()
        println()
        store.pflegeProdukteImStore.removeAt(input - 1)
        println("Artikel erfolgreich entfernt")
        println()
    } catch (e: Exception){
        adminMenu(store)
    }
    //println("Welchen Artikel möchtest du entfernen?")
   // var input = readln().toInt()

   // println()
   // store.pflegeProdukteImStore.removeAt(input - 1)
   // println("Artikel erfolgreich entfernt")
    //println()

    println("Möchtest du noch ein Artikel Entfernen?")
    var input2 = readln()

    if (input2 == "ja" || input2 == "j") {

        produktEntfernenPflege(store)

    } else {
        adminMenu(store)
    }
}

