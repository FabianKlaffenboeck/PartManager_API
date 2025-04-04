package at.fklab.model


enum class ElectricalUnit(val symbol: String, val description: String) {
    // Voltage
    VOLT("V", "Voltage"),

    // Current
    AMPERE("A", "Electric Current"),

    // Resistance
    OHM("Ω", "Electrical Resistance"),

    // Conductance
    SIEMENS("S", "Electrical Conductance"),

    // Capacitance
    FARAD("F", "Capacitance"),

    // Inductance
    HENRY("H", "Inductance"),

    // Power
    WATT("W", "Power"),

    // Energy
    JOULE("J", "Energy"),

    // Electric Charge
    COULOMB("C", "Electric Charge"),

    // Frequency
    HERTZ("Hz", "Frequency");

    override fun toString(): String {
        return "$name ($symbol) - $description"
    }
}
