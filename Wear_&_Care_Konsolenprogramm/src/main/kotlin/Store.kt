import kotlin.system.exitProcess

open class Store() {


    var eingelogterAccount: Account? = null
    var accountListe: MutableList<Account> = mutableListOf(
        Admin("Admin", 1234, "passwort"),
//        Kunde("francesco", 1234,  "M", "42", 80.0, false,27),
        Kunde("alex", 1234, "L", "39", 100.0, true,25, mutableListOf())
    )

    //tshirt objekte:
    var tshirtsImStore: MutableList<Tshirt> = mutableListOf(
        Tshirt("Calvin Klein", 23.99, "M", "weiß", "kurz"),
        Tshirt("Tommy hilfiger", 29.99, "L", "blau", "lang"),
        Tshirt("Hugo Boss", 43.99, "XL", "rot", "lang"),
        Tshirt("Ralph Lauren", 93.99, "XS", "gelb", "kurz"),
        Tshirt("Versace", 103.99, "S", "schwarz", "kurz")
    )

    //Schuhe Objekte:
    var schuheImStore: MutableList<Schuhe> = mutableListOf(
        Schuhe("Nike", 74.99, "39", "rot"),
        Schuhe("Adidas", 69.90, "42", "gelb"),
        Schuhe("Boss", 104.99, "40", "blau"),
        Schuhe("Under Armour", 40.99, "45", "weiß"),
        Schuhe("Jordan", 35.90, "37", "schwarz"),
        Schuhe("Lacoste", 39.90, "44", "rot/schwarz")
    )


    //Objekte von Pfelge-Produkten
    var pflegeProdukteImStore: MutableList<PflegeProdukte> =
        mutableListOf(
            PflegeProdukte("Beauty ofJosen", 21.95, "150", "weiß", true),
            PflegeProdukte("Skin1004", 12.95, "50", "gold", false),
            PflegeProdukte("STAY Well", 1.95, "20", "weiß", false),
            PflegeProdukte("7days", 14.95, "40", "gelb", true),
            PflegeProdukte("Clinique", 24.95, "70", "gelb", true)
        )

    // start menu zur auswahl was man als erstes machen möchte, muss man noch weiter ausbauen wenn mann eingeloggt ist und sich bsp produkte sehen möchte!
    fun startMenu() {
        println()
        println("Wählen Sie einen der folgenden Optionen aus")
        println()
        println("REGISTRIEREN: 1")
        println("ANMELDEN ALS KUNDE: 2")
        println("ANMELDEN ALS ADMIN: 3 ")
        println("EXIT: 4")
        println()
        println("Geben Sie hier ihre Zahl ein:")
        println()

     try {

        var inputEingabe = readln().toInt()

        if (inputEingabe == 1) {
            registrieren()
        } else if (inputEingabe == 2) {
            einloggen()
        } else if (inputEingabe == 3) {
            var i = 0
            accountListe.filterIsInstance<Admin>().forEach { admin -> admin.adminLogin(this) }
            //Admin("Francesco", 1234, "passwort").adminLogin(this)
        } else if (inputEingabe == 4) {
            println("Vielen Dank, bis zum nächsten mal! 👋 ")
            exitProcess(0)  //Um das projekt zu beenden!
        }
    } catch (e:Exception){
        println()
         println("Du hast eine falsche Eingabe gemacht! Versuch es erneut.")
         println()
         startMenu()

     }
}
    fun registrieren() {
        println("Erstellen Sie bitte ein Account !")

        println("Name: ")
        var eingabeName = readln()
        println("PIN: ")
        var eingabePasswort = readln().toInt()
        println("Dein Alter: ")
        var eingabeAlter = readln().toInt()

        if (!accountListe.any { it.name == eingabeName }) {
            var erstellterKunde = Kunde(eingabeName, eingabePasswort,eingabeAlter)
            if (eingabeAlter >= 18){
            erstellterKunde.profilBearbeiten()
            accountListe.add(erstellterKunde)
            println()
            println("Du hast dich Erfolgreich registriert.")
            println()
            startMenu()
        }else if (eingabeAlter < 18){
            println("Du bist noch zu Jung, um dich zu registrieren")
                startMenu()
        }
        } else {
            println("Konto existiert bereits!")
            einloggen()
        }
    }

    fun einloggen() {
        println()
        println("Loggen Sie sich ein!")
        println("Name: ")
        var eingabeName = readln()
        println()
        println("Pin: ")
        var eingabePasswort = readln().toInt()

        val gefundenerAccount = accountListe.find { it.name == eingabeName && it.pin == eingabePasswort }
        eingelogterAccount = gefundenerAccount

        if (gefundenerAccount != null && gefundenerAccount is Kunde) {
            println("Du hast dich Erfolgreich angemeldet ✓")
            println()
            println("Möchten Sie Geld Einzahlen? (ja/nein)")
            when (readln().lowercase()) {
                "ja", "j", "JA", "Ja" -> gefundenerAccount.guthabenEinzahlen(this)
                "nein", "n", "NEIN", "Nein" -> gefundenerAccount.kundenMenu(this)
                else -> (eingelogterAccount as Kunde).kundenMenu(this)
            }
        } else if (gefundenerAccount != null && gefundenerAccount is Admin) {
            println("Du hast dich Erfolgreich angemeldet ✓")
            println()
            when (readln().lowercase()) {
                "ja", "j", "JA", "Ja" -> (gefundenerAccount as Admin)//hier muss noch eine funktion rein
                "nein", "n", "NEIN", "Nein" -> (gefundenerAccount as Admin)
            }


        } else if (!accountListe.any { it.name == eingabeName }) {
            println("Sie können sich nicht anmelden,wenn Sie kein Account haben.")
            registrieren()

        } else if (accountListe.any { it.name == eingabeName && it.pin != eingabePasswort }) {
            println("✖️ Dein Pin ist Falsch, versuch es erneut")
            einloggen()

        } else if (accountListe.any { it.name != eingabeName && it.pin == eingabePasswort }) {
            println("✖️ Dein Nutzername ist Falsch, versuch es erneut")
            einloggen()

        }
    }

    fun tshirtsAlleAnzeigen(store: Store, kunde: Kunde) {
        var j = 0
        for (i in store.tshirtsImStore) {
            println("${j +1} Tshirt Marke: ${store.tshirtsImStore[j].name} \n Preis: ${store.tshirtsImStore[j].preis}€ \n Größe: ${store.tshirtsImStore[j].größe} \n Farbe: ${store.tshirtsImStore[j].farbe} \n Ärmel: ${store.tshirtsImStore[j].ärmel} \n")
            j++
        }
        println()
        println("Möchten Sie ein Artikel von der Liste, in den Warenkorb hinzufügen?")

        var input2 = readln()
        if (input2 == "ja" || input2 == "j") {

            println("Welchen Artikel möchten Sie in ihrem Warenkorb hinzufügen?")
            var input = readln().toInt()
            warenkorbListe.add(store.tshirtsImStore[input - 1])
            println()
            println("Sie haben ein Artikel im Warenkorb hinzugefügt!")


        }else {kunde.kundenMenu(this)}
        println("noch mehr ?")
        var input1 = readln()

        if (input1 == "ja" || input1 == "j") {
            j = 0
            for (i in store.tshirtsImStore) {
                println("${j +1} Tshirt Marke: ${store.tshirtsImStore[j].name} \n Preis: ${store.tshirtsImStore[j].preis}€ \n Farbe: ${store.tshirtsImStore[j].farbe} \n Ärmel: ${store.tshirtsImStore[j].ärmel} \n")
                j++
            }

            tshirtsAlleAnzeigen(this,kunde)

        } else {
            kunde.kundenMenu(store)
        }
    }

    fun tshirtGrößeFilternVomKunden(store: Store, kunde: Kunde) {

        var deineTshirtGröße = tshirtsImStore.filter { it.größe == kunde.größeTshirt }
        //deineTshirtGröße.forEach { it. }
        for (i in 0..deineTshirtGröße.size - 1) {
            print("${i + 1}   ")

            deineTshirtGröße[i].tshirtPräsentieren()
        }
        println()
        println("Möchten Sie ein Artikel von der Liste, in den Warenkorb hinzufügen?")

        var input2 = readln()
        if (input2 == "ja" || input2 == "j") {

            println("Welchen Artikel möchten Sie in ihrem Warenkorb hinzufügen?")
            var input = readln().toInt()
            warenkorbListe.add(deineTshirtGröße[input - 1])

        }else {kunde.kundenMenu(store)}
        println("noch mehr ?")
        var input1 = readln()

        if (input1 == "ja" || input1 == "j") {

            tshirtGrößeFilternVomKunden(store, kunde)
        } else {
            kunde.kundenMenu(store)
        }
    }

    fun schuheAlleAnzeigen(store: Store, kunde: Kunde) {
        var j = 0
        for (i in store.schuheImStore) {
            println("${j +1} Schuh-Marke: ${store.schuheImStore[j].name} \n Preis: ${store.schuheImStore[j].preis}€ \n Größe: ${store.schuheImStore[j].größe}\n Farbe: ${store.schuheImStore[j].farbe} \n")
            j++
        }
        println()
        println("Möchten Sie ein Artikel von der Liste, in den Warenkorb hinzufügen?(ja/nein)")

        var input2 = readln()
        if (input2 == "ja" || input2 == "j") {

            println("Welchen Artikel möchten Sie in ihrem Warenkorb hinzufügen?(gib eine zahl ein)")
            var input = readln().toInt()
            warenkorbListe.add(store.schuheImStore[input - 1])

        }else {kunde.kundenMenu(store)}
        println("noch mehr ? (ja/nein)")
        var input1 = readln()

        if (input1 == "ja" || input1 == "j") {

            schuheAlleAnzeigen(store,kunde)
        } else {
            kunde.kundenMenu(store)
        }
    }

    fun schuhGrößeFilternVomKunden(store: Store, kunde: Kunde) {

        var deineSchuhGröße = schuheImStore.filter { it.größe == kunde.größeSchuhe }
        //deineSchuhGröße.forEach { it.schuhePräsentieren() }
        for (i in 0..deineSchuhGröße.size - 1) {
            print("${i + 1} ")
            deineSchuhGröße[i].schuhePräsentieren()
        }

        println()
        println("Möchten Sie ein Artikel von der Liste, in den Warenkorb hinzufügen? (ja/nein)")

        var input2 = readln()
        if (input2 == "ja" || input2 == "j") {

            println("Welchen Artikel möchten Sie in ihrem Warenkorb hinzufügen?(gib eine Zahl ein)")
            var input = readln().toInt()
            warenkorbListe.add(deineSchuhGröße[input - 1])
        }else {kunde.kundenMenu(store)}
        println("noch mehr ? (ja/nein)")
        var input1 = readln()

        if (input1 == "ja" || input1 == "j") {

            schuhGrößeFilternVomKunden(store, kunde)
        } else {
            kunde.kundenMenu(store)
        }

    }


    fun pflegeProdukteAlleAnzeigen(store: Store, kunde: Kunde) {

        var j = 0
        for (i in store.pflegeProdukteImStore) {
            println("${j +1} Pflege-Produkt Name: ${store.pflegeProdukteImStore[j].name} \nPreis: ${store.pflegeProdukteImStore[j].preis}€ \n (${store.pflegeProdukteImStore[j].größe}) ml \nFarbe: ${store.pflegeProdukteImStore[j].farbe} \nÖlfrei: ${if (store.pflegeProdukteImStore[j].ölfrei) "Ja" else "Nein"}  \n")
            j++
        }

        println()
        println("Möchten Sie ein Artikel von der Liste, in den Warenkorb hinzufügen? (ja/nein)")

        var input2 = readln()
        if (input2 == "ja" || input2 == "j") {

            println("Welchen Artikel möchten Sie in ihrem Warenkorb hinzufügen? (gib eine Zahl ein)")
            var input = readln().toInt()
            warenkorbListe.add(store.pflegeProdukteImStore[input - 1])

        }else {kunde.kundenMenu(store)}
        println("noch mehr ? (ja/nein)")
        var input1 = readln()

        if (input1 == "ja" || input1 == "j") {

            pflegeProdukteAlleAnzeigen(store,kunde)
        } else {
            kunde.kundenMenu(store)
        }
        kunde.kundenMenu(store)

    }

    fun pflegeProdukteFilternVomKunden(store: Store, kunde: Kunde) {

        var deineHauterträglichkeit = pflegeProdukteImStore.filter { it.ölfrei == kunde.hautempfindlich }
        for (i in 0..deineHauterträglichkeit.size - 1) {
            print("${i + 1} ")
            deineHauterträglichkeit[i].pflegeProduktePräsentieren()
        }

        println()
        println("Möchten Sie ein Artikel von der Liste, in den Warenkorb hinzufügen? (ja/nein)")

        var input2 = readln()
        if (input2 == "ja" || input2 == "j") {

            println("Welchen Artikel möchten Sie in ihrem Warenkorb hinzufügen? (gib eine Zahl ein)")
            var input = readln().toInt()
            warenkorbListe.add(deineHauterträglichkeit[input - 1])
        }else {kunde.kundenMenu(store)}
        println("noch mehr ? (ja/nein)")
        var input1 = readln()

        if (input1 == "ja" || input1 == "j") {

            pflegeProdukteFilternVomKunden(store, kunde)
        } else {
            kunde.kundenMenu(store)
        }

    }

    fun preisSortiertVonAllenArtikeln(store: Store, kunde: Kunde) {

        println("--------------TSHIRTS------------------")

        //Preise von allen Tshirts sortiert (reverse)
       var preisReverseSortiertTshirt =  tshirtsImStore.toMutableList()
        preisReverseSortiertTshirt.sortBy { it.preis }
        preisReverseSortiertTshirt.reverse()

        for (tshirt in preisReverseSortiertTshirt) {
            tshirt.alleProduktEigenschaftenAnzeigen()
        }

        println("--------------SCHUHE------------------")

        //Preise von allen Schuhen sortiert (reverse)
        var preisReverseSortiertSchuhe =  schuheImStore.toMutableList()
        preisReverseSortiertSchuhe.sortBy { it.preis }
        preisReverseSortiertSchuhe.reverse()

        for (schuhe in preisReverseSortiertSchuhe) {
            schuhe.alleProduktEigenschaftenAnzeigen()
        }

        println("-------------PFLEGE-PRODUKTE---------------")

        //Preise von allen Pflege-Produkten sortiert (reverse)
        var preisReverseSortiertPflege =  pflegeProdukteImStore.toMutableList()
        preisReverseSortiertPflege.sortBy { it.preis }
        preisReverseSortiertPflege.reverse()

        for (pflege in preisReverseSortiertPflege) {
            pflege.alleProduktEigenschaftenAnzeigen()
        }

        kunde.kundenMenu(store)
    }

    var warenkorbListe: MutableList<ModeProdukte> = mutableListOf()
    //var alteBestellungenListe: MutableList<Bestellung> = mutableListOf()



}




