package calendar

import java.util.*

class Calendar(_rawCalendar: MutableList<Array<String>>, _bounds: Array<String>) {
    private val rawCalendar: MutableList<Array<String>> = _rawCalendar
    private val bounds: Array<String> = _bounds
    private val calendar: List<Array<Int>> = formatInput()

    constructor(rawCalendar: String, bounds: String) : this(getTime(rawCalendar), getTime(bounds)[0])

    companion object {
    fun getTime(stringTime: String): MutableList<Array<String>> {
        val result : MutableList<Array<String>> = mutableListOf()
        for (time_frame in stringTime.split(",")) {
            result.add(time_frame.split("-").toTypedArray())
        }
        return result
    } }

    private fun formatTime(time: Int): String {
        val timeString = time.toString()
        return if (timeString.length == 1) "0$timeString" else timeString
    }

    private fun comp(a: String, b: String?, i: Int): String {
        return if (a.compareTo(b!!) == i) a else b
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val calendar2: Calendar = other as Calendar
        return this.hashCode() == calendar2.hashCode()
    }

    override fun hashCode(): Int {
        return Objects.hash(this.getCalendar())
    }

    fun getCalendar(): String {
        return calendar.toTypedArray().contentDeepToString()
    }

    fun mergeWithCalendar(cal2: Calendar) : Calendar{
        val newCalendar: MutableList<Array<String>> = rawCalendar
        newCalendar.addAll(cal2.rawCalendar)
        newCalendar.sortBy{ o: Array<String> -> o[0] }
        val newBounds = arrayOf(comp(bounds[0], cal2.bounds[0],1), comp(bounds[1], cal2.bounds[1],-1))
        return Calendar(newCalendar, newBounds)
    }

    private fun fi(value : String): Int {
        return value.split(":")[0].toInt()*60 + value.split(":")[1].toInt()
    }

    private fun formatInput(): List<Array<Int>> {
        val calendar: MutableList<Array<String>> = ArrayList()
        val result: MutableList<Array<Int>> = ArrayList()
        calendar.add(arrayOf("00:00", bounds[0]))
        calendar.addAll(rawCalendar)
        calendar.add(arrayOf(bounds[1], "00:00"))
        for (s in calendar) {
            result.add(arrayOf(fi(s[0]), fi(s[1])))
        }
        return result
    }

    fun getFreeTime(minDuration: Int = 30): List<Array<Int>> {
        val result: MutableList<Array<Int>> = ArrayList()
        for (i in 0 until calendar.size - 1) {
            if (calendar[i + 1][0] - calendar[i][1] >= minDuration) {
                result.add(arrayOf(calendar[i][1], calendar[i + 1][0]))
            }
        }
        return result
    }

    fun getPossibleEvents(cal2: Calendar, minDuration: Int): List<Array<Int>> {
        return mergeWithCalendar(cal2).getFreeTime(minDuration)
    }

    fun getPrettyTime(minDuration: Int = 30): String {
        val result: MutableList<Array<String>> = ArrayList()
        for (i in getFreeTime(minDuration)) {
            result.add(
                arrayOf("${formatTime(i[0] / 60)}:${formatTime(i[0] % 60)}",
                    "${formatTime(i[1] / 60)}:${formatTime(i[1] % 60)}"))
        }
        return result.toTypedArray().contentDeepToString()
    }
}